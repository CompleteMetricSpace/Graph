package Helper;

import java.util.ArrayList;
import java.util.List;

import Interfaces.DirectedGraphEdge;
import Interfaces.GraphNode;

public class GEdge<T extends Comparable<T>, L extends Comparable<L>> implements DirectedGraphEdge<T, L>
{
    GraphNode<T> start, end;
    L weight;
    
    public GEdge(GraphNode<T> start, GraphNode<T> end, L weight)
    {
	this.start = start;
	this.end = end;
	this.weight = weight;
    }
    
    @Override
    public List<GraphNode<T>> getVertices()
    {
	List<GraphNode<T>> list = new ArrayList<GraphNode<T>>();
	list.add(start);
	list.add(end);
	return list;
    }

    @Override
    public L getWeight()
    {
	return weight;
    }

    @Override
    public GraphNode<T> getStart()
    {
	return start;
    }

    @Override
    public GraphNode<T> getDestination()
    {
	return end;
    }

    @Override
    public DirectedGraphEdge<T, L> getReverseEdge()
    {
	return new GEdge<T, L>(end, start, weight);
    }

}
