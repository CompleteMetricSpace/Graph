package Interfaces;

import java.util.List;

/**
 * Very raw Graph interface
 * 
 * @author KhAKhA
 *
 * @param <T> Type of element
 * @param <L> Type of weight of edges
 */
public interface Graph<T, L extends Comparable<L>>
{
    /**
     * @return a list of vertices
     */
    public List<GraphNode<T>> getVertices();
    
    /**
     * @return a list of edges
     */
    public List<? extends GraphEdge<T, L>> getEdges();
    
    /**
     * @return number of vertices
     */
    public int getNumberOfVertices();
    
    /**
     * @return number of edges
     */
    public int getNumberOfEdges();
    
    /**
     * @param s a node 
     * @param e a node
     * @return <tt>true<tt> if there is an edge from <tt>s<tt> to <tt>e<tt>, <tt>false<tt> otherwise
     */
    default public boolean hasEdge(GraphNode<T> s, GraphNode<T> e)
    {
	return getWeight(s, e) != null;
    };
    
    /**
     * @param s a node
     * @param e a node
     * @return the weight of the edge (s,e) if (s,e) exists, null otherwise
     */
    public L getWeight(GraphNode<T> s, GraphNode<T> e);
    
    /**
     * @param n a node
     * @return list of all adjacent nodes to node n
     */
    public List<GraphNode<T>> getAdjacent(GraphNode<T> n);
    
}
