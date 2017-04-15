import javax.swing.*;
public class bubblesMain {
	public static void main (String[] args){
		//Coordinate snag = new Coordinate();
		//snag.run();
		JFrame window = new JFrame(" Brian ");

	    // Add the drawing canvas and do necessary things to
	    // make the window appear on the screen!
	    window.getContentPane().add(new draw());
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setVisible(true);
	    window.setSize(1200, 800);
	}

}
