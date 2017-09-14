package Helper;

import Interfaces.GraphNode;

public class GNode<T extends Comparable<T>> implements GraphNode<T>
{
    T element;
   
    public GNode(T element)
    {
	this.element = element;
    }
    
    @Override
    public T getElement()
    {
	return element;
    }

    public boolean equals(Object o)
    {
	if(!(o instanceof GNode))
	    return false;
	GNode b = (GNode)o;
	return element.equals(b.getElement());
    }
    
    @Override
    public int hashCode()
    {
	return this.getElement().hashCode();
    }
    
    

    public String toString()
    {
	return "GN["+element+"]";
    }
}
