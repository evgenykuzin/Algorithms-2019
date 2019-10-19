package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;
import utils.TaskUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    //НЕ РАБОТАЕТ
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        int[] costs = TaskUtils.readIntegers(inputName, 100);
        int maxDiff = costs[1] - costs[0];
        Pair<Integer, Integer> pair = new Pair<>(costs[1], costs[0]);
        for (int i = 0; i < costs.length - 1; i++) {
            int first = costs[i];
            int second = costs[i + 1];
            if (second - first > maxDiff) {
                maxDiff = second - first;
                System.out.println(second + " " + first);
                pair = new Pair<>(i + 1, i + 2);
            }
        }
        return pair;
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        String result = "";
        int i = 0;
        int j = 0;
        int max = 0;
        while (i < first.length() && j < first.length()) {
            if (!second.contains(first.substring(i, j))) {
                while (!second.contains(first.substring(i, j)) && j < first.length()) {
                    i++;
                    j++;
                }
            } else {
                while (second.contains(first.substring(i, j))) {
                    int len = first.substring(i, j).length();
                    if (len > max) {
                        max = len;
                        result = first.substring(i, j);
                    }
                    j++;
                }
            }
        }
        return result;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    //НЕ РАБОТАЕТ
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        String[][] matrix = new String[1000][1000];
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
                    String[] raw = string.split(" ");
                    for (int i = 0; i < raw.length; i++) {
                        matrix[indx][i] = raw[i];
                        size++;
                    }
                }
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> result = new HashSet<>();
        for (String word : words) {
            StringBuilder reached = new StringBuilder();
            if (containsWord(reached, matrix, word.split(""), 0, 0, 0)){
                result.add(word);
            }
        }
        return result;
    }

    private static boolean containsWord(StringBuilder reached, String[][] matrix, String[] word, int indx, int jndx, int kndx) {
        for (int i = indx; i < matrix.length; i++) {

            for (int j = jndx; j < matrix.length; j++) {
                if (matrix[i][j].equals(word[kndx])) {
                    reached.append(word[kndx]);
                    if (Arrays.equals(reached.toString().split(""), word)){
                        return true;
                    }
                    return containsWord(reached, matrix, word, i, j, kndx+1);
                }
            }
        }
        return false;
    }


}
