package Model;

public class Position 
{
	private int id;
	private String title;
	private int salary;
	
	public Position(int id, String title, int salary) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
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
}
