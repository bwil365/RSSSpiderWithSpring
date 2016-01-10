package app.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CSVFileAccess {

	public static CSVData readData(String fileToReadFrom) {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileToReadFrom));
			String line = br.readLine();
			CSVData csvData = new CSVData(Arrays.asList(line.split(","))); //First line is the cvs column definitions
			line = br.readLine();
			while(line != null) {
				csvData.addLine(Arrays.asList(line.split(",")));
				line = br.readLine();
			}
			br.close();
			return csvData;
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static boolean writeData(String fileToWriteTo, CSVData csvData) {
		try {
			File file = new File(fileToWriteTo);
			if(!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write(csvData.toString());
			bufferedWriter.close();
			return true;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
