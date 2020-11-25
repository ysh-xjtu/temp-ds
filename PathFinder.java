import java.util.LinkedList;
import java.util.TreeMap;

public class PathFinder<K extends Comparable<K>> {
    Graph<K> graph;
    TreeMap<K, Boolean> marked;
    int shortestLength = 0; //使用BFS的方式，记录所求两个单词之间的最短词梯的路径长度
    int currentLength = 0;

    public PathFinder(Graph<K> graph) {
        this.graph = graph;
        marked = new TreeMap<K, Boolean>();
    }

    public LinkedList<K> getPath(K from, K to) {
        TreeMap<K, K> fatherVertex = new TreeMap<K, K>();
        for (K s : graph.vertices()) {
            marked.put(s, false);
        }
        shortestLength = 0;
        if (!_getPath(fatherVertex, from, to))
            return null;
        LinkedList<K> path = new LinkedList<K>();
        path.addFirst(to);
        K current = to;
        while (fatherVertex.get(current) != null) {
            path.addFirst(fatherVertex.get(current));
            current = fatherVertex.get(current);
            shortestLength++;
        }
        return path;
    }
    public int getShortestLength(K from, K to){
        getPath(from, to);
        return shortestLength;
    }
    private boolean _getPath(TreeMap<K, K> fatherVertex, K from, K to) {
        LinkedList<K> queue = new LinkedList<K>();
        boolean isConnected = false;
        fatherVertex.put(from, null);
        queue.offer(from);
        while (!queue.isEmpty()) {
            K current = queue.poll();
            if (current.compareTo(to) == 0) {
                isConnected = true;
                break;
            }
            marked.put(current, true);
            for (K vertex : graph.adjacentTo(current))
                if (!marked.get(vertex)) {
                    queue.offer(vertex);
                    if (fatherVertex.get(vertex) == null)
                        fatherVertex.put(vertex, current);
                }
        }
        return isConnected;
    }

    public LinkedList<LinkedList<K>> getAllPath(K from, K to) {
        LinkedList<LinkedList<K>> paths = new LinkedList<LinkedList<K>>();
        for (K s : graph.vertices())
            marked.put(s, false);
        if (!_getAllPath(paths, from, to))
            return null;
        return paths;
    }

    private boolean _getAllPath(LinkedList<LinkedList<K>> paths, K from, K to){
        TreeMap<K, K> fatherVertex = new TreeMap<K, K>();
        //通过调用_getPath方法，借助于BFS能够确认两个单词之间的最短路径长度,将该长度记录在成员变量：shortestLength中，同时也可以确定是否有路径可达
        if(getPath(from, to) == null) return false;
        for (K s : graph.vertices())
            marked.put(s, false);
        //currentLength用来记录使用DFS进行路径探索过程中当前的路径长度
        currentLength = 0;
        _getAllPathDFS(paths, fatherVertex, from, to);
        return true;
    }

    private void _getAllPathDFS(LinkedList<LinkedList<K>> paths, TreeMap<K, K> fatherVertex, K current, K to) {
        marked.put(current, true);
        if (current.compareTo(to) == 0) {
            LinkedList<K> path = new LinkedList<K>();
            path.addFirst(to);
            K cur = to;
            while (fatherVertex.get(cur) != null) {
                path.addFirst(fatherVertex.get(cur));
                cur = fatherVertex.get(cur);
            }
            paths.addFirst(path);
            return;
        }
        for (K vertex : graph.adjacentTo(current)) {
            if(!marked.get(vertex))
            {
                fatherVertex.put(vertex, current);
                currentLength++;
                //使用currentLength和shortestLength的比较，凡是超过最短路径的词链均忽略
                if(currentLength <= shortestLength) _getAllPathDFS(paths, fatherVertex, vertex, to);  
                currentLength--;
                marked.put(vertex, false);  
            }          
        }
    }
}

