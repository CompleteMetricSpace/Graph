package Helper;

import java.util.ArrayList;
import java.util.List;

import Interfaces.GraphEdge;
import Interfaces.GraphNode;

public class GUEdge<T extends Comparable<T>, L extends Comparable<L>> implements GraphEdge<T, L>
{

    GraphNode<T> u, v;
    L weight;

    public GUEdge(GraphNode<T> u, GraphNode<T> v, L weight)
    {
	this.u = u;
	this.v = v;
	this.weight = weight;
    }

    @Override
    public List<GraphNode<T>> getVertices()
    {
	List<GraphNode<T>> list = new ArrayList<GraphNode<T>>(2);
	list.add(u);
	list.add(v);
	return list;
    }

    @Override
    public L getWeight()
    {
	return weight;
    }

    public boolean equals(Object o)
    {
	if(!(o instanceof GUEdge))
	    return false;
	GUEdge b = (GUEdge) o;
	return ((this.u.equals(b.u) && this.v.equals(b.v)) || (this.u.equals(b.v) && this.v
		.equals(b.u))) && this.weight.equals(b.weight);
    }
}
