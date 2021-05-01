import java.util.*;

class KosarajuAlgo {
    Stack<Integer> traversedNode;
    int numberOfNode;
    Map<Integer, Set<Integer>> reverseGraph;

    public KosarajuAlgo(int n) {
        traversedNode = new Stack<>();
        numberOfNode = n;
        reverseGraph = new HashMap<>();
    }

    public List<List<Integer>> stronglyConnectedComponent(Map<Integer, Set<Integer>> graph) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[numberOfNode];
        for (int i = 0; i < numberOfNode; i++)
            DFS(graph, i, visited);
        reverseGraph(graph);
        Arrays.fill(visited, false);
        while (!traversedNode.isEmpty()) {
            int node = traversedNode.pop();
            if (!visited[node]) {
                List<Integer> scc = new ArrayList<>();
                reverseDFS(reverseGraph, node, visited, scc);
                result.add(new ArrayList<>(scc));
            }
        }
        return result;
    }

    public void reverseDFS(Map<Integer, Set<Integer>> graph, int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        for (Integer nextNode : graph.get(node))
            if (!visited[nextNode])
                reverseDFS(graph, nextNode, visited, result);
        result.add(node);
    }

    public void reverseGraph(Map<Integer, Set<Integer>> graph) {
        for (int i = 0; i < numberOfNode; i++)
            reverseGraph.put(i, new HashSet<>());
        for (Map.Entry<Integer, Set<Integer>> node : graph.entrySet()) {
            for (Integer value : node.getValue())
                reverseGraph.get(value).add(node.getKey());
        }
    }

    public void DFS(Map<Integer, Set<Integer>> graph, int node, boolean[] visited) {
        if (visited[node])
            return;
        visited[node] = true;
        for (Integer nextNode : graph.get(node))
            if (!visited[nextNode])
                DFS(graph, nextNode, visited);
        traversedNode.push(node);
    }
}
