package GraphicalOutput;

public class Coord implements Comparable<Coord>
{
    private double x;
    private double y;
     
    public Coord(double x, double y)
    {
	this.x = x;
	this.y = y;
    }
    
    public double getX()
    {
	return x;
    }
    
    public double getY()
    {
	return y;
    }
    
    public int getIntX()
    {
	return (int)x;
    }
    
    public int getIntY()
    {
	return (int)y;
    }
    
    public Coord add(Coord b)
    {
	return new Coord(this.getX()+b.getX(), this.getY()+b.getY());
    }
    
    public Coord sub(Coord b)
    {
	return new Coord(this.getX()-b.getX(), this.getY()-b.getY());
    }
    
    public Coord mult(Coord b)
    {
	return new Coord(this.getX()*b.getX(), this.getY()*b.getY());
    }
    
    public Coord div(Coord b)
    {
	return new Coord(this.getX()/b.getX(), this.getY()/b.getY());
    }
    
    public Coord add(double x, double y)
    {
	return new Coord(this.getX()+x, this.getY()+y);
    }
    
    public Coord sub(double x, double y)
    {
	return new Coord(this.getX()-x, this.getY()-y);
    }
    
    public Coord mult(double x, double y)
    {
	return new Coord(this.getX()*x, this.getY()*y);
    }
    
    public Coord div(double x, double y)
    {
	return new Coord(this.getX()/x, this.getY()/y);
    }
    
    public String toString()
    {
	return "("+x+","+y+")";
    }
    
    public String toIntegerString()
    {
	return "("+getIntX()+","+getIntY()+")";
    }

    @Override
    public int compareTo(Coord o)
    {
	if(x != o.getX())
	{
	    if(x > o.getX())
		return 1;
	    return -1;
	}
	else
	{
	    if(y > o.getY())
		return 1;
	    if(y < o.getY())
		return -1;
	    return 0;
	}
    }
    
    @Override
    public boolean equals(Object b)
    {
	if(!(b instanceof Coord))
	    return false;
	Coord o = (Coord)b;
	return x==o.getX() && y == o.getY();
    }
    
    @Override
    public int hashCode()
    {
	return new Double(x).hashCode()*31+new Double(y).hashCode();
    }
}
