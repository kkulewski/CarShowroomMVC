package Model;

public class Position 
{
	private int id;
	private String title;
	private int salary;
	private boolean isFullTime;
	private boolean isContract;
	
	public Position(int id, String title, int salary, boolean isFullTime, boolean isContract) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
		this.isFullTime = isFullTime;
		this.isContract = isContract;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public boolean getIsFullTime() {
		return isFullTime;
	}
	public void setIsFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}
	public boolean getIsContract() {
		return isContract;
	}
	public void setIsContract(boolean isContract) {
		this.isContract = isContract;
	}
}
