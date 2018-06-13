import java.util.*;
import java.util.stream.*;

/* Ключевым понятием в Stream API является поток данных
 * Одной из отличительных черт Stream API является применение
 * лямбда-выражений, которые позволяют значительно сократить запись
 * выполняемых действий.*/
public class Strems {

    private void examplePrimitiveTypeStream() {
        // Выведем количество чител больше 0 в инициализированном массиве
        // Такие же stream есть и для DoubleStream, LongStream
        long count = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(w -> w > 0).count();
        System.out.println(count);
    }
    /* В основе Stream API лежит интерфейс interface BaseStream<T , S extends BaseStream<T , S>>
     * Здесь параметр T означает тип данных в потоке, а S - тип потока, который наследуется от интерфейса BaseStream
     * void close(): закрывает поток
     * boolean isParallel(): возвращает true, если поток является параллельным
     * Iterator<Т> iterator(): возвращает ссылку на итератор потока
     * Spliterator<Т> spliterator(): возвращает ссылку на сплитератор потока
     * S parallel(): возвращает параллельный поток (параллельные потоки могут взадействовать несколько ядер процессора в многоядерных архитектурах)
     * S sequential(): возвращает последовательный поток
     * S unordered(): возвращает неупорядоченный поток
     *
     * От интерфейса BaseStream наследуется ряд интерфейсов, предназначенных для создания конкретных потоков:
     * Stream<T>: используется для потоков данных, представляющих любой ссылочный тип
     * IntStream: используется для потоков с типом данных int
     * DoubleStream: используется для потоков с типом данных double
     * LongStream: используется для потоков с типом данных long*/

    /* При работе со Stream API важно понимать, что все операции с потоками бывают либо
     * терминальными (terminal), либо промежуточными (intermediate).
     * Промежуточные операции возвращают трансформированный поток.
     * К возвращенном потоку также можно применить ряд промежуточных операций.
     * Конечные или терминальные операции возвращают конкретный результат.
     * Например, в примере выше метод count() представляет терминальную операцию и возвращает число.
     * После этого никаких промежуточных операций естественно применять нельзя.*/

    /*Некоторые методы Stream<T>:
     * boolean allMatch(Predicate<? super T> predicate): возвращает true, если все элементы потока удовлетворяют условию в предикате. Терминальная операция
     * boolean anyMatch(Predicate<? super T> predicate): возвращает true, если хоть один элемент потока удовлетворяют условию в предикате. Терминальная операция
     * <R,A> R collect(Collector<? super T,A,R> collector): добавляет элементы в неизменяемый контейнер с типом R. T представляет тип данных из вызывающего потока, а A - тип данных в контейнере. Терминальная операция
     * long count(): возвращает количество элементов в потоке. Терминальная операция.
     * Optional<T> findFirst(): возвращает первый элемент из потока. Терминальная операция
     * Optional<T> findAny(): возвращает первый попавшийся элемент из потока. Терминальная операция
     * void forEach(Consumer<? super T> action): для каждого элемента выполняется действие action. Терминальная операция
     * Optional<T> max(Comparator<? super T> comparator): возвращает максимальный элемент из потока. Для сравнения элементов применяется компаратор comparator. Терминальная операция
     * Optional<T> min(Comparator<? super T> comparator): возвращает минимальный элемент из потока. Для сравнения элементов применяется компаратор comparator. Терминальная операция
     * boolean noneMatch(Predicate<? super T> predicate): возвращает true, если ни один из элементов в потоке не удовлетворяет условию в предикате. Терминальная операция
     * Object[] toArray(): возвращает массив из элементов потока. Терминальная операция.
     * Stream<T> concat​(Stream<? extends T> a, Stream<? extends T> b): объединяет два потока. Промежуточная операция
     * Stream<T> distinct(): возвращает поток, в котором имеются только уникальные данные с типом T. Промежуточная операция
     * Stream<T> dropWhile​(Predicate<? super T> predicate): пропускает элементы, которые соответствуют условию в predicate, пока не попадется элемент, который не соответствует условию. Выбранные элементы возвращаются в виде потока. Промежуточная операция.
     * Stream<T> filter(Predicate<? super T> predicate): фильтрует элементы в соответствии с условием в предикате. Промежуточная операция
     * Stream<T> limit(long maxSize): оставляет в потоке только maxSize элементов. Промежуточная операция
     * <R> Stream<R> map(Function<? super T,? extends R> mapper): преобразует элементы типа T в элементы типа R и возвращает поток с элементами R. Промежуточная операция
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper): позволяет преобразовать элемент типа T в несколько элементов типа R и возвращает поток с элементами R. Промежуточная операция
     * Stream<T> skip(long n): возвращает поток, в котором отсутствуют первые n элементов. Промежуточная операция.
     * Stream<T> sorted(): возвращает отсортированный поток. Промежуточная операция.
     * Stream<T> sorted(Comparator<? super T> comparator): возвращает отсортированный в соответствии с компаратором поток. Промежуточная операция.
     * Stream<T> takeWhile​(Predicate<? super T> predicate): выбирает из потока элементы, пока они соответствуют условию в predicate. Выбранные элементы возвращаются в виде потока. Промежуточная операция.*/

