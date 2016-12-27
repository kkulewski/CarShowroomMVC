package Model;

import java.util.Currency;

public class Position 
{
	private int id;
	private String title;
	private Currency salary;
	
	public Position(int id, String title, Currency salary) {
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
	public Currency getSalary() {
		return salary;
	}
	public void setSalary(Currency salary) {
		this.salary = salary;
	}
}
