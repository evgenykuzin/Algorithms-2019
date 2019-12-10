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
     */

    private static HashMap<String, String> storage = new HashMap<>();

    private static ArrayList<Character> find_(String f, String s, ArrayList<Character> chars) {
        String first = f;
        String second = s;
        if (f.length() > s.length()) {
            first = s;
            second = f;
        }
        System.out.println("first = " + first);
        System.out.println("second = " + second);
        for (Character ch1 : first.toCharArray()) {
            for (Character ch2 : second.toCharArray()) {
                if (ch1.equals(ch2)) {
                    System.out.println("ch = " + ch1);
                    second = second.substring(second.indexOf(ch2) + 1);
                    first = first.substring(first.indexOf(ch1) + 1);
                    System.out.println("first cut = " + first);
                    System.out.println("second cut = " + second);
                    chars.add(ch1);
                    return find_(first, second, chars);
                }
            }
        }
        System.out.println("result = " + chars);
        return chars;
    }

    private static ArrayList<Character> find(String f, String s, ArrayList<Character> chars, ArrayList<ArrayList<Character>> variants) {
        String first = f;
        String second = s;
        System.out.println("first = " + first);
        System.out.println("second = " + second);
        for (Character ch1 : first.toCharArray()) {
            for (Character ch2 : second.toCharArray()) {
                if (ch1.equals(ch2)) {
                    System.out.println("ch = " + ch1);
                    second = second.substring(second.indexOf(ch2) + 1);
                    first = first.substring(first.indexOf(ch1) + 1);
                    System.out.println("first cut = " + first);
                    System.out.println("second cut = " + second);
                    chars.add(ch1);
                    ArrayList<Character> variant = new ArrayList<>(chars);
                    find(first, second, variant, variants);
                }
            }
        }
        variants.add(chars);
        System.out.println("result = " + chars);
        return chars;
    }

    private static ArrayList<Character> find(String first, String second) {
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<ArrayList<Character>> variants = new ArrayList<>();
        ArrayList<Character> result = new ArrayList<>();
        find(first, second, chars, variants);
        int maxSize = 0;
        for (ArrayList<Character> variant : variants) {
            if (variant.size() > maxSize) {
                result = variant;
                maxSize = variant.size();
            }
        }
        return result;
    }

    public static String longestCommonSubSequence(String first, String second) {
        System.out.println("start");
        ArrayList<Character> sequence1 = find(first, second);
        ArrayList<Character> sequence2 = find(second, first);
        ArrayList<Character> sequence;
        sequence = sequence1.size() > sequence2.size() ? sequence1 : sequence2;
        StringBuilder result = new StringBuilder();
        for (Character s : sequence) {
            result.append(s);
        }
        return result.toString();
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
     */

    static class Field {
        int width;
        int height;
        int[][] cells;
        ArrayList<ArrayList<Integer>> lists;
        int y;
        int x;

        public Field() {
            y = 0;
            x = 0;
            cells = new int[10][10];
            lists = new ArrayList<>();
        }

        public Field(int height, int width) {
            this.width = width;
            this.height = height;
            y = 0;
            x = 0;
            cells = new int[height][width];
            lists = new ArrayList<>();
        }

        void addLine(String line) {
            String[] digits = line.split(" ");
            height = y + 1;
            width = digits.length;
            ArrayList<Integer> l = new ArrayList<>();
            for (String d : digits) {
                //cells[y][x] = Integer.valueOf(d);
                l.add(Integer.valueOf(d));
                x++;
            }
            lists.add(l);
            width = l.size();
            height = lists.size();
            y++;
            x = 0;
        }

        void setCells(){
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    cells[i][j] = lists.get(i).get(j);
                }
            }
        }

        int get(int y, int x) {
             return cells[y][x];
        }

        void goToStart(){
            y = 0;
            x = 0;
        }

        void up(int value) {
            cells[y][x - 1] = value;
            x++;
        }

        void left(int value) {
            cells[y-1][x] = value;
            y++;
        }

        void current(int value) {
            cells[y][x] = value;
            y++;
        }

        void set(int y, int x, int value) {
            cells[y][x] = value;
        }

        int getLast() {
            return lists.get(height - 1).get(width - 1);
            //return cells[height - 1][width - 1];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{\n\n");
            for (ArrayList<Integer> line : lists) {
                boolean n = false;
                for (int d : line) {
                    sb.append(String.valueOf(d)).append(" ");
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

    static HashMap<int[][], int[][]> matrixStorage = new HashMap<>();

    public static int shortestPathOnField(String inputName) {
        if (storage.containsKey(inputName)) return Integer.valueOf(storage.get(inputName));
        Field inputField = loadField(inputName);
        Field resultField = new Field(inputField.height, inputField.width);
        int height = inputField.height;
        int width = inputField.width;
        if (matrixStorage.containsKey(inputField.cells))
            return matrixStorage.get(inputField.cells)[height - 1][width - 1];
        int[][] field = new int[height][width];
        resultField.set(0, 0, inputField.get(0, 0));
        field[0][0] = inputField.get(0, 0);
        for (int i = 1; i < height; i++) {
            //int v1 = resultField.get(i - 1, 0);
            int v1 = field[i - 1][0];
            int v2 = inputField.get(i, 0);
            resultField.set(i, 0, v1 + v2);
            field[i][0] = v1 + v2;
        }
        for (int i = 1; i < width; i++) {
            //int v1 = resultField.get(0, i - 1);
            int v1 = field[0][i - 1];
            int v2 = inputField.get(0, i);
            resultField.set(0, i, v1 + v2);
            field[0][i] = v1 + v2;
        }
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                //int v1 = resultField.get(i - 1, j);
                //int v2 = resultField.get(i, j - 1);
                //int v3 = resultField.get(i - 1, j - 1) + inputField.get(i, j);
                int v1 = field[i - 1][j] + inputField.get(i, j);
                int v2 = field[i][j - 1] + inputField.get(i, j);
                int v3 = field[i - 1][j - 1] + inputField.get(i, j);
                int value = Math.min(Math.min(v1, v2), v3);
                value = min(v1, v2, v3);
                resultField.set(i, j, value);
                field[i][j] = value;
            }
        }
        int result = field[height - 1][width - 1];
        storage.put(inputName, String.valueOf(result));
        matrixStorage.put(inputField.cells, field);
        return result;
        //return resultField.getLast();
    }

    private static int min(int v1, int v2, int v3) {
        int min = v1 + v2 + v3;
        if (v1 >= v2 && v3 >= v2) {
            min = v2;
        }
        if (v2 >= v1 && v3 >= v1) {
            min = v1;
        }
        if (v1 >= v3 && v2 >= v3) {
            min = v3;
        }
        return min;
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
