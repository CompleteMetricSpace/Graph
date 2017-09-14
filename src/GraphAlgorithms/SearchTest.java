package GraphAlgorithms;

import java.util.Enumeration;
import java.util.Hashtable;

import CreateGraph.CreateGraph;
import GraphicalOutput.Coord;
import GraphicalOutput.DrawGraph;
import Helper.GEdge;
import Helper.GNode;
import Helper.Pair;
import Interfaces.GraphNode;
import ListImplementation.LDGraph;

public class SearchTest
{

    public static void test1()
    {
	LDGraph<String, Integer> graph = new LDGraph<>();
	GNode<String> nA = new GNode<String>("A");
	GNode<String> nB = new GNode<String>("B");
	GNode<String> nC = new GNode<String>("C");
	GNode<String> nD = new GNode<String>("D");
	GNode<String> nE = new GNode<String>("E");
	graph.addNode(nA);
	graph.addNode(nB);
	graph.addNode(nC);
	graph.addNode(nD);
	graph.addNode(nE);

	graph.addEdge(new GEdge<String, Integer>(nA, nB, 2));
	graph.addEdge(new GEdge<String, Integer>(nA, nC, 1));
	graph.addEdge(new GEdge<String, Integer>(nC, nB, 1));
	graph.addEdge(new GEdge<String, Integer>(nB, nE, 3));
	graph.addEdge(new GEdge<String, Integer>(nC, nE, 4));
	graph.addEdge(new GEdge<String, Integer>(nB, nD, 2));
	graph.addEdge(new GEdge<String, Integer>(nE, nD, 2));

	Hashtable<GraphNode<String>, Coord> coord = new Hashtable<>();
	coord.put(nA, new Coord(100, 150));
	coord.put(nB, new Coord(200, 100));
	coord.put(nC, new Coord(200, 200));
	coord.put(nD, new Coord(300, 100));
	coord.put(nE, new Coord(300, 200));

	Hashtable<GraphNode<String>, Integer> table = new Hashtable<>();
	Hashtable<GraphNode<String>, Integer> table2 = new Hashtable<>();
	class I
	{
	    int u;
	    public I(int k)
	    {
		u = k;
	    }
	    
	    public void increase()
	    {
		u++;
	    }
	    
	    public int get()
	    {
		return u;
	    }
	}
	I u = new I(0);
	Search.deepFirstSearch(graph, nA, n -> {table.put(n, u.get()); u.increase();}, n ->{table2.put(n, u.get()); u.increase();} , n -> false);
	
	
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println("A: " + table.get(nA)+"|"+table2.get(nA));
	System.out.println("B: " + table.get(nB)+"|"+table2.get(nB));
	System.out.println("C: " + table.get(nC)+"|"+table2.get(nC));
	System.out.println("D: " + table.get(nD)+"|"+table2.get(nD));
	System.out.println("E: " + table.get(nE)+"|"+table2.get(nE));
	
	DrawGraph<String, Integer> dg = new DrawGraph<String, Integer>(graph, coord);
        dg.setSize(1000,1000);
        dg.setVisible(true);
       
        
    }
    
    public static void test4()
    {
	Pair<LDGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> pair = CreateGraph.createGraph(20, 15);
	LDGraph<Coord, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Coord>, Coord> coord = pair.getSecond();
	GNode<Coord> start = new GNode<>(new Coord(5, 4));
	
	Hashtable<GraphNode<Coord>, Integer> table = new Hashtable<>();
	Hashtable<GraphNode<Coord>, Integer> table2 = new Hashtable<>();
	class I
	{
	    int u;
	    public I(int k)
	    {
		u = k;
	    }
	    
	    public void increase()
	    {
		u++;
	    }
	    
	    public int get()
	    {
		return u;
	    }
	}
	I u = new I(0);
	Search.deepFirstSearch(graph, start, n -> {table.put(n, u.get()); u.increase();}, n ->{table2.put(n, u.get()); u.increase();} , n -> false);
	System.out.println(graph.getNumberOfVertices());
	System.out.println("Table Output");
	System.out.println("--------------");
	Enumeration<GraphNode<Coord>> list = table.keys();
	while(list.hasMoreElements())
	{
	    GraphNode<Coord> node = list.nextElement();
	    System.out.println(node.getElement().toIntegerString()+": "+table.getOrDefault(node, null)+" | "+table2.getOrDefault(node, null));
	}

	
	//Graphical output
	DrawGraph<Coord, Integer> dg = new DrawGraph<Coord, Integer>(graph, coord);
	dg.setRadius(15);
	dg.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg.setSize(1000, 1000);
	dg.setVisible(true);
	
    } 
}
