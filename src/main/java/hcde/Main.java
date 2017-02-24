package hcde;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import hcde.algorithm.Algorithm;
import hcde.entities.DataSet;
import hcde.io.Parser;
import hcde.io.Writer;

public class Main {

    public static void main(String... args) throws IOException {
	long start = System.currentTimeMillis();
	
	String[] inputFiles = { "me_at_the_zoo", "videos_worth_spreading", "trending_today", "kittens" };

	for (String file : inputFiles) {
	    DataSet dataSet = Parser.parseFile("src/main/resources/input/" + file + ".in");
	    Map<Integer, List<Integer>> result = Algorithm.analyze(dataSet);
	    Writer.write("src/main/resources/output/" + file + ".out", result);
	}
	
	long end = System.currentTimeMillis();
	System.out.format("Runtime: %d", (end - start));

    }

}
