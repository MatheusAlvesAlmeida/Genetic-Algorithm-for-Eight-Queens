package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import utils.GeneticAlgorithm;

public class Main {

	public static void main(String[] args) {
		int i = 0;
		GeneticAlgorithm ga = new GeneticAlgorithm();

		ArrayList<int[]> population = ga.generatePopulation(50);

		while ((ga.calculateFitness(population.get(0)) != 0) || i == 10000) {
			System.out.println("Generation " + i);

			ArrayList<int[]> parents = ga.selectParents(population);
			int[] child = ga.crossover(parents.get(0), parents.get(1));
			int[] child2 = ga.crossover(parents.get(1), parents.get(0));
			if(ga.contains(child, 0) || ga.contains(child2, 0)) {
				// print parents
				System.out.println("Parents:");
				System.out.println(Arrays.toString(parents.get(0)));
				System.out.println(Arrays.toString(parents.get(1)));
				// print child
				System.out.println("Child:");
				System.out.println(Arrays.toString(child));
				System.out.println(Arrays.toString(child2));
				System.out.println("");
				break;
			}
			child = ga.mutate(child);
			child2 = ga.mutate(child2);
			population.set(population.size() - 1, child);
			population.set(population.size() - 2, child2);
			Collections.sort(population, (a, b) -> ga.calculateFitness(a) -
					ga.calculateFitness(b));

			System.out.println("Best individual: " + Arrays.toString(population.get(0)));
			System.out.println("Fitness: " + ga.calculateFitness(population.get(0)) + "\n");

			if(i == 30){
				System.out.println("teste");
			}
			i++;
		}
	}

}
