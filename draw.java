import java.awt.*;
import javax.swing.JApplet;
import java.io.*;
public class draw extends JApplet
{
  private String FILENAME = "sources/radii.txt";
  private Circle[] circles;
  private int currentCirc = 1;
  private int circToDrawFrom = 0;
  private int circleCounter = 0;
  //-------------------------------------------------
  // Use Graphics methods to add content to the drawing canvas
  //-------------------------------------------------
  public void paint(Graphics page) {
    readRadii();
    //set background
    page.setColor(Color.black);

    circles[0].updatePosition(600,400);
    int count = 0;
    drawFirstCircle(page);
    boolean beenHereBefore = false;
    for (int i = 1; i < circles.length; i++){
      //System.out.println("Drawing circle: "+ i);
      if (i == 1){
        if (beenHereBefore == false){
          beenHereBefore = true;
        } else {
          //been here before so break
          noMoreCircles();
        }
      }
      if (currentCirc > circles.length- 2){
        //System.out.println("No more circles to draw");
        noMoreCircles();
      }
      drawNextCircle(page);
      currentCirc++;
    }
  }

  public void noMoreCircles(){
    System.out.println("No more circles to draw");
    while (true){

    }
  }

  public void drawFirstCircle(Graphics page){
    //draw(circles[0].x, circles[0].y, circles[0].radius, page);
    page.drawOval(circles[0].x - circles[0].radius/2, circles[0].y - circles[0].radius/2, circles[0].radius, circles[0].radius);
  }

  public void drawNextCircle(Graphics page){
    //int counter = count;
    int previousCircleRadius = circles[circToDrawFrom].radius;
    int currentRadius = 0;
    currentRadius = circles[currentCirc].radius;

    int bothRadii = currentRadius + previousCircleRadius;
    int radiusMultiplier = 1+bothRadii/2;

    while (circleCounter < 361){
      //System.out.println("circleCounter: "+circleCounter);
      //draw((int)(radiusMultiplier*Math.cos(Math.toRadians(counter))), (int)(radiusMultiplier*Math.sin(Math.toRadians(counter))), page);

      int potentialX = (int)(radiusMultiplier*Math.cos(Math.toRadians(circleCounter)));
      int potentialY = (int)(radiusMultiplier*Math.sin(Math.toRadians(circleCounter)));
      boolean validLocation = testLocation(potentialX, potentialY);
      if (validLocation) {
        draw(potentialX, potentialY, page);
        break;
        //return counter;
      }
      circleCounter += 1;
    }
    if (circleCounter > 359){
      circleCounter = 0;
      circToDrawFrom++;
    }
  }


  //this function tests the validity of the potential location
  public boolean testLocation(int tX, int tY){
    int currentRadius = 0;
    currentRadius = circles[currentCirc].radius;
    int testX = circles[circToDrawFrom].x + tX;
    int testY = circles[circToDrawFrom].y + tY;
    for (int i = 0; i < currentCirc; i++){
      //System.out.println("comparing with circle number: " + i);
      int minDistance = (circles[i].radius + currentRadius)/2;
      int baseX = circles[i].x;
      int baseY = circles[i].y;
      int xDist = (baseX - testX);
      int yDist = (baseY-testY);
      try {
        Thread.sleep(0);
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

      //System.exit(0);
      if (Math.sqrt(xDist*xDist+yDist*yDist)<minDistance){
        //System.out.println("false");
        return false;
      }

    }
    //System.out.println("true");
    return true;
  }

  public void draw(int x, int y, Graphics page){
    int radius = circles[currentCirc].radius;
    if (false){

    } else {
      page.drawOval(circles[circToDrawFrom].x + x - radius/2, circles[circToDrawFrom].y + y-radius/2,radius,radius);
    }
    circles[currentCirc].updatePosition(circles[circToDrawFrom].x + x, circles[circToDrawFrom].y+y);

    //System.out.println("updatingPosition to: "+ (circles[circToDrawFrom].x + x) (circles[circToDrawFrom].y);
  }

  public void readRadii(){
    BufferedReader br = null;
		FileReader fr = null;
		try {

      //first count them so we can allot an array
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));
      int numRadii = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				numRadii++;
			}
      circles = new Circle[numRadii+1];
      //System.out.println(numRadii);
      //System.exit(0);

      //now add to array
      fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			br = new BufferedReader(new FileReader(FILENAME));
      int counter = 0; //use with while loop to add radii to array
      while ((sCurrentLine = br.readLine()) != null) {
        String[] parts = sCurrentLine.split(",");
        Circle myCirc = new Circle(Integer.parseInt(parts[0]));
        circles[counter] = myCirc;
        counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}//catch
		}//finally
  }//method: readRadii


}//class
