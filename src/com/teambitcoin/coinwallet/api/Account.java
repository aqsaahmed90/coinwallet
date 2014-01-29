package com.teambitcoin.coinwallet.api;


public class Account {
	private String username;
	private String password;
	private String guid;
	
	public Account(){}
	
	public Account(String username, String password, String guid){
		this.username = username;
		this.password = password;
		this.guid = guid;
	}
	
	@Override
	public String toString(){
		return "[username: "+username+", password: "+password+", guid: "+guid+"]";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
}
