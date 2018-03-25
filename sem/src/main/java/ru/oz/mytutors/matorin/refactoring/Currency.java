package ru.oz.mytutors.matorin.refactoring;

public class Currency {

    int amount;

    public static Currency EUR(int amount) {
        return new Currency(amount);
    }

    public static Currency USD(int amount) {
        return new Currency(amount);
    }

    public static Currency RUR(int amount) {
        return new Currency(amount);
    }

    public Currency(int amount) {
        this.amount = amount;
    }

}
