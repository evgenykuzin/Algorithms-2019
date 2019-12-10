package lesson7;

import lesson5.Graph;
import lesson5.Path;
import lesson6.knapsack.Fill;
import lesson6.knapsack.Item;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

// Примечание: в этом уроке достаточно решить одну задачу
@SuppressWarnings("unused")
public class JavaHeuristicsTasks {

    /**
     * Решить задачу о ранце (см. урок 6) любым эвристическим методом
     * <p>
     * Очень сложная
     * <p>
     * load - общая вместимость ранца, items - список предметов
     * <p>
     * Используйте parameters для передачи дополнительных параметров алгоритма
     * (не забудьте изменить тесты так, чтобы они передавали эти параметры)
     */
    public static Fill fillKnapsackHeuristics(int load, List<Item> items, Object... parameters) {
        String s_param = (String) parameters[0];
        int i_param = (int) parameters[1];
        Fill f_param = (Fill) parameters[2];

        System.out.println(s_param + " -> " + f_param.getCost()*i_param);
        return new Fill(0);
    }

    public static void main(String[] args) {
        fillKnapsackHeuristics(10, new ArrayList<>(), "parampampam", 123, new Fill(10, new HashSet<>()));
    }

    /**
     * Решить задачу коммивояжёра (см. урок 5) методом колонии муравьёв
     * или любым другим эвристическим методом, кроме генетического и имитации отжига
     * (этими двумя методами задача уже решена в под-пакетах annealing & genetic).
     * <p>
     * Очень сложная
     * <p>
     * Граф передаётся через параметр graph
     * <p>
     * Используйте parameters для передачи дополнительных параметров алгоритма
     * (не забудьте изменить тесты так, чтобы они передавали эти параметры)
     */
    public static Path findVoyagingPathHeuristics(Graph graph, Object... parameters) {
        return null;
    }
}
