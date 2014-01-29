package com.teambitcoin.coinwallet.api;

public class Address {
	private int balance;
	private String address;
	private String label;
	private int totalReceived;
	
	public Address(){}
	
	public Address(String address, String label, int balance, int totalReceived){
		this.address = address;
		this.label = label;
		this.balance = balance;
		this.totalReceived = totalReceived;
	}
	
	@Override
	public String toString(){
		return "[address: "+address
				+", balance: "+balance
				+", label: "+label
				+", total_received: "+totalReceived+"]";
	}
	
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(int total_received) {
		this.totalReceived = total_received;
	}
	
	
}
