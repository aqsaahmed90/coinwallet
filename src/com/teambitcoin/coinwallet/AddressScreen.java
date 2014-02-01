package com.teambitcoin.coinwallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.teambitcoin.coinwallet.api.Address;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AddressScreen extends Activity {
	SimpleAdapter simpleAdapter;
	AddressContainer addresses;
	List<HashMap<String, String>> addressEntries;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_main);
		
		addresses = new AddressContainer();
		initDummyList();
		createAddressEntries();
		
		ListView addrListView = (ListView) findViewById(R.id.address_list);
		
		simpleAdapter = new SimpleAdapter(this, addressEntries, 
				android.R.layout.simple_list_item_1, new String[] {"address"}, 
				new int[] {android.R.id.text1});
		
		addrListView.setAdapter(simpleAdapter);
		registerForContextMenu(addrListView);
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo info) {
		super.onCreateContextMenu(contextMenu, view, info);
		
		// TODO: change all hard coded strings to resources 
		contextMenu.setHeaderTitle("Options");
		contextMenu.add(1, 1, 1, "Edit");
		contextMenu.add(1, 2, 2, "Archive");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {
		//Toast.makeText(this, "Not yet implemented!", Toast.LENGTH_SHORT).show();
		if(menuItem.getItemId() == 2)
		{
			Toast.makeText(this, "Archiving address", Toast.LENGTH_SHORT).show();
			
		}
		return true;
	}
	
	// TODO: replace with real address fetch
	// TODO: Get rid of this damn thing :)
	private void initDummyList() {
		addresses.CreateAddress(new Address("addr1", "my_addr_label1", 0, 0));
		addresses.CreateAddress(new Address("addr2", "my_addr_label2", 0, 0));
		addresses.CreateAddress(new Address("addr3", "my_addr_label3", 0, 0));
		addresses.CreateAddress(new Address("addr4", "my_addr_label4", 0, 0));
		addresses.CreateAddress(new Address("addr5", "my_addr_label5", 0, 0));
		addresses.CreateAddress(new Address("addr6", "my_addr_label6", 0, 0));
		addresses.CreateAddress(new Address("addr7", "my_addr_label7", 0, 0));
		addresses.CreateAddress(new Address("addr8", "my_addr_label8", 0, 0));
		addresses.CreateAddress(new Address("addr9", "my_addr_label9", 0, 0));
		addresses.CreateAddress(new Address("addr0", "my_addr_label0", 0, 0));
	}
	
	// oye oye.
	public void createAddressEntries()
	{		
		addressEntries = new ArrayList<HashMap<String, String>>();
		for(Address address : addresses.activeAddressList)
		{
			HashMap<String,String> newEntry = new HashMap<String,String>();
			newEntry.put("address",address.getLabel());
			addressEntries.add(newEntry);
		}
	}
	
}
