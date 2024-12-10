package edu.sjsu.android.localeventfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String database = "Signup.db";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, database, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create table allusers(email TEXT PRIMARY KEY, password TEXT, fName TEXT, lName TEXT, address TEXT, phone TEXT)");
    }

    // not used
    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {

    }

    public Boolean insertData(String email, String password, String fName, String lName, String address, String phone) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("fName", fName);
        contentValues.put("lName", lName);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from allusers where email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public Cursor getUserInfo(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        return MyDatabase.rawQuery("select fName, lName, address, phone from allusers where email = ?", new String[]{email});
    }

    public boolean updateUserName(String email, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fName", newName.split(" ")[0]);
        contentValues.put("lName", newName.split(" ")[1]);

        int result = db.update("allusers", contentValues, "email = ?", new String[]{email});

        if (result < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updateUserAddress(String email, String newAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", newAddress);

        int result = db.update("allusers", contentValues, "email = ?", new String[]{email});

        if (result < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updateUserPhone(String email, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", newPhone);

        int result = db.update("allusers", contentValues, "email = ?", new String[]{email});

        if (result < 0) {
            return false;
        }
        else {
            return true;
        }
    }

}
