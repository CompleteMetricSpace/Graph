package ListImplementation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import Helper.GEdge;
import Helper.GUEdge;
import Helper.Helper;
import Interfaces.DirectedGraph;
import Interfaces.DirectedGraphEdge;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;
import Interfaces.UndirectedGraph;

public class LUGraph<T extends Comparable<T>, L extends Comparable<L>> implements
	UndirectedGraph<T, L>
{
    List<ANode<T, L>> nodeList;
    int numberOfEdges;

    public LUGraph()
    {
	nodeList = new LinkedList<>();
	numberOfEdges = 0;
    }

    @Override
    public List<GraphNode<T>> getVertices()
    {
	List<GraphNode<T>> list = new LinkedList<>();
	Iterator<ANode<T, L>> nodeIt = nodeList.iterator();
	while(nodeIt.hasNext())
	    list.add(nodeIt.next().getNode());
	return list;
    }

    @Override
    public List<GraphEdge<T, L>> getEdges()
    {
	List<GraphEdge<T, L>> list = new LinkedList<>();
	Iterator<ANode<T, L>> nodeIt = nodeList.iterator();
	while(nodeIt.hasNext())
	{
	    ANode<T, L> anode = nodeIt.next();
	    Iterator<Pair<T, L>> edgeIt = anode.getPairList().iterator();
	    GraphNode<T> node = anode.getNode();
	    while(edgeIt.hasNext())
	    {
		Pair<T, L> pair = edgeIt.next();
		list.add(new GUEdge<T, L>(node, pair.getFirst(), pair.getSecond()));
	    }
	}
	return list;
    }

    @Override
    public int getNumberOfVertices()
    {
	return nodeList.size();
    }

    @Override
    public int getNumberOfEdges()
    {
	return numberOfEdges;
    }

    @Override
    public List<GraphNode<T>> getAdjacent(GraphNode<T> node)
    {
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList, n -> n.getNode().equals(node));
	    return Helper.map(anode.getPairList(), p -> p.getFirst());
	}
	catch(NoSuchElementException e)
	{
	    throw new IllegalArgumentException(
		    "Cannot compute adjacent of a node that is not in graph");
	}
    }

    @Override
    public L getWeight(GraphNode<T> s, GraphNode<T> e)
    {
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList, n -> n.getNode().equals(s));
	    return anode.getWeight(e);
	}
	catch(NoSuchElementException ex)
	{
	    return null;
	}
    }

    @Override
    public void addEdge(GraphEdge<T, L> e)
    {
	boolean inc = false;
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList,
		    n -> n.getNode().equals(e.getVertices().get(0)));
	    if(!anode.addEdge(e.getVertices().get(1), e.getWeight()))
		inc = true;
	}
	catch(NoSuchElementException ex)
	{
	    ANode<T, L> anode = new ANode<>(e.getVertices().get(0));
	    anode.addEdge(e.getVertices().get(1), e.getWeight());
	    nodeList.add(anode);
	    inc = true;
	}
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList,
		    n -> n.getNode().equals(e.getVertices().get(1)));
	    if(!anode.addEdge(e.getVertices().get(0), e.getWeight()))
		inc = true;
	    
	}
	catch(NoSuchElementException ex)
	{
	    ANode<T, L> anode = new ANode<>(e.getVertices().get(1));
	    anode.addEdge(e.getVertices().get(0), e.getWeight());
	    nodeList.add(anode);
	    inc = true;
	}
	if(inc)
	    numberOfEdges++;
    }

    public void addEdgeCheck(DirectedGraphEdge<T, L> e)
    {
	if(contains(e.getStart()) && contains(e.getDestination()))
	    addEdge(e);
	throw new IllegalArgumentException("Cannot add edge with node not in graph");
    }

    public boolean contains(GraphNode<T> node)
    {
	try
	{
	    Helper.find(nodeList, p -> p.getNode().equals(node));
	    return true;
	}
	catch(NoSuchElementException e)
	{
	    return false;
	}
    }

    @Override
    public void addEdge(List<GraphEdge<T, L>> e)
    {
	Iterator<GraphEdge<T, L>> eIt = e.iterator();
	while(eIt.hasNext())
	    addEdge(eIt.next());
    }

    @Override
    public void addNode(GraphNode<T> e)
    {
	try
	{
	    Helper.find(nodeList, n -> n.getNode().equals(e));
	}
	catch(NoSuchElementException ex)
	{
	    ANode<T, L> anode = new ANode<>(e);
	    nodeList.add(anode);
	}
    }

    @Override
    public void addNode(List<GraphNode<T>> e)
    {
	Iterator<GraphNode<T>> eIt = e.iterator();
	while(eIt.hasNext())
	    addNode(eIt.next());
    }

    @Override
    public void removeEdge(GraphEdge<T, L> e)
    {
	boolean dec = false;
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList,
		    n -> n.getNode().equals(e.getVertices().get(0)));
	    if(anode.removeEdgeTo(e.getVertices().get(1)))
	    {
		dec = true;
		numberOfEdges--;
	    }
	}
	catch(NoSuchElementException ex)
	{
	}
	try
	{
	    ANode<T, L> anode = Helper.find(nodeList,
		    n -> n.getNode().equals(e.getVertices().get(1)));
	    if(anode.removeEdgeTo(e.getVertices().get(0)) && !dec)
		numberOfEdges--;
	}
	catch(NoSuchElementException ex)
	{
	}
    }

    @Override
    public void removeEdge(List<GraphEdge<T, L>> e)
    {
	Iterator<GraphEdge<T, L>> eIt = e.iterator();
	while(eIt.hasNext())
	    addEdge(eIt.next());
    }

    @Override
    public void removeNode(GraphNode<T> e)
    {
	if(Helper.delete(nodeList, n -> n.getNode().equals(e)))
	{
	    Iterator<ANode<T, L>> nodeIt = nodeList.iterator();
	    while(nodeIt.hasNext())
		nodeIt.next().removeEdgeTo(e);
	}
    }

    @Override
    public void removeNode(List<GraphNode<T>> e)
    {
	Iterator<GraphNode<T>> eIt = e.iterator();
	while(eIt.hasNext())
	    removeNode(eIt.next());
    }

}
