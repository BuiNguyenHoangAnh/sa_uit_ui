package standardize;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;

import util.helpFunction;
import util.sparkConfigure;

public class standardizeBUS {
	private static DataFrame socialWordSet;
	private static DataFrame incorrectWords;
	private static DataFrame correctWords;
	private static Row[] correctRows;
	private static Row[] incorrectRows;
	
	private standardizeDTO correctionDto = new standardizeDTO();
	
	/*
	 * standardize data with input data is a string
	 */
	public String standardizeData(sparkConfigure spark, String input) {
		
		String inputString = null;
		String outputString = null;

		System.out.println("CHECKPOINT");
		inputString = input;
		outputString = this.standardize(inputString, spark);
		
		return outputString;

	}
	
	/*
	 * standardize data with input data is a file
	 */
	public String standarizeData(sparkConfigure spark, String fileName){ 
		
		JavaRDD<String> input;
		
		String inputString = null;
		String result = null;
		
		input = spark.getSparkContext().textFile(fileName);
		inputString = helpFunction.pushDataFromFileToString(input);
		result = this.standardize(inputString, spark);
		
		return result;
	}
	
	/*
	 * check each word
	 */
	private String standardize(String string, sparkConfigure spark) {
		
		socialWordSet = correctionDto.getSocialLanguageDictionary(spark);
		
		String result = "";
		String[] words = string.split(" ");
		
		correctWords = socialWordSet.select("correct");
		incorrectWords = socialWordSet.select("incorrect");
		
		correctRows = correctWords.collect();
		incorrectRows = incorrectWords.collect();

		for(String word : words) {
			if(word.isEmpty()) continue;
			int count = this.isSocialLanguage(word, spark);
			if (word == "\n") {
				result += "\n";
			}
			else if (count > 0) {
				//remove teen code, incorrect words, ...				
				result += (correctRows[count-1].toString().substring(1, correctRows[count-1].toString().length() - 1)+" ").toLowerCase();
				continue;
			}
			result += (word.toLowerCase()+" ");
		}
		
		return result;
	}
	
	/*
	 * check if word exist in dictionary
	 * return 0 if it does not exist
	 * else return its position
	 */
	private int isSocialLanguage(String word, sparkConfigure spark) {
		int count = 0;
		for (Row row : incorrectRows) {
			count += 1;
			if(row.toString().substring(1, row.toString().length()-1).equals(word.toLowerCase())) 
				return count;
		}
		return 0;
	}	
}
