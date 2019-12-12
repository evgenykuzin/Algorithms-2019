package lesson6;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     *
     *
     * трудоемкость = O(m * n)
     * ресурсоемкость = O(m * n)
     *
     *
     *
     *
     */


    private static HashMap<String, String> storage = new HashMap<>();

    public static String longestCommonSubSequence(String first, String second) {
        int length1 = first.length()+1;
        int length2 = second.length()+1;
        StringBuilder sequence = new StringBuilder();
        int[][] matrix = new int[length1][length2];
        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                char ch1 = first.charAt(i-1);
                char ch2 = second.charAt(j-1);
                if (ch1 == ch2) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }
        length1--;
        length2--;
        while (length1 > 0 && length2 > 0) {
            char ch1 = first.charAt(length1 - 1);
            char ch2 = second.charAt(length2 - 1);
            if (ch1 == ch2) {
                sequence.append(ch1);
                length1--;
                length2--;
            } else if (matrix[length1 - 1][length2] >= matrix[length1][length2 - 1]) {
                length1--;
            } else {
                length2--;
            }
        }
        return sequence.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        return null;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     *
     * Трудоемкость = O(h * w)
     * Ресурсоемкость = O(h * w)
     *
     */

    static class Field {
        int width;
        int height;
        int[][] cells;
        ArrayList<ArrayList<Integer>> lists;

        Field() {
            cells = new int[10][10];
            lists = new ArrayList<>();
        }

        Field(int height, int width) {
            this.width = width;
            this.height = height;
            cells = new int[height][width];
            lists = new ArrayList<>();
        }

        void addLine(String line) {
            String[] digits = line.split(" ");
            height++;
            width = digits.length;
            ArrayList<Integer> l = new ArrayList<>();
            for (String d : digits) {
                l.add(Integer.valueOf(d));
            }
            lists.add(l);
        }

        void setCells() {
            cells = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    cells[i][j] = lists.get(i).get(j);
                }
            }
        }

        int get(int y, int x) {
            return cells[y][x];
        }

        void set(int y, int x, int value) {
            cells[y][x] = value;
        }

        int getResult() {
            return cells[height - 1][width - 1];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{\n\n");
            for (ArrayList<Integer> line : lists) {
                boolean n = false;
                for (int d : line) {
                    sb.append(d).append(" ");
                    n = true;
                }
                if (n) sb.append("\n");
            }
            sb.append("\n}");
            return sb.toString();
        }
    }

    private static Field loadField(String inputName) {
        Field field = new Field();
        File file = new File(inputName);
        BufferedReader reader = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader(inputName);
            reader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            do {
                if (reader == null) break;
                line = reader.readLine();
                if (line != null) {
                    field.addLine(line);
                }
            } while (line != null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return field;
    }

    private static HashMap<int[][], int[][]> matrixStorage = new HashMap<>();

    public static int shortestPathOnField(String inputName) {
        if (storage.containsKey(inputName)) return Integer.valueOf(storage.get(inputName));
        Field inputField = loadField(inputName);
        Field resultField = new Field(inputField.height, inputField.width);
        int height = inputField.height;
        int width = inputField.width;
        if (matrixStorage.containsKey(inputField.cells))
            return matrixStorage.get(inputField.cells)[height - 1][width - 1];
        inputField.setCells();
        resultField.set(0, 0, inputField.get(0, 0));
        for (int i = 1; i < height; i++) {
            int v1 = resultField.get(i - 1, 0);
            int v2 = inputField.get(i, 0);
            resultField.set(i, 0, v1 + v2);
        }
        for (int i = 1; i < width; i++) {
            int v1 = resultField.get(0, i - 1);
            int v2 = inputField.get(0, i);
            resultField.set(0, i, v1 + v2);
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                int v1 = resultField.get(i - 1, j) + inputField.get(i, j);
                int v2 = resultField.get(i, j - 1) + inputField.get(i, j);
                int v3 = resultField.get(i - 1, j - 1) + inputField.get(i, j);
                int value = Math.min(Math.min(v1, v2), v3);
                resultField.set(i, j, value);
            }
        }
        int result = resultField.getResult();
        storage.put(inputName, String.valueOf(result));
        matrixStorage.put(inputField.cells, resultField.cells);
        return result;
    }


    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
