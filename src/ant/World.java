package ant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class World {
    private List<Node> nodes;
    private List<Connection> connections;
    private List<Ant> ants;

    final static double Q = 0.1;
    final static double Q_SPRAY = 0.5;
    final static double Q_EVAPORATION = 0.5;

    public World(){
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        ants = new ArrayList<>();
    }

    public void initConnections(){
        for(Connection c:connections){
            c.setSaturation(10);
        }
    }

    public void addAnt(Ant ant){
        ants.add(ant);
    }

    public List<Node> getNodes(){
        return nodes;
    }

    public List<Ant> getAnts(){
        return ants;
    }

    public double getPathLength(List<Node> nodes){
        // считаем длину пути
        Node current = nodes.get(0);
        double length = 0;
        for(int i = 1; i < nodes.size(); i++){
            Set<Node> ns = new TreeSet<>();
            ns.add(current);
            ns.add(nodes.get(i));
            Connection c = getConnection(ns);
            length += c.getLength();
            current = nodes.get(i);
        }
        return length;
    }

    public Connection getConnection(Set<Node> nodes){
        // пройти по всем connection и вернуть тот, где совпадает множество узлов
        Connection res = null;
        for(Connection connection:connections){
            Set<Node> n = connection.getNodes();
            if (nodes.containsAll(n)){
                return connection;
            }
        }
        return res;
    }

    public void spray(List<Node> nodes){
        double length = getPathLength(nodes);
        double sprayQuantity = Q / length;
        Node current = nodes.get(0);
        for(int i = 1; i < nodes.size(); i++){
            Set<Node> ns = new TreeSet<>();
            ns.add(current);
            ns.add(nodes.get(i));
            Connection c = getConnection(ns);
            c.setSaturation(Q_EVAPORATION * c.getSaturation()+ sprayQuantity * Q_SPRAY);
            current = nodes.get(i);
        }
    }

    public Map<Connection, Node> getAvailableConnections(List<Node> tabooList){
        Map<Connection, Node> pairs = new TreeMap<>();
        Node current = tabooList.get(tabooList.size()-1);
        for(Node node:current.getNodes()){
            if(tabooList.contains(node))
                continue;
            Set<Node> nodes = new TreeSet<>();
            nodes.add(node);
            nodes.add(current);
            Connection c = getConnection(nodes);
            pairs.put(c, node);
        }
        return pairs;
    }

    public void start(){
        for(Ant ant: ants){
            ant.selectNode();
        }
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addConnection(Connection connect){
        connections.add(connect);
    }
}
