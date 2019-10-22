package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        throw new NotImplementedError();
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
    //Асимтотическая сложность : O(first.length); || O(n);
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
    //Асимтотическая сложность : O((n-3)/2 + (n-3-sqrt(n))/2);
    static public int calcPrimesNumber(int limit) {
        int primesCount = 1;
        if (limit < 2) return 0;
        for (int n = 3; n <= limit; n += 2) {
            boolean prime = true;
            for (int del = 3; del <= Math.sqrt(n); del += 2) {
                if (n % del == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primesCount++;
            }
        }
        return primesCount;
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
            if (containsWord(matrix, word)) {
                result.add(word);
            }
        }
        return result;
    }

    private static boolean containsWord(String[][] matrix, String word) {
        return containsWord(matrix, word.split(""), 0, 0, 0, 0);
    }

    private static boolean inPath(int indx, int jndx) {
        return lockPath.contains(new Pair<>(indx, jndx));
    }

    private static ArrayList<Pair<Integer, Integer>> lockPath = new ArrayList<>();
    private static ArrayList<Pair<Integer, Integer>> unlock = new ArrayList<>();

    private static boolean containsWord(String[][] matrix, String[] word, int indx, int jndx, int kndx, int lockDir) {
        boolean equals = false;
        if (kndx + 1 < word.length) {
            if (indx + 1 < matrix.length) {
                if (matrix[indx + 1][jndx] != null) {
                    equals = matrix[indx + 1][jndx].equals(word[kndx + 1]);
                }
            }
            if (equals && !inPath(indx + 1, jndx)) {
                lockPath.add(new Pair<>(indx + 1, jndx));
                lockPath.removeAll(unlock);
                return containsWord(matrix, word, indx + 1, jndx, kndx + 1, 1);
            } else {
                equals = false;
                if (indx - 1 >= 0) {
                    if (matrix[indx - 1][jndx] != null) {
                        equals = matrix[indx - 1][jndx].equals(word[kndx + 1]);
                    }
                }
                if (equals && !inPath(indx - 1, jndx)) {
                    lockPath.add(new Pair<>(indx - 1, jndx));
                    lockPath.removeAll(unlock);
                    return containsWord(matrix, word, indx - 1, jndx, kndx + 1, -1);
                } else {
                    equals = false;
                    if (jndx + 1 < matrix.length) {
                        if (matrix[indx][jndx + 1] != null) {
                            equals = matrix[indx][jndx + 1].equals(word[kndx + 1]);
                        }
                    }
                    if (equals && !inPath(indx, jndx + 1)) {
                        lockPath.add(new Pair<>(indx, jndx + 1));
                        lockPath.removeAll(unlock);
                        return containsWord(matrix, word, indx, jndx + 1, kndx + 1, 2);
                    } else {
                        equals = false;
                        if (jndx - 1 >= 0) {
                            if (matrix[indx][jndx - 1] != null) {
                                equals = matrix[indx][jndx - 1].equals(word[kndx + 1]);
                            }
                        }
                        if (equals && !inPath(indx, jndx - 1)) {
                            lockPath.add(new Pair<>(indx, jndx - 1));
                            lockPath.removeAll(unlock);
                            return containsWord(matrix, word, indx, jndx - 1, kndx + 1, -2);
                        } else {
                            if (kndx - 1 >= 0) kndx--;
                            if (lockPath.isEmpty() || kndx == 0) {

                                if (jndx + 1 < matrix.length) {
                                    jndx++;
                                } else if (indx + 1 < matrix.length) {
                                    lockPath = new ArrayList<>();
                                    indx++;
                                    jndx = 0;
                                }
                            } else {
                                //unlock.add(new Pair<>(indx, jndx));
                                indx = lockPath.get(kndx).getFirst();
                                jndx = lockPath.get(kndx).getSecond();
                            }
                            if (indx > matrix.length && jndx > matrix.length || matrix[indx][jndx] == null) {
                                lockPath = new ArrayList<>();
                                return false;
                            }
                            return containsWord(matrix, word, indx, jndx, kndx, 0);
                        }
                    }
                }
            }
        } else {
            lockPath = new ArrayList<>();
            return true;
        }
        //System.out.println("return false2");
        //return false;
    }
}
