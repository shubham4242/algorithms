
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topological Sort with Cycle.
 */
public class TopologicalSort {
    public List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> links) {
        List<Integer> sortOrder = new ArrayList<>();
        boolean[] visited = new boolean[jobs.size() + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(graph, links, jobs);
        for (int i = 0; i < jobs.size(); i++) {
            if (!visited[jobs.get(i)]) {
                if (DFS(jobs.get(i), sortOrder, graph, visited,
                        new boolean[jobs.size() + 1]))
                    return new ArrayList<>();
            }
        }
        reverse(sortOrder);
        return sortOrder;
    }

    public void reverse(List<Integer> sortOrder) {
        int i = 0;
        int j = sortOrder.size() - 1;
        while (i < j) {
            int temp = sortOrder.get(i);
            sortOrder.set(i, sortOrder.get(j));
            sortOrder.set(j, temp);
            i++;
            j--;
        }
    }

    public void buildGraph(Map<Integer, List<Integer>> graph, List<Integer[]> deps,
                           List<Integer> jobs) {
        for (int i = 0; i < jobs.size(); i++)
            graph.put(jobs.get(i), new ArrayList<>());
        for (int i = 0; i < deps.size(); i++) {
            graph.get(deps.get(i)[0]).add(deps.get(i)[1]);
        }
    }

    public boolean DFS(int node, List<Integer> stack,
                       Map<Integer, List<Integer>> graph, boolean[] visited,
                       boolean[] containCycle) {
        if (containCycle[node])
            return true;
        if (visited[node])
            return false;
        containCycle[node] = true;
        visited[node] = true;
        boolean result = false;
        for (Integer n : graph.get(node)) {
            result = result || DFS(n, stack, graph, visited, containCycle);
        }
        containCycle[node] = false;
        stack.add(node);
        return result;
    }

}
