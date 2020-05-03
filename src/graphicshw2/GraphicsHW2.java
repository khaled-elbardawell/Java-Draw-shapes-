/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicshw2;

////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;



/////////////////////////////////////////////////////////////////////
abstract class GraphicsObj {
	private Color color;
//    // Create an object with default color blue
    public GraphicsObj(){ 
        this.color=Color.BLUE;
    }
    // Create an object with the specified color
  	public GraphicsObj(Color color){
            this.color =color;
    }
    public Color getColor() {
    	return color;
    }
  	public void setColor(Color color) {
            this.color=color;
           }
    public abstract void draw(Graphics g);
}
 ////////////////////////////////////////////////////////////////////
class MyLine extends GraphicsObj {
 	private int x1, y1, x2, y2;
    // Create a line with the two endpoints(x1,y1) and (x2,y2) with the default color
    public MyLine(int x1, int y1, int x2, int y2){
        super();
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
//        Line l=new Line(10,10,10,10);
    }
    // Create a line with the two endpoints(x1,y1) and (x2,y2) with the specified color
    public MyLine(int x1, int y1, int x2, int y2, Color color){ 
        super(color);
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    } 
    public void draw(Graphics g){
      g.setColor(this.getColor());
      g.drawLine(x1, y1, x2, y2);
    }
     
}
//////////////////////////////////////////////////////////////////////
class Ellipse extends GraphicsObj {
  private boolean fill = false;
    private int x=20, y=20, w=100, h=150;
   // Create an ellipse with the default color and the default values x,y,w,h
    public Ellipse(){ 
        super(); 
    } 
    //Create an ellipse with the default color and the specified values x,y,w,h
    public Ellipse(int x, int y, int w, int h){
        super();
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    //Create an ellipse with the specified color and the specified values x,y,w,h
    public Ellipse(int x, int y, int w, int h,Color color){
        super(color);
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    } 
    //(xc,yc) is the center point of the ellipse, r1 the horizontal radius, r2 is the vertical radius
    public void set (int xc, int yc, int r1, int r2){
        this.x=xc-r1;
        this.y=yc-r2;
        this.w=2*r1;
        this.h=2*r2;
    }
    // Filling the ellipse if fill is true
    public void setFill(boolean fill){
            this.fill=fill;
    }
    public void draw(Graphics g){
        if(this.fill){
            g.setColor(this.getColor());
            g.fillOval(x, y, w, h);
        }else{
            g.setColor(this.getColor());
            g.drawOval(x, y, w, h);
        }
    }
}
//////////////////////////////////////////////////////////////////////
class Circle extends Ellipse {
  	// Create a circle with the center point (xr,yr) and the default color 
  	public Circle (int xc, int yc, int r){
            super(xc-r,yc-r,2*r,2*r);
          }
  	// Create a circle with the center point (xr,yr), radius r and  the specified color c 
  	public Circle (int xc, int yc, int r, Color c){
             super(xc-r,yc-r,2*r,2*r,c);
          }
}
//////////////////////////////////////////////////////////////////////
class MyPolygon extends GraphicsObj {   
	private boolean fill = false;
  	private int x[]={100,200,100,200}, y[]={100,100,200,200};
  	public MyPolygon(){
            super();
  	}
  	// Create a polygon with the default color
  	public MyPolygon (int x[], int y[]){
            super();
            this.x=x;
            this.y=y;
  	}
    // Create a polygon with the specified color
    public MyPolygon (int x[], int y[], Color color){ 
            super(color);
            this.x=x;
            this.y=y;
    }
    // Fill the polygon if fill is true
    public void setFill(boolean fill){
        this.fill=fill;
    }
    public void draw(Graphics g){
     if(this.fill){
            g.setColor(this.getColor()); 
            g.fillPolygon(x, y,x.length);
        }else{
            g.setColor(this.getColor());
            g.drawPolygon(x, y,x.length);
        }
    }
}
//////////////////////////////////////////////////////////////////////
class MyRectangle extends MyPolygon { 
	// create a rectangle with the upper left point(x1,y1),the lower right point(x2,y2) and default color
	public MyRectangle(int x1, int y1, int x2, int y2){
          super(new int[]{x1,x2,x2,x1},new int[] {y1,y1,y2,y2});
       
        } 
  	//create a rectangle with the upper left point(x1,y1) , width and height and specified color
  	public MyRectangle(int x, int y, int w, int h,Color color){
              super(new int[]{x,x+w,x+w,x},new int[] {y,y,y+h,y+h},color);
        }
}
///////////////////////////////////////////////
class Triangle extends MyPolygon { 
 	// Create a rectangle with the deault color
 	public Triangle(int x1,int y1, int x2, int y2, int x3, int y3){
             super(new int[]{x1,x2,x3},new int[] {y1,y2,y3});
    }
    //Create a rectangle with the specified color
  	public Triangle(int x1,int y1, int x2, int y2, int x3, int y3, Color color){
             super(new int[]{x1,x2,x3},new int[] {y1,y2,y3},color);
   
    }
}
//////////////////////////////////////////////////////////////////////////
class DrawingPanel extends JPanel  {
   private ArrayList <GraphicsObj>complexObject=  new ArrayList(); 


