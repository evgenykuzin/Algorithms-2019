package lesson1;

import kotlin.NotImplementedError;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {

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

    static public void sortTimes(String inputName, String outputName) {
        int[] array = new int[1000];
        int size = 0;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                    try {
                        sdf.parse(string);
                    } catch (ParseException pe) {
                        throw new IllegalArgumentException("wrong format of time!");
                    }
                    String[] timeArray = string.
                            replaceAll(" AM", "").
                            replaceAll(" PM", "").
                            split(":");
                    if (Integer.parseInt(timeArray[0]) != 12 && isPM(string)) {
                        timeArray[0] = Integer.parseInt(timeArray[0]) + 12 + "";
                    }
                    if (Integer.parseInt(timeArray[0]) == 12 && isAM(string)) {
                        timeArray[0] = "0";
                    }
                    array[indx] = Integer.parseInt(timeArray[0]) * 3600 +
                            Integer.parseInt(timeArray[1]) * 60 +
                            Integer.parseInt(timeArray[2]);
                    size++;
                }
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (size == 0) throw new IllegalArgumentException("empty file!");
        array = Arrays.copyOf(array, size);
        Sorts.quickSort(array);
        try {
            FileWriter fileWriter = new FileWriter(outputName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = array.length - size; i < array.length; i++) {
                bufferedWriter.write(getFormatTime(array[i]) + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getFormatTime(int seconds) {
        String flag = "AM";
        int h = seconds / 3600;
        int m = seconds % 3600 / 60;
        int s = seconds % 60;
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
    //НЕ РАБОТАЕТ
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
//        FileReader fileReader = null;
//        try {
//            fileReader = new FileReader(inputName);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String string;
//        String[] array = new String[100000];
//        int size = 0;
//        HashMap<String, String> map = new HashMap<>();
//        try {
//            int indx = 0;
//            do {
//                string = bufferedReader.readLine();
//                if (string != null) {
//                    String[] addressArray = string.split(" ");
//                    int value = 0;
//                    String street = addressArray[3] + " " + addressArray[4];
////                    for (int n : street.chars().toArray()) {
////                        value*=100;
////                        value += n;
////                    }
//                    //String number = addressArray[4];
//                    String name = addressArray[0] + " " + addressArray[1];
//                    //array[indx] = value + number;
//                    array[indx] = street;
//                    if (map.containsKey(array[indx])) {
//                        String previousNames = map.get(array[indx]);
//                        StringBuilder names = new StringBuilder(previousNames + ", " + name);
//                        String[] namesArr = names.toString().split(", ");
//                        alphabetSort(namesArr);
//                        names = new StringBuilder(namesArr[0]);
//                        for (int i = 1; i < namesArr.length; i++) {
//                            names.append(", ").append(namesArr[i]);
//                        }
//                        map.replace(array[indx], names.toString());
//                        array[indx] = null;
//                    } else {
//                        map.put(array[indx], name);
//                    }
//                    size++;
//                }
//                indx++;
//            } while (string != null);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        array = Arrays.copyOf(array, size);
//        quickAlphabetSort(array);
//        try {
//            FileWriter fileWriter = new FileWriter(outputName);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for (int i = array.length - size; i < array.length; i++) {
//                if (array[i] != null) {
//                    bufferedWriter.write(array[i] + " - " + map.get(array[i]) + "\n");
//                }
//            }
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void alphabetSort(String[] arrayWords) {
        for (int j = 0; j < arrayWords.length; j++) {
            for (int i = j + 1; i < arrayWords.length; i++) {
                if (arrayWords[i] != null && arrayWords[j] != null) {
                    int home1;
                    int home2;
                    try {
                        home1 = Integer.valueOf(arrayWords[i].split(" ")[1]);
                        home2 = Integer.valueOf(arrayWords[j].split(" ")[1]);
                    } catch (NumberFormatException nfe) {
                        home1 = 0;
                        home2 = 0;
                    }
                    boolean firstLessSecond = arrayWords[i].compareTo(arrayWords[j]) < 0;
                    boolean streetsEquals = arrayWords[i].split(" ")[0].
                            equals(arrayWords[j].split(" ")[0]);
                    if (firstLessSecond || streetsEquals && home1 < home2) {
                        String k = arrayWords[j];
                        arrayWords[j] = arrayWords[i];
                        arrayWords[i] = k;
                    }
                }
            }
        }
    }

    private static boolean less(String s1, String s2) {
        int home1;
        int home2;
        try {
            home1 = Integer.valueOf(s1.split(" ")[1]);
            home2 = Integer.valueOf(s2.split(" ")[1]);
        } catch (NumberFormatException nfe) {
            home1 = 0;
            home2 = 0;
        }
        return s1.compareTo(s2) < 0 || s1.split(" ")[0].
                equals(s2.split(" ")[0]) && home1 < home2;
    }

    private static boolean more(String s1, String s2) {
        return !less(s1, s2) && !s1.equals(s2);
    }

    private static void quickSort(String[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    public static void quickAlphabetSort(String[] elements) {
        quickSort(elements, 0, elements.length - 1);
    }

    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    private static int partition(String[] elements, int min, int max) {
        String x = elements[min + random.nextInt(max - min + 1)];
        int left = min, right = max;
        while (left <= right) {
            if (x != null) {
                boolean less = false;
                System.out.println(elements[left]);
                if (elements[left] != null) {
                    less = less(elements[left], x);
                }
                while (less) {
                    left++;
                    if (elements[left] != null) {
                        less = less(elements[left], x);
                    }
                }
                boolean more = false;
                System.out.println(elements[right]);
                if (elements[right] != null) {
                    more = more(elements[right], x);
                }
                while (more) {
                    if (elements[right] != null) {
                        right--;
                        more = more(elements[right], x);
                    }
                }
                if (left <= right) {
                    if (elements[left] != null && elements[right] != null) {
                        String temp = elements[left];
                        elements[left] = elements[right];
                        elements[right] = temp;
                    }
                    left++;
                    right--;
                }
            }
        }
        return right;
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
    static public void sortTemperatures(String inputName, String outputName) {
        int[] array = new int[10000000];
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        int size = 0;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    array[indx] = Integer.valueOf(
                            String.valueOf(
                                    Double.parseDouble(
                                            string) * 10).replace(".0", ""));
                    size++;
                }
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        array = Arrays.copyOf(array, size);
        Sorts.heapSort(array);
        try {
            FileWriter fileWriter = new FileWriter(outputName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < array.length; i++) {
                double outLine = Double.parseDouble(array[i] + "") / 10;
                bufferedWriter.write(outLine + "\n");
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
    static public void sortSequence(String inputName, String outputName) {
        int[] array = new int[10000000];
        int size = 0;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) {
                    array[indx] = Integer.parseInt(string);
                    size++;
                }
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        array = Arrays.copyOf(array, size);
        int[] count = new int[100000000];
        for (int value : array) {
            count[value]++;
        }
        int maxCountValue = maxCountIndex(count);
        int j = 0;
        boolean trigger = true;
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

    private static void writeToFile(String fileName, int[] arr) {
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
