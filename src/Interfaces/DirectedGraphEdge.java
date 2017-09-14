package Interfaces;

public interface DirectedGraphEdge<T extends Comparable<T>, L extends Comparable<L>> extends GraphEdge<T, L>
{
    public GraphNode<T> getStart();
    public GraphNode<T> getDestination();
    public DirectedGraphEdge<T, L> getReverseEdge();
}
