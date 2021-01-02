package com.example.basicbankingapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{
    RelativeLayout relativeLayout;
    Button bt_press, emergency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        return true;
                    case R.id.Customers:
                        startActivity(new Intent(getApplicationContext(),Customers.class));
                        overridePendingTransition (R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                    case R.id.Trans:
                        startActivity(new Intent(getApplicationContext(),Transactions.class));
                        overridePendingTransition (R.anim.right_slide_in, R.anim.right_slide_out);
                        return true;
                }
                return false;
            }
        });

    }


}
