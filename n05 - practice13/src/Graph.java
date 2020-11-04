import java.util.ArrayList;
import java.util.Scanner;

/* Вариант 4
        Постановка задачи
        Составить программу нахождения кратчайших путей в графе заданным
        методом.
        Выбрать и реализовать способ представления графа в памяти.

        Предусмотреть ввод с клавиатуры произвольного графа.
        Разработать доступный способ (форму) вывода результирующего дерева на
        экран монитора.
        Провести тестовый прогон программы для заданного графа в соответствии
        с индивидуальным заданием
        Метод
        14.4.4 Беллмана-Форда
        Граф для тестового прогона
        14.4.6
*/
// Изначально при запуске программы работа идёт с графом из варианта. Чтобы поменять на ввод графа вручную, нужно
// удалить аргумент из конструктора в main
public class Graph {
    //список рёбер
    private ArrayList<Edge> ed;
    //количество вершин и рёбер
    private int n, m;
    private final static int INF = 100000;

    //метод нахождения кратчайшего пути
    public void bellman_ford(int n, int s){
        //список расстояний
        ArrayList<Integer> result = new ArrayList<>(n);
        for (int i=0; i<n; i++) result.add(INF);
        result.set(s, 0);

        for(int i = 0; i < m; i++){
            for(Edge e: ed){
                if(result.get(e.u) + e.w < result.get(e.v)) result.set(e.v, result.get(e.u) + e.w);
            }
        }

        System.out.println("Список кратчайших путей:");
        for (int i=0; i<n; i++) if (result.get(i) == INF) System.out.println(s+1 + " -> " + (i+1) + " = NOT");
        else System.out.println(s+1 + " -> " + (i+1) + " = " + result.get(i));
    }

    //создание графа, предусматривающее ввод с клавиатуры
    public Graph(){
        int w;
        Scanner sn = new Scanner(System.in);
        System.out.println("Количество вершин: "); n = sn.nextInt();
        ed = new ArrayList<>(n);
        m=0;
        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Вес " + (i + 1) + " -> " + (j + 1) + " > ");
                w = sn.nextInt();
                if (w != 0) {
                    ed.add(new Edge(i, j, w));
                    m++;
                }
            }
        }
    }
    // для тестового прогона графа 14.4.6
    public Graph(int n){
        this.n = 8;
        ed = new ArrayList<>(8);
        ed.add(new Edge(0, 1, 23));
        ed.add(new Edge(0, 2, 12));
        ed.add(new Edge(1, 2, 25));
        ed.add(new Edge(1, 4, 22));
        ed.add(new Edge(1, 7, 35));
        ed.add(new Edge(2, 3, 18));
        ed.add(new Edge(3, 5, 20));
        ed.add(new Edge(4, 5, 23));
        ed.add(new Edge(4, 6, 14));
        ed.add(new Edge(5, 6, 24));
        ed.add(new Edge(6, 7, 16));

        ed.add(new Edge(1, 0, 23));
        ed.add(new Edge(2, 0, 12));
        ed.add(new Edge(2, 1, 25));
        ed.add(new Edge(4, 1, 22));
        ed.add(new Edge(7, 1, 35));
        ed.add(new Edge(3, 2, 18));
        ed.add(new Edge(5, 3, 20));
        ed.add(new Edge(5, 4, 23));
        ed.add(new Edge(6, 4, 14));
        ed.add(new Edge(6, 5, 24));
        ed.add(new Edge(7, 6, 16));
        m = 22;
    }

    // от того, направленный граф или нет, будет зависеть визуализация
    //если граф не направленный, удаляются лишние рёбра
    public void toDotVisualization(boolean directed){
        if(!directed) {
            ArrayList<Edge> ed2 = new ArrayList<>(n);
            boolean flag = false;
            for(Edge e1: ed){
                if(!ed2.isEmpty()){
                    for(Edge e2: ed2){
                        if(e1.u == e2.v && e1.v == e2.u && e1.w == e2.w){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) ed2.add(e1);
                    else flag = false;
                }
                else ed2.add(e1);
            }
            StringBuilder line = new StringBuilder("graph G {\n");
            for (Edge e : ed2) {
                line.append(e.u + 1).append(" -- ").append(e.v + 1).append(" [label=").append(e.w).append("];\n");
            }
            line.append("}");
            System.out.println(line.toString());
        }
        else
        {
            StringBuilder line = new StringBuilder("digraph G {\n");
            for (Edge e : ed) {
                line.append(e.u + 1).append(" -> ").append(e.v + 1).append(" [label=").append(e.w).append("];\n");
            }
            line.append("}");
            System.out.println(line.toString());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph gr = new Graph(8);
        System.out.println("Стартовая вершина > ");
        gr.bellman_ford(gr.n, sc.nextInt()-1);
        System.out.println("Граф на языке DOT: ");
        gr.toDotVisualization(false);
    }
}
