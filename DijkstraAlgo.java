import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraAlgo {
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        int[] dist = new int[edges.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[edges.length];
        dist[start] = 0;
        visited[start] = true;
        PriorityQueue<int[]> edgesPQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });
        edgesPQ.add(new int[]{start, 0});
        while (!edgesPQ.isEmpty()) {
            int node[] = edgesPQ.poll();
            visited[node[0]] = true;
            for (int i = 0; i < edges[node[0]].length; i++) {
                int nextNode = edges[node[0]][i][0];
                int value = edges[node[0]][i][1];
                if (visited[nextNode] || (dist[node[0]] + value) > dist[nextNode])
                    continue;
                dist[nextNode] = dist[node[0]] + value;
                edgesPQ.add(new int[]{nextNode, dist[nextNode]});
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
        return dist;
    }
}
