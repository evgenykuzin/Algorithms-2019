package lesson5;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     *
     * Трудоемкость алгоритма = O(E+V)
     * Ресурсоемкость алгоритма = O(E)
     *
     */

    static class Loop extends ArrayList<Graph.Edge> {
        private Graph.Edge start;
        private Graph.Edge end;
        private int requiredSize;

        Loop(int requiredSize) {
            this.requiredSize = requiredSize;
        }

        @Override
        public boolean add(Graph.Edge o) {
            if (start == null) {
                start = o;
            }
            end = o;
            if (!contains(o)) return super.add(o);
            return false;
        }

        boolean isEulerLoop() {
            return requiredSize == size() && isConnected();
        }

        private boolean isConnected() {
            return start.getEnd().equals(end.getEnd()) ||
                    start.getBegin().equals(end.getBegin()) ||
                    start.getEnd().equals(end.getBegin()) ||
                    start.getBegin().equals(end.getEnd());
        }
    }

    private static boolean findLoop(Graph.Vertex vertex, Graph graph, Loop loop) {
        if (loop.isEulerLoop()) return true;
        Set<Graph.Vertex> neighbors = graph.getNeighbors(vertex);
        for (Graph.Vertex neighbor : neighbors) {
            Graph.Edge connection = graph.getConnection(vertex, neighbor);
            if (loop.contains(connection) && connection != null) continue;
            loop.add(connection);
            if (findLoop(neighbor, graph, loop)) return true;
        }
        if (!loop.isEmpty()) loop.remove(loop.size() - 1);
        return false;
    }

    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        Set<Graph.Vertex> vertices = graph.getVertices();
        Graph.Vertex first;
        if (vertices.isEmpty()) return new ArrayList<>();
        first = (Graph.Vertex) vertices.toArray()[0];
        Loop loop = new Loop(graph.getEdges().size());
        findLoop(first, graph, loop);
        if (loop.size() < 3) return new ArrayList<>();
        return loop;
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     * G    H
     * |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        // throw new NotImplementedError();
        return null;
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     * <p>
     * Дан граф без циклов (получатель), например
     * <p>
     * G -- H -- J
     * |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     * <p>
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     * <p>
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     * <p>
     * В данном случае ответ (A, E, F, D, G, J)
     * <p>
     * Если на входе граф с циклами, бросить IllegalArgumentException
     * <p>
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        // throw new NotImplementedError();
        return null;
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     * <p>
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        // throw new NotImplementedError();
        return null;
    }
}
