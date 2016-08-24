/**
 * This is the class that defines the format of our Time Table. Mostly all
 * Time-Tables are of two dimensional nature but in this respect our Time
 * Table will be of 3D type.
 * Each cell/entity of 2D Chromosome is made up of SubGroup Object.
 * The size of the Time Table Chromosome will be specified in the main application
 *
 */
public class Chromosome {


    public SubGroup[][][] timeTableChromosome = new SubGroup[GA.maxNoOfDays][GA.maxNoOfPeriods][GA.maxNoOfDepsAndSessions];
    private Chromosome[] chromosomeArray = new Chromosome[GA.populationSize];

    // Cache
    private int fitness = 0;

    /**
     * Algorithm for generating a Chromosome. Every Chromosome is a 2D array
     * of SubGroup objects. Therefore, we visit every object and fill in the subgroup object's
     * linear array which is the size of GA.maxNoOfDepsAndSessions.
     * The filling of each object should work like this:
     * * Each object must have a two values to be filled in:
     *      1. TeacherId which will be numerical in nature
     *      2. SubjectId which will be numerical in nature
     * The filling of such values will be done in a random fashion signifying the following few
     * points in mind:
     * Each number being filled will be four digit long: For eg: 0000
     * The first digit specifies the department of the subject or the teacher
     * and rest signifying the respect ID of the faculty or student within the department
     *
     */
    public Chromosome() {
        for(int k = 0; k < GA.maxNoOfDepsAndSessions; k++) {
            for( int i = 0; i < GA.maxNoOfDays; i++) {
                for(int j = 0; j < GA.maxNoOfPeriods; j++) {

                    timeTableChromosome[i][j][k] = new SubGroup();
                }
            }
        }
    }

    public void generateChromosome() {
        int checkSessionLength = 0;
        int departmentNumber = 1000;
        int checkedNumberFaculty = 0;
        int checkedNumberSubject = 0;
        /**
         * In the database the Id of teachers and SubjectId's are stored as such:
         * ArrayofDepartment[i] = DepartmentSerialSemesterSerialTeacherIdNumber;
         *
         */
        for(int k = 0; k < GA.maxNoOfDepsAndSessions; k++) {
            for( int i = 0; i < GA.maxNoOfDays; i++) {
                for(int j = 0; j < GA.maxNoOfPeriods; j++) {

                    timeTableChromosome[i][j][k] = new SubGroup();
                }
            }
        }

        for(int k = 0; k < GA.maxNoOfDepsAndSessions; k++) {
            for( int i = 0; i < GA.maxNoOfDays; i++) {
                for(int j = 0; j < GA.maxNoOfPeriods; j++) {

                    int randomPoint1 = (int) Math.random() * i;
                    int randomPoint2 = (int) Math.random() * j;

                        if(timeTableChromosome[randomPoint1][randomPoint2][k].isSubGroupSlotEmpty) {
                            /**
                             * How to generate a particular block of 3D Chromosome corresponding to
                             * a particular department?
                             */
                            if(checkedNumberFaculty == GA.faculty_id_forStorage.length) {
                                checkedNumberFaculty = GA.faculty_id_forStorage.length - 1;
                            }
                            if(checkedNumberSubject == GA.subject_id_forStorage.length) {
                                checkedNumberSubject = GA.subject_id_forStorage.length - 1;
                            }
                                timeTableChromosome[randomPoint1][randomPoint2][k].faculty_id = GA.faculty_id_forStorage[checkedNumberFaculty];

                                timeTableChromosome[randomPoint1][randomPoint2][k].subject_id = GA.subject_id_forStorage[checkedNumberSubject];
                            checkedNumberFaculty++;
                            checkedNumberSubject++;
                        }
                }
            }
        }
    }


    /**
     *
     * @return Chromosome object created at the instance of the index.
     */
    public Chromosome getChromosome(int index) {
        return chromosomeArray[index];
    }


    /**
     *
     * @param index change the subgroup at index i
     * @param subGroup
     */
    public void setChromosome(int index, SubGroup[][][] subGroup) {

        for(int i = 0; i < GA.maxNoOfDepsAndSessions; i++) {
            for( int j = 0; j < GA.maxNoOfDays; j++) {
                for( int k = 0; k < GA.maxNoOfPeriods; k++) {
                    chromosomeArray[index].timeTableChromosome[i][j][k].faculty_id = subGroup[i][j][k].faculty_id;
                    chromosomeArray[index].timeTableChromosome[i][j][k].faculty_id = subGroup[i][j][k].subject_id;
                }
            }
        }
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return chromosomeArray.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = (int) CalculateFitness.calcFitness(this);
        }
        return fitness;
    }


}
