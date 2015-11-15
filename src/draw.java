import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thoughtworks.xstream.XStream;




public class draw extends JFrame
implements ActionListener, MouseListener,MouseMotionListener{ 
	Stack<List<Shape>> undostack = new Stack<List<Shape>>();
	Stack<List<Shape>> redostack = new Stack<List<Shape>>();
	XStream xstream = new XStream();
	public static String fileChose()
	{
	    JFileChooser fc= new JFileChooser();
	    int ret = fc.showOpenDialog(null);

	               if (ret== JFileChooser.APPROVE_OPTION) 
	               {
	             File file = fc.getSelectedFile();
	             String filename= file.getAbsolutePath();
	             return filename;
	            }

	           else
	             return null;
	 }
	
  int xval;
  int yval;
    int x1;
    int y1;
    int x2;
    int y2;
    public Color colfg;
    public Color colbg;
    Shape moving;
    Shape resize;
    boolean resizeLeft = false;
    boolean resizeRight = false;
    boolean resizeTop = false;
    boolean resizeBottom = false;
	  private List<Shape> shapes = new ArrayList<Shape>();

    Point startDrag=new Point(), endDrag=new Point();

	// static JLabel colorlabel = new JLabel(" Color ");
	    public static JLabel xlab = new JLabel("0");
	    public static JLabel ylab = new JLabel("0");
	     static String shapeType = new String();

	     
	 
	    static JFrame frame1 = new JFrame();
	    static JButton quitbutton = new JButton("Quit");
	    static JButton infobutton = new JButton("Import");
	    static JButton clearbutton = new JButton("Clear");
	    static JButton savebutton = new JButton("Save");
	    static JButton loadbutton = new JButton("load");
	    static JButton redo = new JButton("Re-do");
	    static JButton undo = new JButton("Undo");
	    
	    
	    static Icon Triangle = new ImageIcon("triangle.jpg");
	    
	    static JButton triangle = new JButton(Triangle);
	    static Icon Circle = new ImageIcon("circle.jpg");

	    static JButton circle = new JButton(Circle);
	    static Icon Square = new ImageIcon("square.jpg");
	    static JButton square = new JButton(Square);
	    
	    static Icon Rectangle= new ImageIcon("rectangle.jpg");
	    static JButton rectangle = new JButton(Rectangle);
	    
	    static Icon Oval = new ImageIcon("oval.jpg");

	    static JButton oval = new JButton(Oval);
	    static Icon Line = new ImageIcon("line.jpg");

	    static JButton line = new JButton(Line);

	    static JButton selectcolor = new JButton("Select Color");
	    static JButton selectbg = new JButton("Select Background");
	    public static Color col1 = Color.WHITE;
	    private static Color col2 = Color.WHITE;
	    static JPanel panel2 = new JPanel();
	    static JPanel panel1 = new JPanel();

	    void addPanel1(){
	       // colorlabel.setBackground(Color.WHITE);

	        // panel1.setPreferredSize(new Dimension(600,600));
		       panel1.setBorder(BorderFactory.createLineBorder(Color.PINK, 10));

	         //panel1.setBounds(100, 200, 400, 400);
	         panel1.setBackground(Color.WHITE);
	     }

	  public  void addPanel2(){
//	       draw inst1 = new draw();

		  

	    	triangle.setSize(100, 100);
	    	 panel2.add(triangle);
	    	 panel2.add(rectangle);
	    	 panel2.add(square);
	    	 panel2.add(oval);
	    	 panel2.add(circle);
	    	 panel2.add(line);
	    	// panel2.add(colorlabel);
	         panel2.add(selectbg);
	         panel2.add(selectcolor);
	         
	    	 
	    	    triangle.setActionCommand("Triangle");
	    	    oval.setActionCommand("Oval");
	    	   circle.setActionCommand("Circle");
	    	    line.setActionCommand("Line");
	    	   square.setActionCommand("Square");
	    	    rectangle.setActionCommand("Rectangle");
	            circle.setBackground(Color.WHITE);
	            triangle.setBackground(Color.WHITE);
	            rectangle.setBackground(Color.WHITE);
	            square.setBackground(Color.WHITE);
	            oval.setBackground(Color.WHITE);
	            line.setBackground(Color.WHITE);

//	            triangle.setSize(25, 25);
//	  		  circle.setSize(25, 25);
//	  		  line.setSize(25, 25);
//	  		  square.setSize(25, 25);
//	  		  oval.setSize(25, 25);
//	  		  rectangle.setSize(25, 25);

	    	    xlab.setMinimumSize(new Dimension(30,30));
	            ylab.setMinimumSize(new Dimension(30,30));
	            xlab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            ylab.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            panel2.add(new JLabel(" X: "));
	            panel2.add(xlab);
	            panel2.add(new JLabel(" Y: "));
	            panel2.add(ylab);
	    	 panel2.add(savebutton);
	         panel2.add(clearbutton);
	         panel2.add(quitbutton);
	         panel2.add(infobutton);
	         panel2.add(loadbutton);
	         panel2.add(redo);
	         panel2.add(undo);
	         quitbutton.addActionListener(this);
	         infobutton.addActionListener(this);
	         clearbutton.addActionListener(this);
	         savebutton.addActionListener(this);
	         redo.addActionListener(this);
	         undo.addActionListener(this);
	         loadbutton.addActionListener(this);
	         line.addActionListener(this);
	         rectangle.addActionListener(this);
	         triangle.addActionListener(this);
	         square.addActionListener(this);
	         oval.addActionListener(this);
	         circle.addActionListener(this);
	  
	         selectcolor.addActionListener(this);
	         selectbg.addActionListener(this);
	         
	    	 
	    }
	    
	    public draw() {
	        this.setTitle("paint");
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // add check box group
	        addPanel2();
	        addPanel1();
	        panel2.setPreferredSize(new Dimension(1320,100));
	        panel1.setPreferredSize(new Dimension(1320,600));

	        this.addMouseListener(this);
	        this.setLayout(new BorderLayout());
	        this.add(panel2, BorderLayout.NORTH);
	       
	        this.add(panel1,BorderLayout.SOUTH);
	       // panel1.setBounds(100, 100, 400, 400);
	        this.addMouseMotionListener(this);
	    }
 

		@Override
		public void mouseDragged(MouseEvent me) {
			// TODO Auto-generated method stub
			endDrag = new Point(me.getX(),me.getY());
			endDrag = new Point(me.getX(),me.getY());
	 		 int dx=endDrag.x-startDrag.x;
	 		 int dy=endDrag.y-startDrag.y;
	 		 if(moving!=null)
	 		 {
	 			 moving.moveby(dx,dy);
	 			 startDrag=me.getPoint();
	 		 }
	 		 if (resizeLeft) {
	 		      resize.position.x+=dx;
	 		      resize.setWidth(resize.getWidth()-dx);
	 		     startDrag=me.getPoint();
	 		      }
	 		 if (resizeRight) {
	 			resize.setWidth(resize.getWidth()+dx);
	 		     startDrag=me.getPoint();
	 		      }
	 		 if (resizeTop) {
	 		      resize.position.y+=dy;
	 		      resize.setHeight(resize.getHeight()-dy);
	 		     startDrag=me.getPoint();
	 		      }
	 		 if (resizeBottom) { 
	 			resize.setHeight(resize.getHeight()+dy) ;
	 		     startDrag=me.getPoint();
	 		      }
	         repaint();
			
		}
//		public void AuxClass4(Color input1, Color input2){
//	        colfg=input1;
//	        colbg=input2;
//	       // strokesize = input3;
//	        
//	        this.setBackground(colbg);
//	        //this.setMaximumSize(new Dimension(500,380));
//	        this.addMouseMotionListener(this);
//	 
//	    }
		 public void changeColor(){
		    	//see if selected change color attribute
		    	//change current color
		    	
		        colfg = JColorChooser.showDialog(this, "Select color", colfg);
		       // draw.colorlabel.setBackground(colfg);
		      //  draw.colorlabel.setForeground(colfg);
		        repaint();
		    }
		    public void changeBg(){
		        colbg=JColorChooser.showDialog(this, "Select color", colbg);
		       // colfg=colbg;
		        panel1.setBackground(colbg);
		      //  draw.colorlabel.setBackground(colfg);
		      //  draw.colorlabel.setForeground(colfg);
		        repaint();
		        
		 
		    }

		@Override
		 public void mouseMoved(MouseEvent arg0) {
	        xval = arg0.getX();
	        yval = arg0.getY();
	        String xvalret = Integer.toString(xval);
	        String yvalret = Integer.toString(yval);
	        draw.xlab.setText(" " + xvalret + " ");
	        draw.ylab.setText(" " + yvalret + " ");
	 
	    }

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent me) {
			List<Shape> newList = new ArrayList<Shape>();
			newList.addAll(shapes);
 	        undostack.push(newList);
			startDrag = new Point(me.getX(),me.getY());
	         endDrag = startDrag;
	    	 int flag=0;
	    	 for (Shape p : shapes) {
	    		 
                 if (p.isAt(me.getPoint())) {
                	 
                	 if (me.getX()>= p.getPosition().x-3 && me.getX()<=p.getPosition().x+p.getWidth()+3 &&
                		        me.getY()>=p.getPosition().y-3 && me.getY()<=p.getPosition().y+p.getHeight()+3) {
                		 
                		 if (me.getX()<=p.getPosition().x+3){ resizeLeft = true;resize=p;}
                	      else if (me.getX()>=p.getPosition().x+p.getWidth()-3) {resizeRight = true;resize=p;}
                	      else if (me.getY()<=p.getPosition().y+3){ resizeTop = true;resize=p;}
                	      else if (me.getY()>=p.getPosition().y+p.getHeight()-3){ resizeBottom = true;resize=p;}
                	      else moving=p;
                		 
                	 }
                     flag=1;
                     startDrag = me.getPoint();
                     break;
                 }
                 
             }
	    	 if(flag==0)
		 		startDrag = new Point(me.getX(),me.getY());
		         endDrag = startDrag;
		        repaint();
	        repaint();

		}

		@Override
		public void mouseReleased(MouseEvent me) {
	 		// TODO Auto-generated method stub

	 		Shape s=null;
	 	        if (shapeType.equals("Rectangle")) {
	 	        
	 	        	x1=startDrag.x;y1=startDrag.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	    Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Rectangle();
	 	    	   s.setColor(colfg);
	 	    	    s.setPosition(xx);
	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(y1-y2));
	 	    	   
	 	         
	 	        } 
	 	        else if(shapeType.equals("Oval"))
	 	        {
	 	        
	 	        	x1=startDrag.x;
	 	        	y1=startDrag.y;
	 	    	    x2=me.getX();y2=me.getY();
	 	    	   Point xx=new Point();
	 	    	    xx.x=Math.min(x1,x2);
	 	    	    xx.y=Math.min(y1,y2);
	 	    	   s = new Oval();
	 	    	    s.setPosition(xx);
		 	    	   s.setColor(colfg);

	 	    	    s.setWidth(Math.abs(x1-x2));
	 	    	    s.setHeight(Math.abs(y1-y2));
	 	           
	 	          
	 	        }
	 	        else if(shapeType.equals("Line"))
	 	        {
	 	    	    s = new Line();
		 	    	   s.setColor(colfg);

	 	    	    s.setPosition(startDrag);
	 	    	    s.setWidth(endDrag.x);
	 	    	    s.setHeight(endDrag.y);
	 	         
	 	        }
	 	        if (s != null) {
	 	            shapes.add(s);
	 	            startDrag = null;
	 	            endDrag = null;
	 	           repaint();
	 	        }
	 		
	 	}
		@Override
		public void actionPerformed(ActionEvent arg0){
			// TODO Auto-generated method stub
            if (arg0.getSource()==quitbutton){
                System.exit(0);
            }
//            else if(arg0.getSource()==infobutton){
//            	String path = fileChose();
//    	    	path = path.replaceAll("\\\\", "\\\\\\\\");
//            	//String s=MainFrame.name;
//    			Class Rlc = MyClassLoader.loadClass("Rectangle",path);	
//    			Rlc.
//    			Constructor c = Rlc.getDeclaredConstructor(Coordinate.class,Integer.class , Integer.class );				 
//    			Object Rlo = c.newInstance (new Coordinate(x, y),0,0) ;				
//    			Class[] param1=new Class [1];
//    			param1[0]=Graphics.class;
//    			Method m1 = Rlc.getMethod("Draw" , param1 ) ;
//    			SketchComponent C=(SketchComponent) Rlo ;
//    			shapes.add(C);
//            }
            else if(arg0.getSource()==selectcolor){
 
                changeColor();
 
            }
            else if(arg0.getSource()==selectbg){
            	changeBg();
            	 
            }
//            else if(arg0.getSource()==clearbutton){
//                panel1.colfg = panel1.colbg;
//                colorlabel.setBackground(panel1.colfg);
//                colorlabel.setForeground(panel1.colfg);
//                panel1.setForeground(null);
// 
//            }
            else if(arg0.getSource()==savebutton){
            	String xml = xstream.toXML(shapes);
            	
            	JFrame parentFrame = new JFrame();
            	 
            	JFileChooser fileChooser = new JFileChooser();
            	fileChooser.setDialogTitle("Specify a file to save");   
            	 
            	int userSelection = fileChooser.showSaveDialog(parentFrame);
            	 
            	if (userSelection == JFileChooser.APPROVE_OPTION) {
            	    File fileToSave = fileChooser.getSelectedFile();
            	   // System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            	    String path = fileToSave.getAbsolutePath();
        	    	path = path.replaceAll("\\\\", "\\\\\\\\");
        	    	//System.out.println(path);
            	    try{
            	    	
            	        Writer output = null;
            	        File file = new File(path);
            	        output = new BufferedWriter(new FileWriter(file));

            	        output.write(xml);

            	        output.close();
            	       // System.out.println("File has been written");

            	    }catch(Exception e){
            	        //System.out.println("Could not create file");
            	    }
            	}
            	
            	
            }
            else if(arg0.getSource()==loadbutton){

            	String path = fileChose();
    	    	path = path.replaceAll("\\\\", "\\\\\\\\");

            	try {
					BufferedReader in =new BufferedReader(new FileReader(path));
					StringBuilder out = new StringBuilder();
			        String line;
			        while ((line = in.readLine()) != null) {
			            out.append(line);
			        }
			       String xml = out.toString();
					shapes = (List<Shape>) xstream.fromXML(xml);
					repaint();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            }
            else if(arg0.getSource()==undo){
         
            	if(!undostack.isEmpty()){
            		List<Shape> ff = new ArrayList<Shape>();
            		ff.addAll(shapes);
         	        redostack.push(ff);
            		//shapes = undostack.peek();
            		List<Shape> nn = new ArrayList<Shape>();
        			nn.addAll(undostack.pop());
        			shapes.clear();
        			shapes.addAll(nn);

            		repaint();
            	}
            	 
            }
            else if(arg0.getSource()==redo){
 

            	if(!redostack.isEmpty()){ 
            		List<Shape> cc = new ArrayList<Shape>();
            		cc.addAll(shapes);
         	        undostack.push(cc);
            		List<Shape> ff = new ArrayList<Shape>();
            		ff.addAll(redostack.pop());
            		shapes.clear();
            		shapes.addAll(ff);
            		repaint();

            	}
            }
	 		shapeType = arg0.getActionCommand();

		}
		public void paint(Graphics g) {
	        super.paintComponents(g);
	       // Graphics2D g2 = (Graphics2D)g; 
	        g.setColor(colfg);

	        for (Shape shape : shapes){ 
	          shape.draw(g);
	        }
	        
	        if (startDrag != null && endDrag != null) {
	        	 x1=startDrag.x;y1=startDrag.y;
	      	    x2=endDrag.x;y2=endDrag.y;
	      	  Point xx=new Point();
 	    	    xx.x=Math.min(x1,x2);
 	    	    xx.y=Math.min(y1,y2);
 	    	    Shape r=null;
	   
	 	        if (shapeType.equals("Rectangle")) {
	 	        	
	 	        	 r=new Rectangle();
	 	        	 r.setPosition(xx);
		 	    	 r.setWidth(Math.abs(x1-x2));
		 	    	 r.setHeight(Math.abs(y1-y2));
		 	    	 r.setColor(colfg);
	 	             r.draw(g);
	 	        
	 	        } 
	 	        else if(shapeType.equals("Oval"))
	 	        {
	 	      
	 	        	  r=new Oval();
	 	        	  r.setPosition(xx);
			 	      r.setWidth(Math.abs(x1-x2));
			 	      r.setHeight(Math.abs(y1-y2));
			 	    	 r.setColor(colfg);

	 	              r.draw(g);;
	 	    
	 	        }
	 	        else if(shapeType.equals("Line"))
	 	        {
	 	        	    r=new Line();
	 	        	    r.setPosition(startDrag);
			 	    	r.setWidth(x2);
			 	    	r.setHeight(y2);
			 	    	 r.setColor(colfg);

	 	                r.draw(g);;
	 	     
	 	        }
	          }
		}
}
