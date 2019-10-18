package utils;

import java.io.*;
import java.util.Arrays;

public class TaskUtils {
    private static String[] read(File file, int size){
        String[] array = new String[size];
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = null;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) array[indx] = string;
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
    public static String[] readFile(File file, int size){
        return read(file, size);
    }
    public static String[] readFile(String path, int size){
        File file = new File(path);
        return read(file, size);
    }
    public static String[] readFile(File file){
        return read(file, 100);
    }
    public static String[] readFile(String path){
        File file = new File(path);
        return read(file, 100);
    }

    public static int[] readIntegers(String path, int size){
        int[] array = new int[size];
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = null;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null && string != "") array[indx] = Integer.parseInt(string);
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static double[] readDoubles(String path, int size){
        double[] array = new double[size];
        File file = new File(path);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = null;
        try {
            int indx = 0;
            do {
                string = bufferedReader.readLine();
                if (string != null) array[indx] = Double.parseDouble(string);
                indx++;
            } while (string != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

}
