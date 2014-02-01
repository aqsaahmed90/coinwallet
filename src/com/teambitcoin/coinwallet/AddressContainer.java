package com.teambitcoin.coinwallet;

import java.util.ArrayList;
import java.util.List;

import com.teambitcoin.coinwallet.api.Address;
import com.teambitcoin.coinwallet.api.DatabaseConnection;

public class AddressContainer {

	public DatabaseConnection dbConnection;
	
	public List<Address> activeAddressList;
	public List<Address> archivedAddressList;
	
	public AddressContainer()
	{
		dbConnection = new DatabaseConnection();
		activeAddressList = new ArrayList<Address>();
		archivedAddressList = new ArrayList<Address>();
		PopulateActiveAddressList();
		PopulateArchivedAddressList();
	}

	
	public void CreateAddress(Address address)
	{
		activeAddressList.add(address);
		ActiveAddressToDatabase(address);
	}
	
	public void ArchiveAddress(Address address)
	{
		activeAddressList.remove(address);
		archivedAddressList.add(address);
	}
	
	/**
	 * Database interactions 
	 * 
	 */
	public void PopulateActiveAddressList()
	{
		dbConnection.RetrieveActiveAddresses(activeAddressList);
	}

	public void PopulateArchivedAddressList()
	{
		dbConnection.RetrieveArchivedAddresses(archivedAddressList);
	}
	
	public void ActiveAddressToDatabase(Address address)
	{
		dbConnection.WriteNewActiveAddress(address);
	}
	
	public void ArchiveAddressToDatabase(Address address)
	{
		dbConnection.WriteNewArchivedAddress(address);
	}
	
}
