package CreateGraph;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import GraphicalOutput.Coord;
import Helper.GEdge;
import Helper.GNode;
import Helper.GUEdge;
import Helper.Pair;
import Interfaces.DirectedGraph;
import Interfaces.DirectedGraphEdge;
import Interfaces.Graph;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;
import Interfaces.UndirectedGraph;
import ListImplementation.LDGraph;
import ListImplementation.LUGraph;

/**
 * 
 * @author KhAKhA
 *
 * Class for creating some graphs
 */
public class CreateGraph
{
    /**
     * Creates some directed graph grid of size n \times m and with some nodes removed
     * @param m 
     * @param n
     * @return
     */
    public static Pair<LDGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> createGraph(
	    int m, int n)
    {
	LDGraph<Coord, Integer> graph = new LDGraph<>();
	List<GraphNode<Coord>> list = new LinkedList<>();
	Hashtable<GraphNode<Coord>, Coord> coord = new Hashtable<>();
	for(int i = 1; i < m; i++)
	{
	    for(int j = 1; j < n; j++)
	    {
		GNode<Coord> node = new GNode<Coord>(new Coord(i, j));
		coord.put(node, new Coord(i * 50 + 20, j * 50 + 20));
		list.add(node);
	    }
	}
	graph.addNode(list);
	for(GraphNode<Coord> node : list)
	{
	    int i = node.getElement().getIntX();
	    int j = node.getElement().getIntY();
	    if(i < m - 1)
	    {
		graph.addEdge(new GEdge<>(node, new GNode<Coord>(new Coord(i + 1, j)), 100));
		graph.addEdge(new GEdge<>(new GNode<Coord>(new Coord(i + 1, j)), node, 100));
	    }
	    if(j < n - 1)
	    {
		graph.addEdge(new GEdge<>(node, new GNode<Coord>(new Coord(i, j + 1)), 100));
		graph.addEdge(new GEdge<>(new GNode<Coord>(new Coord(i, j + 1)), node, 100));
	    }
	    if(i < m - 1 && j < n - 1)
	    {
		graph.addEdge(new GEdge<>(node, new GNode<Coord>(new Coord(i + 1, j + 1)), 141));
		graph.addEdge(new GEdge<>(new GNode<Coord>(new Coord(i + 1, j + 1)), node, 141));
	    }
	    if(i < m - 1 && j > 1)
	    {
		graph.addEdge(new GEdge<>(node, new GNode<Coord>(new Coord(i + 1, j - 1)), 141));
		graph.addEdge(new GEdge<>(new GNode<Coord>(new Coord(i + 1, j - 1)), node, 141));
	    }
	}
	graph.removeNode(new GNode<Coord>(new Coord(3, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(4, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(5, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(6, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 5)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 4)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 3)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 2)));
	for(int i = 3; i < 16; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(i, 10)));
	for(int i = 1; i < 8; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(9, i)));
	for(int i = 8; i <= 12; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(1, i)));
	for(int i = 1; i <= 8; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(i, 12)));
	for(int i = 4; i <= 9; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(12, i)));
	return new Pair<>(graph, coord);
    }

    /**
     * Creates some undirected graph grid of size n \times m and with some nodes removed
     * @param m 
     * @param n
     * @return
     */
    public static Pair<LUGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> createUndirectedGraph(
	    int m, int n)
    {
	LUGraph<Coord, Integer> graph = new LUGraph<>();
	List<GraphNode<Coord>> list = new LinkedList<>();
	Hashtable<GraphNode<Coord>, Coord> coord = new Hashtable<>();
	for(int i = 1; i < m; i++)
	{
	    for(int j = 1; j < n; j++)
	    {
		GNode<Coord> node = new GNode<Coord>(new Coord(i, j));
		coord.put(node, new Coord(i * 50 + 20, j * 50 + 20));
		list.add(node);
	    }
	}
	graph.addNode(list);
	for(GraphNode<Coord> node : list)
	{
	    int i = node.getElement().getIntX();
	    int j = node.getElement().getIntY();
	    if(i < m - 1)
	    {
		graph.addEdge(new GUEdge<>(node, new GNode<Coord>(new Coord(i + 1, j)), 100));
	    }
	    if(j < n - 1)
	    {
		graph.addEdge(new GUEdge<>(node, new GNode<Coord>(new Coord(i, j + 1)), 100));
	    }
	    if(i < m - 1 && j < n - 1)
	    {
		graph.addEdge(new GUEdge<>(node, new GNode<Coord>(new Coord(i + 1, j + 1)), 141));
	    }
	    if(i < m - 1 && j > 1)
	    {
		graph.addEdge(new GUEdge<>(node, new GNode<Coord>(new Coord(i + 1, j - 1)), 141));
	    }
	}
	graph.removeNode(new GNode<Coord>(new Coord(3, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(4, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(5, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(6, 6)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 5)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 4)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 3)));
	graph.removeNode(new GNode<Coord>(new Coord(3, 2)));
	for(int i = 3; i < 16; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(i, 10)));
	for(int i = 1; i < 8; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(9, i)));
	for(int i = 8; i <= 12; i++)
	    graph.removeNode(new GNode<Coord>(new Coord(1, i)));
	return new Pair<>(graph, coord);
    }

    
    public static <T extends Comparable<T>, L extends Comparable<L>> Graph<T, L> createGraph(
	    List<GraphEdge<T, L>>
	    list)
    {
	LUGraph<T, L> graph = new LUGraph<>();
	Iterator<GraphEdge<T, L>> edgeIt = list.iterator();
	while(edgeIt.hasNext())
	    graph.addEdge(edgeIt.next());
	return graph;
    }

    public static <T extends Comparable<T>> DirectedGraph<T, Integer> constructPathGraph(
	    Graph<T, Integer> graph,
	    Hashtable<GraphNode<T>, Pair<GraphNode<T>, Integer>> table)
    {
	DirectedGraph<T, Integer> path = new LDGraph<>();
	path.addNode(graph.getVertices());
	Iterator<GraphNode<T>> verticesIt = graph.getVertices().iterator();
	while(verticesIt.hasNext())
	{
	    GraphNode<T> node = verticesIt.next();
	    Pair<GraphNode<T>, Integer> pair = table.getOrDefault(node, new Pair<>(null, null));
	    if(pair.getFirst() != null)
		path.addEdge(new GEdge<>(node, pair.getFirst(), pair.getSecond()));
	}
	return path;
    }

    /**
     * Creates some random undirected graph with m random located nodes and n random edges 
     * 
     * Nodes are placed in the square 1000 \times 1000 randomly
     * 
     * @param m
     * @param n
     * @return the pair (G, H) where G is the undirected Graph and H is the location hashtable 
     */
    public static Pair<UndirectedGraph<Integer, Integer>, Hashtable<GraphNode<Integer>, Coord>> createRandomUndirectedGraph(
	    int m, int n)
    {
	LUGraph<Integer, Integer> graph = new LUGraph<>();
	List<GraphNode<Integer>> list = new LinkedList<>();
	List<GraphEdge<Integer, Integer>> edgeList = new LinkedList<>();
	Hashtable<GraphNode<Integer>, Coord> coord = new Hashtable<>();
	for(int i = 1; i <= m; i++)
	{
	    GNode<Integer> node = new GNode<Integer>(new Integer(i));
	    coord.put(node, new Coord(Math.random() * 1000, Math.random() * 1000));
	    list.add(node);
	}
	for(int i = 0; i < n; i++)
	{
	    int a = (int)(Math.random()*m);
	    int b = (int)(Math.random()*m);
	    GraphEdge<Integer, Integer> edge = new GUEdge<>(list.get(a), list.get(b), 1); 
	    while(a == b || edgeList.contains(edge))
	    {
		a = (int)(Math.random()*m);
		b = (int)(Math.random()*m);
		edge = new GUEdge<>(list.get(a), list.get(b), 1); 
	    }
	    edgeList.add(edge);
	}
	graph.addNode(list);
	graph.addEdge(edgeList);
	return new Pair<>(graph, coord);
    }
    
    /**
     * Balance a graph using the force-approach:
     * 
     * Nodes that are adjacent are attracted towards each other
     * Nodes that are not adjacent are repelled
     *  
     * @param graph a graph
     * @param table a location hashtable
     * @param M number of times force is applied to the nodes
     * @return a new location hashtable that has (hopefully) better locations for nodes
     */
    public static <T extends Comparable<T>, L extends Comparable<L>> Hashtable<GraphNode<T>, Coord> forceBalance(UndirectedGraph<T, L> graph, Hashtable<GraphNode<L>, Coord> table, int M)
    {
	List<GraphNode<T>> nodeList = graph.getVertices();
	Hashtable<GraphNode<T>, Coord> newTable = (Hashtable<GraphNode<T>, Coord>)table.clone();
	for(int i = 0;i<M;i++)
	{
	    //Compute Force
	    List<Coord> forceList = new LinkedList<>();
	    Iterator<GraphNode<T>> nodeIt = nodeList.iterator();
	    while(nodeIt.hasNext())
	    {
		GraphNode<T> node = nodeIt.next();
		Coord force = new Coord(0,0);
		Iterator<GraphNode<T>> nodeIt2 = nodeList.iterator();
		while(nodeIt2.hasNext())
		{
		    GraphNode<T> node2 = nodeIt2.next();
		    if(node.equals(node2))
			continue;
		    Coord r = newTable.get(node2).sub(newTable.get(node));
		    if(graph.hasEdge(node, node2))
		    {
			force = force.add(r);
		    }
		    else
		    {
			force = force.add(new Coord(-1/2,-1/2).mult(r));
		    }
		}
		forceList.add(force);
	    }
	    
	    //Move nodes
	    nodeIt = nodeList.iterator();
	    Iterator<Coord> forceIt = forceList.iterator();
	    while(nodeIt.hasNext())
	    {
		GraphNode<T> node = nodeIt.next();
		Coord force = forceIt.next();
		newTable.put(node, newTable.get(node).add(force.div(100, 100)));
	    }
	}
	return newTable;
    }
}
