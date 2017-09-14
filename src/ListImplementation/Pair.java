package ListImplementation;

import Interfaces.GraphNode;

class Pair<T extends Comparable<T>, L extends Comparable<L>>
{
    GraphNode<T> first;
    L second;

    public Pair(GraphNode<T> first, L second)
    {
        super();
        this.first = first;
        this.second = second;
    }

    public GraphNode<T> getFirst()
    {
        return first;
    }

    public L getSecond()
    {
        return second;
    }

    public void setSecond(L second)
    {
        this.second = second;
    }
}