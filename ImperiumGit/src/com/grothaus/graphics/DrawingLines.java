package com.grothaus.graphics;

import java.applet.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.grothaus.gamelogic.Pair;

public class DrawingLines extends Applet implements MouseListener {

	private Stack<Polygon> polygons;
	private ArrayList<Pair> circles;
	private ArrayList<Pair> quadrats;
	Set<Pair> edges;
	private Graphics g;
	private static int radius = 4;
	private static int zoom = 2;
	private static int startX = 8;
	private static int startY = 14;
	private int pointXdifferenceLeft;
	private int pointYdifferenceLeft;
	private int pointXdifferenceRight;
	private int pointYdifferenceRight;
	private int pointXdifferenceUp;
	private int pointYdifferenceUp;

	public void init() {
		this.setSize(800, 800);
		polygons =  new Stack<>();
		circles = new ArrayList<>();
		quadrats = new ArrayList<>();
		createPolygons(g);
		addMouseListener(this);
	}

	private void createPolygons(Graphics g) {
		
		int[] xPoints = new int[6];
		int[] yPoints = new int[6];
		
		
		//define polygon
		xPoints[0] = startX; //upper right edge
		yPoints[0] = startY;
		xPoints[1] = startX + startX; //middle right edge
		yPoints[1] = startY - startY;
		xPoints[2] = xPoints[1] -  startX; //bottom right edge
		yPoints[2] = yPoints[1] - startY;
		xPoints[3] = xPoints[0] - (2*startX); //bottom left edge
		yPoints[3] = yPoints[2];
		xPoints[4] = xPoints[3] -  startX; //middle left edge
		yPoints[4] = yPoints[3] + startY;
		xPoints[5] = xPoints[4] +  startX; //upper left edge
		yPoints[5] = yPoints[4] + startY;
		
		pointXdifferenceLeft = (- ( 3 * startX)) * zoom;
		pointYdifferenceLeft = startY * zoom;
		pointXdifferenceRight = (3 * startX) * zoom;
		pointYdifferenceRight = startY * zoom;
		pointXdifferenceUp = 0;
		pointYdifferenceUp = ( 2 * startY ) * zoom;
		

		for(int i = 0; i < xPoints.length; i++){
			xPoints[i] = (xPoints[i]*zoom) + 400;
			yPoints[i] = (yPoints[i]*zoom) + 500;
		}

		//define field
		Polygon startigon = new Polygon(xPoints, yPoints, xPoints.length);
		polygons.add(startigon);
		
		
		addPolygons(startigon, 3, pointXdifferenceRight, pointYdifferenceRight);
		addPolygons(startigon, 3, pointXdifferenceLeft, pointYdifferenceLeft);
	
		addPolygons(polygons.get(1), 5, pointXdifferenceUp, pointYdifferenceUp);
		addPolygons(polygons.get(2), 4, pointXdifferenceUp, pointYdifferenceUp);
		addPolygons(polygons.get(3), 3, pointXdifferenceUp, pointYdifferenceUp);
		
		addPolygons(startigon, 6, pointXdifferenceUp, pointYdifferenceUp);
		
		addPolygons(polygons.get(4), 5, pointXdifferenceUp, pointYdifferenceUp);
		addPolygons(polygons.get(5), 4, pointXdifferenceUp, pointYdifferenceUp);
		addPolygons(polygons.get(6), 3, pointXdifferenceUp, pointYdifferenceUp);
		
		
//		//get and draw all polygon-edges
		edges = new TreeSet();
		Pair p;
			for(Polygon polygon: polygons){
				for(int i = 0; i < polygon.xpoints.length; i++){
					p = new Pair(polygon.xpoints[i],polygon.ypoints[i]);
					if(!(edges.contains(p))){
						
						edges.add(p);
						drawQuadrat(p.getX(), p.getY());
						drawCircle(p.getX(), p.getY());
				}}
		}

	}
	
	private void drawQuadrat(int x, int y) {
		  Graphics g = getGraphics();
		  	g.setColor(Color.BLACK);
	        g.drawRect(x+5, y+5, 5,5);
	        g.setColor(Color.BLACK);
	        quadrats.add(new Pair(x,y));
		
	}
	
	private void addPolygons(Polygon startigon, int quantity, int xdifference, int ydifference){
		Polygon polygon = new Polygon(startigon.xpoints, startigon.ypoints,	startigon.xpoints.length);
		int[] secondXPoints = new int[startigon.xpoints.length];
		int[] secondYPoints = new int[startigon.xpoints.length];
		for (int i = 0; i < quantity; i++) {

			for (int j = 0; j < 6; j++) {
				secondXPoints[j] = polygon.xpoints[j] - xdifference;
				secondYPoints[j] = polygon.ypoints[j] - ydifference;
			}

			polygon = new Polygon(secondXPoints, secondYPoints,secondXPoints.length);
			polygons.add(polygon);
			polygon = polygons.peek();

		}
	}


	public void paint(Graphics g) {
		g.setColor(Color.green);
		for (Polygon p : polygons) {
			g.drawPolygon(p.xpoints, p.ypoints, p.xpoints.length);

		}
		for(Pair circle : circles){
			 g.drawOval(circle.getX() - 2, circle.getY() - 2, 4, 4);
		}
		for(Pair quadrat: quadrats){
			g.drawRect(quadrat.getX()-5, quadrat.getY()-5, 10, 10);
			g.drawString(String.valueOf(quadrat.getX()-5) + ", " + String.valueOf(quadrat.getY()-5), quadrat.getX()-5, quadrat.getY()-5);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        if(intersectsQuadrat(x,y)){
        	drawCircle(x,y);
        }
       
    }
    private boolean intersectsQuadrat(int x, int y) {
    	
    	for(Pair quadrat: quadrats){
    		System.out.println("comparing " + x + "," + y + " with " + (quadrat.getX()-5) + "," + (quadrat.getY()-5));
    		if(quadrat.getX()-5 <= x && quadrat.getX() >= x){
    			if(quadrat.getY()-5 >= y && quadrat.getY() <= y){
    				return true;
    			}
    		}
			//g.drawRect(quadrat.getX()-5, quadrat.getY()-5, 10, 10);
		}
		return false;
	}

    public void drawRoundButton(int x, int y){
    	add(new RoundButton("test"));
    	
    }
    
	public void drawCircle(int x, int y) {
		
        Graphics g = getGraphics();
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.BLACK);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        circles.add(new Pair(x,y));
    }

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	   
}
