package ListImplementation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import Helper.Helper;
import Interfaces.GraphNode;

class ANode<T extends Comparable<T>, L extends Comparable<L>>
{
    /*
     * nodeList is always kept in order of T
     */
    GraphNode<T> node;
    int incomingDegree;
    LinkedList<Pair<T, L>> nodeList;

    public ANode(GraphNode<T> node)
    {
        this.node = node;
        nodeList = new LinkedList<>();
        incomingDegree = 0;
    }

    public L getWeight(GraphNode<T> n)
    {
        Iterator<Pair<T, L>> it = nodeList.iterator();
        while(it.hasNext())
        {
    	Pair<T, L> pair = it.next();
    	if(pair.getFirst().equals(n))
    	    return pair.getSecond();
        }
        return null;
    }

    public GraphNode<T> getNode()
    {
        return node;
    }

    public List<Pair<T, L>> getPairList()
    {
        return nodeList;
    }

    /**
     * Adds an edge to another node with weight in order
     * @param n a node 
     * @param weight a weight coefficient
     * @return true if edge was already existent (with possibly different weight), false otherwise
     */
    public boolean addEdge(GraphNode<T> n, L weight)
    {
        ListIterator<Pair<T, L>> it = nodeList.listIterator();
        while(it.hasNext())
        {
    	Pair<T, L> pair = it.next();
    	//If next element is greater, add a new edge right before it
    	if(pair.getFirst().getElement().compareTo(n.getElement()) > 0)
    	{
    	    it.previous();
    	    it.add(new Pair<T, L>(n, weight));
    	    return false;
    	}
    	if(pair.getFirst().equals(n))
    	{
    	    pair.setSecond(weight);
    	    return true;
    	}
        }
        nodeList.add(new Pair<>(n, weight));
        return false;
    }
    
    public void incrementIncomingDegree()
    {
	incomingDegree++;
    }
    
    public void decrementIncomingDegree()
    {
	incomingDegree--;
    }
    
    public int getIncomingDegree()
    {
	return incomingDegree;
    }
    /**
     * Removes an edge to a node
     * @param n a node
     * @return true if there was an edge to node n, false otherwise
     */
    public boolean removeEdgeTo(GraphNode<T> n)
    {
        return Helper.delete(nodeList, p -> p.getFirst().equals(n));
    }
}