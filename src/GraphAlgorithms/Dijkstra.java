package GraphAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import Helper.Helper;
import Helper.Pair;
import Interfaces.Graph;
import Interfaces.GraphNode;

public class Dijkstra
{
    private Dijkstra()
    {
    }

    /**
     * Dijkstra Algorithm on a Graph with natural numbers as weights
     * 
     * @param graph
     *            a graph
     * @param start
     *            a starting node S
     * @return a hash-table such that the node X is the key for the pair (Y, n)
     *         where Y is the predecessor of X in the shortest path to X from S
     *         and n is the total distance from S to X.
     */
    public static <T> Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> dijkstra(
	    Graph<T, Integer> graph, GraphNode<T> start)
    {
	Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> table = new Hashtable<>();

	/*
	 * Defines comparator which treats null as INFINITY
	 */
	Comparator<GraphNode<T>> comparator =
		(a, b) -> Helper.compare(table.getOrDefault(a, new Pair<>(null, null)).getSecond(),
			table.getOrDefault(b, new Pair<>(null, null)).getSecond());

	// Initialize table and set R
	PriorityQueue<GraphNode<T>> remainingVertices = new PriorityQueue<>(comparator);
	table.put(start, new Pair<>(null, 0));
	remainingVertices.add(start);

	while(!remainingVertices.isEmpty())
	{
	    GraphNode<T> closestNode = remainingVertices.poll();
	    Iterator<GraphNode<T>> childrenIt = graph.getAdjacent(closestNode).iterator();
	    while(childrenIt.hasNext())
	    {
		GraphNode<T> node = childrenIt.next();

		Pair<GraphNode<T>, Integer> pair = table.getOrDefault(node, new Pair<>(null, null));
		Integer sum = Helper.add(table.getOrDefault(closestNode, new Pair<>(null, null))
			.getSecond(),
			graph.getWeight(closestNode, node));
		if(Helper.compare(pair.getSecond(), sum) > 0)
		{
		    pair.setSecond(sum);
		    pair.setFirst(closestNode);
		    table.put(node, pair);
		    if(!remainingVertices.contains(node))
			remainingVertices.add(node);
		}
	    }
	}
	return table;
    }

}
