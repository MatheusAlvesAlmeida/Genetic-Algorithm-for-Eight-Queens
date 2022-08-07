package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import utils.GeneticAlgorithm;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		GeneticAlgorithm ga = new GeneticAlgorithm();

		ArrayList<int[]> population = ga.generatePopulation(100);

		while ((ga.calculateFitness(population.get(0)) != 0) || i == 10000) {
			System.out.println("Generation " + i);

			ArrayList<int[]> parents = ga.selectParents(population);
			int[] child = ga.crossover(parents.get(0), parents.get(1));
			child = ga.mutate(child);
			population.set(population.size() - 1, child);

			Collections.sort(population, (a, b) -> ga.calculateFitness(a) - ga.calculateFitness(b));

			// Print best individual and fitness
			System.out.println("Best individual: " + Arrays.toString(population.get(0)));
			System.out.println("Fitness: " + ga.calculateFitness(population.get(0)) + "\n");
			i++;
		}
	}

}
