
public class Class {
	private int id;
	private Project project;
	private Doctors instructor;
	private Doctors examiner;
	private Doctors examiner1;
	private TimeSlots timeSlot;
//	private Room room; 

	public Class(int id, Project project) {
		this.id = id;
		this.project = project;
	}

	public void setInstructor(Doctors instructor) {
		this.instructor = instructor;
	}

	public void setExaminer(Doctors examiner) {
		this.examiner = examiner;
	}

	public void setExaminer1(Doctors examiner1) {
		this.examiner1 = examiner1;
	}

	public void setTimeSlot(TimeSlots timeSlot) {
		this.timeSlot = timeSlot;
	}

//////////////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public Project getProject() {
		return project;
	}

	public Doctors getInstructor() {
		return instructor;
	}

	public Doctors getExaminer() {
		return examiner;
	}

	public Doctors getExaminer1() {
		return examiner1;
	}

	public TimeSlots getTimeSlot() {
		return timeSlot;
	}

//////////////////////////////////////////////////////
	@Override
	public String toString() {
		return "[" + project.getNum() +  "," + instructor.getId() + ","
				+ examiner.getId() + "," + examiner1.getId() + "," + timeSlot.getId() + "]";
	}

}
