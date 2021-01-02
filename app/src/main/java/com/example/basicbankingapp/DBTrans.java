package  com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class DBTrans extends SQLiteOpenHelper {
    public static final String t = "Transactions";
    public static final String e = "email";

    public DBTrans(@Nullable Context context) {
        super(context, "transactions.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("t2");
        db.execSQL("Create table Transactions(sno integer primary key autoincrement,sender_name text ,receiver_name text,amount integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("t3");
        db.execSQL("DROP TABLE IF EXISTS " + t);
    }

    public void insert(String s_name,String r_name,int amt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sender_name", s_name);
        contentValues.put("receiver_name",r_name);
        contentValues.put("amount",amt);
        db.insert("Transactions", null, contentValues);

    }

    public ArrayList display_data() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_sname = new ArrayList<String>();
        ArrayList<String> array_rname = new ArrayList<String>();
        ArrayList<String> array_amt = new ArrayList<String>();

        ArrayList<ArrayList<String>> array_result = new ArrayList<>();

        Cursor res=db.rawQuery("SELECT * FROM Transactions",null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            array_sname.add(res.getString(res.getColumnIndex("sender_name")));
            array_rname.add(res.getString(res.getColumnIndex("receiver_name")));
            array_amt.add(res.getString(res.getColumnIndex("amount")));
            res.moveToNext();
        }
        System.out.println("sname" +array_sname+" "+"rname"+" "+array_rname+" "+"amt"+array_amt);
        array_result.add(array_sname);
        array_result.add(array_rname);
        array_result.add(array_amt);

        return array_result;
    }

    public void Deletedata(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(t,"receiver_name=?",new String[]{name});
    }

}