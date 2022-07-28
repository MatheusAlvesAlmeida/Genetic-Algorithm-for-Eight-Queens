package utils;

import java.util.Arrays;
import java.util.Collections;

public class Utils {
	
	private int[] defaultArray = {1, 2, 3, 4, 5, 6, 7, 8};
	
	public int[] generateIndividual() {
		Collections.shuffle(Arrays.asList(this.defaultArray));
		return this.defaultArray;
	}
	
	public int calculateFitness(int[] individual) {
		int fitness = 0;
		for (int i = 1; i <= individual.length; i++) {
			for (int j = 1; j <= individual.length; j++) {
				if((individual[i] != individual[j]) && Math.abs(individual[i] - individual[j]) == Math.abs(i - j)) {
					fitness++;
				}
			}
		}
		return fitness;
	}
}
