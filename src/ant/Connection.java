package ant;
import java.util.HashSet;

public class Connection implements Comparable {
    java.util.Set<Node> nodes;
    double length;
    double saturation;

    public Connection() {

        nodes = new HashSet();
    }

    public void setLength(double length){
        this.length = length;
    }

    public void setSaturation(double saturation){
        this.saturation = saturation;
    }

    public double getSaturation(){
        return saturation;
    }

    public double getLength(){
        return length;
    }

    public void addNodes(Node node){
        nodes.add(node);
    }

    public java.util.Set<Node> getNodes(){
        return nodes;
    }

    @Override
    public int compareTo(Object obj){
        Connection c = (Connection)obj;

        return (int)(length - c.length);
    }
}
