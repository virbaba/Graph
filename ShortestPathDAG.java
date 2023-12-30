public static int[] shortestPathInDAG(int n, int m, int[][] edges) {
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            adjList.get(from).add(new int[]{to, weight});
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();

        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[0] = 0;

        for (int vertex = 0; vertex < n; vertex++) {
            if (!visited[vertex]) {
                DFSTraversal(vertex, visited, st, adjList);
            }
        }

        while (!st.isEmpty()) {
            int vertex = st.pop();
            if (shortestPath[vertex] != Integer.MAX_VALUE) {
                ArrayList<int[]> neighboursList = adjList.get(vertex);
                if (neighboursList != null) {
                    for (int[] neighbour : neighboursList) {
                        int dest = neighbour[0];
                        int weight = neighbour[1];
                        int total = shortestPath[vertex] + weight;
                        shortestPath[dest] = Math.min(shortestPath[dest], total);
                    }
                }
            }
        }

        // Convert unreachable nodes (-1) to the specified output format (3 -> -1)
        for (int i = 0; i < n; i++) {
            if (shortestPath[i] == Integer.MAX_VALUE) {
                shortestPath[i] = -1;
            }
        }

        return shortestPath;
    }

    public static void DFSTraversal(int vertex, boolean[] visited, Stack<Integer> st,
            ArrayList<ArrayList<int[]>> adjList) {

        visited[vertex] = true;

        ArrayList<int[]> neighboursList = adjList.get(vertex);
        if (neighboursList != null) {
            for (int[] neighbour : neighboursList) {
                int dest = neighbour[0];
                if (!visited[dest]) {
                    DFSTraversal(dest, visited, st, adjList);
                }
            }
        }
        st.push(vertex);
    }
