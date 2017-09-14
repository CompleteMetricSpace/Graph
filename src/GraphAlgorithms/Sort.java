package GraphAlgorithms;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import Helper.GNode;
import Helper.Pair;
import Interfaces.DirectedGraph;
import Interfaces.Graph;
import Interfaces.GraphNode;
import Interfaces.UndirectedGraph;

public class Sort
{
    public static <T extends Comparable<T>, L extends Comparable<L>> Pair<Hashtable<GraphNode<T>, Integer>, Integer> connectedComponents(
	    UndirectedGraph<T, L> graph)
    {
	Hashtable<GraphNode<T>, Integer> visited = new Hashtable<>();
	Iterator<GraphNode<T>> nodeIt = graph.getVertices().iterator();
	int counter = 0;
	while(nodeIt.hasNext())
	{
	    GraphNode<T> node = nodeIt.next();
	    if(!visited.containsKey(node))
	    {
		counter++;
		final int c = counter;
		Search.deepFirstSearch(graph, node, n -> {
		    visited.put(n, c);
		}, n -> {}, n -> false);
	    }
	}
	return new Pair<>(visited, counter);
    }

    public static <T extends Comparable<T>, L extends Comparable<L>> List<GraphNode<T>> topologicalSortDFS(
	    DirectedGraph<T, L> graph)
    {
	List<GraphNode<T>> list = new LinkedList<>();
	Search.deepFirstSearch(graph, n -> {}, n -> list.add(0, n), n -> false);
	return list;
    }

    public static <T extends Comparable<T>, L extends Comparable<L>> List<GraphNode<T>> topologicalSortSource(
	    DirectedGraph<T, L> graph)
    {
	Hashtable<GraphNode<T>, Integer> nodeToInteger = new Hashtable<>();
	Hashtable<Integer, List<GraphNode<T>>> integerToNode = new Hashtable<>();
	List<GraphNode<T>> list = new LinkedList<>();
	Iterator<GraphNode<T>> nodeIt = graph.getVertices().iterator();
	int m = graph.getNumberOfVertices();
	while(nodeIt.hasNext())
	{
	    GraphNode<T> node = nodeIt.next();
	    int n = graph.getInDegree(node);
	    nodeToInteger.put(node, n);
	    List<GraphNode<T>> nodes = integerToNode.getOrDefault(n, new LinkedList<>());
	    nodes.add(node);
	    integerToNode.put(n, nodes);
	}
	while(list.size() < m)
	{
	    List<GraphNode<T>> sourceList = integerToNode.getOrDefault(0, new LinkedList<>());
	    if(sourceList.isEmpty())
		throw new IllegalArgumentException("Graph contains a cycle");
	    list.addAll(sourceList);
	    integerToNode.remove(0);
	    // Update values of nodes
	    Iterator<GraphNode<T>> sourceIt = sourceList.iterator();
	    while(sourceIt.hasNext())
	    {
		GraphNode<T> source = sourceIt.next();
		List<GraphNode<T>> adjacent = graph.getAdjacent(source);
		Iterator<GraphNode<T>> adjacentIt = adjacent.iterator();
		while(adjacentIt.hasNext())
		{
		    GraphNode<T> adj = adjacentIt.next();
		    int k = nodeToInteger.get(adj);
		    nodeToInteger.put(adj, k - 1);
		    List<GraphNode<T>> l = integerToNode.get(k);
		    l.remove(adj);
		    List<GraphNode<T>> c = integerToNode.getOrDefault(k - 1, new LinkedList<>());
		    c.add(adj);
		    integerToNode.put(k - 1, c);
		}
	    }
	}
	return list;
    }
}
