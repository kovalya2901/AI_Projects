package ant;
import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable {
    List<Node> nodes;
    String name;

    Node(String name){
        nodes = new ArrayList();
        setName(name);
    }

    public void setName(String name){
        this.name = name;
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public List<Node> getNodes(){
        return nodes;
    }
    @Override
    public int compareTo(Object obj){
        Node n = (Node)obj;
        return name.compareTo(n.name);
    }
}
