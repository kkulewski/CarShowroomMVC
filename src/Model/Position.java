package Model;

import java.math.BigDecimal;

public class Position 
{
	private int id;
	private String title;
	private BigDecimal salary;
	
	public Position(int id, String title, BigDecimal salary) {
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
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
}
