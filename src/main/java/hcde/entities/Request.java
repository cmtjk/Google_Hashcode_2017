package hcde.entities;

public class Request {

    public final int endpointId;
    public final int videoId;
    public final int savings;

    public Request(int i, int v, int s) {
        this.endpointId = i;
        this.videoId = v;
        this.savings = s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endpointId;
        result = prime * result + savings;
        result = prime * result + videoId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        if (endpointId != other.endpointId)
            return false;
        if (savings != other.savings)
            return false;
        if (videoId != other.videoId)
            return false;
        return true;
    }
}
