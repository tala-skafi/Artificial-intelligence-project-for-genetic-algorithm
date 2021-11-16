import java.util.ArrayList;
import java.util.stream.IntStream;

public class GA {
	private Data data;

	public GA(Data data) {
		super();
		this.data = data;
	}

	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getChromosome().size(), data);
		IntStream.range(0, Driver.NUMB_OF_ELITE_GENERATION)
				.forEach(x -> crossoverPopulation.getChromosome().set(x, population.getChromosome().get(x)));
		IntStream.range(Driver.NUMB_OF_ELITE_GENERATION, population.getChromosome().size()).forEach(x -> {
			if (Driver.CROSSOVER_RATE > Math.random()) {
				Chromosome chromosome1 = selectTournamentPopulation(population).sortByFitness().getChromosome().get(0);
				Chromosome chromosome2 = selectTournamentPopulation(population).sortByFitness().getChromosome().get(0);
				crossoverPopulation.getChromosome().set(x, crossoverSchedule(chromosome1, chromosome2));
			} else
				crossoverPopulation.getChromosome().set(x, population.getChromosome().get(x));
		});
		return crossoverPopulation;
	}

	Chromosome crossoverSchedule(Chromosome chromosome1, Chromosome chromosome2) {
		Chromosome crossoverSchedule = new Chromosome(data).initialize();
		IntStream.range(0, crossoverSchedule.getDiscussion().size()).forEach(x -> {
			if (Math.random() > 0.7)
				crossoverSchedule.getDiscussion().set(x, chromosome1.getDiscussion().get(x));
			else
				crossoverSchedule.getDiscussion().set(x, chromosome2.getDiscussion().get(x));
		});
		return crossoverSchedule;
	}

	Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getChromosome().size(), data);
		ArrayList<Chromosome> chromosomes = mutatePopulation.getChromosome();
		IntStream.range(0, Driver.NUMB_OF_ELITE_GENERATION)
				.forEach(x -> chromosomes.set(x, population.getChromosome().get(x)));
		IntStream.range(Driver.NUMB_OF_ELITE_GENERATION, population.getChromosome().size()).forEach(x -> {
			chromosomes.set(x, mutateChromosome(population.getChromosome().get(x)));
		});

		return mutatePopulation;
	}

	Chromosome mutateChromosome(Chromosome mutatechromosome) {
		Chromosome chromosome = new Chromosome(data).initialize();
		IntStream.range(0, mutatechromosome.getDiscussion().size()).forEach(x -> {
			if (Driver.MUTATION_RATE > Math.random())
				mutatechromosome.getDiscussion().set(x, chromosome.getDiscussion().get(x));
		});
		return mutatechromosome;
	}

	Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(Driver.TOURNAMENT_SELECTION_SIZE, data);
		IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
			tournamentPopulation.getChromosome().set(x,
					population.getChromosome().get((int) (Math.random() * population.getChromosome().size())));
		});
		return tournamentPopulation;
	}
}
