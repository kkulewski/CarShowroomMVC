package Model;

public class Worker 
{
	private int id;
	private String name;
	private String surname;
	private long cnum;
	private String city;
	private String street;
	private int id_position;
	
	public Worker(int id, String name, String surname, long cnum, String city, String street, int id_position) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.cnum = cnum;
		this.city = city;
		this.street = street;
		this.id_position = id_position;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public long getCnum() {
		return cnum;
	}
	public void setCnum(long cnum) {
		this.cnum = cnum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getId_position() {
		return id_position;
	}
	public void setId_position(int id_position) {
		this.id_position = id_position;
	}
	
	
}