    // Create a complex obejct which contains of some simple objects 
    //For example a flag contains of anumber of objects of type GraphicsOj
    public DrawingPanel(){
            this.setBounds(0,35,1200,1000);

    }
    // add an object of type GraphicsObj to the complex object
    public void addObj(GraphicsObj obj){
        complexObject.add(obj);
    }
    public void paint(Graphics g){
        super.paint(g);
        for(int i=0;i<complexObject.size();i++){
            complexObject.get(i).draw(g);
        }
    }
      
}
//////////////////////////////////////////////////////////////////////////
public class GraphicsHW2 extends JFrame implements ActionListener  {

   private  DrawingPanel drawing = new DrawingPanel();
   private  Random r=new Random();
   private  Insets in=getInsets();
   private  int leftBar = in.left;
   private  int topBar = in.top+30;
   private  int x[],y[];
   JMenu m1;
   JMenuItem mi1,mi2,mi3,mi4,mi5,mi6;
   

 public GraphicsHW2(){
      setLayout(null);
      JMenuBar bar = new JMenuBar();
      bar.setBounds(0, 0, 700, 30);
      add(bar);
      m1 = new JMenu("Draw");
      bar.add(m1);
      m1.setSelected(true);
      
            
      mi1=new JMenuItem("Line");
      m1.add(mi1);
      m1.setSelected(true);
      
      mi2=new JMenuItem("Ellipse");
      m1.add(mi2);
        
       mi3=new JMenuItem("Triangle");
      m1.add(mi3);
     
       mi4=new JMenuItem("Rectangle");
       m1.add(mi4);
     
       mi5=new JMenuItem("Circle");
       m1.add(mi5);
      
       mi6=new JMenuItem("Polygon");
       m1.add(mi6);
      
      mi1.addActionListener(this);
      mi2.addActionListener(this);
      mi3.addActionListener(this);
      mi4.addActionListener(this);
      mi5.addActionListener(this);
      mi6.addActionListener(this);
          
      add(drawing);  
     this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);    
     this.setLayout(new BorderLayout());
     this.setVisible(true);
     this.setSize(700,700);
        
    }

 
        public static void main(String args[]){
          new GraphicsHW2();
      }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g = getGraphics();
        int x,y,x2,y2,h,w,x3,y3,ployX[],ployY[],num;
        try{
            
        if(e.getSource() == mi1){
            
            String strX = JOptionPane.showInputDialog(null, "X1 ");
             x =  Integer.valueOf(strX);
            
            String strY = JOptionPane.showInputDialog(null, "Y1 ");
             y =  Integer.valueOf(strY);
            
             String strX2 = JOptionPane.showInputDialog(null, "X2 ");
             x2 =  Integer.valueOf(strX2);
            
            String strY2 = JOptionPane.showInputDialog(null, "Y2 ");
             y2 =  Integer.valueOf(strY2);
            
            int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
            if(state == 1){
                MyLine l1=new MyLine( leftBar+x, topBar+y,leftBar+x2 ,topBar+y2 );
                drawing.addObj(l1);
                drawing.repaint();
            }else if(state == 0){
                     Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                     MyLine l1=new MyLine( leftBar+x, topBar+y,leftBar+x2 ,topBar+y2,newColor );
                     drawing.addObj(l1);
                     drawing.repaint();
            }
            
            
        }else   if(e.getSource() == mi2){
            
                String strX = JOptionPane.showInputDialog(null, "X ");
                 x =  Integer.valueOf(strX);

                String strY = JOptionPane.showInputDialog(null, "Y ");
                 y =  Integer.valueOf(strY);

                 String strW = JOptionPane.showInputDialog(null, "width ");
                 w =  Integer.valueOf(strW);

                String strH = JOptionPane.showInputDialog(null, "hight ");
                 h =  Integer.valueOf(strH);

                int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
                
            if(state == 1){
                Ellipse ee = new Ellipse(leftBar+x,topBar+y,w,h);
                drawing.addObj(ee);
                drawing.repaint();
            }else if(state == 0){
                    Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    Ellipse ee = new Ellipse(leftBar+x,topBar+y,w,h,newColor);
                    ee.setFill(true);
                    drawing.addObj(ee);         
                    drawing.repaint();
            }
            
            
        }else   if(e.getSource() == mi3){
            
                String strX1 = JOptionPane.showInputDialog(null, "X1 ");
                 x =  Integer.valueOf(strX1);

                String strY1 = JOptionPane.showInputDialog(null, "Y1 ");
                 y =  Integer.valueOf(strY1);

                  String strX2 = JOptionPane.showInputDialog(null, "X2 ");
                 x2 =  Integer.valueOf(strX2);

                String strY2 = JOptionPane.showInputDialog(null, "Y2 ");
                 y2 =  Integer.valueOf(strY2);
                 
                  String strX3 = JOptionPane.showInputDialog(null, "X3 ");
                  x3 =  Integer.valueOf(strX3);

                 String strY3 = JOptionPane.showInputDialog(null, "Y3 ");
                 y3 =  Integer.valueOf(strY3);
                

                int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
                
            if(state == 1){
                
                Triangle t =   new Triangle( leftBar+x,topBar+y,leftBar+x2,topBar+y2,leftBar+x3,topBar+y3 );
                drawing.addObj(t);
                drawing.repaint();
            }else if(state == 0){
                 Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                 Triangle t =   new Triangle( leftBar+x,topBar+y,leftBar+x2,topBar+y2,leftBar+x3,topBar+y3,newColor );
                 t.setFill(true);
                drawing.addObj(t);
                drawing.repaint();                   
            }
            
            
        }else   if(e.getSource() == mi4){
            
                String strX = JOptionPane.showInputDialog(null, "X ");
                 x =  Integer.valueOf(strX);

                String strY = JOptionPane.showInputDialog(null, "Y ");
                 y =  Integer.valueOf(strY);

                 String strW = JOptionPane.showInputDialog(null, "width ");
                 w =  Integer.valueOf(strW);

                String strH = JOptionPane.showInputDialog(null, "hight ");
                 h =  Integer.valueOf(strH);

                int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
                
            if(state == 1){
              
                MyRectangle r =  new MyRectangle( leftBar+x, topBar+y ,w,h);
                drawing.addObj(r);
                drawing.repaint();
     
            }else if(state == 0){
                    Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    MyRectangle r =  new MyRectangle( leftBar+x, topBar+y ,w,h,newColor );
                    r.setFill(true);
                    drawing.addObj(r);         
                    drawing.repaint();
            }
            
            
        }else   if(e.getSource() == mi5){
            
                String strX = JOptionPane.showInputDialog(null, "X center ");
                 x =  Integer.valueOf(strX);

                String strY = JOptionPane.showInputDialog(null, "Y center");
                 y =  Integer.valueOf(strY);

                 String strW = JOptionPane.showInputDialog(null, "R ");
                 w =  Integer.valueOf(strW);

              

                int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
                
            if(state == 1){
                 Circle c = new Circle(leftBar+x,topBar+y,w);
                drawing.addObj(c);
                drawing.repaint();
            }else if(state == 0){
                    Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    Circle c = new Circle (leftBar+x,topBar+y,w,newColor);
                    c.setFill(true);
                    drawing.addObj(c);        
                    drawing.repaint();
            }
            
            
        }else   if(e.getSource() == mi6){
               
                 String strNum = JOptionPane.showInputDialog(null, "Nubmer points ");
                 num =  Integer.valueOf(strNum);
                 
                ployX=new int[num];
                ployY=new int[num];
                for(int i =0;i<num;i++){

                     String arr = JOptionPane.showInputDialog(null, "X");
                     ployX[i] =Integer.valueOf(arr)+leftBar; 
                     
                     arr = JOptionPane.showInputDialog(null, "Y");
                     ployY[i] =Integer.valueOf(arr)+topBar; 
                   
                }

                int state =JOptionPane.showConfirmDialog(null, "Draw Fill shape ","Fill",0);
                
            if(state == 1){
           
             MyPolygon p =new MyPolygon(ployX,ployY);
             drawing.addObj(p) ;  
             drawing.repaint();
            }else if(state == 0){
              Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
              MyPolygon p =new MyPolygon(ployX,ployY,newColor);
              p.setFill(true);
              drawing.addObj(p);  
              drawing.repaint() ;         
            }
            
            
        }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "please enter correct value !! ");
                    }
    }

 

    }

//////////////////////////////////////////////////////////////////////////
