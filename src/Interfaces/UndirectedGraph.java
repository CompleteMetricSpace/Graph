package Interfaces;

import java.util.List;

public interface UndirectedGraph<T extends Comparable<T>, L extends Comparable<L>> extends Graph<T, L>
{
    public List<? extends GraphEdge<T, L>> getEdges();
    
    default public boolean hasEdgeFromTo(GraphEdge<T, L> e)
    {
	return hasEdge(e.getVertices().get(0), e.getVertices().get(1));
    };
    
    public void addEdge(GraphEdge<T, L> e);
    public void addEdge(List<GraphEdge<T, L>> e);
    
    public void addNode(GraphNode<T> e);
    public void addNode(List<GraphNode<T>> e);
    
    public void removeEdge(GraphEdge<T, L> e);
    public void removeEdge(List<GraphEdge<T, L>> e);
    
    public void removeNode(GraphNode<T> e);
    public void removeNode(List<GraphNode<T>> e);
    
}
