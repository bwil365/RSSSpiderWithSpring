package app.file;

import java.util.ArrayList;
import java.util.List;

public class FileData {
	private List<String> data;
	public FileData() {
		data = new ArrayList<String>();
	}
	public boolean addline(String line) {
		if(line != null) {
			data.add(line);
			return true;
		}
		return false;
	}
	public boolean removeLine(int index) {
		if(index >= 0 && index < data.size()) {
			data.remove(index);
			return true;
		}
		return false;
	}
	public String getLine(int index) {
		if(index >= 0 && index < data.size()) {
			return data.get(index);
		}
		return null;
	}
	public List<String> getData() {
		return data;
	}
	public int numberOfRows() {
		return data.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(String line : data) {
			stringBuilder.append(line);
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
