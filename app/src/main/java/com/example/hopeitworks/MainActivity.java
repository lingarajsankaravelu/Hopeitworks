package com.example.hopeitworks;
/* Right now this module works fine..
 * Aim of this module is design a drawerlayout from a blank activity.
 * we were not using the Fragment layout inorder to achieve so.
 * Drawerlayout from v4 as v7 have some modification over.
 * but here we have invoked depreceated drawerlayout of v4 so while commming back dont get confused
 * if you change the code make sure the changes were according to either v7 or v4.
 * using this confusion only, we can develop an app which can run on all  versions.
 * Mission accomplished..
 * drawable layout working with custom list .
 * list images seems little large have to compress it
 * bacground colour have to be modified
 * have to insert new theme and check
 * Action bar color have been changed finally.
 * Inserting new theme result in problems.. so we have to specify every other thing seperately
 * 
 * */



import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;






public class MainActivity extends ActionBarActivity implements OnItemClickListener {
 private DrawerLayout drawlay;
 public ListView fraglist;
 private String [] planets;
 private homeAdapter myadap;
 public Fragment myfrag;
 
private ActionBarDrawerToggle drawerlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
            drawlay=(DrawerLayout) findViewById(R.id.drawer);
        fraglist=(ListView) findViewById(R.id.drawlist);
      myadap=new homeAdapter(this);
       fraglist.setAdapter(myadap);
       fraglist.setOnItemClickListener(this);
       drawerlistener= new ActionBarDrawerToggle(this, drawlay, R.drawable.menu17, R.string.open_drawable, R.string.close_drawable){
    	   
       @Override
    public void onDrawerClosed(View drawerView) {
    	// TODO Auto-generated method stub
    	super.onDrawerClosed(drawerView);
    }
     @Override
    	public void onDrawerOpened(View drawerView) {
    		// TODO Auto-generated method stub
    		super.onDrawerOpened(drawerView);
    	}  
       
    
       };
    	
    		
      
    
       
    drawlay.setDrawerListener(drawerlistener);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    myfrag=new Mainfragmet();
    FragmentManager fragmentManager = getFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.maincontent, myfrag).commit();
    }
    @SuppressWarnings("deprecation")
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerlistener.syncState();
	}


    @SuppressWarnings("deprecation")
	public void onConfigurationChanged(Configuration newConfig) {
	
		super.onConfigurationChanged(newConfig);
		drawerlistener.onConfigurationChanged(newConfig);
	}

    
    
    
    public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	if(drawerlistener.onOptionsItemSelected(item))
	{
	   return true;
	}
	
   switch (item.getItemId()) {
case R.id.NewDevice:
	handleBluetooth();
	break;

default:
	break;
}
   return super.onOptionsItemSelected(item);
   }
    
    
 
  
    
    
   
    
    
    public void handleBluetooth() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Bluetooth was clicked", Toast.LENGTH_SHORT).show();
	    Intent newin=new Intent();
	    newin.setClass(getApplicationContext(), Addnewdevice.class);
	    startActivity(newin);
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		
		selectedItem(position);		
		// TODO Auto-generated method stub
		
	}


	public void selectedItem(int position) {
		// TODO Auto-generated method stub
	
		fraglist.setItemChecked(position, true);
		
	}
	public void setTitle(String Title)
	{
		getSupportActionBar().setTitle(Title);
	}
	
}
class homeAdapter extends BaseAdapter
{
Context context;
String [] frameitems={"Chrome Support File","Firefox Support File","Advertisement","Exit"};
int [] imageid={R.drawable.google,R.drawable.firefox,R.drawable.advertisement,R.drawable.exit};
 
public homeAdapter(Context context) {
		// TODO Auto-generated constructor stub
	this.context=context;
	}
		
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageid.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=null;
		if(convertView==null)
		{
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			row=inflater.inflate(R.layout.drawerlayout, parent,false);
		}
		else
		{
			row=convertView;
			
		}
		ImageView drawimage=(ImageView)row.findViewById(R.id.imageView1);
		TextView drawtext=(TextView)row.findViewById(R.id.textView1);
		drawimage.setImageResource(imageid[position]);
		drawtext.setText(frameitems[position]);
		return row;
	}
	
}
