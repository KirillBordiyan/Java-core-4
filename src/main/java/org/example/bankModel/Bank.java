package org.example.bankModel;

import lombok.*;
import org.example.bankEntity.*;
import org.example.bankEntity.AccBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bank {
    /*
        В теории, было бы неплохо даже добавить связь транзакций с определенным аккаунтом
     */
    private HashMap<Integer, Account> accountList = new HashMap<>();
    private ArrayList<Transaction> transactionList = new ArrayList<>();

    public void createAccount(int startBalance) throws IllegalArgumentException {
        Account account = new AccBuilder().setStartAccBalance(startBalance).build();
        accountList.put(accountList.size() + 1, account);
    }

    public void doAction(int accountId, int sumValue, Procedure procedure) {
        if (procedure == Procedure.replenishment) {
            replenishment(accountId, sumValue);
        }
        if (procedure == Procedure.withdrawal) {
            withdrawal(accountId, sumValue);
        }
    }

    private void replenishment(int accountId, int sumValue) {
        try {
            if (accountList.containsKey(accountId) && accountList.get(accountId) != null) {
                Account account = accountList.get(accountId);

                if (sumValue > 0) {
                    Transaction transaction = new Transaction(sumValue, Procedure.replenishment, accountId);
                    account.setAccountBalance(account.getAccountBalance() + sumValue);
                    accountList.put(accountId, account);
                    transactionList.add(transaction);
                    System.out.println("Пополнение прошло успешно!");
                } else throw new IllegalArgumentException("сумма должна быть больше нуля");
            } else throw new NullPointerException("не существует такого аккаунта");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdrawal(int accountId, int sumValue) {
        try {
            if (accountList.containsKey(accountId) && accountList.get(accountId) != null) {
                Account account = accountList.get(accountId);

                if (sumValue <= account.getAccountBalance() && sumValue >=0) {
                    Transaction transaction = new Transaction(sumValue, Procedure.withdrawal, accountId);
                    account.setAccountBalance(account.getAccountBalance() - sumValue);
                    accountList.put(accountId, account);
                    transactionList.add(transaction);
                    System.out.println("Снятие прошло успешно!");
                } else throw new InsufficientFundsException("сумма снятия больше имеющихся средств или < 0");
            } else throw new NullPointerException("не существует такого аккаунта");
        } catch (InsufficientFundsException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getFormattedAccount(HashMap<Integer, Account> accountList) {
        StringBuilder formatted = new StringBuilder();
        for (Map.Entry<Integer, Account> account : accountList.entrySet()) {
            formatted.append(account.getKey())
                    .append(": ")
                    .append(account.getValue())
                    .append("\n");
        }
        return formatted.toString();
    }

    public String getFormattedTransaction(ArrayList<Transaction> transactionList){
        StringBuilder formatted = new StringBuilder();
        for (Transaction transaction : transactionList) {
            formatted.append(transaction)
                    .append("\n");
        }
        return  formatted.toString();
    }
}
