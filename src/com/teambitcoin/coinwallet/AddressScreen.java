package com.teambitcoin.coinwallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.teambitcoin.coinwallet.api.Address;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AddressScreen extends Activity {
	List<HashMap<String, String>> addressList;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_main);
		
		addressList = new ArrayList<HashMap<String, String>>();
		initDummyList();
		
		ListView addrListView = (ListView) findViewById(R.id.address_list);
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, addressList, 
				android.R.layout.simple_list_item_1, new String[] {"address"}, 
				new int[] {android.R.id.text1});
		
		addrListView.setAdapter(simpleAdapter);
	}
	
	// TODO: replace with real address fetch
	private void initDummyList() {
		addressList.add(createAddress("address", new Address("addr010101", "my_addr_label", 0, 0)));
		addressList.add(createAddress("address", new Address("addr21212", "my_addr_label2", 0, 0)));
	}
	
	private HashMap<String, String> createAddress(String key, Address addr) {
		HashMap<String, String> addressMap = new HashMap<String, String>();
		addressMap.put(key, addr.getAddress());
		
		return addressMap;
	}
	
}
