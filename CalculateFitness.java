public class CalculateFitness {
    private double sumViolations = 0;
    int[] countIndexOccurencesFac = new int[5000];

    public CalculateFitness() {
        this.sumViolations = 0;
//this.countIndexOccurencesFac
    }

    static double calcFitness(Chromosome indiv) {
        double violations = 0;
//
        double sumViolations = 0;
        double[] a = new double[243];
        int k = 0;
        int m = 0;
        double sum = 0;
//this loop calculates the clashes of faculty on a particularperiod of a particular day

            for (int i = 0; i < GA.maxNoOfDays; i++) {
                for (int j = 0; j < GA.maxNoOfPeriods; j++) {
                    violations =
                            checkClashInSubGroup(indiv.timeTableChromosome, i, j);
                    a[k] = violations;
                    k++;
                }
            }

        m = k;
        for (int i = 0; i < m; i++) {
            sum = sum + a[i];
        }
        return getFitness(sum);
    }

    private static double checkClashInSubGroup(SubGroup[][][] subGroup, int positioni, int positionj) {


        int sum = 0;
        SubGroup[] copyArray = new SubGroup[GA.maxNoOfDepsAndSessions];

        for(int i = 0; i < GA.maxNoOfDepsAndSessions; i++) {
            copyArray[i] = subGroup[positioni][positionj][i];
        }

        int[] countIndexOccurencesFac = new int[5000];
        for (int i = 0; i < 5000; i++) {
            countIndexOccurencesFac[i] = 0;
        }
        for (int i = 0; i < GA.maxNoOfDepsAndSessions; i++) {
            countIndexOccurencesFac[copyArray[i].faculty_id]++;
        }
        for (int i = 0; i < 5000; i++) {
            if (countIndexOccurencesFac[i] > 1) {
                sum = sum +
                        countIndexOccurencesFac[i];
            }
        }
        return sum;
    }

    public static double getFitness(double totalViolations) {
        return (double) (1000 / (double) (1000 - totalViolations));
    }
}
