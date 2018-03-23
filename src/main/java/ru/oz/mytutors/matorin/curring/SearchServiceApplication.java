package ru.oz.mytutors.matorin.curring;

import java.util.Random;
import java.util.function.*;

public class SearchServiceApplication {
    @FunctionalInterface
    public interface MyFuncInterface {

        int method1();
    }

    public Runnable decorate(Runnable func) {
        return () -> {
            long time = System.currentTimeMillis();
            try {
                func.run();
            } finally {
                System.out.println("Exec time: " + (System.currentTimeMillis() - time));
            }
        };
    }

    public static void main(String[] args) {

        // SUPPLIER FUNCTION INTERFACE
        Random randomGenerator = new Random();
        Supplier<Integer> intSupplier = () -> (randomGenerator.nextInt(100));  /** Generate random integers in the range 0..99. */
        System.out.println("Generated : " + intSupplier.get());   // new random int
        System.out.println("Generated : " + intSupplier.get());   // new random int
        System.out.println("Generated : " + intSupplier.get());   // new random int
        System.out.println("Generated : " + intSupplier.get());   // new random int

//      SIMPLE LAMBDA EXAMPLE
//        Comparator<String> comparator = (p1, p2) -> p1.compareTo(p2);


        // OPTIONAL //
//        Optional<String> optional = Optional.ofNullable(null); // Optional.of("bam"); // =null
//
//        System.out.println(" optional.orElse(\"fallback\") = " + optional.orElse("fallback"));    // "bam"
//        System.out.println(" optional.isPresent() = " + optional.isPresent());           // true
//        System.out.println(" optional.get() = " + optional.get());                 // "bam"
//
//        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"


        Function<String, String> func = (in) -> "*******" + in + "*******";
        System.out.println(func
                .compose(func) // хер знает для чего
                .compose(func.andThen(func))
                .apply("test"));


        // Функции как ссылки
        SearchServiceApplication instance = new SearchServiceApplication();
        BiFunction<Integer, Integer, Integer> localSum = instance.sum;
        BiFunction<Integer, Integer, Integer> localSumma = instance::summa;
        BinaryOperator<Integer> operator = instance :: summa;
        IntBinaryOperator intBinaryOperator = instance :: summa;

        System.out.println("localSumma = " + localSumma.apply(1, 5));
    }

    int summa(int a, int b) {
        return a + b;
    }

    BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

}
