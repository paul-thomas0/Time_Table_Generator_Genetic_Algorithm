This is a mockup of my Final Year Major Project. It will ecompass only the portion
which is independent of the UI i. e. only the processing portion of the Time Table
Generator. The processing/ creation of Time Table generation is done using 
Genetic Algorithms.
The whole java implementation is divided into the following parts: 
AlgorithmTTG.java: Contains the main Genetic Algorithm Code i. e. the implementation
of:
1. Selection
2. Mutation
3. Crossover

Chromosome.java: is a class that constitutes how the University Time Table is 
represented in this code. The following points will briefly explain how the Time
Table/Chromosome/Entity is represented:
1. Each Entity is a 3D object.
2. The first two points/dimension point to a particular period on a particular
   day. For example, timeTableChromosome[0][0][x] may point to 1st period of 
   the day Monday.
3. Each Entity has a fitness value. The assignement of the fitness may vary from
   various Genetic Algorithm methodologies that are used.
4. There could be two or more values/attributes to a particular point in the Chromosome. 
   These values are assigned are assigned using a different class called SubGroup.

SubGroup.java: is a class that consists of two or more variables which are assigned 
during Chromosome generation. These variables are used the fitness of the Chromosome.

CalcFitness.java: is a class that is used to calculate the fitness of the Chromosome.
The basic concept is you input the Chromosome object in the getfitness(); method of 
CalcFitness class and it will return the appropriate fitness.

GA.java: This contains all the global variables accessible for information purposes.
For example, No of Department, ID of faculty, ID of Subjects taught by the faculties.

Population.java: contains a class that represents the condition of the 
population as a whole. For example, number of individuals, who is the fitteest
member in the whole population, choose a particular individual from the whole
population etc.


