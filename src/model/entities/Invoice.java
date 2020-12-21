package model.entities;

import java.util.Date;

public class Invoice {
	private int invoiceCode;
	private Date period;
	private int user;
	private int plan;
	
	public Invoice(int invoiceCode, Date period, int user, int plan) {
		super();
		this.invoiceCode = invoiceCode;
		this.period = period;
		this.user = user;
		this.plan = plan;
	}
	
	public String toString() {
		return "To be implemented...";
	}
	
	public int getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(int invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
}
