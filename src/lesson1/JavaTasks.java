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
    //Асимтотическая сложность :
    //quickSort() - O(n*log(n));
    //чтение + запись = O(2n);
    //в итоге: O(2n + n*log(n))
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
    //heapSort() - O(n*log(n));
    //чтение + запись = O(2n);
    //в итоге: O(2n + n*log(n))
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
    //Асимтотическая сложность :
    //maxCountIndex() = O(maxValue);
    //чтение + запись = O(2n);
    //определение частоты числа + перестановка чисел в конец = O(2n);
    //в итоге: O(2n + 2n + maxValue)

    static public void sortSequence(String inputName, String outputName) {
        int maxValue = 100000000;
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
        int[] count = new int[maxValue];
        maxValue = 0;
        for (int value : array) {
            if (value<count.length) {
                if (value > maxValue) maxValue = value;
                count[value]++;
            }
        }
        count = Arrays.copyOf(count, maxValue);
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
