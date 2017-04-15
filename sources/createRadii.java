import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class createRadii {

	private static final String FILENAME = "radii.txt";

	public static void main(String[] args) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

      int numItems = 4000;

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);

      System.out.print("Writing to file...");
      for (int i = 0; i < numItems; i++){
        int radius = (int)(Math.random() * 100);
        String line = Integer.toString(radius) + ",whyDoWeNeedThisPart\n";
  			bw.write(line);
      }


			System.out.print("Done\n");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
