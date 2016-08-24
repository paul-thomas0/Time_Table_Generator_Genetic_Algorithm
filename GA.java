import java.util.Random;
import java.sql.*;

/**
 *This class will contain the main method which will store each Generation that is created
 * During the creation of a generation there are several properties that we will store about
 * each generation that can become potential data which can be utilized to further improve
 * Genetic Algorithm.
 *
 * Since this is just a Mock up, we will just use some hypothetically created data to
 * simulated a database consisting of faculty and already assigned subject to them. This
 * generation of fake data will be done in this class as well.
 */
public class GA {

    public static int maxNoOfDays = 5;
    public static int maxNoOfPeriods = 8;
    public static int maxNoOfSessions = 3;
    public static int maxNoOfDepartments = 3;
    public static int maxNoOfDepsAndSessions = maxNoOfSessions * maxNoOfDepartments;
    public static int populationSize=1000;
    public static int noOfGenerations = 1000;
    public static int[] faculty_id_forStorage = new int[18];
    public static int[] subject_id_forStorage = new int[54];
    public static Population[] generations;

    public static void main(String args[]) {

        generateIds();;
        int iterations =0;
        Population population = new Population(populationSize, true);
        generations = new Population[noOfGenerations];

        while(population.getFittest().getFitness() < 1 && iterations < noOfGenerations) {
            generations[iterations] = population;

            iterations++;
        }
    }

    private static void generateIds() {
        //ID format for faculty: x1= Department ID then rest faculty ID
        //ID format for subject: x1= Department ID, x2=semester ID, x3, x4= subject_id
        int department = 1000, faculty_id = 1, subject_id = 1, session = 100, checkSession = 1;
        for (int i = 0; i < 18; i++) {
            if(faculty_id== 6) {
                faculty_id = 1;
            }
            faculty_id_forStorage[i] = department + faculty_id;
            faculty_id++;

        }
        department = 1000;
        for(int i = 0; i < subject_id_forStorage.length; i++) {
            if(subject_id == 6) {
                session += 100;
                subject_id = 1;
            }
            if(checkSession == 18) {
                session = 100;
                checkSession = 1;
                department += 1000;
            }
            subject_id_forStorage[i] = department + session + subject_id;
            subject_id++;
            checkSession++;
        }

    }
}