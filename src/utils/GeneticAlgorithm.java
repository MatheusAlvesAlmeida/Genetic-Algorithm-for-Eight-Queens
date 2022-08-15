package utils;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {

	private String[] defaultArray = { Integer.toBinaryString(1), Integer.toBinaryString(2), Integer.toBinaryString(3),
			Integer.toBinaryString(4), Integer.toBinaryString(5), Integer.toBinaryString(6), Integer.toBinaryString(7),
			Integer.toBinaryString(8) };

	// Generate individual random array
	public String[] generateIndividual() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < defaultArray.length; i++) {
			list.add(defaultArray[i]);
		}
		Collections.shuffle(list);
		String[] shuffledArray = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			shuffledArray[i] = list.get(i);
		}
		return shuffledArray;
	}

	// Generate random population
	public ArrayList<String[]> generatePopulation(int populationSize) {
		System.out.println("Generating population...");
		ArrayList<String[]> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(generateIndividual());
		}
		return population;
	}

	public int calculateFitness(String[] individual) {
		int fitness = 0;
		for (int i = 0; i < individual.length; i++) {
			for (int j = 0; j < individual.length; j++) {
				if (!(individual[i].equals(individual[j])) && Math
						.abs(Integer.parseInt(individual[i], 2) - Integer.parseInt(individual[j], 2)) == Math
								.abs(i - j)) {
					fitness++;
				}
			}
		}
		return fitness;
	}

	// Select five parents and choose two best parents
	public ArrayList<String[]> selectParents(ArrayList<String[]> population) {
		Collections.shuffle(population);
		ArrayList<String[]> parents = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomIndex = this.getRandomNumber(0, population.size() - 1);
			parents.add(population.get(randomIndex));
		}
		Collections.sort(parents, (a, b) -> calculateFitness(a) - calculateFitness(b));
		return parents;
	}

	public String[] crossover(String[] parent1, String[] parent2) {
		int prob = this.getRandomNumber(1, 100);
		if (prob <= 90) {
			String[] child = new String[parent1.length];
			int randomIndex = this.getRandomNumber(0, 6);
			int randomIndex2 = this.getRandomNumber(randomIndex + 1, 7);

			for (int i = randomIndex; i < randomIndex2; i++) {
				child[i] = parent1[i];
			}

			for (int i = randomIndex2; i < child.length; i++) {
				if (!this.contains(child, parent2[i]) && child[i] == null) {
					child[i] = parent2[i];
				}

			}

			for (int i = 0; i < parent2.length; i++) {
				if (!this.contains(child, parent2[i])) {
					for (int j = 0; j < child.length; j++) {
						if (child[j] == null) {
							child[j] = parent2[i];
							j = child.length;
						}
					}
				}
			}

			return child;
		}
		return parent1;
	}

	// mutate child by swaping two random elements
	public String[] mutate(String[] child) {
		int prob = this.getRandomNumber(1, 100);
		if (prob <= 80) {
			int randomIndex1 = this.getRandomNumber(0, 7);
			int randomIndex2 = this.getRandomNumber(0, 7);
			String temp = child[randomIndex1];
			child[randomIndex1] = child[randomIndex2];
			child[randomIndex2] = temp;
		}
		return child;
	}

	// Check if array contains an element
	public boolean contains(String[] array, String element) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
