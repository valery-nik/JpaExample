https://youtu.be/rNg8jrWelXk - 



Декларотивное программирование - подход в написании кода, где разработчик говорит, что он хочет сделать/получить и не
 говорит каким образом. 

Импреативное программирование -

Функциональное программирование -

Мутабельность - https://habrahabr.ru/post/315050/#comment_9905804

Функция высшего порядка - функция которая принимает в качестве аргумента другую функцию или отдает в качестве результата
    новую функцию.
    
        // обертка для func логируюшая время выполнения. Приняли функцию и вернули функцию
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

Лямбда исчисление - это правила построения и вычисления анонимных функций (http://thesz.livejournal.com/11693.html)

Лямбда выражение - представляет собой блок кода, который можно передать в другое место, поэтому он может быть
    выполнен позже, один или несколько раз. Для этого блока кода введен специальный тип - Функциональные интерфейсы. 
  
    Collections.sort(names, (String a, String b) -> b.compareTo(a));
                    или 
    Collections.sort(names, (a, b) -> b.compareTo(a));



http://innostc.azurewebsites.net/category/%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D0%B8
Функциональные интерфейсы - интерфейс, содержащий ровно один метод. Аннотация @FunctionalInterface заставляет копилятор
    генерить ошибку, если в функц. интерфейсе попытаются добавить более одного метода. Это долгожданная фича Java 8,
    потому что это позволяет нам использовать лямбда-выражения для создания экземпляра таких интерфейсов. Кажое 
    лямбда-выражение является инстансом функционального интерфейса, т.е. тип лямбды - функц. интерфейс.
    Дополнительно JDK допилили, добавив к старым стандартным интерфейсам анотацию @FunctionalInterface и 
    удобные дефолтные методы(см. Comparator).

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

Новые функциональные интерфейсы:    
Function - принимает один аргумент и возвращает один результат (Function<String, Integer> toInteger = Integer::valueOf;)
Consumer - Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
Supplier - ничего не принимает, только возвращает один результат (Supplier<Person> personSupplier = Person::new;)

    Supplier<Integer> intSupplier = () -> (randomGenerator.nextInt(100));  /** Generate random integers in the range 0..99. */

Predicate - на входе аргумент, на выходе boolean значение 
Comparator - Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
функциональные интерфейсов еще больше, см пакет package java.util.function.

Получить результаты от выполнения этих функций можно с методами get и apply.

Ссылки на методы и их инициализация - что бы сослаться на метод класса или объекта используется оператор ":" - 
    
    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
                    или
    Converter<String, Integer> converter = Integer::valueOf;    
    Integer converted = converter.convert("123"); // см выше interface Converter
    System.out.println(converted);   // 123
                    или
                    

http://info.javarush.ru/translation/2014/10/09/%D0%9E%D1%81%D0%BE%D0%B1%D0%B5%D0%BD%D0%BD%D0%BE%D1%81%D1%82%D0%B8-Java-8-%D0%BC%D0%B0%D0%BA%D1%81%D0%B8%D0%BC%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5-%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE-%D1%87%D0%B0%D1%81%D1%82%D1%8C-2-.html    
Опциональные значения(optionals) - это по сути контейнер для значения, которое может быть равно null. Например, вам 
    нужен метод, который возвращает какое-то значение, но иногда он должен возвращать пустое значение. Вместо того, 
    чтобы возвращать null, в Java 8 вы можете вернуть опциональное значение.

    Optional<String> optional = Optional.of("bam");
    optional.isPresent();           // true
    optional.get();                 // "bam"
    optional.orElse("fallback");    // "bam"
    optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    
Предикаты
    На входе объект - на выходе булеан. Предикаты можно друг с другом объединять!
    https://howtodoinjava.com/java-8/how-to-use-predicate-in-java-8/ - предикаты в стримах
    https://youtu.be/rNg8jrWelXk?t=1294 - рефакторинг предикатов
    При работе со стрим фильтрацией, смысл -> вынести фильтр(предикат) в параметр метода. (cм PredicateApplication)
    
        List<Employee> filterEmployees (List<Employee> employees, Predicate<Employee> predicate)