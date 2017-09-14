package GraphAlgorithms;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Predicate;

import GraphicalOutput.Coord;
import Helper.GNode;
import Interfaces.Graph;
import Interfaces.GraphNode;

public class Search
{

    public static <T extends Comparable<T>, L extends Comparable<L>> void deepFirstSearch(
	    Graph<T, L> graph, Consumer<GraphNode<T>> first,
	    Consumer<GraphNode<T>> last, Predicate<GraphNode<T>> finish)
    {
	Hashtable<GraphNode<T>, Boolean> visited = new Hashtable<>();
	Iterator<GraphNode<T>> nodeIt = graph.getVertices().iterator();
	while(nodeIt.hasNext())
	{
	    GraphNode<T> node = nodeIt.next();
	    if(!visited.getOrDefault(node, false))
		deepFirstSearch(graph, node, n -> {visited.put(n, true);first.accept(n);}, last, finish);
	}
    }
    
    public static <T extends Comparable<T>, L extends Comparable<L>> void deepFirstSearch(
	    Graph<T, L> graph, GraphNode<T> start, Consumer<GraphNode<T>> first,
	    Consumer<GraphNode<T>> last, Predicate<GraphNode<T>> finish)
    {
	deepFirstSearchIt(graph, start, first, last, finish);
    }

    private static <T extends Comparable<T>, L extends Comparable<L>> boolean deepFirstSearchRec(
	    Graph<T, L> graph, GraphNode<T> start, Consumer<GraphNode<T>> first,
	    Consumer<GraphNode<T>> last, Predicate<GraphNode<T>> finish,
	    Hashtable<GraphNode<T>, Boolean> visited)
    {
	visited.put(start, true);
	first.accept(start);
	Iterator<GraphNode<T>> nodeIt = graph.getAdjacent(start).iterator();
	while(nodeIt.hasNext())
	{
	    GraphNode<T> node = nodeIt.next();
	    if(!visited.getOrDefault(node, false))
	    {
		boolean done = deepFirstSearchRec(graph, node, first, last, finish, visited);
		if(done)
		{
		    last.accept(start);
		    return true;
		}
	    }
	}
	last.accept(start);
	return finish.test(start);
    }
    
    private static <T extends Comparable<T>, L extends Comparable<L>> void deepFirstSearchIt(
	    Graph<T, L> graph, GraphNode<T> start, Consumer<GraphNode<T>> first,
	    Consumer<GraphNode<T>> last, Predicate<GraphNode<T>> finish)
    {
	Hashtable<GraphNode<T>, Boolean> visited = new Hashtable<>();
	Hashtable<GraphNode<T>, Boolean> done = new Hashtable<>();
	Stack<GraphNode<T>> stack = new Stack<>();
	stack.push(start);
	while(!stack.isEmpty())
	{
	    GraphNode<T> node = stack.pop();
	    if(done.getOrDefault(node, false))
		continue;
	    if(visited.getOrDefault(node, false))
	    {
		last.accept(node);
		done.put(node, true);
		continue;
	    }
	    visited.put(node, true);
	    if(finish.test(node))
	    {
		last.accept(node);
		return;
	    }
	    stack.push(node);
	    first.accept(node);
	    List<GraphNode<T>> list = graph.getAdjacent(node);
	    ListIterator<GraphNode<T>> adjacentIt = list.listIterator(list.size());
	    while(adjacentIt.hasPrevious())
	    {
		GraphNode<T> adj = adjacentIt.previous();
		if(!visited.getOrDefault(adj, false))
		    stack.push(adj);
	    }
	}
    }
    
    
}
