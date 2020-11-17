import java.util.LinkedList;
import java.util.TreeMap;
public class PathFinder<K extends Comparable<K>> {
    Graph<K> graph;
    TreeMap<K, Boolean> marked;
    public PathFinder(Graph<K> graph){
        this.graph = graph;
        marked = new TreeMap<K, Boolean>();
    }
    public  LinkedList<K> getPath(K from, K to){
        TreeMap<K,K> fatherVertex = new TreeMap<K,K>();
        for(K s : graph.vertices()){
            marked.put(s, false);
        }
        if(!_getPath(fatherVertex, from, to))
           return null;
        LinkedList<K> path = new LinkedList<K>();
        path.addFirst(to);
        K current = to;
        while(fatherVertex.get(current) != null){
            path.addFirst(fatherVertex.get(current));
            current = fatherVertex.get(current);
        }
        return path;
    }
    private boolean _getPath(TreeMap<K,K> fatherVertex, K from, K to){
        LinkedList<K> queue = new LinkedList<K>();
        boolean isConnected = false;
        fatherVertex.put(from,null);
        queue.offer(from);
        while(!queue.isEmpty()){
            K current = queue.poll();
            if(current.compareTo(to) == 0){
                isConnected = true;
                break;
            }
            marked.put(current, true);
            for(K vertex : graph.adjacentTo(current))
                if(!marked.get(vertex)){
                    queue.offer(vertex);
                    if(fatherVertex.get(vertex) == null) fatherVertex.put(vertex,current);
                }
        }
        return isConnected;
    }
    public ST<K,SET<K>> getAllPath(K from, K to){
        ST<K,SET<K>> fatherVertexes = new ST<K,SET<K>>();
        for(K s : graph.vertices()){
            marked.put(s, false);
        }
        if(!_getAllPath(fatherVertexes, from, to)) return null;
        return fatherVertexes;
    }

    private boolean _getAllPath(ST<K, SET<K>> fatherVertexes, K from, K to) {
        LinkedList<K> queue = new LinkedList<K>();
        boolean isConnected = false;
        fatherVertexes.put(from , null);
        queue.offer(from);
        while(!queue.isEmpty()){
            //目前还没有写代码
        }
        return false;
    }
    
}
