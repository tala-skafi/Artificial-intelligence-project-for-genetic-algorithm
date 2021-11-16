import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {
	private ArrayList<Chromosome> chromosomes;

	public Population(int size, Data data) {
		chromosomes = new ArrayList<Chromosome>(size);
		IntStream.range(0, size).forEach(x -> chromosomes.add(new Chromosome(data).initialize()));
	}

	public ArrayList<Chromosome> getChromosome() {
		return this.chromosomes;
	}

	public Population sortByFitness() {
		chromosomes.sort((chromosome1, chromosome2) -> {
			int returnValue = 0;
			if (chromosome1.getFitness() > chromosome2.getFitness())
				returnValue = -1;
			else if (chromosome1.getFitness() < chromosome2.getFitness())
				returnValue = 1;
			return returnValue;
		});
		return this;
	}
}
