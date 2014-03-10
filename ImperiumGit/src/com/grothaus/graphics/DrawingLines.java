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

import com.grothaus.GamePlay.Pair;

public class DrawingLines extends Applet implements MouseListener {

	Stack<Polygon> polygons = new Stack<>();
	ArrayList<Pair> circles = new ArrayList<>();
	Set<Pair> edges;
	private Graphics g;

	public void init() {
		this.setSize(800, 800);
		//setBackground(Color.black);
		createPolygons(g);
		addMouseListener(this);
	}

	private void createPolygons(Graphics g) {

		int[] xPoints = { 110, 100, 110, 125, 135, 125 };
		int[] yPoints = { 100, 115, 130, 130, 115, 100 };

		for(int i = 0; i < xPoints.length; i++){
			xPoints[i] = xPoints[i]+400;
			yPoints[i] = yPoints[i]+400;
		}
		
		Polygon startigon = new Polygon(xPoints, yPoints, xPoints.length);

		polygons.add(startigon);
		
		
		//1,2,3
		addPolygonsLeft(startigon, 3);
		//4,5,6
		addPolygonsRight(startigon, 3);
		//7,8,9,10,11,12
		addPolygonsTop(polygons.get(1),5);
		//13,14,15,16
		addPolygonsTop(polygons.get(2),4);
		//17,18,19
		addPolygonsTop(polygons.get(3),3);
		//20,21,22,23,24,25
		addPolygonsTop(startigon, 6);
		
		addPolygonsTop(polygons.get(4), 5);
		addPolygonsTop(polygons.get(5), 4);
		addPolygonsTop(polygons.get(6), 3);
		
		//get and draw all polygon-edges
		edges = new TreeSet();
		Pair p;
			for(Polygon polygon: polygons){
				for(int i = 0; i < polygon.xpoints.length; i++){
					p = new Pair(polygon.xpoints[i],polygon.ypoints[i]);
					if(!(edges.contains(p))){
						
						edges.add(p);
						drawCircle(p.getX(), p.getY());
				}}
		}

	}
	
	private void addPolygonsTop(Polygon startigon, int q) {
		Polygon polygon = new Polygon(startigon.xpoints, startigon.ypoints,
				startigon.xpoints.length);
		int[] secondXPoints = new int[startigon.xpoints.length];
		int[] secondYPoints = new int[startigon.xpoints.length];
		for (int i = 0; i < q; i++) {

			// move Points up (y) for 30
			for (int j = 0; j < 6; j++) {
				secondXPoints[j] = polygon.xpoints[j] - 0;
				secondYPoints[j] = polygon.ypoints[j] - 30;
			}

			polygon = new Polygon(secondXPoints, secondYPoints,
					secondXPoints.length);
			polygons.add(polygon);
			polygon = polygons.peek();

		}
		
		

	}

	private void addPolygonsLeft(Polygon startigon, int q) {
		Polygon polygon = new Polygon(startigon.xpoints.clone(), startigon.ypoints.clone(),
				startigon.xpoints.length);
		int[] secondXPoints = new int[startigon.xpoints.length];
		int[] secondYPoints = new int[startigon.xpoints.length];
		
		for (int i = 0; i < q; i++) {

			// move Points left (x) for 25 and up (y) for 15
			for (int j = 0; j < 6; j++) {
				secondXPoints[j] = polygon.xpoints[j] - 25;
				secondYPoints[j] = polygon.ypoints[j] - 15;
			}

			polygon = new Polygon(secondXPoints, secondYPoints,
					secondXPoints.length);
			polygons.add(polygon);
			polygon = polygons.peek();

		}
		
	}

	private void addPolygonsRight(Polygon startigon, int q) {
		Polygon polygon = new Polygon(startigon.xpoints.clone(), startigon.ypoints.clone(),
				startigon.xpoints.length);
		int[] secondXPoints = new int[startigon.xpoints.length];
		int[] secondYPoints = new int[startigon.xpoints.length];
		
		for (int i = 0; i < q; i++) {
			
			
			// move Points right (x) for 25 and up (y) for 15
			for (int j = 0; j < polygon.xpoints.length; j++) {
				secondXPoints[j] = polygon.xpoints[j] + 25;
				secondYPoints[j] = polygon.ypoints[j] - 15;
			}
			polygon = new Polygon(secondXPoints, secondYPoints,
					secondXPoints.length);
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
	}
	 int radius = 2;
	@Override
	public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
       
        if(isPolygonEdge(x,y)){
        	drawCircle(e.getX()-(radius/2), e.getY()-(radius/2));
        }
    }
    private boolean isPolygonEdge(int x, int y) {
		return true;
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
