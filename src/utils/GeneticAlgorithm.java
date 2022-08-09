package utils;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {

	private int[] defaultArray = { 1, 2, 3, 4, 5, 6, 7, 8 };

	// Generate individual random array
	public int[] generateIndividual() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < defaultArray.length; i++) {
			list.add(defaultArray[i]);
		}
		Collections.shuffle(list);
		int[] shuffledArray = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			shuffledArray[i] = list.get(i);
		}
		return shuffledArray;
	}

	// Generate random population
	public ArrayList<int[]> generatePopulation(int populationSize) {
		System.out.println("Generating population...");
		ArrayList<int[]> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(generateIndividual());
		}
		return population;
	}

	public int calculateFitness(int[] individual) {
		int fitness = 0;
		for (int i = 0; i < individual.length; i++) {
			for (int j = 0; j < individual.length; j++) {
				if ((individual[i] != individual[j]) && Math.abs(individual[i] - individual[j]) == Math.abs(i - j)) {
					fitness++;
				}
			}
		}
		return fitness;
	}

	// Select five parents and choose two best parents
	public ArrayList<int[]> selectParents(ArrayList<int[]> population) {
		ArrayList<int[]> parents = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomIndex = this.getRandomNumber(0, population.size() - 1);
			parents.add(population.get(randomIndex));
		}
		Collections.sort(parents, (a, b) -> calculateFitness(a) - calculateFitness(b));
		return parents;
	}

	public int[] crossover(int[] parent1, int[] parent2) {
		int[] child = new int[parent1.length];
		int randomIndex = this.getRandomNumber(0, 6);
		int randomIndex2 = this.getRandomNumber(randomIndex + 1, 7);

		for (int i = randomIndex; i < randomIndex2; i++) {
			child[i] = parent1[i];
		}

		for (int i = randomIndex2; i < child.length; i++) {
			if (!this.contains(child, parent2[i]) && child[i] == 0) {
				child[i] = parent2[i];
			}

		}

		for (int i = 0; i < parent2.length; i++) {
			if (!this.contains(child, parent2[i])) {
				for (int j = 0; j < child.length; j++) {
					if (child[j] == 0) {
						child[j] = parent2[i];
						j = child.length;
					}
				}
			}
		}

		return child;
	}

	// mutate child by swaping two random elements
	public int[] mutate(int[] child) {
		int randomIndex1 = this.getRandomNumber(0, 7);
		int randomIndex2 = this.getRandomNumber(0, 7);
		int temp = child[randomIndex1];
		child[randomIndex1] = child[randomIndex2];
		child[randomIndex2] = temp;
		return child;
	}

	// Check if array contains an element
	public boolean contains(int[] array, int element) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return true;
			}
		}
		return false;
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
