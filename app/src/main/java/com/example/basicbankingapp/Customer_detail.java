package com.example.basicbankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Customer_detail extends AppCompatActivity {
    DataHelper1 db;
    ListView result_list;
    SimpleAdapter adapter;
    String[] acc_no, name, email, bal, result;
    String account_no;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
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
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        result = db.display_customer(name);
        String acc=result[0];
        String email=result[1];
        String bal=result[2];

        TextView t1=(TextView)findViewById(R.id.textView2);
        t1.setText(name);

        TextView t2=(TextView)findViewById(R.id.textView4);
        t2.setText(acc);

        TextView t3=(TextView)findViewById(R.id.textView6);
        t3.setText(email);

        TextView t4=(TextView)findViewById(R.id.textView8);
        t4.setText("Rs"+" "+bal);

        btn=findViewById(R.id.transfer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Customer_detail.this,Transfer.class);
                i.putExtra("to_user",name);
                startActivity(i);
                overridePendingTransition (R.anim.right_slide_in, R.anim.right_slide_out);

            }});




    }

}
