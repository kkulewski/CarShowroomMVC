package Model;

import java.sql.Date;

public class Purchase 
{
	private int id;
	private int id_client;
	private int id_worker;
	private int id_car;
	private Date  transaction_date;
	
	public Purchase(int id, int id_client, int id_worker, int id_car, Date transaction_date) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_worker = id_worker;
		this.id_car = id_car;
		this.transaction_date = transaction_date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_worker() {
		return id_worker;
	}
	public void setId_worker(int id_worker) {
		this.id_worker = id_worker;
	}
	public int getId_car() {
		return id_car;
	}
	public void setId_car(int id_car) {
		this.id_car = id_car;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	
}
