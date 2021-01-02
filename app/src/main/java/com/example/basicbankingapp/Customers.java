package com.example.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customers extends AppCompatActivity implements View.OnClickListener {
    DataHelper1 db;
    ListView result_list;
    SimpleAdapter adapter;
    ArrayList acc_no, name, email, bal, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        db = new DataHelper1(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.Customers);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                    case R.id.Customers:

                        return true;
                    case R.id.Trans:
                        startActivity(new Intent(getApplicationContext(), Transactions.class));
                        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                }
                return false;
            }
        });
        simpleArray();
    }

    public void simpleArray() {
//        db.insert(1,"meghashahri@gmail.com","Megha Shahri",2174);
//        db.insert(2,"vaswani.raj@gmail.com","Raj Vaswani",550);
//        db.insert(3,"parveshkalwani3@gmail.com","Parvesh Kalwani",25411);
//        db.insert(4,"pal.dipali2oct@gmail.com","Dipali Pal",2584);
//        db.insert(5,"vaishnaviu12@gmail.com","Vaishnavi Utane",631);
//        db.insert(6,"pol.prajakta@gmail.com","Prajakta Pol",7890);
//        db.insert(7,"snehaaratnam@gmail.com","Sneha Ratnam",850);
//        db.insert(8,"jacky.ahuja15@gmail.com","Jacky Ahuja",4900);
//        db.insert(9,"punjabimanish@gmail.com","Manish Punjabi",10000);
//        db.insert(10,"manishashahri@gmail.com","Manisha Shahri",5908);

        result = db.display_data();
        System.out.println(result);
        System.out.println(result.get(0));
        acc_no = (ArrayList) result.get(0);
        email = (ArrayList) result.get(1);
        name = (ArrayList) result.get(2);
        bal = (ArrayList) result.get(3);
        result_list = (ListView) findViewById(R.id.customer_list);

        String[] from = new String[]{"name"};
        int[] to = new int[]{R.id.username};

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();


        for (int i = 0; i < acc_no.size(); i++) {
            int j = i + 1;
            HashMap<String, String> map = new HashMap<String, String>();
            System.out.println(acc_no.get(i));
            System.out.println(name.get(i));
            System.out.println(email.get(i));
            System.out.println(bal.get(i));
            map.put("name", "" +name.get(i));
            System.out.println("mappp" + map);
            fillMaps.add(map);

        }
    adapter =new SimpleAdapter(this,fillMaps, R.layout.activity_listview, from, to);
        result_list.setAdapter(adapter);
}
    @Override
    public void onClick(View view) {
        ListView list = (ListView)findViewById(R.id.customer_list);
        for (int i=0; i < list.getChildCount(); i++)
        {
            System.out.println("child"+list.getChildAt(i));
        }

        //get the row the clicked button is in
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        TextView child = (TextView)vwParentRow.getChildAt(0);
        String c=child.getText().toString();
        System.out.println(c);
        System.out.println("10");
        Intent intent = new Intent(getApplicationContext(), Customer_detail.class);
        System.out.println("11");
        intent.putExtra("name", c);
        System.out.println("12");
        startActivity(intent);
        System.out.println("13");
        overridePendingTransition (R.anim.right_slide_in, R.anim.right_slide_out);

    }
}