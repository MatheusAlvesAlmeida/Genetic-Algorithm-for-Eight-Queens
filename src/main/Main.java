package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import utils.GeneticAlgorithm;

public class Main {

	public static void main(String[] args) {
		int i = 1;
		GeneticAlgorithm ga = new GeneticAlgorithm();

		int convergenceTimes = 0;
		double averageFitness[] = new double[30];
		int convergenceIndexes[] = new int[30];

		ArrayList<String[]> population = ga.generatePopulation(100);

		for (int j = 0; j < 30; j++) {
			while ((ga.calculateFitness(population.get(0)) != 0) || i == 10000) {
				System.out.println("Generation " + i);
				// Sort population by fitness
				Collections.sort(population, (a, b) -> ga.calculateFitness(a) -
						ga.calculateFitness(b));

				// Delete half of the population
				for (int k = 0; k < population.size() / 2; k++) {
					population.remove(population.size() - 1);
				}

				// Generate 50 new individuals
				for (int k = 0; k < 50; k++) {
					population.add(ga.generateIndividual());
				}

				// Select 5 parents and choose two best parents
				ArrayList<String[]> parents = ga.selectParents(population);
				String[] child = ga.crossover(parents.get(0), parents.get(1));
				String[] child2 = ga.crossover(parents.get(1), parents.get(0));
				// Mutate childrens and add to population replacing the worst individual
				child = ga.mutate(child);
				child2 = ga.mutate(child2);
				Collections.sort(population, (a, b) -> ga.calculateFitness(a) -
						ga.calculateFitness(b));
				population.set(population.size() - 1, child);
				population.set(population.size() - 2, child2);

				System.out.println("Best individual: " + Arrays.toString(population.get(0)));
				System.out.println("Fitness: " + ga.calculateFitness(population.get(0)) + "\n");
				i++;
			}
			// Compute average fitness of the population
			double average = 0;
			for (String[] individual : population) {
				average += ga.calculateFitness(individual);
			}
			average /= population.size();
			averageFitness[j] = average;
			convergenceIndexes[j] = i;
			convergenceTimes++;
			// Reset population and start again
			population = ga.generatePopulation(100);
			i = 1;
		}
		System.out.println("Convergence times: " + convergenceTimes);
		System.out.println("Average fitness: " + Arrays.toString(averageFitness));
		System.out.println("Convergence indexes: " + Arrays.toString(convergenceIndexes));

	}

}
