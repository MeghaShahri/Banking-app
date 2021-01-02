package com.example.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankingapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Transactions extends AppCompatActivity {
    DBTrans db;
    ListView result_list;
    private SimpleAdapter adapter;
    ArrayList senderName,receiverName,amount,result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        db=new DBTrans(this);


        result=db.display_data();
        System.out.println("size"+result.size());


        System.out.println(result);
       System.out.println(result.get(0));
       senderName= (ArrayList) result.get(0);
       receiverName=(ArrayList) result.get(1);
       amount=(ArrayList) result.get(2);
        db.Deletedata("Manisha Shahri");


        if (senderName.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Transactions yet",Toast.LENGTH_LONG).show();
        }


       System.out.println("senName"+senderName);
       System.out.println("recName"+receiverName);
       System.out.println("amt"+amount);

       result_list =  (ListView)findViewById(R.id.lv);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.Trans);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                    case R.id.Customers:
                        startActivity(new Intent(getApplicationContext(), Customers.class));
                        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                    case R.id.Trans:

                        return true;
                }
                return false;
            }
        });
        simpleArray();
   }

    private void simpleArray(){


        String[] from = new String[] {"date", "category","text"};
        int[] to= new int[] { R.id.date, R.id.mycategory,R.id.usertext};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        if (result.size()<1){
            Toast.makeText(getApplicationContext(),"No Transactions yet",Toast.LENGTH_LONG).show();
        }

        for(int i = 0; i < senderName.size(); i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", "" + senderName.get(i));
            map.put("category", "" + receiverName.get(i));
            map.put("text", "Rs" +" "+amount.get(i));
            fillMaps.add(map);
        }

        adapter = new SimpleAdapter(this, fillMaps, R.layout.trans_list, from, to);
        result_list.setAdapter(adapter);
    }


}