    /* Потоки не хранят элементы. Элементы, используемые в потоках, могут храниться в коллекции, либо при необходимости могут быть напрямую сгенерированы.
     * Операции с потоками не изменяют источника данных. Операции с потоками лишь возвращают новый поток с результатами этих операций.
     * Для потоков характерно отложенное выполнение. То есть выполнение всех операций с потоком происходит лишь тогда, когда выполняется терминальная операция и возвращается конкретный результат, а не новый поток.*/

    /* Способы создания потока
     * default Stream<E> stream: возвращается поток данных из коллекции
     * default Stream<E> parallelStream: возвращается параллельный поток данных из коллекции*/
    private void exampleCreateStream() {
        System.out.println("Создание потока: ");
        ArrayList<String> cities = new ArrayList<String>();
        Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
        cities.stream() // получаем поток
                .filter(s -> s.length() == 6) // применяем фильтрацию по длине строки
                .forEach(System.out::println); // выводим отфильтрованные строки на консоль
        /* Важно помнить, что после вызова терминальнойй операции, последующие вызовы операций не возможны, т.е. она
        * является конечной операцией cities.stream()
         .forEach(s->System.out.println(s));
         .filter(s->s.length()==6)  здесь будет ошибка т.к. фор терминальная операция  */

        /* Другие способы*/
        System.out.println("Метод Arrays.stream(T[] array)");
        Stream<String> citiesStream = Arrays.stream(new String[]{"Париж", "Лондон", "Мадрид"});
        citiesStream.forEach(System.out::println); // выводим все элементы массива

        System.out.println("Поток IntStream");
        IntStream intStream = Arrays.stream(new int[]{1, 2, 4, 5, 7});
        intStream.forEach(System.out::println);

        System.out.println("Поток DoubleStream");
        LongStream longStream = Arrays.stream(new long[]{100, 250, 400, 5843787, 237});
        longStream.forEach(System.out::println);

        System.out.println("Поток LongStream ");
        DoubleStream doubleStream = Arrays.stream(new double[]{3.4, 6.7, 9.5, 8.2345, 121});
        doubleStream.forEach(System.out::println);

        System.out.println("Cтатический метод of(T..values) класса Stream");
        Stream<String> citiesStream1 = Stream.of("Париж", "Лондон", "Мадрид");
        citiesStream1.forEach(System.out::println);

        // можно передать массив
        String[] cities1 = {"Париж", "Лондон", "Мадрид"};
        Stream<String> citiesStream2 = Stream.of(cities1);

        IntStream intStream1 = IntStream.of(1, 2, 4, 5, 7);
        intStream1.forEach(System.out::println);

        LongStream longStream1 = LongStream.of(100, 250, 400, 5843787, 237);
        longStream1.forEach(System.out::println);

        DoubleStream doubleStream1 = DoubleStream.of(3.4, 6.7, 9.5, 8.2345, 121);
        doubleStream1.forEach(System.out::println);

    }

