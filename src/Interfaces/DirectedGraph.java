package Interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public interface DirectedGraph<T extends Comparable<T>, L extends Comparable<L>> extends Graph<T, L>
{
    public List<DirectedGraphEdge<T, L>> getEdges();
       
    default public boolean hasEdgeFromTo(DirectedGraphEdge<T, L> e)
    {
	return hasEdge(e.getStart(), e.getDestination());
    };
    
    public int getInDegree(GraphNode<T> e);
    public List<DirectedGraphEdge<T, L>> getIncomingEdges(GraphNode<T> e);
   
    public void addEdge(DirectedGraphEdge<T, L> e);
    public void addEdge(List<DirectedGraphEdge<T, L>> e);
    
    public void addNode(GraphNode<T> e);
    public void addNode(List<GraphNode<T>> e);
    
    public void removeEdge(DirectedGraphEdge<T, L> e);
    public void removeEdge(List<DirectedGraphEdge<T, L>> e);
    
    public void removeNode(GraphNode<T> e);
    public void removeNode(List<GraphNode<T>> e);
    
}
