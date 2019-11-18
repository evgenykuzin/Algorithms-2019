package lesson5;

import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        System.out.println("start");
       List<Graph.Edge> eulerLoop = new ArrayList<>();
        Graph.Vertex first = null;
        Graph.Edge lastEd = null;
        int n = 0;
        int edgeCount = graph.getEdges().size();
        while (n < edgeCount) {
            for (Graph.Edge edge : graph.getEdges()) {
                if (first == null) {
                    first = edge.getBegin();
                }
                System.out.println(edge);
                if (!eulerLoop.contains(edge) && (consistent(edge, lastEd) || lastEd == null)) {
                    System.out.println("added -> "+edge);
                    lastEd = edge;
                    eulerLoop.add(edge);

                }
            }
            n++;
        }
       if (lastEd != null) {
           if (lastEd.getEnd().equals(first)) return eulerLoop;
       }
       return new ArrayList<>();
    }

    private static boolean consistent(Graph.Edge e1, Graph.Edge e2){
        if (e1 == null || e2 == null) return false;
        return e1.getBegin().equals(e2.getEnd()) ||
                e1.getEnd().equals(e2.getBegin()) ; //||
//                (!e1.equals(e2) &&
//                        (e1.getEnd().equals(e2.getEnd()) ||
//                        e1.getBegin().equals(e2.getBegin())));
    }

    public static void main(String[] args) {
        GraphBuilder gb = new GraphBuilder();
        gb.addConnection(new GraphBuilder.VertexImpl("A"), new GraphBuilder.VertexImpl("B"), 1);
        gb.addConnection(new GraphBuilder.VertexImpl("B"), new GraphBuilder.VertexImpl("C"), 1);
        gb.addConnection(new GraphBuilder.VertexImpl("C"), new GraphBuilder.VertexImpl("D"), 1);
        gb.addConnection(new GraphBuilder.VertexImpl("D"), new GraphBuilder.VertexImpl("A"), 1);
        gb.addConnection(new GraphBuilder.VertexImpl("A"), new GraphBuilder.VertexImpl("F"), 1);
        gb.addConnection(new GraphBuilder.VertexImpl("E"), new GraphBuilder.VertexImpl("R"), 1);

        Graph graph = gb.build();
        System.out.println(findEulerLoop(graph));
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }
}
