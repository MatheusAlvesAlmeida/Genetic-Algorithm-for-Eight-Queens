package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import utils.GeneticAlgorithm;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		GeneticAlgorithm ga = new GeneticAlgorithm();

		ArrayList<String[]> population = ga.generatePopulation(100);

		while ((ga.calculateFitness(population.get(0)) != 0) || i == 10000) {
			System.out.println("Generation " + i);
			// Select 5 parents and choose two best parents
			ArrayList<String[]> parents = ga.selectParents(population);
			String[] child = ga.crossover(parents.get(0), parents.get(1));
			String[] child2 = ga.crossover(parents.get(1), parents.get(0));
			// Mutate childrens and add to population replacing the worst individual
			child = ga.mutate(child);
			child2 = ga.mutate(child2);
			population.set(population.size() - 1, child);
			population.set(population.size() - 2, child2);
			// Sort population by fitness
			Collections.sort(population, (a, b) -> ga.calculateFitness(a) -
					ga.calculateFitness(b));

			System.out.println("Best individual: " + Arrays.toString(population.get(0)));
			System.out.println("Fitness: " + ga.calculateFitness(population.get(0)) + "\n");
			i++;
		}
	}

}
