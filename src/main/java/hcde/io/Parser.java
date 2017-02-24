package hcde.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import hcde.entities.Cache;
import hcde.entities.ConnectedCache;
import hcde.entities.DataSet;
import hcde.entities.Endpoint;
import hcde.entities.Video;

public class Parser {

    private static DataSet dataSet;

    public static DataSet parseFile(String filePath) throws IOException {
	long start = System.currentTimeMillis();

	dataSet = new DataSet();

	Path path = Paths.get(filePath);
	Iterator<String> linesIter = Files.readAllLines(path).iterator();

	// Set Header
	String[] headerValues = linesIter.next().split(" ");
	dataSet.setHeader(Integer.parseInt(headerValues[0]), Integer.parseInt(headerValues[1]),
		Integer.parseInt(headerValues[2]), Integer.parseInt(headerValues[3]),
		Integer.parseInt(headerValues[4]));

	// Create Caches
	dataSet.createCaches();

	// Set Videos
	String[] videoSizes = linesIter.next().split(" ");
	for (int i = 0; i < videoSizes.length; i++) {
	    dataSet.videos.put(i, new Video(i, Integer.parseInt(videoSizes[i])));
	}

	// Create Endpoints
	for (int j = 0; j < dataSet.endpointCount; j++) {
	    String[] lineValues = linesIter.next().split(" ");
	    Endpoint endpoint = new Endpoint(j, Integer.parseInt(lineValues[0]), Integer.parseInt(lineValues[1]),
		    dataSet.videos);

	    // Add Caches to Endpoints
	    for (int i = 0; i < endpoint.cachesCount; i++) {
		lineValues = linesIter.next().split(" ");
		Cache cache = dataSet.getCacheById(Integer.parseInt(lineValues[0]));
		endpoint.connectedCaches.put(cache.id, new ConnectedCache(cache, Integer.parseInt(lineValues[1])));
	    }

	    dataSet.endpoints.put(endpoint.id, endpoint);
	}
	// Add Requests to Endpoints
	while (linesIter.hasNext()) {
	    String[] lineValues = linesIter.next().split(" ");
	    Endpoint endpoint = dataSet.getEndpointById(Integer.parseInt(lineValues[1]));
	    endpoint.addRequest(Integer.parseInt(lineValues[0]), Integer.parseInt(lineValues[2]));
	}

	if (dataSet.validate()) {
	    long end = System.currentTimeMillis();
	    System.out.format("File %s took %d milliseconds to parse and seems to be valid.\n", path.getFileName(),
		    (end - start));
	    return dataSet;
	} else {
	    throw new IllegalStateException();
	}
    }
}
