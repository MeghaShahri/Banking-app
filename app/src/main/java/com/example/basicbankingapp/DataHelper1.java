package  com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class DataHelper1 extends SQLiteOpenHelper {
    public static final String b = "bank";
    public static final String c = "Customers";
    public static final String t = "Transactions";
    public static final String e = "email";

    public DataHelper1(@Nullable Context context) {
super(context, "bank.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Customers(account_number integer primary key ,email text,name text,balance integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + c);

    }

    public void insert(int account_number, String email,String name,int balance) {
        System.out.println("inside insert");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_number", account_number);
        contentValues.put("email", email);
        contentValues.put("name",name);
        contentValues.put("balance",balance);

        db.insert("Customers", null, contentValues);

    }

    public ArrayList display_data() {
        System.out.println("inside display");
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_accnumber = new ArrayList<String>();
        ArrayList<String> array_email = new ArrayList<String>();
        ArrayList<String> array_name= new ArrayList<String>();
        ArrayList<String> array_bal= new ArrayList<String>();
        ArrayList<ArrayList<String>> array_result = new ArrayList<>();

        Cursor res=db.rawQuery("SELECT * FROM Customers",null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            array_accnumber.add(res.getString(res.getColumnIndex("account_number")));
            array_email.add(res.getString(res.getColumnIndex("email")));
            array_name.add(res.getString(res.getColumnIndex("name")));
            array_bal.add(res.getString(res.getColumnIndex("balance")));
            res.moveToNext();
        }
        System.out.println("acc_no" +array_accnumber+""+"email"+array_email+""+"name"+array_name+""+"bal"+array_bal);
        array_result.add(array_accnumber);
        array_result.add(array_email);
        array_result.add(array_name);
        array_result.add(array_bal);
        return array_result;
    }

    public String[] display_customer(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] result=new String[3];
        ArrayList<String> array_accnumber = new ArrayList<String>();
        ArrayList<String> array_email = new ArrayList<String>();
        ArrayList<String> array_name= new ArrayList<String>();
        ArrayList<String> array_bal= new ArrayList<String>();
        ArrayList<ArrayList<String>> array_result = new ArrayList<>();
        String acc_no=null,email=null,bal=null;
        Cursor res=db.rawQuery("SELECT account_number,email, balance FROM Customers WHERE name = ? ", new String[] {name});
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            System.out.println("s"+" "+res.getString(res.getColumnIndex("account_number")));
           acc_no=res.getString(res.getColumnIndex("account_number"));
            email=res.getString(res.getColumnIndex("email"));
            bal=res.getString(res.getColumnIndex("balance"));
            res.moveToNext();
        }
        System.out.println("acc_no" +acc_no+""+"email"+email+""+"bal"+bal);
        result[0]=acc_no;
        result[1]=email;
        result[2]=bal;
        System.out.println(result);
        return result;

    }
    public String get_name(String acc){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res=db.rawQuery("SELECT name FROM Customers WHERE account_number = ? ", new String[] {acc});
        String name=null;
        res.moveToFirst();
        while(res.isAfterLast() == false) {

            name=res.getString(res.getColumnIndex("name"));
            System.out.println(name);
            res.moveToNext();
        }
        return name;
    }

    public boolean transaction(String acc_sender, int balance_sender,String acc_receiver,int balance_receiver) {
        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println(balance_sender+" "+balance_receiver);
        ContentValues contentValues = new ContentValues();
        ContentValues cv = new ContentValues();

        contentValues.put("balance", balance_sender);
        cv.put("balance", balance_receiver);

        Integer update_sender=db.update(c, contentValues, "account_number=?", new String[]{acc_sender});
        Integer updated_receiver=db.update(c, cv, "account_number=?", new String[]{acc_receiver});
        if(update_sender>0 && updated_receiver>0){
            return true;
        }
        else{
            return false;
        }
    }

}
