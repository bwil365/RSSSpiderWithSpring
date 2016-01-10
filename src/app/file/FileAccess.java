package app.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccess {
	public static FileData readData(String fileToReadFrom) {
		try {
			FileData fileData = new FileData();
			BufferedReader br = new BufferedReader(new FileReader(fileToReadFrom));
			String line = null;
			while((line = br.readLine()) != null) {
				fileData.addline(line);
			}
			br.close();
			return fileData;
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static boolean writeData(String fileToWriteTo, FileData fileData) {
		try {
			File file = new File(fileToWriteTo);
			if(!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bufferedWriter.write(fileData.toString());
			bufferedWriter.close();
			return true;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
