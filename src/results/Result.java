package results;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Result {
	static String COL_SEPARATOR = ", ";
	static String ROW_SEPARATOR = "_____";
	
	ArrayList<HashMap<String, Double>> data;
	int size;
	ArrayList<String> colTitles = new ArrayList<String>();
	HashMap<String, String> metaData = new HashMap<String, String>();
	
	public Result(int size) {
		this.size = size;
		data = new ArrayList<HashMap<String, Double>>(size);
		for (int i = 0; i < size; i++) {
			data.add(new HashMap<String, Double>());
		}
	}
	
	public void addData(int index, String key, Double value) {
		if (!colTitles.contains(key)) {
			colTitles.add(key);
		}
		HashMap<String, Double> row = data.get(index);
		row.put(key, value);
	}
	
	public Double getData(int index, String key) {
		HashMap<String, Double> row = data.get(index);
		return row.get(key);
	}
	
	public void addMetaData(String key, String value) {
		metaData.put(key, value);
	}
	
	public void process() {
		findAverages();
	}
	
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void findAverages() {
		for (String key : colTitles) {
			double curSum = 0;
			for (int i = 0; i < size; i++) {
				curSum += getData(i, key);
			}
			double avg = curSum / size;
			String avgVal = Double.toString(avg);
			metaData.put(key + " - avg", avgVal);
		}
	}
	
	public String getColTitlesString() {
		String titles = "";
		for (String key : colTitles) {
			titles += key + COL_SEPARATOR;
		}
		titles += "\n";
		return titles;
	}
	
	public String getRow(int index) {
		String rowString = "";
		HashMap<String, Double> row = data.get(index);
		for (String key : colTitles) {
			Double value = row.get(key);
			rowString += value + COL_SEPARATOR;
		}
		rowString += "\n";
		return rowString;
	}
	
	public String getRowsString() {
		String rows = "";
		for (int i = 0; i < size; i++) {
			rows += getRow(i);
		}
		return rows;
	}
	
	public String getMetaDataString() {
		String metadataString = "";
		for (String key: metaData.keySet()) {
			metadataString += key + COL_SEPARATOR;
		}
		
		metadataString += "\n";
		
		for (String key: metaData.keySet()) {
			String value = metaData.get(key);
			metadataString += value + COL_SEPARATOR;
		}
		
		metadataString += "\n";
		return metadataString;
	}
	
	public void print() {
		process();
		String metaDataString = getMetaDataString();
		String colTitlesString = getColTitlesString();
		String rowsString = getRowsString();
		System.out.print(metaDataString);
		System.out.print(colTitlesString);
		System.out.println(ROW_SEPARATOR);
		System.out.print(rowsString);
		
	}
	
	public void save() {
		process();
		try {
			PrintWriter pw = new PrintWriter(new File("./testResults/test-" + getDate() + ".csv"));
			StringBuilder sb = new StringBuilder();
			String metaDataString = getMetaDataString();
			String colTitlesString = getColTitlesString();
			String rowsString = getRowsString();
			sb.append(metaDataString);
			sb.append("\n\n");
			sb.append(colTitlesString);
			sb.append(rowsString);
			pw.write(sb.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
