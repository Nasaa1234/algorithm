package BD3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.*;

class Edge {
    int to;
    int weight;
    Edge(int to, int weight) { this.to = to; this.weight = weight; }
}

class Graph {
    int V;
    List<Edge>[] adj;
    double[][] pos;
    String[] labels;

    @SuppressWarnings("unchecked")
    Graph(int V, String[] labels) {
        this.V = V;
        this.labels = labels;
        adj = new ArrayList[V];
        pos = new double[V][2];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
    }

    void addEdge(int u, int v, int w) {
        adj[u].add(new Edge(v, w));
        adj[v].add(new Edge(u, w));
    }

    void setPos(int node, double x, double y) {
        pos[node][0] = x;
        pos[node][1] = y;
    }
}

public class Main extends Application {

    private Graph g;
    private static final int START_NODE = 0; // Node 'A'

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        g = createSampleGraph();
        int[] prev = dijkstra(g, START_NODE);

        Canvas canvas = new Canvas(650, 450);
        draw(canvas.getGraphicsContext2D(), g, prev);

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Dijkstra Shortest Path Visualizer (JavaFX)");
        stage.show();
    }


    Graph createSampleGraph() {
        String[] labels = {"A", "B", "C", "D", "E"};
        Graph g = new Graph(5, labels);

        g.addEdge(0, 1, 7); // A-B (7)
        g.addEdge(0, 4, 1); // A-E (1)
        g.addEdge(1, 2, 3); // B-C (3)
        g.addEdge(1, 4, 8); // B-E (8)
        g.addEdge(2, 4, 12); // C-E (2)
        g.addEdge(2, 3, 6); // C-D (6)
        g.addEdge(4, 3, 7); // E-D (7)

        g.setPos(0, 150, 250); // A
        g.setPos(1, 250, 150); // B
        g.setPos(2, 450, 150); // C
        g.setPos(3, 550, 250); // D
        g.setPos(4, 350, 350); // E

        return g;
    }


    int[] dijkstra(Graph g, int src) {
        int[] dist = new int[g.V];
        int[] prev = new int[g.V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(src);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            if (dist[u] == Integer.MAX_VALUE) continue;

            for (Edge e : g.adj[u]) {
                if (dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    prev[e.to] = u;
                    pq.add(e.to);
                }
            }
        }

        System.out.println("Dijkstra Results (Start Node: " + g.labels[src] + "):");
        for(int i = 0; i < g.V; i++) {
            String distance = dist[i] == Integer.MAX_VALUE ? "INF" : String.valueOf(dist[i]);
            String predecessor = prev[i] == -1 ? "-" : g.labels[prev[i]];
            System.out.printf("Node %s: Dist=%s, Prev=%s%n", g.labels[i], distance, predecessor);
        }

        return prev;
    }


    void draw(GraphicsContext gc, Graph g, int[] prev) {
        gc.setLineWidth(2);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        final double NODE_RADIUS = 20;

        gc.setStroke(Color.GRAY.darker());
        gc.setFill(Color.BLACK);

        for (int u = 0; u < g.V; u++) {
            for (Edge e : g.adj[u]) {
                int v = e.to;
                if (u < v) {
                    double x1 = g.pos[u][0], y1 = g.pos[u][1];
                    double x2 = g.pos[v][0], y2 = g.pos[v][1];

                    gc.strokeLine(x1, y1, x2, y2);

                    gc.fillText(String.valueOf(e.weight), (x1 + x2) / 2 + 5, (y1 + y2) / 2 - 5);
                }
            }
        }

        gc.setStroke(Color.RED);
        gc.setLineWidth(4);

        for (int v = 0; v < g.V; v++) {
            int p = prev[v];
            if (p != -1) {
                double x1 = g.pos[v][0], y1 = g.pos[v][1];
                double x2 = g.pos[p][0], y2 = g.pos[p][1];
                gc.strokeLine(x1, y1, x2, y2);
            }
        }

        for (int i = 0; i < g.V; i++) {
            double x = g.pos[i][0];
            double y = g.pos[i][1];

            if (i == START_NODE) {
                gc.setFill(Color.GOLD);
            } else {
                gc.setFill(Color.LIGHTBLUE);
            }

            gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

            gc.setStroke(Color.DARKBLUE);
            gc.setLineWidth(2);
            gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
            gc.fillText(g.labels[i], x - 5, y + 6);
        }
    }
}