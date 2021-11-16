import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Data {
	private static Scanner ps;
	private static Scanner ms;

//	private ArrayList<Room> room;
	private ArrayList<TimeSlots> timeSlots;
	private ArrayList<Doctors> doctors;
	private ArrayList<Project> projects;
	private int numberOfProjects = 0;

	public Data() throws IOException {
		initialize();
	}

	private Data initialize() throws IOException {

		TimeSlots time1 = new TimeSlots("T1", "09:00 - 10:00");
		TimeSlots time2 = new TimeSlots("T2", "10:00 - 11:00");
		TimeSlots time3 = new TimeSlots("T3", "11:00 - 12:00");
		TimeSlots time4 = new TimeSlots("T4", "12:00 - 01:00");
		TimeSlots time5 = new TimeSlots("T5", "01:00 - 02:00");
		TimeSlots time6 = new TimeSlots("T6", "02:00 - 03:00");
		timeSlots = new ArrayList<TimeSlots>(Arrays.asList(time1, time2, time3, time4, time5, time6));

		doctors = new ArrayList<Doctors>();
		projects = new ArrayList<Project>();
		File filedoctors = new File("members.txt");
		File fileprojects = new File("projects.txt");
		openmyfile();
		readfileofmembers(filedoctors, doctors);
		readfileofprojects(fileprojects, projects, doctors);

		closemyfile();
		numberOfProjects += projects.size();
		return this;

	}

	public ArrayList<TimeSlots> getTimeSlots() {
		return timeSlots;
	}

	public ArrayList<Doctors> getDoctors() {
		return doctors;
	}

	public ArrayList<Project> getProject() {
		return projects;
	}

	public int getNumberOfProjects() {
		return this.numberOfProjects;
	}

	public static void openmyfile() {

		try {
			ms = new Scanner(new File("members.txt"));
		} catch (Exception e) {
			System.out.println("cannot find the file");
		}
		try {
			ps = new Scanner(new File("projects.txt"));
		} catch (Exception e) {
			System.out.println("cannot find the file");
		}
	}

	public static void readfileofmembers(File members, ArrayList<Doctors> input1) throws IOException {

		FileReader fr1 = new FileReader(members);
		BufferedReader br1 = new BufferedReader(fr1);
		String line;

		while((line=br1.readLine())!=null) {
			Doctors adoctor = new Doctors();
			
         String []p =line.split("@");
		
         String preferences= "" ;
   adoctor.setId(p[0]);
	adoctor.setName(p[1]);
			
			preferences=p[2];
			//preferences[1]=p[3];
			adoctor.setPref(preferences);

			adoctor.setPref(preferences);
			input1.add(adoctor);
		}

		fr1.close();
		br1.close();

	}

	public static void readfileofprojects(File t, ArrayList<Project> projects, ArrayList<Doctors> doctors)
			throws IOException {

		FileReader fr = new FileReader(t);
		BufferedReader br = new BufferedReader(fr);
		String line1;
		while ((line1 = br.readLine()) != null) {

			String[] sp = line1.split("@");

			Project aproject = new Project(sp[0], sp[1], sp[2], doctors, doctors, doctors, sp[3]);
			projects.add(aproject);

		}
		br.close();
		fr.close();
	}

	public static void closemyfile() {
		ms.close();
		ps.close();
	}

}
