package BD3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class DijkstraTest {

    @Test
    void testAddEdge() {
        String[] labels = {"A", "B", "C"};
        Graph g = new Graph(3, labels);

        g.addEdge(0, 1, 5);

        assertEquals(1, g.adj[0].get(0).to);
        assertEquals(5, g.adj[0].get(0).weight);

        assertEquals(0, g.adj[1].get(0).to);
        assertEquals(5, g.adj[1].get(0).weight);
    }

    @Test
    void testSetNodePosition() {
        String[] labels = {"A"};
        Graph g = new Graph(1, labels);

        g.setPos(0, 150.0, 250.0);

        assertEquals(150.0, g.pos[0][0]);
        assertEquals(250.0, g.pos[0][1]);
    }



    private int[] computeDistByPrevChain(Graph g, int[] prev, int src) {
        int n = g.V;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int v = 0; v < n; v++) {
            if (v == src) continue;

            int cur = v;
            int total = 0;
            boolean reachable = true;

            while (cur != src) {
                int p = prev[cur];
                if (p == -1) {
                    reachable = false;
                    break;
                }

                boolean found = false;
                for (Edge e : g.adj[p]) {
                    if (e.to == cur) {
                        total += e.weight;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    reachable = false;
                    break;
                }

                cur = p;
            }

            dist[v] = reachable ? total : Integer.MAX_VALUE;
        }

        return dist;
    }
}
