package Model;

public class Car 
{
	private int id;
	private String brand;
	private String model;
	private double price;
	private boolean isNew;
	
	public Car(int id, String brand, String model, double price, boolean isNew) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.isNew = isNew;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
