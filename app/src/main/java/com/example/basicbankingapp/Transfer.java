package com.example.basicbankingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Transfer extends AppCompatActivity {
    EditText acc,amt;
    String acc_no,amount;
    DataHelper1 db;
    DBTrans db_t;
    Button btn;
    String[] result,result_r;
    String to_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        db = new DataHelper1(this);
        db_t=new DBTrans(this);

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
                        startActivity(new Intent(getApplicationContext(), Customers.class));
                        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
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
        to_name = intent.getStringExtra("to_user");
        System.out.println(to_name);

        TextView t1=(TextView)findViewById(R.id.textView4);
        t1.setText(to_name);

        acc=findViewById(R.id.e1);
        amt=findViewById(R.id.e2);

        btn=findViewById(R.id.transfer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (acc.getText().toString().equals("") || amt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "All Fields are Required!", Toast.LENGTH_LONG).show();

                } else {
                    acc_no = acc.getText().toString();
                    amount = amt.getText().toString();
                    System.out.println(acc_no + " " + amount);
                    result = db.display_customer(to_name);
                    String to_accno = result[0];
                    System.out.println(to_accno);

                    if (to_accno.equals(acc_no)) {
                        Toast.makeText(getApplicationContext(), "You cannot transfer to your own account", Toast.LENGTH_LONG).show();
                    } else {
                        //senders details
                        String name = db.get_name(acc_no);
                        result = db.display_customer(name);
                        String bal = result[2];
                        int int_amt = Integer.parseInt(amount);
                        int int_bal = Integer.parseInt(bal);

                        //receivers details
                        String name_r = db.get_name(to_accno);
                        result_r = db.display_customer(name_r);
                        String bal_r = result_r[2];
                        int int_bal_r = Integer.parseInt(bal_r);


                        //amount transferring is greater than current balance
                        if (int_amt > int_bal) {
                            Toast.makeText(getApplicationContext(), "Failed! Insufficient balance", Toast.LENGTH_LONG).show();
                        } else {
                            Boolean r = db.transaction(acc_no, int_bal - int_amt, to_accno, int_bal_r + int_amt);

                            if (r == true) {
                                db_t.insert(name, name_r, int_amt);

                                Toast.makeText(getApplicationContext(), "Transferred successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                            }

                        }


                    }

                }

            }});

    }
}