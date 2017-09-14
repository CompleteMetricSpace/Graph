package GraphAlgorithms;

import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

import Helper.Pair;
import Interfaces.Graph;
import Interfaces.GraphNode;

public class AStar
{
    public static class Triple<U, V, W> 
    {
	U u;
	V v;
	W w;

	public Triple(U u, V v, W w)
	{
	    this.u = u;
	    this.v = v;
	    this.w = w;
	}

	public U getFirst()
	{
	    return u;
	}

	public V getSecond()
	{
	    return v;
	}

	public W getThird()
	{
	    return w;
	}
	
	public void setFirst(U u)
	{
	    this.u = u;
	}

	public void setSecond(V v)
	{
	    this.v = v;
	}

	public void setThird(W w)
	{
	    this.w = w;
	}

	public String toString()
	{
	    return "Triple[" + u + " | " + v + " | "+w+"]";
	}
    }

    
    private AStar()
    {
    }
    
    public static <T> Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> aStar(Graph<T, Integer> graph,
	    GraphNode<T> start, GraphNode<T> end,
	    Function<GraphNode<T>, Integer> h)
    {
	/*
	 * Creates a Hashtable with values (node, f, g) where f is the f-value, 
	 * g is the actual distance and node is the predecessor
	 */
	Hashtable<GraphNode<T>, Triple<GraphNode<T>, Integer, Integer>> fgTable = new Hashtable<>();
	
	/*
	 * Defines comparator which treats null as INFINITY
	 */
	Comparator<GraphNode<T>> comparator =
		(a, b) -> compare(fgTable.getOrDefault(a, new Triple<>(null, null, null)).getSecond(),
			fgTable.getOrDefault(b, new Triple<>(null, null, null)).getSecond());
	PriorityQueue<GraphNode<T>> openSet = new PriorityQueue<>(comparator);
	List<GraphNode<T>> closedSet = new LinkedList<GraphNode<T>>();
	fgTable.put(start, new Triple<>(null, h.apply(start), 0));
	openSet.add(start);
	while(!openSet.isEmpty())
	{
	    GraphNode<T> node = openSet.poll();
	    if(node.equals(end))
		break;
	    Iterator<GraphNode<T>> adjacentIt = graph.getAdjacent(node).iterator();
	    while(adjacentIt.hasNext())
	    {
		GraphNode<T> v = adjacentIt.next();
		if(closedSet.contains(v))
		    continue;
		Integer vGValue = add(fgTable.getOrDefault(node, new Triple<>(null, null, null)).getThird(), graph.getWeight(node, v));
		Integer cost = add(vGValue, h.apply(v));
		Triple<GraphNode<T>, Integer, Integer> vTriple = fgTable.getOrDefault(v, new Triple<>(null, null, null));
		if(openSet.contains(v) && compare(cost, vTriple.getThird()) >= 0)
		    continue;
		vTriple.setFirst(node);
		vTriple.setSecond(cost);
		vTriple.setThird(vGValue);
		fgTable.put(v, vTriple);
		if(!openSet.contains(v))
		    openSet.add(v);
	    }
	    closedSet.add(node);
	}
	return convert(fgTable);
    }
    
    private static Integer add(Integer a, Integer b)
    {
	return (a == null || b == null) ? null : a + b;
    }

    private static int compare(Integer a, Integer b)
    {
	if(a == null && b == null)
	    return 0;
	if(a == null)
	    return 1;
	if(b == null)
	    return -1;
	return a.compareTo(b);
    }
    
    private static <T> Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> convert(Hashtable<GraphNode<T>, Triple<GraphNode<T>, Integer, Integer>> fgTable)
    {
	Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> table = new Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>>();
	Enumeration<GraphNode<T>> it = fgTable.keys();
	while(it.hasMoreElements())
	{
	    GraphNode<T> node = it.nextElement();
	    Triple<GraphNode<T>, Integer, Integer> triple = fgTable.get(node);
	    table.put(node, new Pair<>(triple.getFirst(), triple.getThird()));
	}
	return table;
    }
}
