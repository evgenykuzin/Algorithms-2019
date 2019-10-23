package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private static boolean less(Comparable o1, Comparable o2){
        if (o1 == null && o2 == null) return false;
        if (o1 == null) return true;
        if (o2 == null) return false;
        return  (o1.compareTo(o2) < 0);
    }

    private static boolean more(Comparable o1, Comparable o2){
        if (o1 == null && o2 == null) return false;
        if (o1 == null) return false;
        if (o2 == null) return true;
        return  (o1.compareTo(o2) > 0);
    }

    private static int partition(Comparable[] elements, int min, int max) {
        Comparable x = elements[min + random.nextInt(max - min + 1)];
        int left = min;
        int right = max;
        while (left <= right) {

                    while (less(elements[left], x)) {
                        left++;
                    }

                    while (more(elements[right], x)) {
                        right--;

                    }

            if (left <= right) {
                    Comparable temp = elements[left];
                    elements[left] = elements[right];
                    elements[right] = temp;
                    left++;
                    right--;

            }
        }

        return right;
    }

    private static void quickSort(Comparable[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    public static void quickSort(Comparable[] elements) {
        quickSort(elements, 0, elements.length - 1);
    }


    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    //Асимтотическая сложность :
    //quickSort() - O(n*log(n));
    //чтение + запись = O(2n);
    //в итоге: O(2n + n*log(n))
    static public void sortTimes(String inputName, String outputName) throws FileNotFoundException {
        Date[] array = new Date[1000];
        int size = 0;
        FileReader fileReader;
        fileReader = new FileReader(inputName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    Date time;
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                        time = sdf.parse(string);
                    } catch (ParseException pe) {
                        throw new IllegalArgumentException("wrong format of time!");
                    }
                    if (time != null) {
                        array[size] = time;
                        size++;
                    }
                }
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (size == 0) throw new IllegalArgumentException("empty file!");
        quickSort(array);
        try {
            FileWriter fileWriter = new FileWriter(outputName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = array.length - size; i < array.length; i++) {
                if (array[i] != null) {
                    bufferedWriter.write(getFormatTime(array[i])+"\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getFormatTime(Date time) {
        String flag = "AM";
        int h = time.getHours();
        int m = time.getMinutes();
        int s = time.getSeconds();
        if (h >= 12 && h != 24) {
            h -= 12;
            flag = "PM";
        }
        if (h == 0) h = 12;
        String ss = s < 10 ? "0" + s : "" + s;
        String sm = m < 10 ? "0" + m : "" + m;
        String sh = h < 10 ? "0" + h : "" + h;
        return sh + ":" + sm + ":" + ss + " " + flag;
    }

    private static boolean isPM(String time) {
        return time.split(" ")[1].equals("PM");
    }

    private static boolean isAM(String time) {
        return time.split(" ")[1].equals("AM");
    }


    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    //Асимтотическая сложность :
    //quickSort() - O(n*log(n));
    //чтение + запись = O(2n);
    //в итоге: O(2n + n*log(n))
    static public void sortTemperatures(String inputName, String outputName) {
        ArrayList<Double> arrayList = new ArrayList<>();
        Double[] array;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        try {
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    arrayList.add(Double.valueOf(string));
                }
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        array = arrayList.toArray(new Double[0]);
        quickSort(array);
        try {
            FileWriter fileWriter = new FileWriter(outputName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (double value : array) {
                bufferedWriter.write(value + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    //Асимтотическая сложность :
    //maxCountIndex() = O(maxValue);
    //чтение + запись = O(2n);
    //определение частоты числа + перестановка чисел в конец = O(2n);
    //в итоге: O(2n + 2n + maxValue)
    static public void sortSequence(String inputName, String outputName) {
        int maxValue = Integer.MAX_VALUE / 20;
        Integer[] array;
        ArrayList<Integer> arrayList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        try {
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    arrayList.add(Integer.valueOf(string));
                }
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        array = arrayList.toArray(new Integer[0]);
        int[] count = new int[maxValue];
        maxValue = 0;
        for (int value : array) {
            count[value]++;
            if (value > maxValue) maxValue = value;
        }
        int maxCountValue = maxCountIndex(count);
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != maxCountValue) {
                if (j < i) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
                j++;
            }
        }
        writeToFile(outputName, array);
    }

    private static void writeToFile(String fileName, Integer[] arr) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                if (num != 0) {
                    sb.append(num);
                    sb.append("\n");
                }
            }
            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int maxCountIndex(int[] count) {
        int maxValue = 0;
        int indx = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxValue) {
                maxValue = count[i];
                indx = i;
            }
        }
        return indx;
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
