package ant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ant {
    private List<Node> tabooList;
    private boolean isActive;
    private World world;

    public Ant(World world){
        this.world = world;
        setActive(true);
        tabooList = new ArrayList<>();
    }

    public List<Node> getTabooList(){
        return tabooList;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isActive(){
        return isActive;
    }

    public void fermentAdd(){

    }

    public void selectNode(){
        Map<Connection, Node> pairs = world.getAvailableConnections(tabooList);
        if(pairs.isEmpty()){
            setActive(false);
            return;
        }
        System.out.println("My Node = "+tabooList.get(tabooList.size()-1).name);


        double sumProb = 0;
        for(Map.Entry<Connection, Node> entry : pairs.entrySet()){
            Connection c = entry.getKey();
            sumProb += c.saturation/c.getLength();
        }

        double[] probability = new double[pairs.size()];
        int i = 0;
        double s = 0;
        for(Map.Entry<Connection, Node> entry : pairs.entrySet()){
            Connection c = entry.getKey();
            double ph = c.getSaturation();
            double l = 1./c.getLength();
            s += (ph*l) / sumProb;
            probability[i++] = s;
        }

        System.out.println("Probabilities:");
        for(int j = 0; j < probability.length; j++){
            System.out.println(probability[j]);
        }

        // choose
        double ball = Math.random();
        int choose = 0;
        for(int j = 0; j < probability.length; j++){
            if(ball<probability[j]){
                choose = j;
                break;
            }
        }
        System.out.println("I chose "+choose);
        Node result = new ArrayList<Node>(pairs.values()).get(choose);
        if(result == null){
            setActive(false);
            System.out.println("result is null");

            return;
        }
        System.out.println("I chose node "+result.name);
        addNode(result);
    }

    public void addNode(Node node){
        tabooList.add(node);
    }

    public void printTaboo(){
        for(Node node: tabooList){
            System.out.println(node.name);
        }
    }

    public void resetTabooList(){
        Node first = tabooList.get(0);
        tabooList.clear();
        tabooList.add(first);
    }
}
