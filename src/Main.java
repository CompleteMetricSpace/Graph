import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import CreateGraph.CreateGraph;
import GraphAlgorithms.AStar;
import GraphAlgorithms.AStarTest;
import GraphAlgorithms.BellmanFord;
import GraphAlgorithms.BellmanFordTest;
import GraphAlgorithms.Dijkstra;
import GraphAlgorithms.DijkstraTest;
import GraphAlgorithms.Kruskal;
import GraphAlgorithms.KruskalTest;
import GraphAlgorithms.Search;
import GraphAlgorithms.SearchTest;
import GraphAlgorithms.Sort;
import GraphicalOutput.Coord;
import GraphicalOutput.DrawGraph;
import GraphicalOutput.P;
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

public class Main
{
    public static void main(String[] args)
    {
	Pair<UndirectedGraph<Integer, Integer>, Hashtable<GraphNode<Integer>, Coord>> pair = CreateGraph.createRandomUndirectedGraph(10, 12);
	UndirectedGraph<Integer, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Integer>, Coord> coords = pair.getSecond();
        coords = CreateGraph.forceBalance(graph, coords, 20);
	DrawGraph<Integer, Integer> dg = new DrawGraph<Integer, Integer>(graph, coords);
        dg.setSize(1000,1000);
        dg.setVisible(true);
    }

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

	
	List<GraphNode<String>> list = Sort.topologicalSortSource(graph); 
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println(list);
	
	DrawGraph<String, Integer> dg = new DrawGraph<String, Integer>(graph, coord);
        dg.setSize(1000,1000);
        dg.setVisible(true);
    }
    
    public static void test4()
    {
	Pair<LDGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> pair = CreateGraph.createGraph(20, 15);
	LDGraph<Coord, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Coord>, Coord> coord = pair.getSecond();
	
	List<GraphNode<Coord>> list = Sort.topologicalSortSource(graph); 
	
	System.out.println(graph.getNumberOfVertices());
	System.out.println("Table Output");
	System.out.println("--------------");
	System.out.println(list);
	
	//Graphical output
	DrawGraph<Coord, Integer> dg = new DrawGraph<Coord, Integer>(graph, coord);
	dg.setRadius(15);
	dg.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg.setSize(1000, 1000);
	dg.setVisible(true);
	
    } 
}
