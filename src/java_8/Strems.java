package java_8;

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
    private void exampleFilterForDisplay() {
        System.out.println("Фильтрация, перебор элементов и отображение");

        /* Для фильтрации элементов в потоке применяется метод filter(),
         * который представляет промежуточную операцию. Он принимает в качестве параметра
         * некоторое условие в виде объекта Predicate<T> и возвращает новый поток из элементов,
         * которые удовлетворяют этому условию*/
        Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид", "Берлин", "Брюссель");
        citiesStream.filter(s -> s.length() == 6).forEach(System.out::println);

        /* Более сложный пример, фильтрация
         * Допустим, у нас есть следующий класс java_8.Phone
         * Отфильтруем набор телефонов по цене*/
        System.out.println("Фильтрация класса java_8.Phone по цене < 50 000");
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", "", 54000), new Phone("Lumia 950", "", 45000),
                new Phone("Samsung Galaxy S 6", "", 40000));
        phoneStream.filter(p -> p.getPrice() < 50000).forEach(p -> System.out.println(p.getName()));

        /* Отображение или маппинг позволяет задать функцию преобразования одного объекта в другой,
         * то есть получить из элемента одного типа элемент другого типа. Для отображения используется метод map,
         * который имеет следующее определение
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         *
         * Возьмем вышеопределенный класс телефонов и выполним преобразование от типа java_8.Phone к типу String*/
        System.out.println("Преобразование класса java_8.Phone к String, только имена");
        Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone 6 S", "", 54000), new Phone("Lumia 950", "", 45000),
                new Phone("Samsung Galaxy S 6", "", 40000));
        phoneStream1
                .map(p -> p.getName()) // помещаем в поток только названия телефонов
                .forEach(System.out::println);
        /* Или*/
        System.out.println("Преобразование класса java_8.Phone к String");
        Stream<Phone> phoneStream2 = Stream.of(new Phone("iPhone 6 S", "", 54000), new Phone("Lumia 950", "", 45000),
                new Phone("Samsung Galaxy S 6", "", 40000));
        phoneStream2
                .map(p -> "название: " + p.getName() + " цена: " + p.getPrice())
                .forEach(System.out::println);
        /* Для преобразования объектов в типы Integer, Long, Double
         * определены специальные методы mapToInt(), mapToLong() и mapToDouble() соответственно*/

        /* Плоское отображение выполняется тогда, когда из одного элемента нужно получить несколько.
         * Данную операцию выполняет метод flatMap
         * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)*/

        /* Например, в примере выше мы выводим название телефона и его цену.
         * Но что, если мы хотим установить для каждого телефона цену со скидкой и цену без скидки.
         * То есть из одного объекта java_8.Phone нам надо получить два объекта с информацией, например,
         * в виде строки. Для этого применим flatMap*/
        System.out.println("Плоское преобразование");
        Stream<Phone> phoneStream3 = Stream.of(new Phone("iPhone 6 S", "", 54000), new Phone("Lumia 950", "", 45000),
                new Phone("Samsung Galaxy S 6", "", 40000));
        phoneStream3
                .flatMap(p -> Stream.of(
                        String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int) (p.getPrice() * 0.1))
                ))
                .forEach(System.out::println);
    }

    /* Сортировка
     * Для простой сортировки по возрастанию применяется метод sorted()*/
    private void exampleSort() {
        System.out.println("Сортировка ");
        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9", "Huawei Nexus 6P",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI6",
                "ASUS Zenfone 3", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 2");

        phones.stream()
                .filter(p -> p.length() < 12)
                .sorted() // сортировка по возрастанию
                .forEach(System.out::println);

        /* Для сортировки класса java_8.Phone нужно определить компаратор класс java_8.PhoneComparator,
         * в нем и укажим по какому полю сортировать и как*/
        System.out.println("Сортировка класса java_8.Phone");
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Nokia 9", "HMD Global", 150),
                new Phone("Galaxy S9", "Samsung", 300));

        phoneStream.sorted(new PhoneComparator())
                .forEach(p -> System.out.printf("%s (%s) - %d \n",
                        p.getName(), p.getCompany(), p.getPrice()));


    }

    /* Методы skip, limit
     * Метод skip(long n) используется для пропуска n элементов.
     * Этот метод возвращает новый поток, в котором пропущены первые n элементов.
     * Метод limit(long n) применяется для выборки первых n элементов потоков.
     * Этот метод также возвращает модифицированный поток, в котором не более n элементов.
     *
     * Зачастую эта пара методов используется вместе для создания эффекта постраничной навигации*/
    private void exampleMethod() {
        /* В данном случае метод skip пропускает один первый элемент, а метод limit выбирает два следующих элемента*/
        Stream<String> phoneStream = Stream.of("iPhone 6 S", "Lumia 950", "Samsung Galaxy S 6", "LG G 4", "Nexus 7");
        phoneStream.skip(1)
                .limit(2)
                .forEach(s -> System.out.println(s));
        /* Вполне может быть, что метод skip может принимать в качестве параметра число большее,
         * чем количество элементов в потоке. В этом случае будут пропущены все элементы, а в
         * результирующем потоке будет 0 элементов.
         * И если в метод limit передается число, большее, чем количество элементов,
         * то просто выбираются все элементы потока*/
    }

    /* Пограничная навигация*/
    private void exampleBorderNavigation() {

        /* В данном случае у нас набор из 10 элементов. С помощью переменной pageSize
         * определяем количество элементов на странице - 3. То есть у нас получится 4 страницы
         * (на последней будет только один элемент).
         * В бесконечном цикле получаем номер страницы и выбираем только те элементы,
         * которые находятся на указанной странице*/
        List<String> phones = new ArrayList<String>();
        phones.addAll(Arrays.asList(new String[]
                {"iPhone 6 S", "Lumia 950", "Huawei Nexus 6P",
                        "Samsung Galaxy S 6", "LG G 4", "Xiaomi MI 5",
                        "ASUS Zenfone 2", "Sony Xperia Z5", "Meizu Pro 5",
                        "Lenovo S 850"}));

        int pageSize = 3; // количество элементов на страницу
        Scanner scanner = new Scanner(System.in);
        int page = 3; //номер страницы, которую выведем
        phones.stream().skip((page - 1) * pageSize)
                .limit(pageSize)
                .forEach(s -> System.out.println(s));
    }

    /* Операции сведения
     * Операции сведения представляют терминальные операции,
     * которые возвращают некоторое значение - результат операции
     * count, findFirst и findAny, allMatch, anyMatch, noneMatch, min и max*/
    private void exampleInformationOperations() {

        /* Метод count() возвращает количество элементов в потоке данных*/
        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        System.out.println(names.stream().count());  // 4
        // количество элементов с длиной не больше 3 символов
        System.out.println(names.stream().filter(n -> n.length() <= 3).count());

        /* Метод findFirst() извлекает из потока первый элемент,
         * а findAny() извлекает случайный объект из потока (нередко так же первый)*/
        ArrayList<String> names1 = new ArrayList<String>();
        names1.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        Optional<String> first = names1.stream().findFirst();
        System.out.println(first.get());    // Tom
        Optional<String> any = names.stream().findAny();
        System.out.println(any.get());

        /* Еще одна группа операций сведения возвращает логическое значение true или false:
         * boolean allMatch(Predicate<? super T> predicate): возвращает true,
         * если все элементы потока удовлетворяют условию в предикате
         * boolean anyMatch(Predicate<? super T> predicate): возвращает true,
         * если хоть один элемент потока удовлетворяют условию в предикате
         * boolean noneMatch(Predicate<? super T> predicate): возвращает true,
         * если ни один из элементов в потоке не удовлетворяет условию в предикате*/
        ArrayList<String> names2 = new ArrayList<String>();
        names2.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        // есть ли в потоке строка, длина которой больше 3
        boolean any2 = names2.stream().anyMatch(s -> s.length() > 3);
        System.out.println(any);    // true
        // все ли строки имеют длину в 3 символа
        boolean all2 = names2.stream().allMatch(s -> s.length() == 3);
        System.out.println(all2);    // false
        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        boolean none2 = names2.stream().noneMatch(s -> s == "Bill");
        System.out.println(none2);   // true

        /* Методы min() и max() возвращают соответственно минимальное и максимальное значение.
         * Поскольку данные в потоке могут представлять различные типы, в том числе сложные классы,
         * то в качестве параметра в эти методы передается объект интерфейса Comparator, который указывает,
         * как сравнивать объекты*/
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println(min.get());  // 1
        System.out.println(max.get());  // 9

        /*min max на примере класса и объектов*/
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.addAll(Arrays.asList(new Phone[]{
                new Phone("iPhone 8", "", 52000),
                new Phone("Nokia 9", "", 35000),
                new Phone("Samsung Galaxy S9", "", 48000),
                new Phone("HTC U12", "", 36000)
        }));

        Phone min1 = phones.stream().min(Phone::compare).get();
        Phone max1 = phones.stream().max(Phone::compare).get();
        System.out.printf("MIN Name: %s Price: %d \n", min1.getName(), min1.getPrice());
        System.out.printf("MAX Name: %s Price: %d \n", max1.getName(), max1.getPrice());
    }

    /* Метод reduce
     * Метод reduce выполняет терминальные операции сведения, возвращая некоторое значение - результат операции
     * Optional<T> reduce(BinaryOperator<T> accumulator)
     * T reduce(T identity, BinaryOperator<T> accumulator)
     * U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)*/
    private void exampleReduce() {
        /* Первая форма возвращает результат в виде объекта Optional<T>
         *  Например, вычислим произведение набора чисел
         *  Объект BinaryOperator<T> представляет функцию, которая принимает два элемента и
         *  выполняет над ними некоторую операцию, возвращая результат.
         *  При этом метод reduce сохраняет результат и затем опять же применяет к этому результату и
         *  следующему элементу в наборе бинарную операцию. Фактически в данном случае мы получим результат,
         *  который будет равен: n1 op n2 op n3 op n4 op n5 op n6, где op - это операция (в данном случае умножения),
         *  а n1, n2, ... - элементы из потока*/
        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> result = numbersStream.reduce((x, y) -> x * y);
        System.out.println(result.get()); // 720

       /* Если нам надо, чтобы первым элементом в наборе было какое-то определенное значение,
       то мы можем использовать вторую версию метода reduce(), которая в качестве первого параметра
       принимает T identity. Этот параметр хранит значение, с которого будет начинаться цепочка бинарных операций*/
        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
        String sentence = wordsStream.reduce("Результат:", (x, y) -> x + " " + y);
        System.out.println(sentence); // Результат: мама мыла раму

        /* В случае с классом java_8.Phone
         * мы хотим найти сумму цен тех телефонов,
         * у которых цена меньше определенного значения. Для этого используем третью версию метода reduce
         *
         * Опять же здесь в качестве первого параметра идет значение по умолчанию - 0.
         * Второй параметр производит бинарную операцию, которая получает промежуточное значение -
         * суммарную цену текущего и предыдущего телефонов. Третий параметр представляет бинарную операцию,
         * которая суммирует все промежуточные вычисления*/
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", "", 54000),
                new Phone("Lumia 950", "", 45000),
                new Phone("Samsung Galaxy S 6", "", 40000),
                new Phone("LG G 4", "", 32000));

        int sum = phoneStream.reduce(0,
                (x, y) -> {
                    if (y.getPrice() < 50000)
                        return x + y.getPrice();
                    else
                        return x + 0;
                },
                (x, y) -> x + y);

        System.out.println(sum); // 117000
    }

    /* Тип Optional
     * Ряд операций сведения, такие как min, max, reduce,
     * возвращают объект Optional<T>. Этот объект фактически обертывает результат операции.
     * После выполнения операции с помощью метода get() объекта Optional мы можем получить его значение*/
    private void examleOptional() {
        /* Ранее есть примеры с некоторыми операциями
         * Но что, если поток не содержит вообще никаких данных
         * В этом случае программа выдаст исключение java.util.NoSuchElementException
         * Для этого класс Optional предоставляет ряд методов*/

        /* Самой простой способ избежать подобной ситуации - это предварительная проверка наличия значения
         * в Optional с помощью метода isPresent(). Он возврашает true, если значение присутствует
         * в Optional, и false, если значение отсутствует*/
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        if (min.isPresent()) {
            System.out.println(min.get());
        }

        /* Метод orElse() позволяет определить альтернативное значение,
         * которое будет возвращаться, если Optional не получит из потока какого-нибудь значения*/
        // пустой список
        ArrayList<Integer> numbers1 = new ArrayList<Integer>();
        Optional<Integer> min1 = numbers.stream().min(Integer::compare);
        System.out.println(min1.orElse(-1)); // -1
        // непустой список
        numbers1.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        min = numbers1.stream().min(Integer::compare);
        System.out.println(min.orElse(-1)); // 4

        /* Метод orElseGet() позволяет задать функцию, которая будет возвращать значение по умолчанию*/
        ArrayList<Integer> numbers2 = new ArrayList<Integer>();
        Optional<Integer> min2 = numbers.stream().min(Integer::compare);
        Random rnd = new Random();
        System.out.println(min2.orElseGet(() -> rnd.nextInt(100)));

        /* Еще один метод - orElseThrow позволяет сгенерировать исключение, если Optional не содержит значения*/
        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
        Optional<Integer> min3 = numbers3.stream().min(Integer::compare);
        // генеррация исключения IllegalStateException
        //System.out.println(min3.orElseThrow(IllegalStateException::new)); //Раскоментив эту строчку на экране получим исключение

        /* Обработка полученного значения
         * Метод ifPresent() определяет действия со значением в Optional, если значение имеется
         * В метод ifPresent передается функция, которая принимает один параметр - значение из Optional.
         * В данном случае полученное минимальное число выводится на консоль. Но если бы массив numbers
         * был бы пустым, и соответственно Optional не сдержало бы никакого значения, то никакой ошибки бы не было*/
        ArrayList<Integer> numbers4 = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{4, 5, 6, 7, 8, 9}));
        Optional<Integer> min4 = numbers.stream().min(Integer::compare);
        min4.ifPresent(v -> System.out.println(v)); // 4

        /* Метод ifPresentOrElse() позволяет определить альтернативную логику на случай, если значение в Optional отсутствует
         * В метод ifPresentOrElse передается две функции.
         * Первая обрабатывает значение в Optional, если оно присутствует.
         * Вторая функция представляет действия, которые выполняются, если значение в Optional отсутствует*/
        ArrayList<Integer> numbers5 = new ArrayList<Integer>();
        Optional<Integer> min5 = numbers.stream().min(Integer::compare);
        min5.ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Value not found")
        );

    }

    /* Большинство операций класса Stream, которые модифицируют набор данных, возвращают этот набор в виде потока.
     * Однако бывают ситуации, когда хотелось бы получить данные не в виде потока, а в виде обычной коллекции,
     * например, ArrayList или HashSet. И для этого у класса Stream определен метод collect. Первая версия метода
     * принимает в качестве параметра функцию преобразования к коллекции <R,A> R collect(Collector<? super T,A,R> collector)
     * Параметр R представляет тип результата метода, параметр Т - тип элемента в потоке,
     * а параметр А - тип промежуточных накапливаемых данных. В итоге параметр collector представляет функцию
     * преобразования потока в коллекцию.
     * Эта функция представляет объект Collector, который определен в пакете java.util.stream.
     * Мы можем написать свою реализацию функции, однако Java уже предоставляет ряд встроенных функций,
     * определенных в классе Collectors:
     * toList(): преобразование к типу List
     * toSet(): преобразование к типу Set
     * toMap(): преобразование к типу Map*/
    private void exapleCollect() {
        /* преобразуем набор в потоке в список
         * Использование метода toSet() аналогично toList()*/
        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");
        List<String> filteredPhones = phones.stream()
                .filter(s -> s.length() < 10)
                .collect(Collectors.toList());
        for (String s : filteredPhones) {
            System.out.println(s);
        }

        /* Для применения метода toMap() надо задать ключ и значение
         * Лямбда-выражение p->p.getName() получает значение для ключа элемента,
         * а t->t.getPrice() - извлекает значение элемента*/
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 8", "", 54000),
                new Phone("Nokia 9", "", 45000),
                new Phone("Samsung Galaxy S9", "", 40000),
                new Phone("LG G6", "", 32000));
        Map<String, Integer> phones1 = phoneStream
                .collect(Collectors.toMap(p -> p.getName(), t -> t.getPrice()));
        phones1.forEach((k, v) -> System.out.println(k + " " + v));

        /* Если нам надо создать какой-то определенный тип коллекции, например, HashSet,
         * то мы можем использовать специальные функции, которые определены в классах-коллекций
         * аналогично и другие например
         * ArrayList<String> result = phones.collect(Collectors.toCollection(ArrayList::new));*/
        Stream<String> phones2 = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");
        TreeSet<String> filteredPhones2 = phones2.filter(s -> s.length() < 12).
                collect(Collectors.toCollection(TreeSet::new));
        filteredPhones2.forEach(s -> System.out.println(s));

        /* Вторая форма метода collect имеет три параметра
         * <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
         * supplier: создает объект коллекции
         * accumulator: добавляет элемент в коллекцию
         * combiner: бинарная функция, которая объединяет два объекта*/
        Stream<String> phones3 = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        ArrayList<String> filteredPhones3 = phones3.filter(s -> s.length() < 12)
                .collect(
                        () -> new ArrayList<String>(), // создаем ArrayList
                        (list, item) -> list.add(item), // добавляем в список элемент
                        (list1, list2) -> list1.addAll(list2)); // добавляем в список другой список

        filteredPhones3.forEach(s -> System.out.println(s));
    }

    /* Группировка
     * Чтобы сгруппировать данные по какому-нибудь признаку, нам надо использовать в связке метод collect()
     * объекта Stream и метод Collectors.groupingBy()*/
    private void exampleGroup() {
        /* у нас есть набор объектов java_8.Phone, которые мы хотим сгруппировать по компании
         * Итак, для создания групп в метод phoneStream.collect() передается вызов функции Collectors.groupingBy(),
         * которая с помощью выражения java_8.Phone::getCompany группирует объекты по компании. В итоге будет создан
         * объект Map, в котором ключами являются названия компаний, а значениями - список связанных с
         * компаниями телефонов*/
        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));
        Map<String, List<Phone>> phonesByCompany = phoneStream.collect(
                Collectors.groupingBy(Phone::getCompany));
        for (Map.Entry<String, List<Phone>> item : phonesByCompany.entrySet()) {
            System.out.println(item.getKey());
            for (Phone phone : item.getValue()) {
                System.out.println(phone.getName());
            }
            System.out.println();
        }

        /* Метод Collectors.partitioningBy() имеет похожее действие, только он делит элементы на группы по принципу,
         * соответствует ли элемент определенному условию
         * В данном случае с помощью условия p->p.getCompany()=="Apple" мы смотрим, принадлежит ли телефон компании Apple.
         * Если телефон принадлежит этой компании, то он попадает в одну группу, если нет, то в другую*/
        Stream<Phone> phoneStream1 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));
        Map<Boolean, List<Phone>> phonesByCompany1 = phoneStream1.collect(
                Collectors.partitioningBy(p -> p.getCompany() == "Apple"));
        for (Map.Entry<Boolean, List<Phone>> item : phonesByCompany1.entrySet()) {
            System.out.println(item.getKey());
            for (Phone phone : item.getValue()) {
                System.out.println(phone.getName());
            }
            System.out.println();
        }

        /* Метод Collectors.counting применяется в Collectors.groupingBy() для вычисления количества элементов в каждой группе*/
        Stream<Phone> phoneStream2 = Stream.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340)
        );
        Map<String, Long> phonesByCompany2 = phoneStream2.collect(
                Collectors.groupingBy(Phone::getCompany, Collectors.counting()));
        for (Map.Entry<String, Long> item : phonesByCompany2.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue());
        }

        /* Метод Collectors.summing применяется для подсчета суммы. В зависимости от типа данных,
         * к которым применяется метод, он имеет следующие формы: summingInt(), summingLong(), summingDouble().
         * Применим этот метод для подсчета стоимости всех смартфонов по компаниям
         * С помощью выражения Collectors.summingInt(java_8.Phone::getPrice)) мы указываем,
         * что для каждой компании будет вычислять совокупная цена всех ее смартфонов.
         * И поскольку вычисляется результат - сумма для значений типа int, то в качестве
         * типа возвращаемой коллекции используется тип Map<String, Integer>*/
        Stream<Phone> phoneStream3 = Stream.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340)
        );
        Map<String, Integer> phonesByCompany3 = phoneStream3.collect(
                Collectors.groupingBy(Phone::getCompany, Collectors.summingInt(Phone::getPrice)));
        for (Map.Entry<String, Integer> item : phonesByCompany3.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue());
        }

        /* Методы maxBy и minBy применяются для подсчета минимального и максимального значения в каждой группе.
         * В качестве параметра эти методы принимают функцию компаратора, которая нужна для сравнения значений.
         * Например, найдем для каждой компании телефон с минимальной ценой*/
        Stream<Phone> phoneStream4 = Stream.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340)
        );
        Map<String, Optional<Phone>> phonesByCompany4 = phoneStream4.collect(
                Collectors.groupingBy(Phone::getCompany,
                        Collectors.minBy(Comparator.comparing(Phone::getPrice))));
        for (Map.Entry<String, Optional<Phone>> item : phonesByCompany4.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().get().getName());
        }

        /* Методы summarizingInt() / summarizingLong() / summarizingDouble()
         * позволяют объединить в набор значения соответствующих типов
         * Метод Collectors.summarizingInt(java_8.Phone::getPrice)) создает набор,
         * в который помещаются цены для всех телефонов каждой из групп.
         * Данный набор инкапсулируется в объекте IntSummaryStatistics. Соответственно если бы мы применяли методы
         * summarizingLong() или summarizingDouble(), то соответственно бы получали объекты LongSummaryStatistics
         * или DoubleSummaryStatistics.
         * У этих объектов есть ряд методов, который позволяют выполнить различные атомарные операции над набором:
         * getAverage(): возвращает среднее значение
         * getCount(): возвращает количество элементов в наборе
         * getMax(): возвращает максимальное значение
         * getMin(): возвращает минимальное значение
         * getSum(): возвращает сумму элементов
         * accept(): добавляет в набор новый элемент
         * В данном случае мы получаем среднюю цену смартфонов для каждой группы*/
        Stream<Phone> phoneStream5 = Stream.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340)
        );
        Map<String, IntSummaryStatistics> priceSummary = phoneStream5.collect(
                Collectors.groupingBy(Phone::getCompany,
                        Collectors.summarizingInt(Phone::getPrice)));
        for (Map.Entry<String, IntSummaryStatistics> item : priceSummary.entrySet()) {
            System.out.println(item.getKey() + " - " + item.getValue().getAverage());
        }

        /* Метод mapping позволяет дополнительно обработать данные и задать функцию отображения
         * объектов из потока на какой-нибудь другой тип данных
         * Выражение Collectors.mapping(java_8.Phone::getName, Collectors.toList()) указывает,
         * что в группу будут выделятся названия смартфонов, причем группа будет представлять объект List*/
        Stream<Phone> phoneStream6 = Stream.of(
                new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340)
        );
        Map<String, List<String>> phonesByCompany6 = phoneStream6.collect(
                Collectors.groupingBy(Phone::getCompany,
                        Collectors.mapping(Phone::getName, Collectors.toList())));
        for (Map.Entry<String, List<String>> item : phonesByCompany6.entrySet()) {
            System.out.println(item.getKey());
            for (String name : item.getValue()) {
                System.out.println(name);
            }
        }
    }

    /* Параллельные потоки
     * Кроме последовательных потоков Stream API поддерживает параллельные потоки.
     * Распараллеливание потоков позволяет задействовать несколько ядер процессора (если целевая машина многоядерная)
     * и тем самым может повысить производительность и ускорить вычисления. В то же время говорить,
     * что применение параллельных потоков на многоядерных машинах однозначно повысит производительность -
     * не совсем корректно. В каждом конкретном случае надо проверять и тестировать.
     * Чтобы сделать обычный последовательный поток параллельным, надо вызвать у объекта Stream метод parallel.
     * Кроме того, можно также использовать метод parallelStream() интерфейса Collection для создания
     * параллельного потока из коллекции.
     * В то же время если рабочая машина не является многоядерной, то поток будет выполняться как последовательный.
     * Применение параллельных потоков во многих случаях будет аналогично*/
    private void exampleParalelStream() {
        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> result = numbersStream.parallel().reduce((x, y) -> x * y);
        System.out.println(result.get()); // 720

        /* Однако не все функции можно без ущерба для точности вычисления перенести с последовательных потоков на
         * параллельные. Прежде всего такие функции должны быть без сохранения состояния и ассоциативными,
         * то есть при выполнении слева направо давать тот же результат, что и при выполнении справа налево,
         * как в случае с произведением чисел. Например если
         * Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
         * String sentence = wordsStream.parallel().reduce("Результат:", (x,y)->x + " " + y);S
         * System.out.println(sentence); то вывод будет таким Результат: мама Результат: мыла Результат: раму
         *
         * Данный вывод не является правильным. Если же мы не уверены, что на каком-то этапе работы с параллельным
         * потоком он адекватно сможет выполнить какую-нибудь операцию, то мы можем преобразовать этот поток
         * в последовательный посредством вызова метода sequential()*/
        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму", "hello world");
        String sentence = wordsStream.parallel()
                .filter(s -> s.length() < 10) // фильтрация над параллельным потоком
                .sequential()
                .reduce("Результат:", (x, y) -> x + " " + y); // операция над последовательным потоком
        System.out.println(sentence);

        /* Упорядоченность в параллельных потоках
         * Как правило, элементы передаются в поток в том же порядке, в котором они определены в источнике данных.
         * При работе с параллельными потоками система сохраняет порядок следования элементов.
         * Исключение составляет метод forEach(), который может выводить элементы в произвольном порядке.
         * И чтобы сохранить порядок следования, необходимо применять метод forEachOrdered*/
        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9", "Huawei Nexus 6P",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI6",
                "ASUS Zenfone 3", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 2");
        phones.parallelStream()
                .sorted()
                .forEachOrdered(s -> System.out.println(s));

        /* Сохранение порядка в параллельных потоках увеличивает издержки при выполнении.
         * Но если нам порядок не важен, то мы можем отключить его сохранение и тем самым увеличить
         * производительность, использовав метод unordered*/
        List<String> phones1 = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9", "Huawei Nexus 6P",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI6",
                "ASUS Zenfone 3", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 2");
        phones1.parallelStream()
                .sorted()
                .unordered()
                .forEach(s -> System.out.println(s));
    }

    /* Параллельные операции над массивами
     * В JDK 8 к классу Arrays было добавлено ряд методов, которые позволяют в параллельном режиме
     * совершать обработку элементов массива. И хотя данные методы формально не входят в Stream API,
     * но реализуют схожую функциональность, что и параллельные потоки:
     * parallelPrefix(): вычисляет некоторое значение для элементов массива (например, сумму элементов)
     * parallelSetAll(): устанавливает элементы массива с помощью лямбда-выражения
     * parallelSort(): сортирует массив*/
    private void exampleParallelArrays() {
        /* Используем метод parallelSetAll() для установки элементов массива
         * В метод Arrays.parallelSetAll передается два параметра: изменяемый массив и функция,
         * которая устанавливает элементы массива. Эта функция перебирает все элементы и в качестве параметра
         * получает индекс текущего перебираемого элемента. Выражение i -> i*10 означает,
         * что по каждому индексу в массиве будет хранится число, равное i * 10*/
        int[] numbers = new int[10];
        Arrays.parallelSetAll(numbers, i -> i * 10);
        for (int i : numbers)
            System.out.println(i);

        /* На примере класса java_8.Phone
         * Теперь лямбда-выражение в методе Arrays.parallelSetAll представляет блок кода.
         * И так как лямбда-выражение должно возвращать объект, то нам надо явным образом использовать оператор return.
         * В этом лямбда-выражении опять же функция получает индексы перебираемых элементов, и по этим индексам мы
         * можем обратиться к элементам массива и их изменить. Конкретно в данном случае происходит уменьшение
         * цены смартфонов на 10000 единиц*/
        Phone[] phones = new Phone[]{new Phone("iPhone 8", "", 54000),
                new Phone("Pixel 2", "", 45000),
                new Phone("Samsung Galaxy S9", "", 40000),
                new Phone("Nokia 9", "", 32000)};

        Arrays.parallelSetAll(phones, i -> {
            phones[i].setPrice(phones[i].getPrice() - 10000);
            return phones[i];
        });

        for (Phone p : phones)
            System.out.printf("%s - %d \n", p.getName(), p.getPrice());

        /* Отсортируем массив чисел в параллельном режиме
         * Метод Arrays.parallelSort() в качестве параметра принимает массив и сортирует его по возрастанию*/
        int[] nums = {30, -4, 5, 29, 7, -8};
        Arrays.parallelSort(nums);
        for (int i : nums)
            System.out.println(i);


        /* Если же нам надо как-то по-другому отсортировать объекты, например, по модулю числа, или у нас
         * более сложные объекты, то мы можем создать свой компаратор и передать его в качестве второго параметра
         * в Arrays.parallelSort(). Например, возьмем выше определенный класс java_8.Phone и его копратор java_8.PhoneComparator*/
        Phone[] phones1 = new Phone[]{new Phone("iPhone 8", "", 54000),
                new Phone("Pixel 2", "", 45000),
                new Phone("Samsung Galaxy S9", "", 40000),
                new Phone("Nokia 9", "", 32000)};

        Arrays.parallelSort(phones1, new PhoneComparator());

        for (Phone p : phones1)
            System.out.println(p.getName());

        /* Метод parallelPrefix() походит для тех случаев, когда надо получить элемент массива или объект
         * того же типа, что и элементы массива, который обладает некоторыми признаками.
         * Например, в массиве чисел это может быть максимальное, минимальное значения и т.д.
         * Например, найдем произведение чисел
         *
         * То есть, лямбда-выражение из Arrays.parallelPrefix, которое представляет бинарную функцию,
         * получает два элемента и выполняет над ними операцию. Результат операции сохраняется и передается
         * в следующий вызов бинарной функции*/
        int[] numbers1 = {1, 2, 3, 4, 5, 6};
        Arrays.parallelPrefix(numbers1, (x, y) -> x * y);
        for (int i : numbers1)
            System.out.println(i);
    }


    public void example() {
        System.out.println("Пример общего примения Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        examplePrimitiveTypeStream();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример создания Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleCreateStream();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример отображения Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleFilterForDisplay();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример сортировки Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleSort();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример методов skip and limit");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleMethod();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример пограничной навигации Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleBorderNavigation();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример операций сведения Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleInformationOperations();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример метода reduce Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleReduce();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример типа Optional Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        examleOptional();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример метода collect Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exapleCollect();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример группировки Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleGroup();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример параллельных потоков Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleParalelStream();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Пример параллельных операций над массивами Stream");
        System.out.println("-------------------------------------------------------------------------------------------");
        exampleParallelArrays();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println();
    }

}

class Phone {

    private String name;
    private String company;
    private int price;

    public Phone(String name, String comp, int price) {
        this.name = name;
        this.company = comp;
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

    public static int compare(Phone p1, Phone p2) {
        if (p1.getPrice() > p2.getPrice())
            return 1;
        return -1;
    }
}

class PhoneComparator implements Comparator<Phone> {

    public int compare(Phone a, Phone b) {

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}
