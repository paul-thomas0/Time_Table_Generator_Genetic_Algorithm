import java.util.Random;
import java.io.*;
import java.util.*;

/*
AlgorithmTTG class is like a main function. Now as the name suggests all the steps
corresponding Genetic Algorithm is supposed to take place in here.
The important steps of Genetic Algorithm are as follows:
1. Selection
2. Mutation
3. Crossover
All the above steps take place or are applied when a new population is generated
from the old One.
The steps over a fixed or number of new population generation seem to reduce or
create a new population with children with less birth defects or in our case
the best possible time table Chromosome.
Now, the best possible Chromosome is a entity in a population which fulfills all
our input constraints. Currently our constraints are that:
    *Faculty assignment and subject assignment should not clash
 */

public class AlgorithmTTG {
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int mutationDepth = 0;
    private static final int tournamentSize = 100;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population oldPop) {
        Population newPopulation = new Population(oldPop.getPopulationSize(), true);
        if (elitism) {
            newPopulation.saveIndividual(0, oldPop.getFittest());
        }

        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = 0; i < oldPop.getPopulationSize(); i++) {

            Chromosome indiv1 = tournamentSelection(oldPop);
            Chromosome indiv2 = tournamentSelection(oldPop);
            Chromosome newIndiv = crossover(indiv1, indiv2);

            newPopulation.saveIndividual(i, newIndiv);
        }
        for (int i = elitismOffset; i <
                newPopulation.getPopulationSize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }
        return newPopulation;
    }

    /**
     * Mutation of a Chromosome will take place as following:
     * Choose two random points that will choose a subgroup, and
     * shuffle the subgroup by shuffling the the session.
     *
     * @param indiv
     */
    private static void mutate(Chromosome indiv) {
        Random points = new Random();
        int randPoint1 = points.nextInt(GA.maxNoOfDays);
        int randPoint2 = points.nextInt(GA.maxNoOfPeriods);
        if (Math.random() <= mutationRate)
            mutateThisSubGroup(indiv, randPoint1, randPoint2);
    }

    private static void mutateThisSubGroup(Chromosome indiv, int randPoint1, int randPoint2) {
        int[] tempArrayF = new int[GA.maxNoOfDepsAndSessions];
        int[] tempArrayS = new int[GA.maxNoOfDepsAndSessions];
        Object[] returnedArray;
        for(int i = 0; i < tempArrayF.length; i++) {
            tempArrayF[i] = indiv.timeTableChromosome[randPoint1][randPoint2][i].faculty_id;
            tempArrayS[i] = indiv.timeTableChromosome[randPoint1][randPoint2][i].subject_id;
        }
        returnedArray = shuffleAndMutate(tempArrayF, tempArrayS);
        tempArrayF = (int[]) returnedArray[0];
        tempArrayS = (int[]) returnedArray[1];

        for (int i = 0; i < tempArrayF.length; i++) {
            indiv.timeTableChromosome[randPoint1][randPoint2][i].faculty_id = tempArrayF[i];
            indiv.timeTableChromosome[randPoint1][randPoint2][i].subject_id = tempArrayS[i];
        }

    }

    private static Chromosome crossover(Chromosome indiv1,
                                        Chromosome indiv2) {
        Random points = new Random();
        int randPoint1 = points.nextInt(GA.maxNoOfDays);
        int randPoint2 = points.nextInt(GA.maxNoOfPeriods);

        Chromosome newChromosome = new Chromosome();

        for (int i = 0; i < randPoint1; i++) {
            for (int j = 0; j < randPoint2; j++) {
                for (int k = 0; k < GA.maxNoOfDepsAndSessions; k++) {
                    newChromosome.timeTableChromosome[i][j][k].faculty_id = indiv1.timeTableChromosome[i][j][k].faculty_id;
                    newChromosome.timeTableChromosome[i][j][k].subject_id = indiv1.timeTableChromosome[i][j][k].subject_id;
                }
            }
        }

        for (int i = randPoint1; i < GA.maxNoOfDays; i++) {
            for (int j = randPoint2; j < GA.maxNoOfPeriods; j++) {
                for (int k = 0; k < GA.maxNoOfDepsAndSessions; k++) {
                    newChromosome.timeTableChromosome[i][j][k].faculty_id = indiv2.timeTableChromosome[i][j][k].faculty_id;
                    newChromosome.timeTableChromosome[i][j][k].subject_id = indiv2.timeTableChromosome[i][j][k].subject_id;
                }
            }
        }


        return newChromosome;
    }

    /**
     * Working of Tournament Selection is as follows:
     * Randomly Select a group of tournamentSize from the population and
     * return the fittest Chromosome in that group.
     */

    private static Chromosome tournamentSelection(Population population) {

        Random random = new Random();
        int randomPoint = random.nextInt(population.getPopulationSize() - tournamentSize);
        Population tournamentPopulation = new Population(randomPoint, true);
        for(int i = randomPoint; i < randomPoint + tournamentSize; i++) {
            tournamentPopulation.saveIndividual(i, population.individuals[i]);
        }
       return tournamentPopulation.getFittest();
    }

    private static Chromosome rouletteWheelSelection(Population population) {
        return new Chromosome();
    }

    private static Chromosome truncationSelection(Population population) {
        return new Chromosome();
    }

    private static int getFittestInATournament(Chromosome[]
                                                       tournamentBetweenThese) {
        return 0;
    }

    /**
     * Tweaked Fisher-Yates Shuffling methodology.
     */
    private static Object[] shuffleAndMutate(int[] faculty_id, int[] subject_id) {

        int index1 = 0, index2 = 0;
        int random1, random2, randomNumber1, randomNumber2;
        for (int i = 0; i < faculty_id.length; i++) {
            random1 = randomNumberBetweenTwoNumbers(index1, index2);
            random2 = randomNumberBetweenTwoNumbers(index1, index2);
            if(random2 == index1) {
                random2 += 1;
            }
            else {
                random2 -= 1;
            }
            randomNumber1 = faculty_id[random1];
            randomNumber2 = subject_id[random1];
            faculty_id[random1] = faculty_id[random2];
            subject_id[random1] = subject_id[random2];
            faculty_id[random2] = randomNumber1;
            subject_id[random2] = randomNumber2;
        }
        return new Object[] {faculty_id, subject_id};
        
    }
    
    private static int randomNumberBetweenTwoNumbers(int minNumber, int maxNumber) {
        int smallNumber, bigNumber;
        if(minNumber < maxNumber) {
            smallNumber = minNumber;
            bigNumber = maxNumber + 1;
        }
        else {
            smallNumber = maxNumber + 1;
            bigNumber = minNumber;
        }
        return (int) (Math.random() % (bigNumber - smallNumber)) + smallNumber;
        
    }
}
