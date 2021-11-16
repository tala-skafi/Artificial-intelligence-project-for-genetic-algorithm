import java.util.ArrayList;

public class Project {
	private String num;
	private String name;
	private String topic;
	private ArrayList<Doctors> doctor;
	private ArrayList<Doctors> examiner;
	private ArrayList<Doctors> examiner1;
	private String std;

	public Project(String num, String name, String topic, ArrayList<Doctors> doctor, ArrayList<Doctors> examiner,
			ArrayList<Doctors> examiner1, String std) {
		this.num = num;
		this.name = name;
		this.topic = topic;
		this.doctor = doctor;
		this.examiner = examiner;
		this.examiner1 = examiner1;
		this.std = std;
	}

	public String getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public String getTopic() {
		return topic;
	}

	public ArrayList<Doctors> getDoctor() {
		return doctor;
	}

	public ArrayList<Doctors> getExaminer() {
		return examiner;
	}

	public ArrayList<Doctors> getExaminer1() {
		return examiner1;
	}

	public String getStd() {
		return std;
	}

	@Override
	public String toString() {
		return name;
	}
}
