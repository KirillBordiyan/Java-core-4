package org.example.bankEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {

    private int sumValue = 0;
    private Procedure procedure;
    private int idAccount;

    @Override
    public String toString() {
        return "Transaction: " +
                "sumValue=" + sumValue +
                ", procedure=" + procedure +
                ", idAccount=" + idAccount;
    }
}
