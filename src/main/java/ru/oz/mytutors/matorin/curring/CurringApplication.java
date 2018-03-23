package ru.oz.mytutors.matorin.curring;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

/**
 * - Каррирование функции sum - преобразование функции от многих аргументов к функциям от одного аргумента. (currySum)
 *
 * - Сокращение аргументов у sum
 * Функция с 3-мя аргументами - преобразуем её к новой с двумя аргументами partialSum
 * фиксируя третий.
 *
 */
public class CurringApplication {

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static IntBinaryOperator partialSum(int x) {
        // x - фиксируется
        return (b, c) -> sum(x, b, c);
    }

    // Каррированная версия для sum() функции
    public static IntFunction<IntFunction<IntUnaryOperator>> currySum() {
        return x -> y -> z -> sum(x, y, z);
    }

    public static void main(String[] args) {

        IntBinaryOperator func = partialSum(10); // преобразованная функция (фиксируем 1-ый аргумент)
        System.out.println("Свертка равна = " + func.applyAsInt(5, 8)); // 10 + 5 + 8
        System.out.println("Свертка равна = " + func.applyAsInt(1, 111)); // 10 + 1 + 111

        int sum = currySum().apply(1).apply(2) .applyAsInt(3); // aka builder pattern
        System.out.println("sum = " + sum);
    }
}
