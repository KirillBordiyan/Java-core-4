package org.example;

import org.example.bankController.BankController;
import org.example.bankModel.Bank;
import org.example.bankView.BankView;

public class Program {
    public static void main(String[] args) {

        Bank bank = new Bank();
        BankController bc = new BankController(bank);
        BankView bv = new BankView(bc);

        bv.init();
    }
}
