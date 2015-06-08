package com.example.hopeitworks;


import java.util.ArrayList;










import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Addnewdevice extends ActionBarActivity
{
     int i;
     String name;
	BroadcastReceiver devicediscovery = new BroadcastReceiver(){

	   	  
	   	  public void onReceive(Context context, Intent intent) {
	   	   // TODO Auto-generated method stub
	   	   String action = intent.getAction();
	   	  
	   	 Toast.makeText(getApplicationContext(), action, Toast.LENGTH_SHORT).show();   
	   	   
	   	   if(BluetoothDevice.ACTION_FOUND.equals(action)) {
	   		   BluetoothDevice sdevice;
	   		         
	   		         
	   		        	 
	   		         
	   	             sdevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	   	          
	   	        try
	   	        {
	   	            barraylist.add(sdevice);
	   	       Toast.makeText(getApplicationContext(), barraylist.toString(), Toast.LENGTH_LONG).show();
	   	         disarray.add(sdevice.getName() +"\n"+ sdevice.getAddress());
		               disarray.notifyDataSetChanged();
	   	        }    
	   		         catch(Exception found)
	   		         {
	   		        	Toast.makeText(getApplicationContext(),found.toString(), Toast.LENGTH_SHORT).show();  	 
	   		         }
	   	             
	   	           
	   	   }
	   	   if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
	   	   {
	   		
	   		
	   		Toast.makeText(getApplicationContext()," Discovery finished", Toast.LENGTH_SHORT).show();  
	   		
	   	   }
	   	  if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action))
	   	  {
	   		  int i,j;
	   		  i = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -2147483648);
	          j = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -2147483648);
	   	 
	           Toast.makeText(getApplicationContext(), "paired",Toast.LENGTH_SHORT).show();
	          
	           
	          
	         
	   	  }
	   	  }
	   	  };
	
	
	
	
	public int arrayposition;
	
	
   public BluetoothDevice device;
 
	 ArrayAdapter<String> disarray;
	 ArrayList<BluetoothDevice> barraylist=new ArrayList<BluetoothDevice>();
	
	 
	 ArrayAdapter< String> rfid;
 	 ListView discoverlist;
 	 Boolean fuuid[];

 	BluetoothAdapter ba=BluetoothAdapter.getDefaultAdapter();
 	Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
	@Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.newdevicelist);
    
 	    discoverlist=(ListView) findViewById(R.id.listView1);
      TextView startdiscovery=(TextView) findViewById(R.id.textView1);
      checkBluetoothState();
     /* INtent filter type broadcast receiver was declared here before*/
      discoverlist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		
			if(ba.enable())
			{
			ba.cancelDiscovery();
			}
			
		BluetoothDevice local=barraylist.get(position);
		
		Toast.makeText(getApplicationContext(), local.toString(), Toast.LENGTH_SHORT).show();
		
		 
	        if (local.getBondState() == 12)
	        {
	        	Toast.makeText(getApplicationContext(), "Device already paired", Toast.LENGTH_SHORT).show();
	          return;
	        }
	        Toast.makeText(getApplicationContext(), "pairing", Toast.LENGTH_SHORT).show();
	        newPairDevice(local);
	       
		
			 
		
		
		}

		
		
		
	
      
     
     
     
     
     });
     
     
    
     
     
     
     
     
  
      startdiscovery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				if(!ba.isEnabled())
				{
					checkBluetoothState();
				}
				Toast.makeText(getApplicationContext(), "Textview clicked", Toast.LENGTH_SHORT).show();
				search(v);
				
			 
			}

			
		});
       
      disarray=new ArrayAdapter<String>(Addnewdevice.this, android.R.layout.simple_expandable_list_item_1);
		discoverlist.setAdapter(disarray);
	
	}
	 
	public void onResume() {
		
		 IntentFilter devicediscoverintent=new IntentFilter();
	       devicediscoverintent.addAction(BluetoothDevice.ACTION_FOUND);
	       devicediscoverintent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	       devicediscoverintent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
	      registerReceiver(devicediscovery, devicediscoverintent);
	      super.onResume();
		
	}
	public void onPause() {
		
		
			
		try
		{
		Addnewdevice.this.unregisterReceiver(devicediscovery);
		}
		catch(Exception e)
		{
			
		}
		super.onPause();
	}
	  public void newPairDevice(BluetoothDevice pdevice)
	{
		try
		{
			
		
			 pdevice.getClass().getMethod("createBond", null).invoke(pdevice, null);
		      return;	
		}
		catch(Exception lo)
		{
			Toast.makeText(getApplicationContext(), lo.toString(), Toast.LENGTH_LONG).show();
	}
		}
	
   
   
	
	
	
	
	
	

 	    
 	
 	  
 	  
 	  
 	  
public void onStop() {

if(ba.enable())
{
ba.disable();	
}
try
{
	Addnewdevice.this.unregisterReceiver(devicediscovery);
}
catch(Exception e)
{
	
}
super.onStop();
}
  public void checkBluetoothState() {
	// TODO Auto-generated method stub
	if(ba.isEnabled())
  {
  	Toast.makeText(getApplicationContext(), "Bluetooth already enabled", Toast.LENGTH_LONG).show(); 
  	 startActivity(discoverableIntent);
  	 
  }
else
{
	Intent in=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	 //startActivity(in);    
	 startActivity(discoverableIntent);
	
}

}
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
  }
   
 
  	  private void search(View v) {
				// TODO Auto-generated method stub
			if(ba.isDiscovering())
			{
				Toast.makeText(getApplicationContext(), "discovering", Toast.LENGTH_SHORT).show();
			}
			else
			{
			disarray.clear();
			ba.startDiscovery();
			Toast.makeText(getApplicationContext(), "discovery started", Toast.LENGTH_SHORT).show();
			
			 
			}
			}



  @Override
  /*public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings) {
          return true;
      }
      return super.onOptionsItemSelected(item);
  }*/

public void onDestroy() {
	
	try
	{
	Addnewdevice.this.unregisterReceiver(devicediscovery);
	}
	catch(Exception e)
	{
		
	}
	super.onDestroy();
	}






}


