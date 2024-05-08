package org.example.bankEntity;

import lombok.ToString;

@ToString
public class AccBuilder {
    private Integer startAccountBalance = null;

    public AccBuilder setStartAccBalance(int startAccountBalance) {
        try{
            if(startAccountBalance < 0) {
                throw new IllegalArgumentException("Начальный баланс не может быть меньше 0; начальная сумма автоматически приведена к 0");
            }
            else {
                this.startAccountBalance = startAccountBalance;
                System.out.println("Аккаунт создан успешно");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return this;
    }

    public Account build() {
        if (startAccountBalance == null)
            return new Account();
        else return new Account(startAccountBalance);
    }
}
