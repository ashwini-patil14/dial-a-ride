package io;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class OutputInFile {

	public void outputFile(String display, String fileName)
	{
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(""+fileName, true)));
		    out.println(display);
		    out.close();
		} catch (IOException e) {
		}
	}
	
}
