/**
 * Created by paul on 6/8/16.
 */

/**
 * Population class contains all the variables that is required to understand
 * the dynamics of a particular population of the generation. Each Chromosome
 * that is created during the population creation process is returned and
 * stored in the individuals array of Chromosome.
 * The fitness of each Chromosome is stored in a separate array.
 * The average fitness is also stored.
 * The individual with highest fitness can also be retrieved.
 * The populationSize can be initialized too.
 */
public class Population {

    Chromosome[] individuals;
    int populationSize;
    Chromosome fittestChromosome = new Chromosome();
    int[] individualFitnessArray;
    int averageFitness;

    public Population(int populationSize, boolean initialize) {
        individuals = new Chromosome[populationSize];
        this.populationSize = populationSize;

        if(initialize) {
            for(int i = 0; i < size(); i++) {
                Chromosome newChromosome = new Chromosome();
                newChromosome.generateChromosome();
                saveChromosome(i, newChromosome);
            }
        }
    }

    public Chromosome getChromosome(int index) {
        return individuals[index];
    }


    public Chromosome[] getIndividuals() {
        return individuals;
    }

    public Chromosome getFittest() {
        fittestChromosome = individuals[0];

        for( int i = 0; i < size(); i++) {
            if( fittestChromosome.getFitness() <= getChromosome(i).getFitness()) {
                fittestChromosome = getChromosome(i);
            }
        }

        return fittestChromosome;
    }

    public int size() {
        return individuals.length;
    }

    public void saveChromosome(int index, Chromosome individual) {
        individuals[index] = individual;
    }




    public void saveIndividual(int i, Chromosome fittest) {
    }

    public Chromosome getIndividual(int i) {

        return new Chromosome();
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getAverageFitness() {
        int sum = 0;
        for(int i = 0; i < getPopulationSize(); i++) {
            sum = sum + individuals[i].getFitness();
        }
        return sum / populationSize;
    }


}
