package MatrixImplementation;

import java.util.ArrayList;
import java.util.List;



import Helper.GEdge;
import Interfaces.DirectedGraph;
import Interfaces.DirectedGraphEdge;
import Interfaces.GraphNode;

/**
 * 
 * @author KhAKhA
 *
 *         A Graph which is constructed and represented by a matrix. If value
 *         matrix.get(i).get(j) is NaN, then the edge does not exist
 *
 * @param <T>
 *            Element class of Node
 * @param <K>
 *            Key class of Node
 */
public class MGraph<T extends Comparable<T>, L extends Comparable<L>> implements DirectedGraph<T, L>
{
    public ArrayList<ArrayList<L>> matrix;
    int numberOfEdges;
    ArrayList<GraphNode<T>> nodeList;

    public MGraph(int size)
    {
	matrix = new ArrayList<ArrayList<L>>();
	numberOfEdges = 0;
	nodeList = new ArrayList<>(size);
    }

    private int size()
    {
	return nodeList.size();
    }

    @Override
    public List<GraphNode<T>> getVertices()
    {
	return new ArrayList<GraphNode<T>>(nodeList);
    }

    @Override
    public List<DirectedGraphEdge<T, L>> getEdges()
    {
	ArrayList<DirectedGraphEdge<T, L>> list = new ArrayList<>();
	for(int i = 0; i < size(); i++)
	{
	    for(int j = 0; j < size(); j++)
	    {
		if(matrix.get(i).get(j) == null)
		    list.add(new GEdge<T, L>(nodeList.get(i), nodeList.get(j), matrix.get(i).get(j)));
	    }
	}
	return list;
    }

    @Override
    public void addEdge(DirectedGraphEdge<T, L> e)
    {
	if(!nodeList.contains(e.getStart()) || !nodeList.contains(e.getDestination()))
	    throw new IllegalArgumentException("Cannot add edge with start/end not in graph");
	matrix.get(nodeList.indexOf(e.getStart())).set(nodeList.indexOf(e.getDestination()),
		e.getWeight());
    }

    @Override
    public void addEdge(List<DirectedGraphEdge<T, L>> e)
    {
	for(int i = 0; i < e.size(); i++)
	    this.addEdge(e.get(i));
    }

    @Override
    public void addNode(GraphNode<T> e)
    {
	ArrayList<GraphNode<T>> list = new ArrayList<>();
	list.add(e);
	this.addNode(list);
    }

    @Override
    public void addNode(List<GraphNode<T>> e)
    {
	ArrayList<GraphNode<T>> list = new ArrayList<>();
	for(int i = 0; i < e.size(); i++)
	{
	    if(!nodeList.contains(e.get(i)))
		list.add(e.get(i));
	}
	nodeList.addAll(list);
	ArrayList<ArrayList<L>> newMatrix = new ArrayList<ArrayList<L>>(size());
	ArrayList<L> NaNListShort = new ArrayList<>();
	while(NaNListShort.size() < list.size())
	    NaNListShort.add(null);
	ArrayList<L> NaNListLong = new ArrayList<>();
	while(NaNListLong.size() < size())
	    NaNListLong.add(null);
	for(int i = 0; i < size(); i++)
	{
	    if(i < matrix.size())
	    {
		matrix.get(i).addAll(NaNListShort);
		newMatrix.add(matrix.get(i));
	    }
	    else
	    {
		newMatrix.add(new ArrayList<L>(NaNListLong));
	    }
	}
	matrix = newMatrix;
    }

    @Override
    public void removeEdge(DirectedGraphEdge<T, L> e)
    {
	if(!nodeList.contains(e.getStart()) || !nodeList.contains(e.getDestination()))
	    return;
	matrix.get(nodeList.indexOf(e.getStart())).set(nodeList.indexOf(e.getDestination()),
		null);
    }

    @Override
    public void removeEdge(List<DirectedGraphEdge<T, L>> e)
    {
	for(int i = 0; i < e.size(); i++)
	    this.removeEdge(e.get(i));
    }

    @Override
    public void removeNode(GraphNode<T> e)
    {
	ArrayList<GraphNode<T>> list = new ArrayList<>();
	list.add(e);
	this.removeNode(list);
    }

    @Override
    public void removeNode(List<GraphNode<T>> e)
    {
	
	for(int i = 0; i < size(); i++)
	{
	    if(e.contains(nodeList.get(i)))
	    {
		matrix.remove(i);
		for(int j = 0; j < matrix.size(); j++)
		    matrix.get(j).remove(i);
	    }
	}
	nodeList.removeAll(e);
    }

    @Override
    public int getNumberOfVertices()
    {
	return size();
    }

    @Override
    public int getNumberOfEdges()
    {
	throw new UnsupportedOperationException();
    }

    @Override
    public L getWeight(GraphNode<T> s, GraphNode<T> e)
    {
	if(!nodeList.contains(s) || !nodeList.contains(e))
	    return null;
	return matrix.get(nodeList.indexOf(s)).get(nodeList.indexOf(e));
    }

}
