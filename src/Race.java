package Problem2;

public class Race {
	
	private String type;
	private int level;

	public Race( String type, int level) {
		super();
		this.type = type;
		this.level = level;
	}
	
	

	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	@Override
	public String toString() {
		return "Race [type=" + type + ", level=" + level + "]";
	}
	
	
	

}
