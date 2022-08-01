package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {

	private int[] defaultArray = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public int[] generateIndividual() {
		Collections.shuffle(Arrays.asList(this.defaultArray));
		return this.defaultArray;
	}

	public ArrayList<int[]> generatePopulation(int populationSize) {
		ArrayList<int[]> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(generateIndividual());
		}
		return population;
	}

	public int calculateFitness(int[] individual) {
		int fitness = 0;
		for (int i = 1; i <= individual.length; i++) {
			for (int j = 1; j <= individual.length; j++) {
				if ((individual[i] != individual[j]) && Math.abs(individual[i] - individual[j]) == Math.abs(i - j)) {
					fitness++;
				}
			}
		}
		return fitness;
	}

	public ArrayList<int[]> chooseParents(ArrayList<int[]> population) {
		ArrayList<int[]> parents = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomIndex = (int) (Math.random() * population.size());
			parents.add(population.get(randomIndex));
		}
		int bestFitness = 1000000;
		int secondeBestFitness = 1000000;
		for (int i = 0; i < parents.size(); i++) {
			if (calculateFitness(parents.get(i)) < bestFitness) {
				Collections.swap(parents, 1, 0);
				Collections.swap(parents, i, 0);
			} else if (calculateFitness(parents.get(i)) < secondeBestFitness) {
				Collections.swap(parents, i, 1);
			}
		}
		ArrayList<int[]> bestParents = new ArrayList<>();
		bestParents.add(parents.get(0));
		bestParents.add(parents.get(1));
		return bestParents;
	}

	// Order one crossover
	public int[] crossover(int[] parent1, int[] parent2) {
		int[] child = new int[parent1.length];
		int randomIndex = (int) (Math.random() * parent1.length);
		for (int i = 0; i < randomIndex; i++) {
			child[i] = parent1[i];
		}
		for (int i = randomIndex; i < parent1.length; i++) {
			child[i] = parent2[i];
		}
		return child;
	}

	public int[] mutate(int[] individual) {
		Random randomNumber = new Random();
		if (randomNumber.nextInt(100) > 39) {
			int aux = randomNumber.nextInt(8);
			int aux2 = randomNumber.nextInt(8);
			int aux3 = individual[aux];
			int aux4 = individual[aux2];
			individual[aux2] = aux3;
			individual[aux] = aux4;
		}
		return individual;
	}
}
