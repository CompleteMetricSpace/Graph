package GraphAlgorithms;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import Helper.Helper;
import Helper.Pair;
import Interfaces.DirectedGraphEdge;
import Interfaces.Graph;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;

public class BellmanFord
{

    public static <T extends Comparable<T>> Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> bellmanFord(
	    Graph<T, Integer> graph, GraphNode<T> start, boolean check)
    {
	Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> table = new Hashtable<>();
	table.put(start, new Pair<>(null, 0));
	int N = graph.getNumberOfVertices();
	List<? extends GraphEdge<T, Integer>> edgeList = graph.getEdges();
	for(int i = 0; i < N - 1; i++)
	{
	    boolean hasChanged = loop(edgeList, table);
	    if(!hasChanged)
		return table;
	}
	if(check)
	{
	    if(loop(edgeList, table))
		throw new IllegalArgumentException("Graph has a negative cycle");
	    return table;
	}
	return table;
    }
    
    private static <T extends Comparable<T>> boolean loop(List<? extends GraphEdge<T, Integer>> edgeList,
	    Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> table)
    {
	    boolean hasChanged = false;
	    Iterator<? extends GraphEdge<T, Integer>> edgeIt = edgeList.iterator();
	    while(edgeIt.hasNext())
	    {
		GraphEdge<T, Integer> edge = edgeIt.next();
		if(edge instanceof DirectedGraphEdge)
		{
		    GraphNode<T> source = ((DirectedGraphEdge<T, Integer>)edge).getStart();
		    GraphNode<T> destination = ((DirectedGraphEdge<T, Integer>)edge).getDestination();
		    Integer currentDistance = table.getOrDefault(destination, new Pair<>(null, null)).getSecond();
		    Integer newDistance = Helper.add(table.getOrDefault(source, new Pair<>(null, null)).getSecond(),
			    edge.getWeight());
		    if(Helper.compare(currentDistance, newDistance) > 0)
		    {
			table.put(destination, new Pair<>(source, newDistance));
			hasChanged = true;
		    }
		}
		else
		{
		    GraphNode<T> nodeOne = edge.getVertices().get(0);
		    GraphNode<T> nodeTwo = edge.getVertices().get(1);
		    
		    // Do it for nodeTwo
		    Integer currentDistance = table.getOrDefault(nodeTwo, new Pair<>(null, null)).getSecond();
		    Integer newDistance = Helper.add(table.getOrDefault(nodeOne, new Pair<>(null, null)).getSecond(),
			    edge.getWeight());
		    if(Helper.compare(currentDistance, newDistance) > 0)
		    {
			table.put(nodeTwo, new Pair<>(nodeOne, newDistance));
			hasChanged = true;
		    }
		    
		    // Do it for nodeOne
		    currentDistance = table.getOrDefault(nodeOne, new Pair<>(null, null)).getSecond();
		    newDistance = Helper.add(table.getOrDefault(nodeTwo, new Pair<>(null, null)).getSecond(),
			    edge.getWeight());
		    if(Helper.compare(currentDistance, newDistance) > 0)
		    {
			table.put(nodeOne, new Pair<>(nodeTwo, newDistance));
			hasChanged = true;
		    }
		}
	    }
	    return hasChanged;
    }
}
