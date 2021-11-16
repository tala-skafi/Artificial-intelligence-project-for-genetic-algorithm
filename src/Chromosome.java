import java.util.ArrayList;

public class Chromosome {
	private ArrayList<Class> discussions;
	private boolean isFitChange = true;
	private double fitness = -1;
	private int discussionNum = 0;
	private Data data;
	private int numOfConflicts = 0;

	public Data getData() {
		return data;
	}

	public Chromosome(Data data) {
		this.data = data;
		discussions = new ArrayList<Class>(data.getNumberOfProjects());
	}

	public Chromosome initialize() {
		new ArrayList<Project>(data.getProject()).forEach(p -> {
			Class newDisc = new Class(discussionNum++, p);
			newDisc.setTimeSlot(data.getTimeSlots().get((int) (data.getTimeSlots().size() * Math.random())));
//			newDisc.setRoom(data.getRoom().get((int)(data.getRoom().size()*Math.random())));
			newDisc.setInstructor(p.getDoctor().get((int) (p.getDoctor().size() * Math.random())));
			newDisc.setExaminer(p.getExaminer().get((int) (p.getExaminer().size() * Math.random())));
			newDisc.setExaminer1(p.getExaminer1().get((int) (p.getExaminer1().size() * Math.random())));
			discussions.add(newDisc);
		});
		return this;
	}

	public int getNumOfConflicts() {
		return numOfConflicts;
	}

	public ArrayList<Class> getDiscussion() {
		isFitChange = true;
		return discussions;
	}

	public double getFitness() {
		if (isFitChange == true) {
			fitness = calculateFitness();
			isFitChange = false;
		}
		return fitness;
	}

	public double calculateFitness() {
		//int i=0;
		numOfConflicts = 0;
		discussions.forEach(d -> {
			if(d.getInstructor() == d.getExaminer() || d.getInstructor() == d.getExaminer1() || d.getExaminer() == d.getExaminer1())
				numOfConflicts++;
			String s1 = d.getInstructor().getPref();

			  int total1 = 0;
			  for(int i=0; i<s1.length(); i+=2) {
			    total1 = total1 + Character.getNumericValue(s1.charAt(i));
			  }
			  String s2 = d.getExaminer().getPref();

			  int total2 = 0;
			  for(int i=0; i<s2.length(); i+=2) {
				  total2 = total2 + Character.getNumericValue(s2.charAt(i));
			  }
			  String s3 = d.getExaminer1().getPref();

			  int total3 = 0;
			  for(int i=0; i<s3.length(); i+=2) {
				  total3 = total3 + Character.getNumericValue(s3.charAt(i));
			  }
			  
			  String t =  d.getProject().getTopic();

			  int topic = 0;
			  for(int i=0; i<t.length(); i+=2) {
				  topic = topic + Character.getNumericValue(t.charAt(i));
			  }
			if(total1 !=topic || total2!=topic ||total3!=topic )
					numOfConflicts++;
			
			
	discussions.stream().filter( h -> discussions.indexOf(h) >= discussions.indexOf(d)).forEach(h -> {
				if(d.getTimeSlot() == h.getTimeSlot() && d.getId() != h.getId()) {
					numOfConflicts++;
					//if(d.getInstructor() == h.getInstructor() || d.getExaminer() == h.getExaminer1() || d.getExaminer1() == h.getExaminer1())
						//numOfConflicts++;
				}
			});
		});
		return 1/(double)(numOfConflicts + 1);}

	@Override
	public String toString() {
		String returnValue = new String();
		for (int x = 0; x < discussions.size() - 1; x++)
			returnValue += discussions.get(x) + ",";
		returnValue += discussions.get(discussions.size() - 1);
		return returnValue;
	}
}
