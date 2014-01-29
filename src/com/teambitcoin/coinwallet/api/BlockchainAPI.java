package com.teambitcoin.coinwallet.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * This class is used to connect with Blockchain's API.
 * 
 * Example usage:
 * Account newAccount = new BlockchainAPI().createAccount(<username>, <password>);
 * 
 * I decided to keep it as a normal class instead of a class with static methods,
 * because it might be easier to adapt it later if we need to do pooling or keep
 * track of states, etc.
 * 
 * @author FT
 *
 */
public class BlockchainAPI {
	private URIBuilder getBase(){
		final String API_KEY = "LK75FDss";
		try{
			return new URIBuilder()
						.setScheme("http")
						.setHost("blockchain.info")
						.addParameter("api_code", API_KEY);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Creates a new Account with a single Bitcoin address in it.
	 * 
	 * @param username username associated with that account 
	 * @param password password of the user, must be at least 10 characters long
	 * @return a new Account if the creation was successful, null otherwise
	 * @throws Exception in case there was a problem creating the Account
	 */
	public Account createAccount(String username, String password) throws Exception {
		if(password.length() < 10){
			throw new Exception("ERROR: Password's length must be at least 10!");
		}
		Request r = Request.Get(getBase().setPath("/api/v2/create_wallet")
										.addParameter("password", password)
										.build());
		JsonObject response = (JsonObject) new JsonParser().parse(r.execute()
															.returnContent()
															.asString());
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setGuid(response.get("guid").getAsString());
		return account;
	}
	
	/**
	 * Gets all the addresses related to an account.
	 * 
	 * Example usage:
	 * 
	 * List<Address> addresses = new BlockchainAPI().getAddresses(new Account(<username>,<pass>,<guid>));
	 * 
	 * @param account account for which the addresses are wanted
	 * @return a list of addresses
	 * @throws Exception if the supplied account is not valid
	 */
	public List<Address> getAddresses(Account account) throws Exception{
		if(account.getGuid() == null || account.getGuid().length() <= 0){
			throw new Exception("ERROR: Not a valid GUID!");
		}
		Request r = Request.Get(getBase().setPath("/merchant/"+account.getGuid()+"/list")
										 .addParameter("password", account.getPassword())
										 .build());
		
		JsonObject response = (JsonObject) new JsonParser().parse(r.execute()
																	.returnContent()
																	.asString());
		
		JsonArray addrs = response.get("addresses").getAsJsonArray();
		List<Address> addresses = new ArrayList<Address>();
		for(int i = 0; i < addrs.size(); i++){
			Address addr = new Address();
			JsonObject addrObj = (JsonObject) addrs.get(i);
			addr.setAddress(addrObj.get("address").getAsString());
			addr.setBalance(addrObj.get("balance").getAsInt());
			addr.setLabel(addrObj.get("label").getAsString());
			addr.setTotalReceived(addrObj.get("total_received").getAsInt());
			
			addresses.add(addr);
		}
		
		return addresses;
	}
	
	/**
	 * Adds a new bitcoin address to the account.
	 * 
	 * @param account account to which the new address should be added
	 * @return the new Address that was added
	 * @throws Exception if the account is not valid
	 */
	public Address generateNewAddress(Account account) throws Exception {
		if(account.getGuid() == null || account.getGuid().length() <= 0){
			throw new Exception("ERROR: Not a valid GUID!");
		}
		Request r = Request.Get(getBase().setPath("/merchant/"+account.getGuid()+"/new_address")
										 .addParameter("password", account.getPassword())
										 .addParameter("label", UUID.randomUUID().toString())
										 .build());
		
		JsonObject response = (JsonObject) new JsonParser().parse(r.execute()
																	.returnContent()
																	.asString());
		
		Address address = new Address();
		address.setAddress(response.get("address").getAsString());
		address.setBalance(0);
		address.setLabel(response.get("label").getAsString());
		address.setTotalReceived(0);
		
		return address;
	}
	
	/**
	 * Will archive the given address that's associated with the account.
	 * 
	 * IMPORTANT: the archived address must be SAVED in the local DB, because
	 * there's no API call that let's us find list of archived addresses.
	 * 
	 * @param account
	 * @param address
	 * @throws Exception if account is not valid or if the archiving fails
	 */
	public void archiveAddress(Account account, Address address) throws Exception {
		if(account.getGuid() == null || account.getGuid().length() <= 0){
			throw new Exception("ERROR: Not a valid GUID!");
		}
		Request r = Request.Get(getBase().setPath("/merchant/"+account.getGuid()+"/archive_address")
										 .addParameter("password", account.getPassword())
										 .addParameter("address", address.getAddress())
										 .build());
		JsonObject response = (JsonObject) new JsonParser().parse(r.execute()
																	.returnContent()
																	.asString());
		if(!response.get("archived").getAsString().equals(address.getAddress())){
			throw new Exception("ERROR: couldn't archive!");
		}
	}
	
	/**
	 * Will unarchive the given address that's associated with the account.
	 * 
	 * IMPORTANT: the archived address must be REMOVED in the local DB.
	 * 
	 * @param account
	 * @param address
	 * @throws Exception if unarchiving fails
	 */
	public void unarchiveAddress(Account account, Address address) throws Exception{
		if(account.getGuid() == null || account.getGuid().length() <= 0){
			throw new Exception("ERROR: Not a valid GUID!");
		}
		Request r = Request.Get(getBase().setPath("/merchant/"+account.getGuid()+"/unarchive_address")
										 .addParameter("password", account.getPassword())
										 .addParameter("address", address.getAddress())
										 .build());
		JsonObject response = (JsonObject) new JsonParser().parse(r.execute()
																	.returnContent()
																	.asString());
		if(!response.get("active").getAsString().equals(address.getAddress())){
			throw new Exception("ERROR: couldn't unarchive!");
		}
	}
}
