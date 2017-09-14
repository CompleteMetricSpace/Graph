package GraphAlgorithms;

import java.util.Hashtable;
import java.util.List;

import CreateGraph.CreateGraph;
import GraphicalOutput.Coord;
import GraphicalOutput.DrawGraph;
import Helper.Pair;
import Interfaces.Graph;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;
import ListImplementation.LUGraph;

public class KruskalTest
{
    public static void test4()
    {
	Pair<LUGraph<Coord, Integer>, Hashtable<GraphNode<Coord>, Coord>> pair = CreateGraph.createUndirectedGraph(20, 15);
	LUGraph<Coord, Integer> graph = pair.getFirst();
	Hashtable<GraphNode<Coord>, Coord> coord = pair.getSecond();
	
	List<GraphEdge<Coord, Integer>> edgeList = Kruskal.kruskal(graph);
	Graph<Coord, Integer> tree = CreateGraph.createGraph(edgeList); 
	
	//Graphical output
	DrawGraph<Coord, Integer> dg = new DrawGraph<Coord, Integer>(graph, coord);
	dg.setRadius(15);
	dg.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg.setSize(1000, 1000);
	dg.setVisible(true);
	DrawGraph<Coord, Integer> dg2 = new DrawGraph<Coord, Integer>(tree, coord);
	dg2.setRadius(15);
	dg2.setStringFunction(n -> n.getElement().getIntX()+"|"+n.getElement().getIntY());
	dg2.setSize(1000, 1000);
	dg2.setVisible(true);
    }
}
