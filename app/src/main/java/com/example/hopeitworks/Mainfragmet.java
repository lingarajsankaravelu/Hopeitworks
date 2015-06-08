package com.example.hopeitworks;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Mainfragmet extends Fragment{

	TextView text1,text2;
	Button sendla,receivela,sendlwd,receivelwd;
    @Override
    
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedinstance) {
        View view = inflater.inflate(R.layout.mymainwindow, container, false);
        //String menu = getArguments().getString("Menu");
       // text= (TextView) view.findViewById(R.id.detail);
        //text.setText(menu);
        text1=(TextView) view.findViewById(R.id.linkalonetextview);
        text2=(TextView) view.findViewById(R.id.linkwithdatatextView);
        sendla=(Button) view.findViewById(R.id.linksend);
        receivela=(Button) view.findViewById(R.id.linkreceive);
        sendlwd=(Button) view.findViewById(R.id.linkdatasend);
        receivelwd=(Button) view.findViewById(R.id.linkdatareceive);
        return view;
        
    }
	
}
