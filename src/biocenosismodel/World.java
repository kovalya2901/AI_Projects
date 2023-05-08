package biocenosismodel;

import java.util.ArrayList;
import java.util.List;

public class World {
    static final int HERBIVOROUS = 1;
    static final int PREDATOR = 2;
    static final int PLANT = 3;
    static final int EMPTY = 0;

    int[][] map;

    List<Organism> organisms = new ArrayList<>();


    World(int size, int herCount, int predCount, int plantCount){
        map = new int [size][size];
    }

    public void generateOrganisms(int type, int num){
        java.util.Random r = new java.util.Random();
        for(int i =0; i<num; i++){
            boolean ok = false;
            do{
                ok = true;
                int x = r.nextInt(map.length);
                int y = r.nextInt(map[x].length);
                if(!(map[x][y]==World.EMPTY)){
                    ok = false;
                    continue;
                }
                // create
                Organism o = type==World.HERBIVOROUS? new Herbivorous() : new Predator();
                o.setCoords(x, y);
                o.setDirection(r.nextInt(4));
            } while(!ok);
        }

    }

}
