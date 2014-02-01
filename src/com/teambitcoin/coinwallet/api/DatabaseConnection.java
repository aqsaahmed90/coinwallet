package com.teambitcoin.coinwallet.api;

import java.util.List;

// Does nothing, just there so it compiles
// Its an ideal interface with the DB but since we have no DB code,
// I'll use these to give a meaning to the code structure
public class DatabaseConnection 
{

	public List<Address> RetrieveActiveAddresses(List<Address> list)
	{
		return null;
	}
	public List<Address> RetrieveArchivedAddresses(List<Address> list)
	{
		return null;
	}
	public void WriteNewArchivedAddress(Address address)
	{
		
	}
	public void WriteNewActiveAddress(Address address)
	{
		
	}
}
