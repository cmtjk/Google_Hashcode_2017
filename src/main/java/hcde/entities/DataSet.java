package hcde.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataSet {
    
    public static Set<Request> globallyStoredRequests = new HashSet<>();

    public int videosCount = 0;
    public int endpointCount = 0;
    public int requestDescCount = 0;
    public int cachesCount = 0;

    public int cachesSize = 0;

    public Map<Integer, Video> videos = new HashMap<>();
    public Map<Integer, Endpoint> endpoints = new HashMap<>();
    public Map<Integer, Cache> caches = new HashMap<>();

    public void setHeader(int v, int e, int r, int c, int s) {
	this.videosCount = v;
	this.endpointCount = e;
	this.requestDescCount = r;
	this.cachesCount = c;
	this.cachesSize = s;
    }

    public void createCaches() {
	for (int i = 0; i < cachesCount; i++) {
	    caches.put(i, new Cache(i, cachesSize, videos));
	}
    }

    public Cache getCacheById(int id) {
	return caches.get(id);
    }

    public Endpoint getEndpointById(int id) {
	return endpoints.get(id);
    }

    public Video getVideoById(int id) {
	return videos.get(id);
    }

    public boolean validate() {
	if (endpoints.size() != endpointCount)
	    return false;
	if (caches.size() != cachesCount)
	    return false;
	if (videos.size() != videosCount)
	    return false;
	return true;

    }

    @Override
    public String toString() {
	return "DataSet [videosCount=" + videosCount + ", endpointCount=" + endpointCount + ", requestDescCount="
		+ requestDescCount + ", cachesCount=" + cachesCount + ", cachesSize=" + cachesSize + ", videos="
		+ videos + ", endpoints=" + endpoints + ", caches=" + caches + "]";
    }

}
