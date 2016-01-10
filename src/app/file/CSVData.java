package app.file;

import java.util.ArrayList;
import java.util.List;

public class CSVData {
	private List<String> header;
	private List<List<String>> data;
	public CSVData(List<String> header) {
		this.header = header;
		data = new ArrayList<List<String>>();
	}
	public boolean addLine(List<String> line) {
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
	public List<String> getHeader() {
		return header;
	}
	public List<String> getLine(int index) {
		if(index >= 0 && index < data.size()) {
			return data.get(index);
		}
		return null;
	}
	public String getCell(int index, String column) {
		for(int i = 0; i < header.size(); ++i) {
			if(header.get(i).contentEquals(column)) {
				return getLine(index).get(i);
			}
		}
		return null;
	}
	public List<List<String>> getData() {
		return data;
	}
	public int numberOfRows() {
		return data.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(String headerCell : header) {
			stringBuilder.append(headerCell + ",");
		}
		stringBuilder.append("\n");
		
		for(List<String> line : data) {
			for(String cell : line) {
				stringBuilder.append(cell + ",");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
}
