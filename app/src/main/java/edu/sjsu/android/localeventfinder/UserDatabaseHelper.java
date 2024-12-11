package edu.sjsu.android.localeventfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Signup.db";
    private static final String TABLE_NAME = "allusers";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_FNAME = "fName";
    private static final String COL_LNAME = "lName";
    private static final String COL_ADDRESS = "address";
    private static final String COL_PHONE = "phone";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_EMAIL + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT, " +
                COL_FNAME + " TEXT, " +
                COL_LNAME + " TEXT, " +
                COL_ADDRESS + " TEXT, " +
                COL_PHONE + " TEXT)");
    }

    // not used
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
  
    public Boolean insertData(String email, String password, String fName, String lName, String address, String phone) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Hash the password before storing
        String hashedPassword = hashPassword(password);
  
        // Insert user data
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, hashedPassword);
        contentValues.put(COL_FNAME, fName);
        contentValues.put(COL_LNAME, lName);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_PHONE, phone);

        try {
            long result = MyDatabase.insert(TABLE_NAME, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e("UserDatabaseHelper", "Error inserting data", e);
            return false;
        } finally {
            MyDatabase.close();
        }
    }

    // Check if email exists
    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?", new String[]{email});
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor to release resources
            }
        }
    }

    // Check if email and password match
    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        // Hash the password before comparing
        String hashedPassword = hashPassword(password);

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ?",
                    new String[]{email, hashedPassword});
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor to release resources
            }
        }
    }

    // Hashing function for passwords
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
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