    /* Фильтрация, перебор элементов и отображение*/
    private void exampleFilterForDisplay(){
        System.out.println("Фильтрация, перебор элементов и отображение");

        /* Для фильтрации элементов в потоке применяется метод filter(),
         * который представляет промежуточную операцию. Он принимает в качестве параметра
         * некоторое условие в виде объекта Predicate<T> и возвращает новый поток из элементов,
         * которые удовлетворяют этому условию*/
        Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
        citiesStream.filter(s->s.length()==6).forEach(System.out::println);

        /* Более сложный пример, фильтрация
        * Допустим, у нас есть следующий класс Phone
        * Отфильтруем набор телефонов по цене*/
        System.out.println("Фильтрация класса Phone по цене < 50 000");
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S","", 54000), new Phone("Lumia 950","",  45000),
                new Phone("Samsung Galaxy S 6","",  40000));
        phoneStream.filter(p->p.getPrice()<50000).forEach(p->System.out.println(p.getName()));

        /* Отображение или маппинг позволяет задать функцию преобразования одного объекта в другой,
         * то есть получить из элемента одного типа элемент другого типа. Для отображения используется метод map,
         * который имеет следующее определение
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         *
         * Возьмем вышеопределенный класс телефонов и выполним преобразование от типа Phone к типу String*/
        System.out.println("Преобразование класса Phone к String, только имена");
        Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone 6 S","",  54000), new Phone("Lumia 950","",  45000),
                new Phone("Samsung Galaxy S 6","",  40000));
        phoneStream1
                .map(p-> p.getName()) // помещаем в поток только названия телефонов
                .forEach(System.out::println);
        /* Или*/
        System.out.println("Преобразование класса Phone к String");
        Stream<Phone> phoneStream2 = Stream.of(new Phone("iPhone 6 S","",  54000), new Phone("Lumia 950","",  45000),
                new Phone("Samsung Galaxy S 6","",  40000));
        phoneStream2
                .map(p-> "название: " + p.getName() + " цена: " + p.getPrice())
                .forEach(System.out::println);
        /* Для преобразования объектов в типы Integer, Long, Double
         * определены специальные методы mapToInt(), mapToLong() и mapToDouble() соответственно*/

        /* Плоское отображение выполняется тогда, когда из одного элемента нужно получить несколько.
         * Данную операцию выполняет метод flatMap
         * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)*/

        /* Например, в примере выше мы выводим название телефона и его цену.
         * Но что, если мы хотим установить для каждого телефона цену со скидкой и цену без скидки.
         * То есть из одного объекта Phone нам надо получить два объекта с информацией, например,
         * в виде строки. Для этого применим flatMap*/
        System.out.println("Плоское преобразование");
        Stream<Phone> phoneStream3 = Stream.of(new Phone("iPhone 6 S","",  54000), new Phone("Lumia 950","",  45000),
                new Phone("Samsung Galaxy S 6","",  40000));
        phoneStream3
                .flatMap(p->Stream.of(
                        String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int)(p.getPrice()*0.1))
                ))
                .forEach(System.out::println);
    }

    /* Сортировка
    * Для простой сортировки по возрастанию применяется метод sorted()*/
    private void exampleSort(){
        System.out.println("Сортировка ");
        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9", "Huawei Nexus 6P",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI6",
                "ASUS Zenfone 3", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 2");

        phones.stream()
                .filter(p->p.length()<12)
                .sorted() // сортировка по возрастанию
                .forEach(System.out::println);

        /* Для сортировки класса Phone нужно определить компаратор класс PhoneComparator,
        * в нем и укажим по какому полю сортировать и как*/
        System.out.println("Сортировка класса Phone");
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple",450),
                new Phone("Nokia 9", "HMD Global",150),
                new Phone("Galaxy S9", "Samsung", 300));

        phoneStream.sorted(new PhoneComparator())
                .forEach(p->System.out.printf("%s (%s) - %d \n",
                        p.getName(), p.getCompany(), p.getPrice()));


}




    public void example() {
        examplePrimitiveTypeStream();
        exampleCreateStream();
        exampleFilterForDisplay();
        exampleSort();
    }

}

class Phone{

    private String name;
    private String company;
    private int price;

    public Phone(String name, String comp, int price){
        this.name=name;
        this.company=comp;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class PhoneComparator implements Comparator<Phone> {

    public int compare(Phone a, Phone b){

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}
