import java.util.Comparator;
import java.util.ArrayList;
import java.util.Comparator;


public class GenAlgPerceptron {
    java.util.List<Double> proba;
    java.util.List<Chromoperceptron> chromosomes;
    static int POPULATION_SIZE = 1000;
    GenAlgPerceptron(){
        init();
        for(int i=0; i<1000; i++){
            mark();
            selection();
            recombination();

        }
        mark();
        selection();
        Chromoperceptron best = chromosomes.get(0);
        best.test();
    }

    void init(){
        chromosomes = new java.util.ArrayList<>();
        proba = new java.util.ArrayList<>();
        java.util.Random r = new java.util.Random();
        for(int i=0; i<100; i++){
            Chromoperceptron chromosome = new Chromoperceptron();
            chromosome.weights[0] = r.nextDouble();
            chromosome.weights[1] = r.nextDouble();
            chromosomes.add(chromosome);
        }
    }

    void mark(){
        for(Chromoperceptron chromosome:chromosomes){
            chromosome.countFitness();
        }
    }

    void selection(){
        chromosomes.sort(new Comparator(){
            @Override
            public int compare(Object obj1, Object obj2){
                int res = 0;
                double fitness1 = ((Chromoperceptron)obj1).fitness;
                double fitness2 = ((Chromoperceptron)obj2).fitness;
                if (fitness2 < fitness1) res = 1; else
                if(fitness2 > fitness1) res = -1; else res = 0;
                return res;
            }
        });


        chromosomes = chromosomes.subList(0, chromosomes.size() / 2);
        for(Chromoperceptron chromosome: chromosomes){
            System.out.println(chromosome.weights[0]+" "+chromosome.weights[1]+" "+chromosome.fitness);;
        }
        System.out.println("__________________________________________________________");
    }

    void selection1() {
        chromosomes.sort(new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                int res = 0;
                double prob1 = ((Chromosome) obj1).selectionProb;
                double prob2 = ((Chromosome) obj2).selectionProb;
                if (prob2 < prob1) res = 1;
                else if (prob2 > prob1) res = -1;
                else res = 0;
                return res;
            }
        });
    }



    void recombination(){
        java.util.Random r = new java.util.Random();
        java.util.ArrayList<Chromoperceptron> chromosomes2 = new java.util.ArrayList<>();
        for(int i = 0; i < 50; i++){
            int index = r.nextInt(chromosomes.size());
            Chromoperceptron c1 = chromosomes.get(index);

            index = r.nextInt(chromosomes.size());
            Chromoperceptron c2 = chromosomes.get(index);


            Chromoperceptron c1new = new Chromoperceptron();
            c1new.weights[0] = c1.weights[0];
            c1new.weights[1] = c2.weights[1];

            Chromoperceptron c2new = new Chromoperceptron();
            c2new.weights[0] = c2.weights[0];
            c2new.weights[1] = c1.weights[1];

            chromosomes2.add(c1new);
            chromosomes2.add(c2new);
        }
        chromosomes = chromosomes2;
    }

}
