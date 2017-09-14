package GraphicalOutput;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Function;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Interfaces.DirectedGraph;
import Interfaces.DirectedGraphEdge;
import Interfaces.Graph;
import Interfaces.GraphEdge;
import Interfaces.GraphNode;
import Interfaces.UndirectedGraph;

public class DrawGraph<T extends Comparable<T>, L extends Comparable<L>> extends JFrame
{
    Graph<T, L> graph;
    Hashtable<GraphNode<T>, Coord> coord;

    List<GraphNode<T>> nodeList;
    List<? extends GraphEdge<T, L>> edgeList;
    Canvas c = new Canvas();
    Function<GraphNode<T>, String> toString;
    Function<GraphNode<T>, Color> colorFunction;
    int radius = 10;

    public DrawGraph(Graph<T, L> graph, Hashtable<GraphNode<T>, Coord> coord)
    {
	super();
	this.graph = graph;
	nodeList = graph.getVertices();
	edgeList = graph.getEdges();
	toString = n -> n.getElement().toString();
	colorFunction = n ->Color.WHITE;
	this.coord = coord;
	c.setSize(1000, 1000);
	add(c);
	c.repaint();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setRadius(int r)
    {
	radius = r;
    }

    public void setStringFunction(Function<GraphNode<T>, String> f)
    {
	toString = f;
    }
    
    public void setColorFunction(Function<GraphNode<T>, Color> f)
    {
	colorFunction = f;
    }

    class Canvas extends JComponent
    {
	public void paint(Graphics g)
	{
	    for(GraphEdge<T, L> edge : edgeList)
	    {
		if(edge instanceof DirectedGraphEdge)
		{
		    DirectedGraphEdge<T, L> directedEdge = (DirectedGraphEdge<T, L>)edge;
		    Coord posStart = coord.get(directedEdge.getStart());
		    Coord posEnd = coord.get(directedEdge.getDestination());
		    int psx = posStart.getIntX() + radius;
		    int psy = posStart.getIntY() + radius;
		    int pex = posEnd.getIntX() + radius;
		    int pey = posEnd.getIntY() + radius;
		    double angleStart = Math.atan2(pey - psy, pex - psx);
		    double angleEnd = angleStart + Math.PI;
		    g.drawLine((int) (psx + radius * Math.cos(angleStart)), (int) (psy + radius
			    * Math.sin(angleStart)),
			    (int) (pex + radius * Math.cos(angleEnd)),
			    (int) (pey + radius * Math.sin(angleEnd)));
		    drawArrowHead((Graphics2D) g,
			    new Point((int) (pex + (radius + 5) * Math.cos(angleEnd)),
				    (int) (pey + (radius + 5) * Math.sin(angleEnd))), angleStart);
		}
		else
		{
		    Coord posStart = coord.get(edge.getVertices().get(0));
		    Coord posEnd = coord.get(edge.getVertices().get(1));
		    int psx = posStart.getIntX() + radius;
		    int psy = posStart.getIntY() + radius;
		    int pex = posEnd.getIntX() + radius;
		    int pey = posEnd.getIntY() + radius;
		    g.drawLine((int) psx, (int) psy,(int) pex,(int) pey);
		}
	    }
	    for(GraphNode<T> node : nodeList)
	    {
		Coord pos = coord.get(node);
		g.setColor(colorFunction.apply(node));
		g.fillOval(pos.getIntX(), pos.getIntY(), 2 * radius, 2 * radius);
		g.setColor(Color.BLACK);
		g.drawOval(pos.getIntX(), pos.getIntY(), 2 * radius, 2 * radius);
		g.drawString("" + toString.apply(node), pos.getIntX() + radius / 2,
			pos.getIntY() + (int) (1.5 * radius));
	    }
	}

	private void drawArrowHead(Graphics2D g, Point tip, double angle)
	{
	    AffineTransform oldT = g.getTransform();
	    AffineTransform tx = new AffineTransform();
	    tx.setToIdentity();
	    Polygon arrowHead = new Polygon();
	    arrowHead.addPoint(5, 0);
	    arrowHead.addPoint(0, -5);
	    arrowHead.addPoint(0, 5);
	    tx.translate(tip.getX(), tip.getY());
	    tx.rotate(angle);
	    g.transform(tx);
	    g.fill(arrowHead);
	    g.setTransform(oldT);
	}
    }
   
}    
   

