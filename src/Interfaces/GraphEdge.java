package Interfaces;
import java.util.List;


public interface GraphEdge<T, L extends Comparable<L>>
{
    public List<GraphNode<T>> getVertices();
    public L getWeight();
}
