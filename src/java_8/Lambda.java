package java_8;

/*Лямбда-выражение не выполняется само по себе, а образует реализацию метода,
 * определенного в функциональном интерфейсе. При этом важно,
 * что функциональный интерфейс должен содержать только один единственный метод без реализации.*/
public class Lambda {

    /*В роли функционального интерфейса выступает интерфейс Operation,
     * в котором определен один метод без реализации - метод calculate.
     * Данный метод принимает два параметра - целых числа, и возвращает некоторое целое число.*/
    private void exampleOperation() {
        Operation operation = (int x, int y) -> x + y;         // в лябда выражении в скобках можно опустить описание типов
        int rezult = operation.calculate(10, 20);       // т.к. компилятор сам знает что там находится из
        System.out.println(rezult);                            // функчионального интрефейса

        // Чтобы объявить и использовать лямбда-выражение, основная программа разбивается на ряд этапов
        // Определение ссылки на функциональный интерфейс и Создание лямбда-выражения
        Operation operation1 = (x, y) -> x - y;
        // Использование лямбда-выражения в виде вызова метода интерфейса
        int rezult1 = operation1.calculate(20, 10);
        System.out.println(rezult1);

        /*Одним из ключевых моментов в использовании лямбд является
         * отложенное выполнение (deferred execution).
         * То есть мы определяем в одном месте программы лямбда-выражение
         * и затем можем его вызывать при необходимости неопределенное
         * количество раз в различных частях программы*/

        // Если метод не принимает никаких параметров, то пишутся пустые скобки
        OperationNotParameters notParameters = () -> 20 + 30;
        int rezult3 = notParameters.calculate();
        System.out.println(rezult3);

        // Если метод принимает только один параметр, то скобки можно опустить
        OperationOneParameters oneParameters = n -> n + n;
        int rezult4 = oneParameters.calculate(5);
        System.out.println(rezult3);

        // Также метод может не возвращать никакого значения
        OperationVoid operationVoid = s -> System.out.println(s);
        operationVoid.print("Не возвражает ничего");

        // В случае если параметр на входе и параметр на выходе один и тот же, то можно сократить лямбда выражение через ::
        OperationVoid operationVoid1 = System.out::println;
        operationVoid.print("Не возвращает ничего");

        /* Переменные объявленые на уровне класса,
         * и в лямбда-выражении мы их может получить и даже изменить.
         * Но Локальные переменные уровня метода мы также может использовать в лямбдах,
         * но изменять их значение мы уже не сможем.
         * Если мы попробуем это сделать, то среда разработки (Netbeans)
         * может нам высветить ошибку и то, что такую переменную надо пометить
         * с помощью ключевого слова final, то есть сделать константой: final int n=70;.
         * Однако это необязательно. Более того, мы не сможем изменить значение переменной,
         * которая используется в лямбда-выражении, вне этого выражения. То есть даже если такая
         * переменная не объявлена как константа, по сути она является константой. */

        /* Существуют два типа лямбда-выражений:
         * однострочное выражение и блок кода. Примеры однострочных выражений демонстрировались выше.
         * Блочные выражения обрамляются фигурными скобками.
         * В блочных лямбда-выражениях можно использовать внутренние вложенные блоки, циклы,
         * конструкции if, switch, создавать переменные и т.д.
         * Если блочное лямбда-выражение должно возвращать значение, то явным образом применяется оператор return,
         * в однострочном лямбда выражении return не обязателен, компилятор и так это поймет*/
    }

    /*Одним из преимуществ лямбд в java является то, что их можно передавать в качестве параметров в методы
     * Функциональный интерфейс Expression определяет метод isEqual(),
     * который возвращает true, если в отношении числа n действует какое-нибудь равенство
     * метод sum(), который вычисляет сумму всех элементов массива,
     * соответствующих некоторому условию. А само условие передается через параметр Expression func.
     * Причем на момент написания метода sum мы можем абсолютно не знать, какое именно условие будет
     * использоваться. Само же условие определяется в виде лямбда-выражения*/
    private int sum(int[] numbers, Expression func) {
        int result = 0;
        for (int i : numbers) {
            if (func.isEqual(i))
                result += i;
        }
        return result;
    }

    //Также метод в Java может возвращать лямбда-выражение
    private static Operation action(int number) {
        switch (number) {
            case 1:
                return (x, y) -> x + y;
            case 2:
                return (x, y) -> x - y;
            case 3:
                return (x, y) -> x * y;
            default:
                return (x, y) -> 0;
        }
    }

    private void exampleLambdaParameter() {
        Expression func = (n) -> n % 2 == 0;
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int x = sum(nums, func);
        System.out.println(x);

        // можно не определять переменную интерфейса, а сразу передать в метод лямбда-выражение
        x = sum(nums, (n) -> n > 5); // сумма чисел, которые больше 5
        System.out.println(x);

        /* Так же можно передавать ссылку на метод:
         * сылка на метод передается в виде имя_класса::имя_статического_метода (если метод статический)
         * или объект_класса::имя_метода (если метод нестатический)*/

        int[] nums1 = {-5, -4, -3, -2, -1, 0, 1, 2, 3};
        System.out.println(sum(nums1, ExpressionHelper::isEven)); // статический метод, сумма четных элементов

        ExpressionHelper expressionHelper = new ExpressionHelper();
        Expression expr = expressionHelper::isPositive;         // не статиский метожд, сумма элементов больше 0
        System.out.println(sum(nums1, expr));

        /* Таким же образом можно делать ссылки на конструкторы название_класса::new
         * При использовании конструкторов методы функциональных интерфейсов должны принимать
         * тот же список параметров, что и конструкторы класса, и должны возвращать объект данного класса.*/

        UserBuilder userBuilder = User::new;
        User user = userBuilder.create("Tom");
        System.out.println(user.getName());

        // Лямбда как результат
        Operation operation = action(1);
        int a = operation.calculate(6, 5);
        System.out.println(a);

        int b = action(2).calculate(8, 2);
        System.out.println(b);


    }


    public void example() {
        exampleOperation();
        exampleLambdaParameter();

    }

    @FunctionalInterface
            // не обязательная анотация, следит за тем чтобы в интрефейсе был ТОЛЬКО ОДИН метод
    interface Operation {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface OperationNotParameters {
        int calculate();
    }

    @FunctionalInterface
    interface OperationOneParameters {
        int calculate(int a);
    }

    @FunctionalInterface
    interface OperationVoid {
        void print(String s);
    }

    @FunctionalInterface
    interface Expression {
        boolean isEqual(int n);
    }

    @FunctionalInterface
    interface UserBuilder {
        User create(String name);
    }


}

class ExpressionHelper {

    static boolean isEven(int n) {

        return n % 2 == 0;
    }

    boolean isPositive(int n) {

        return n > 0;
    }
}

class User {

    private String name;

    String getName() {
        return name;
    }

    User(String n) {
        this.name = n;
    }
}
