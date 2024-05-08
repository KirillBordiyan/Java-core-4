package org.example.bankController;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.bankModel.Bank;

@Getter
@Setter
@ToString
public class BankController {
    private Bank bank;
    public BankController(Bank bank) {
        this.bank = bank;
    }
}
