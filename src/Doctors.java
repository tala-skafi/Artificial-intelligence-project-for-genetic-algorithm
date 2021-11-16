
public class Doctors {
	private String id;
	private String name;
	public String pref;
	public Doctors() {}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPref(String pref) {
		this.pref = pref;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPref() {
		return pref;
	}
	
	@Override
	public String toString() {
		return "Dr." + name;
	}	
}
