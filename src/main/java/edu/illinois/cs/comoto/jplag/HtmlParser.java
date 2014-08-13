package edu.illinois.cs.comoto.jplag;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	
	public void parse(Long comparisonId) throws Exception {
		File arquivo = new File("C:\\temp\\comparisons\\" + comparisonId + "\\results\\index.html");
		
		if (!arquivo.exists()) {
			return;
		}
		
		Document document = Jsoup.parse(arquivo, "UTF-8");
		
		Elements tables = document.select("table");
		
		Element table1 = tables.get(2);
		Element table2 = tables.get(3);
		
		Elements element1 = table1.select("font");
		Elements element2 = table2.select("font");
		
		if (element1.size() > 0 && element2.size() > 0) {
			BigDecimal averageSimilarity = new BigDecimal(element1.get(0).text().replaceAll("%|\\.|\\(|\\)", "")).divide(new BigDecimal(1000));
			BigDecimal maximumSimilarity = new BigDecimal(element2.get(0).text().replaceAll("%|\\.|\\(|\\)", "")).divide(new BigDecimal(1000));
			
			update(comparisonId, averageSimilarity, maximumSimilarity);
		}
	}
	
	public Integer update(Long comparisonId, BigDecimal averageSimilarity, BigDecimal maximumSimilarity) throws Exception {
		Class.forName("org.postgresql.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://172.20.7.10/plagiarism", "plagiarism", "123456");
		
		PreparedStatement prepareStatement = connection.prepareStatement("update plagiarism.comparisons set jplag_average_similarity = ?, jplag_maximum_similarity = ? where id = ?");
		prepareStatement.setBigDecimal(1, averageSimilarity);
		prepareStatement.setBigDecimal(2, maximumSimilarity);
		prepareStatement.setLong(3, comparisonId);
		
		return prepareStatement.executeUpdate();
	}
	
	public static void main(String[] args) throws Exception {
		File directory = new File("C:\\temp\\comparisons");
		
		for (String comparisonDirectory : directory.list()) {
			System.out.println(comparisonDirectory);
			new HtmlParser().parse(Long.valueOf(comparisonDirectory));			
		}
	}

}
