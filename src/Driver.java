import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	public static final int POPULATION_SIZE = 100;
	public static final double MUTATION_RATE = 0.1;
	public static final double CROSSOVER_RATE = 0.7;
	public static final int TOURNAMENT_SELECTION_SIZE = 2;
	public static final int NUMB_OF_ELITE_GENERATION = 1;
	private Data data;
	private int chromosomenumb = 0;
	private int projectNumb = 1;

	public static void main(String[] args) throws IOException {
		Driver d = new Driver();
		d.data = new Data();
		int generationNumber = 0;
//		d.printData();
		System.out.println("Generation # " + ++generationNumber);
		System.out.print("CHROMOSOME # |           ");
		System.out.print("    [projectID,InstructorID,Examiner1ID,Examiner2ID,meeting-time]       ");
		System.out.println(" | Fitness | Conflicts|");
		System.out.println(
				"**************************************************************************************************");

		GA geneticAlgorithm = new GA(d.data);
		Population population = new Population(Driver.POPULATION_SIZE, d.data).sortByFitness();
		population.getChromosome()
				.forEach(chromosome -> System.out.println("       " + d.chromosomenumb++ + "     | " + chromosome
						+ " | " + String.format("%.5f", chromosome.getFitness()) + " | "
						+ chromosome.getNumOfConflicts()));
		d.printScheduleAsTable(population.getChromosome().get(0), generationNumber);
		d.projectNumb = 1;

		while (population.getChromosome().get(0).getFitness() != 1.0) {
			System.out.println("Generation # " + ++generationNumber);
			System.out.print("  CHROMOSOME # |           ");
			System.out.print("    [projectID,InstructorID,Examiner1ID,Examiner2ID,meeting-time]       ");
			System.out.println("   | Fitness | Conflicts|");
			System.out.print("**************************************************************************************************");
			System.out.println("**************************************************************************************************");
			population = geneticAlgorithm.evolve(population).sortByFitness();
			d.chromosomenumb = 0;
			population.getChromosome()
					.forEach(chromosome -> System.out.println("       " + d.chromosomenumb++ + "       | " + chromosome
							+ " | " + String.format("%.5f", chromosome.getFitness()) + " | "
							+ chromosome.getNumOfConflicts()));
			System.out.println("\n\n                       TABLE:");
			System.out.print("                       ------");
			d.printScheduleAsTable(population.getChromosome().get(0), generationNumber);
			d.projectNumb = 1;
		}

	}

	// printing data in files
	private void printData() {
		System.out.println("Projects");
		System.out.println("========");
		data.getProject().forEach(x -> System.out.println(
				x.getNum() + ",  name: " + x.getName() + ",  topics: " + x.getTopic() + ",  Students:" + x.getStd()));
		System.out.println();

		System.out.println("Doctors");
		System.out.println("=======");
		data.getDoctors().forEach(
				x -> System.out.println(x.getId() + ",  name:  " + x.getName() + ",  preferences:  " + x.getPref()));
		System.out.println();

//		System.out.println("Rooms");
//		System.out.println("=======");
//		data.getRoom().forEach(x -> System.out.println("Room " + x.getNumber()));
//		System.out.println();

		System.out.println("Time Slots");
		System.out.println("==========");
		data.getTimeSlots().forEach(x -> System.out.println("ID: " + x.getId() + "   ||  time: " + x.getTime()));
		System.out.println();
		System.out.print(
				"*************************************************************************************************************************");
		System.out.println();
	}

	private void printScheduleAsTable(Chromosome chromosome, int generationNumber) {
		ArrayList<Class> discussions = chromosome.getDiscussion();
		System.out.print("\n                       ");
		System.out.println(
				"Discussion # ^^ Project (name, id, students)   ^^  Instructor (Id)   ^^  Examiner1 (Id)    ^^   Examiner2 (Id)    ^^     Meeting Time (Id)");
		System.out.print("                       ");
		System.out.print("**************************************************************************************************");
		System.out.println("**************************************************************************************************");
		discussions.forEach(x -> {
			int projectIndex = data.getProject().indexOf(x.getProject());
			int instructorsIndex = data.getDoctors().indexOf(x.getInstructor());
			int firstexaminerIndex = data.getDoctors().indexOf(x.getExaminer());
			int secondexaminerIndex = data.getDoctors().indexOf(x.getExaminer1());
			int timeIndex = data.getTimeSlots().indexOf(x.getTimeSlot());
			System.out.print("                       ");
			System.out.print(String.format("  %1$7d  ", projectNumb) + "  ^^ ");
			System.out
					.print(String.format("%1$25s",
							data.getProject().get(projectIndex).getName() + " ("
									+ data.getProject().get(projectIndex).getNum() + ", " + x.getProject().getStd())
							+ ")     ^^ ");
			System.out.print(String.format("%1$18s", data.getDoctors().get(instructorsIndex).getName() + " ("
					+ data.getDoctors().get(instructorsIndex).getId() + ")") + "  ^^ ");
			System.out.print(String.format("%1$18s", data.getDoctors().get(firstexaminerIndex).getName() + " ("
					+ data.getDoctors().get(firstexaminerIndex).getId() + ")") + "  ^^ ");
			System.out.print(String.format("%1$18s", data.getDoctors().get(secondexaminerIndex).getName() + " ("
					+ data.getDoctors().get(secondexaminerIndex).getId() + ")") + "  ^^ ");
			System.out.println(String.format("%1$20s",data.getTimeSlots().get(timeIndex).getTime() + " ("
					+ data.getTimeSlots().get(timeIndex).getId() + ")"));
			projectNumb++;
		});
		if (chromosome.getFitness() == 1)
			System.out.println("> Solution Found in " + (generationNumber + 1) + " generations");
		System.out.print("-----------------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------------------");
	}
}
