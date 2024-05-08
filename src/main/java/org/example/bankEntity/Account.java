package org.example.bankEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private int accountBalance;

    /**
     * Конструктор, когда на баланс счета сразу начисляется N-сумма
     *
     * @param accountBalance - стартовая сумма
     */
    Account(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    Account() {
        this(0);
    }

    @Override
    public String toString() {
        return "accountBalance=" + accountBalance;
    }
}
