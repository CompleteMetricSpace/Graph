package GraphicalOutput;

public class P<T>
{
    T element;
    int x,y;
    
    
    public P(T element, int x, int y)
    {
	this.element = element;
	this.x = x;
	this.y = y;
    }
    public T getElement()
    {
        return element;
    }
    public void setElement(T element)
    {
        this.element = element;
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}
