package ant;

public class AntAlg {
    AntAlg(){
        World world = new World();
        Node moscow = new Node("Moscow");
        Node petersbourgh = new Node("Petersbourgh");
        Node novgorod = new Node("Novgorod");

        world.addNode(moscow);
        world.addNode(petersbourgh);
        world.addNode(novgorod);

        moscow.addNode(petersbourgh);
        moscow.addNode(novgorod);
        petersbourgh.addNode(moscow);
        novgorod.addNode(moscow);

        Connection mp = new Connection();
        mp.setLength(10);
        mp.addNodes(moscow);
        mp.addNodes(petersbourgh);

        Connection mn = new Connection();
        mn.setLength(5);
        mn.addNodes(moscow);
        mn.addNodes(novgorod);

        Connection pm = new Connection();
        pm.setLength(10);
        pm.addNodes(petersbourgh);
        pm.addNodes(moscow);

        Connection nm = new Connection();
        nm.setLength(50);
        nm.addNodes(novgorod);
        nm.addNodes(moscow);


        world.addConnection(nm);
        world.addConnection(pm);
        world.addConnection(mp);
        world.addConnection(mn);

        world.initConnections();

        for(Node node:world.getNodes()){
            // Создаем и размещаем по узлам муравьев
            Ant ant = new Ant(world);
            ant.addNode(node);
            world.addAnt(ant);
        }

        boolean inActive = true;

        for(int i = 0; i < 10; i++){
            do{
                inActive = false;
                world.start();
                for(Ant ant:world.getAnts()){
                    if(ant.isActive()){
                        inActive = true;
                        break;
                    }
                }
            } while(inActive);

            for(Ant ant: world.getAnts()){
                world.spray(ant.getTabooList());
            }

            for(Ant ant: world.getAnts()){
                ant.printTaboo();
                ant.resetTabooList();
                System.out.println();
            }

        }
    }
}
