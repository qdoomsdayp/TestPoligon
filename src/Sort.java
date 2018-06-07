import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.*;


/* Если добавлять сортировку то нужно в названии метода пометить ее словом Sort или sort, в других методах очень желательно чтобы такого слова не было*/
public class Sort {
    public HashMap<String, Integer[]> hashMap;
    public HashMap<String, Class[]> nameParametersType;

    /* Сортировка выбором:
     * По очереди будем просматривать все подмножества
     * элементов массива. Предполагаем, что первый элемент (в каждом
     * подмножестве элементов) является минимальным
     * В оставшейся части подмножества ищем элемент,
     * который меньше предположенного минимума.Если находим, запоминаем его индекс.
     * Если нашелся элемент, меньший, чем на текущей позиции,
     * меняем их местами O(n^2)*/
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int min_i = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
    }

    /* Сортировка пузырьком:
     * Алгоритм проходит массив от начала и до конца, сравнивая попарно соседние элементы,
     * Если элементы стоят в неправильном порядке, то они меняются местами, таким образом,
     * после первого прохода на конце массива оказывается максимальный элемент (для сортировки по возрастанию).
     * Затем проход массива повторяется, и на предпоследнем месте оказывается другой наибольший после максимального элемент и т.д.
     * В итоге, наименьший элемент постепенно перемещается  к началу массива («всплывает» до нужной позиции как пузырёк в воде). O(n^2) */
    public void bubbleSortEnd(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /*
     * + модификация если пришел сортированный массив, то не сортируем его*/
    public void bubbleSort(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /* Сортировка с помощью стандартного пакета utils, по возрастанию */
    public void sortArraysCollectionsAsc(int[] arr) {
        Arrays.sort(arr);
    }

    /* по убыванию*/
    public void sortArraysCollectionsDesc(int[] arr) {
        Integer arr1[] = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        Arrays.sort(arr1, Collections.reverseOrder());
    }

    /* Быстрая сортировка:
     * Выбрать опорный элемент из массива. Обычно опорным элементом является средний элемент.
     * Разделить массив на два подмассива: элементы, меньше опорного и элементы, больше опорного.
     * Рекурсивно применить сортировку к двум подмассивам. O(nlog(n)) */
    public void quickSort(int[] arr, int low, int high) {
        if (arr.length == 0)
            return;
        if (low >= high)
            return;
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = arr[middle];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < opora) {
                i++;
            }
            while (arr[j] > opora) {
                j--;
            }
            if (i <= j) {//меняем местами
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

    /* Глупая сортировка:
     * Сортировка производится от начала массива. Текущий элемент сравнивается со следующим,
     * ЕСЛИ следующий меньше, ТО эти элементы меняются местами и возвращаемся в начало массива.
     * Сортировка закончена, когда будет пройден весь массив до конца, и не будет сделано ни одной перестановки элементов. */
    public void stupidSort(int[] arr) {
        int i = 0, tmp;
        while (i < arr.length - 1) {
            if (arr[i + 1] < arr[i]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                i = 0;
            } else i++;
        }
    }

    /*Шейкерная сортировка:
     * Она же сортировка перемешиванием, она же коктейльная сортировка.
     * Начинается процесс как в «пузырьке»: выдавливаем максимум на самые задворки.
     * После этого разворачиваемся на 1800 и идём в обратную сторону, при этом уже перекатывая в начало не максимум, а минимум.
     * Отсортировав в массиве первый и последний элементы, снова делаем кульбит.
     * Обойдя туда-обратно несколько раз, в итоге заканчиваем процесс, оказавшись в середине списка.
     * Вообщем двунаправленный пузырек */
    public void shakerSort(int[] arr) {
        int b = 0;
        int left = 0;//Левая граница
        int right = arr.length - 1;//Правая граница
        while (left < right) {
            for (int i = left; i < right; i++)//Слева направо...
            {
                if (arr[i] > arr[i + 1]) {
                    b = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = b;
                    b = i;
                }
            }
            right = b;//Сохраним последнюю перестановку как границу
            if (left >= right) break;//Если границы сошлись выходим
            for (int i = right; i > left; i--)//Справа налево...
            {
                if (arr[i - 1] > arr[i]) {
                    b = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = b;
                    b = i;
                }
            }
            left = b;//Сохраним последнюю перестановку как границу
        }
    }


    public void initArrays(int n, HashMap<String, Class[]> s) {
        hashMap = new HashMap<>();
        Random r = new Random();
        for (String name : s.keySet()) {
            Integer[] temp = new Integer[n];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = r.nextInt(n*(i+1)/3+1);
            }
            hashMap.put(name, temp);
        }


    }

    public HashMap<String, Class[]> namesMethodsClass() {
        Method[] methods = Sort.class.getMethods();
        nameParametersType = new HashMap<>();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().contains("sort") || methods[i].getName().contains("Sort")) {
                nameParametersType.put(methods[i].getName(), methods[i].getParameterTypes());
            }
        }
        return nameParametersType;
    }

    public void example(int n, Sort s) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String, Long> timeMethods = new HashMap<>();
        initArrays(n, namesMethodsClass());
        long start = 0, finish = 0;
        for (String name : nameParametersType.keySet()) {
            Integer[] temp = hashMap.get(name);
            int[] tempArr = Arrays.stream(temp).mapToInt(Integer::intValue).toArray();
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println(Arrays.toString(tempArr));

            Method method = getClass().getMethod(name, nameParametersType.get(name));
            start = System.currentTimeMillis();
            if (name.equals("quickSort")) {
                method.invoke(s, tempArr, 0, tempArr.length - 1);
            } else {
                method.invoke(s, tempArr);
            }
            finish = System.currentTimeMillis();

            long timeMethod = finish - start;
            timeMethods.put(name, timeMethod);
            System.out.println("Mass " + name + ": " + Arrays.toString(tempArr));

        }
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Map.Entry entry : timeMethods.entrySet()) {
            System.out.println("Time " + entry.getKey() + ": " + entry.getValue());
        }

    }


}
