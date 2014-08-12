package edu.illinois.cs.comoto.jplag;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComparisonFiles {
	
	private List<List<String>> comparisons = new ArrayList<>();
	
	public void execute() throws Exception {
		Class.forName("org.postgresql.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://172.20.7.10/plagiarism", "plagiarism", "123456");
		
		PreparedStatement prepareStatement = connection.prepareStatement("select c.id, s1.filename, s2.filename from comparisons c "
				+ "inner join submissions s1 on (s1.id = c.source_submission_id) "
				+ "inner join submissions s2 on (s2.id = c.target_submission_id) "
				+ "inner join groups g on (g.id = s1.group_id and g.id = s2.group_id) "
				+ "where g.user_id = ? and g.id in (3239, 3154, 3230, 3235, 3226, 3148, 3237, 3228, 3233, 3151)");
		
		prepareStatement.setLong(1, 1L);
		
		ResultSet rs = prepareStatement.executeQuery();
		
		while (rs.next()) {
			String comparisonId = rs.getString(1);
			String filename1 = rs.getString(2);
			String filename2 = rs.getString(3);
			
			List<String> comparison = new ArrayList<>(2);
			comparison.add(comparisonId);
			comparison.add(filename1);
			comparison.add(filename2);
			
			this.comparisons.add(comparison);
		}
		
		
		
		for (List<String> comparison : this.comparisons) {			
			File directory = new File("c:\\temp\\comparisons\\" + comparison.get(0));
			directory.mkdir();
			
			File originalFile1 = new File("c:\\temp" + comparison.get(1));
			File destinationFile1 = File.createTempFile("plagio", ".c", directory);
			
			copyTextFile(originalFile1, destinationFile1);
			
			File originalFile2 = new File("c:\\temp" + comparison.get(2));			
			File destinationFile2 = File.createTempFile("plagio", ".c", directory);
			
			copyTextFile(originalFile2, destinationFile2);
		}
	}
	
	private void copyTextFile(File from, File to) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(from));
		BufferedWriter writer = new BufferedWriter(new FileWriter(to));
		
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			writer.append(line);
			writer.newLine();
		}
		
		reader.close();
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		new ComparisonFiles().execute();
	}

}
