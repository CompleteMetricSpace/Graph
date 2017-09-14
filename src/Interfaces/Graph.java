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
    public List<GraphNode<T>> getVertices();
    public List<? extends GraphEdge<T, L>> getEdges();
    public int getNumberOfVertices();
    public int getNumberOfEdges();
    
    default public boolean hasEdge(GraphNode<T> s, GraphNode<T> e)
    {
	return getWeight(s, e) != null;
    };
    
    public L getWeight(GraphNode<T> s, GraphNode<T> e);
    
    public List<GraphNode<T>> getAdjacent(GraphNode<T> n);
    
}
