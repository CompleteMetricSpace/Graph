package GraphAlgorithms;

import java.awt.Color;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import CreateGraph.CreateGraph;
import GraphicalOutput.Coord;
import GraphicalOutput.DrawGraph;
import Helper.GEdge;
import Helper.GNode;
import Helper.Pair;
import Interfaces.DirectedGraph;
import Interfaces.GraphNode;
import ListImplementation.LDGraph;

public class DijkstraTest
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

	Hashtable<GraphNode<String>, Pair<GraphNode<String>, Integer>> table = Dijkstra.dijkstra(
		graph, nA);
	DirectedGraph<String, Integer> path = CreateGraph.constructPathGraph(graph, table);
	
	
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println("A: " + table.get(nA));
	System.out.println("B: " + table.get(nB));
	System.out.println("C: " + table.get(nC));
	System.out.println("D: " + table.get(nD));
	System.out.println("E: " + table.get(nE));
	
	DrawGraph<String, Integer> dg = new DrawGraph<String, Integer>(graph, coord);
        dg.setSize(1000,1000);
        dg.setVisible(true);
        DrawGraph<String, Integer> dg2 = new DrawGraph<String, Integer>(path, coord);
        dg2.setSize(1000,1000);
        dg2.setVisible(true);
        
    }

    public static void test2()
    {
	LDGraph<String, Integer> graph = new LDGraph<>();
	GNode<String> nA = new GNode<String>("A");
	GNode<String> nB = new GNode<String>("B");
	GNode<String> nC = new GNode<String>("C");

	graph.addNode(nA);
	graph.addNode(nB);
	graph.addNode(nC);

	graph.addEdge(new GEdge<String, Integer>(nA, nB, 1));
	graph.addEdge(new GEdge<String, Integer>(nB, nC, 1));
	graph.addEdge(new GEdge<String, Integer>(nC, nA, 1));

	Hashtable<GraphNode<String>, Coord> coord = new Hashtable<>();
	coord.put(nA, new Coord(100, 150));
	coord.put(nB, new Coord(200, 100));
	coord.put(nC, new Coord(200, 200));

	DrawGraph<String, Integer> dg = new DrawGraph<String, Integer>(graph, coord);
	dg.setSize(1000, 1000);
	dg.setVisible(true);

	Hashtable<GraphNode<String>, Pair<GraphNode<String>, Integer>> table = Dijkstra.dijkstra(
		graph, nA);
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println("A: " + table.get(nA));
	System.out.println("B: " + table.get(nB));
	System.out.println("C: " + table.get(nC));
    }

    public static void test3()
    {
	LDGraph<String, Integer> graph = new LDGraph<>();
	GNode<String> nA = new GNode<String>("A");
	GNode<String> nB = new GNode<String>("B");

	graph.addNode(nA);
	graph.addNode(nB);

	graph.addEdge(new GEdge<String, Integer>(nA, nB, 1));
	graph.addEdge(new GEdge<String, Integer>(nB, nA, 1));

	Hashtable<GraphNode<String>, Coord> coord = new Hashtable<>();
	coord.put(nA, new Coord(100, 200));
	coord.put(nB, new Coord(200, 100));

	DrawGraph<String, Integer> dg = new DrawGraph<String, Integer>(graph, coord);
	dg.setSize(1000, 1000);
	dg.setVisible(true);

	Hashtable<GraphNode<String>, Pair<GraphNode<String>, Integer>> table = Dijkstra.dijkstra(
		graph, nA);
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println("A: " + table.get(nA));
	System.out.println("B: " + table.get(nB));
    }

    public static void test4()
    {
	Pair<LDGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> pair = CreateGraph.createGraph(20, 15);
	LDGraph<Coord, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Coord>, Coord> coord = pair.getSecond();
	GNode<Coord> start = new GNode<>(new Coord(5, 4));
	
	Hashtable<GraphNode<Coord>, Pair<GraphNode<Coord>, Integer>> table = Dijkstra.dijkstra(
		graph, start);
	DirectedGraph<Coord, Integer> path = CreateGraph.constructPathGraph(graph, table);
	
	System.out.println("Table Output");
	System.out.println("--------------");
	for(int i = 1; i < 10; i++)
	{
	    for(int j = 1; j < 10; j++)
	    {
		System.out.println(i+""+j+": "+table.getOrDefault(new GNode<>(i+""+j), new Pair<>(null, null)));
	    }
	}
	
	//Graphical output
	DrawGraph<Coord, Integer> dg = new DrawGraph<Coord, Integer>(graph, coord);
	dg.setRadius(15);
	dg.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg.setSize(1000, 1000);
	dg.setVisible(true);
	DrawGraph<Coord, Integer> dg2 = new DrawGraph<Coord, Integer>(path, coord);
	dg2.setRadius(15);
	dg2.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg2.setColorFunction(n -> {
	    if(n.equals(start))
		return Color.GREEN;
	    return Color.WHITE;
	});
	dg2.setSize(1000, 1000);
	dg2.setVisible(true);
    }
}
