package hcde.entities;

public class ConnectedCache {
    
    public final Cache cache;
    public final int latency;
    
    public ConnectedCache(Cache c, int l) {
	this.cache = c;
	this.latency = l;
    }

}
