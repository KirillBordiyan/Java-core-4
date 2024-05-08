package org.example.bankView;

import org.example.bankController.BankController;
import org.example.bankEntity.Procedure;

import java.util.Scanner;

public class BankView {
    private BankController bc;

    public BankView(BankController bc) {
        this.bc = bc;
    }

    public void init(){
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning){
            System.out.println("Выберите действие:");
            System.out.println("1. Создать аккаунт");
            System.out.println("2. Просмотреть информацию об аккаунте");
            System.out.println("3. Выполнить операцию");
            System.out.println("4. Просмотр всех операций");
            System.out.println("5. Выйти");

            int inputChoice = scanner.nextInt();
            switch (inputChoice){
                case 1:
                    System.out.println("Введите начальную сумму счета (целое число)");
                    int value = scanner.nextInt();
                    bc.getBank().createAccount(value);
                    break;
                case 2:
                    System.out.println("Список созданных счетов:");
                    showAccountList(bc);
                    /*
                      Тут описана логика получения всей дополнительной информации о конкретном счете
                      в нашем случае, скорее всего это бесполезно, но в кач-ве галочки оставлю :)
                     */
                    System.out.println("Выберите номер аккаунта");
                    int accIdInfo = scanner.nextInt();
                    show(bc, accIdInfo);
                    break;
                case 3:
                    showAccountList(bc);

                    System.out.println("Выберите номер аккаунта");
                    int accIdAction = scanner.nextInt();
                    if(accIdAction > bc.getBank().getAccountList().size()) {
                        System.out.println("Номер аккаунта выбран неверно");
                        break;
                    }

                    System.out.println("Введите номер операции:");
                    System.out.println("1 - пополнение");
                    System.out.println("2 - снятие");
                    int operation = scanner.nextInt();

                    if(operation == 1){
                        System.out.println("Введите сумму для пополнения:");
                        int sum = scanner.nextInt();
                        bc.getBank().doAction(accIdAction, sum, Procedure.replenishment);
                    }
                    else if(operation == 2) {
                        System.out.println("Введите сумму снятия:");
                        int sum = scanner.nextInt();
                        bc.getBank().doAction(accIdAction, sum, Procedure.withdrawal);
                    }else{
                        System.out.println("Выбрана несуществующая операция");
                    }
                    break;
                case 4:
                    System.out.println("Список всех операций:");
                    showTransactionList(bc);
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Выход из программы");
                    break;
            }
        }
    }

    private void showTransactionList(BankController bc){
        System.out.println(bc.getBank().getFormattedTransaction(bc.getBank().getTransactionList()));
    }

    private void showAccountList(BankController bc){
        System.out.println(bc.getBank().getFormattedAccount(bc.getBank().getAccountList()));
    }

    private void show(BankController bc, int accountID){
        System.out.printf("состояние счета %d: %s\n", accountID, bc.getBank().getAccountList().get(accountID));
    }
}
