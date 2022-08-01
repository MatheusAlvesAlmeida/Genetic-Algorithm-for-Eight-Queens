package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {

	private int[] defaultArray = { 1, 2, 3, 4, 5, 6, 7, 8 };

	// Generate individual random array
	public int[] generateIndividual() {
		int[] individual = new int[defaultArray.length];
		for (int i = 0; i < individual.length; i++) {
			individual[i] = defaultArray[i];
		}
		return individual;
	}

	// Generate random population
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

	// Select five parents and choose two best parents
	public ArrayList<int[]> selectParents(ArrayList<int[]> population) {
		ArrayList<int[]> parents = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomIndex = new Random().nextInt(population.size());
			parents.add(population.get(randomIndex));
		}
		Collections.sort(parents, (a, b) -> calculateFitness(a) - calculateFitness(b));
		return parents;
	}

	// Crossover parents to create children
	public ArrayList<int[]> crossover(ArrayList<int[]> parents) {
		ArrayList<int[]> children = new ArrayList<>();
		for (int i = 0; i < parents.size(); i += 2) {
			int[] child1 = new int[defaultArray.length];
			int[] child2 = new int[defaultArray.length];
			int randomIndex = new Random().nextInt(defaultArray.length);
			for (int j = 0; j < defaultArray.length; j++) {
				if (j < randomIndex) {
					child1[j] = parents.get(i)[j];
					child2[j] = parents.get(i + 1)[j];
				} else {
					child1[j] = parents.get(i + 1)[j];
					child2[j] = parents.get(i)[j];
				}
			}
			children.add(child1);
			children.add(child2);
		}
		return children;
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

	// mutate child by swaping two random elements
	public int[] mutate(int[] child) {
		int randomIndex1 = new Random().nextInt(child.length);
		int randomIndex2 = new Random().nextInt(child.length);
		int temp = child[randomIndex1];
		child[randomIndex1] = child[randomIndex2];
		child[randomIndex2] = temp;
		return child;
	}
}
