package GraphAlgorithms;

import java.awt.Color;
import java.util.Hashtable;

import CreateGraph.CreateGraph;
import GraphicalOutput.Coord;
import GraphicalOutput.DrawGraph;
import Helper.GNode;
import Helper.Pair;
import Interfaces.DirectedGraph;
import Interfaces.GraphNode;
import ListImplementation.LDGraph;

public class BellmanFordTest
{
    public static void test4()
    {
	Pair<LDGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> pair = CreateGraph.createGraph(20, 15);
	LDGraph<Coord, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Coord>, Coord> coord = pair.getSecond();
	GNode<Coord> start = new GNode<>(new Coord(5, 4));
	
	Hashtable<GraphNode<Coord>, Pair<GraphNode<Coord>, Integer>> table = BellmanFord.bellmanFord(
		graph, start, true);
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
