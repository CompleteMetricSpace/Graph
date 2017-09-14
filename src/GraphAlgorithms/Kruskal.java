package GraphAlgorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Helper.Pair;
import Interfaces.Graph;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;

public class Kruskal
{
    private Kruskal()
    {

    }

    public static <T extends Comparable<T>, L extends Comparable<L>> List<GraphEdge<T, L>> kruskal(
	    Graph<T, L> graph)
    {
	Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> setTable = new Hashtable<>();
	Iterator<GraphNode<T>> nodeIt = graph.getVertices().iterator();
	while(nodeIt.hasNext())
	{
	    GraphNode<T> node = nodeIt.next();
	    setTable.put(node, new Pair<>(node, 0));
	}
	LinkedList<GraphEdge<T, L>> spanX = new LinkedList<>();
	List<? extends GraphEdge<T, L>> edgeList = graph.getEdges();
	edgeList.sort((n, m) -> n.getWeight().compareTo(m.getWeight()));
	Iterator<? extends GraphEdge<T, L>> edgeIt = edgeList.iterator();
	while(edgeIt.hasNext())
	{
	    GraphEdge<T, L> edge = edgeIt.next();
	    if(!find(edge.getVertices().get(0), setTable).equals(
		    find(edge.getVertices().get(1), setTable)))
	    {
		spanX.add(edge);
		union(edge.getVertices().get(0), edge.getVertices().get(1), setTable);
	    }
	}
	return spanX;
    }

    private static <S> S find(S x, Hashtable<S, Pair<S, Integer>> table)
    {
	S parent = table.get(x).getFirst();
	while(!parent.equals(x))
	{
	    x = parent;
	    parent = table.get(x).getFirst();
	}
	return x;
    }

    private static <S> void union(S x, S y, Hashtable<S, Pair<S, Integer>> table)
    {
	S rootX = find(x, table);
	S rootY = find(y, table);
	if(rootX.equals(rootY))
	    return; // Same tree
	Pair<S, Integer> pairX = table.get(rootX);
	Pair<S, Integer> pairY = table.get(rootY);
	if(pairX.getSecond().compareTo(pairY.getSecond()) > 0)
	    pairY.setFirst(rootX);
	else
	{
	    pairX.setFirst(rootY);
	    if(pairX.getSecond().equals(pairY.getSecond()))
		pairX.setSecond(pairX.getSecond() + 1);
	}
    }
}
