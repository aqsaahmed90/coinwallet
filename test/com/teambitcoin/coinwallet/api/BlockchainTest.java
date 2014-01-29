package com.teambitcoin.coinwallet.api;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlockchainTest {
	BlockchainAPI api;

	@Before
	public void setUp() throws Exception {
		api = new BlockchainAPI();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void accountCreation() throws Exception{
		Account testAccount = api.createAccount("test", "thisisapassword");
		Address added = api.generateNewAddress(testAccount);
		List<Address> addresses = api.getAddresses(testAccount);
		
		boolean found = false; 
		for(Address addr : addresses){
			if(addr.getAddress().equals(added.getAddress())){
				found = true;
			}
		}
		assertEquals(true, found);
	}
	
	@Test
	public void archiving() throws Exception{
		Account account = api.createAccount("test", "thisisapassword");
		Address address = api.generateNewAddress(account);
		List<Address> addresses = api.getAddresses(account);
		
		boolean found = false;
		for(Address addr : addresses){
			if(addr.getAddress().equals(address.getAddress())){
				found = true;
			}
		}
		assertEquals(true, found);
		
		api.archiveAddress(account, address);
		addresses = api.getAddresses(account);
		found = false;
		for(Address addr : addresses){
			if(addr.getAddress().equals(address.getAddress())){
				found = true;
			}
		}
		assertEquals(false, found);
		
		api.unarchiveAddress(account, address);
		addresses = api.getAddresses(account);
		found = false;
		for(Address addr : addresses){
			if(addr.getAddress().equals(address.getAddress())){
				found = true;
			}
		}
		assertEquals(true, found);
	}

}
