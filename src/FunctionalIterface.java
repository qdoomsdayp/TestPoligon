import java.util.function.*;


/* Функциональные интерфейсы:
 * интерфейсы содержащие ТОЛЬКО ОДИН (абстрактый) метод,
 * появились в java 8, их в пакете java.util.function 43,
 * они помечаются анотацией @FunctionalInterface, можно и не помечать,
 * но для наглядности желательно
 * основные из них:
 *       Predicate<T>
 *       Consumer<T>
 *       Function<T,R>
 *       Supplier<T>
 *       UnaryOperator<T>
 *       BinaryOperator<T>*/

public class FunctionalIterface {

    /* Функциональный интерфейс Predicate<T>:
     *  проверяет соблюдение некоторого условия.
     *  Если оно соблюдается, то возвращается значение true.
     *  В качестве параметра лямбда-выражение принимает объект типа T
     *  основной метод boolean test(T t)*/
    private void examplePredicate() {
        System.out.println("Пример функционального интерфеса Predicate<T>");
        Predicate<Integer> isNumber = x -> x > 0;
        System.out.println(isNumber.test(5));
        System.out.println(isNumber.test(-1));
    }

    /* Функциональный интерфейс BinaryOperator<T>:
     *  принимает в качестве параметра два объекта типа T,
     *  выполняет над ними бинарную операцию и возвращает ее результат также в виде объекта типа T
     *  основной метод T apply(T t1, T t2)*/
    private void exampleBinaryOperator() {
        System.out.println("Пример функционального интерфеса BinaryOperator<T>");
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        System.out.println(multiply.apply(3, 5));
        System.out.println(multiply.apply(10, -2));
    }

    /* Функциональный интерфейс UnaryOperator<T>:
     *  принимает в качестве параметра объект типа T,
     *  выполняет над ними операции и возвращает результат операций в виде объекта типа T
     *  основной метод T apply(T t)*/
    private void exampleUnaryOperator() {
        System.out.println("Пример функционального интерфеса UnaryOperator<T>");
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println(square.apply(5));
    }

    /* Функциональный интерфейс Function<T,R>:
     *  представляет функцию перехода от объекта типа T к объекту типа R
     *  основной метод R apply(T t)*/
    private void exampleFunction() {
        System.out.println("Пример функционального интерфеса Function<T,R>");
        Function<Integer, String> convert = x -> String.valueOf(x) + " долларов";
        System.out.println(convert.apply(5));
    }

    /* Функциональный интерфейс Consumer<T>:
     *  выполняет некоторое действие над объектом типа T, при этом ничего не возвращая
     *  основной метод void accept(T t)*/
    private void exampleConsumer() {
        System.out.println("Пример функционального интерфеса Consumer<T>");
        Consumer<Integer> printer = x -> System.out.printf("%d долларов \n", x);
        printer.accept(600);
    }

    /* Функциональный интерфейс Supplier<T>:
     *  не принимает никаких аргументов, но должен возвращать объект типа T
     *  основной метод T get()*/
    private void exampleSupplier() {
        System.out.println("Пример функционального интерфеса Supplier<T>");
        Supplier<String> userFactory = () -> {
            String str = "Supliner";
            return str;
        };
        System.out.println(userFactory.get());
    }

    public void example() {
        System.out.println("----------------------------------------------------------------------------------------------------");
        examplePredicate();
        System.out.println("----------------------------------------------------------------------------------------------------");
        exampleBinaryOperator();
        System.out.println("----------------------------------------------------------------------------------------------------");
        exampleUnaryOperator();
        System.out.println("----------------------------------------------------------------------------------------------------");
        exampleFunction();
        System.out.println("----------------------------------------------------------------------------------------------------");
        exampleConsumer();
        System.out.println("----------------------------------------------------------------------------------------------------");
        exampleSupplier();
        System.out.println("----------------------------------------------------------------------------------------------------");
    }


}
