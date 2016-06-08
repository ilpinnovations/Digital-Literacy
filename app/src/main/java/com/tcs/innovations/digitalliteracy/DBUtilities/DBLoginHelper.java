package com.tcs.innovations.digitalliteracy.DBUtilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tcs.innovations.digitalliteracy.Beans.User;

import java.util.ArrayList;


public class DBLoginHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user";
    private static final int VERSION = 2;

    private static final String COLUMN1 = "id";
    private static final String COLUMN2 = "userName";
    private static final String COLUMN3 = "firstName";
    private static final String COLUMN4 = "lastName";
    private static final String COLUMN5 = "gender";
    private static final String COLUMN6 = "emailID";
    private static final String COLUMN7 = "mobile";
    private static final String COLUMN8 = "DOB";
    private static final String COLUMN9 = "nationality";
    private static final String COLUMN10 = "address";
    private static final String COLUMN11 = "country";
    private static final String COLUMN12 = "state";
    private static final String COLUMN13 = "district";
    private static final String COLUMN14 = "zip";
    private static final String COLUMN15 = "password";

    Context context;
    private SQLiteDatabase myDb;

    public DBLoginHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN1 + " INTEGER PRIMARY KEY,"
                + COLUMN2 + " TEXT,"
                + COLUMN3 + " TEXT, "
                + COLUMN4 + " TEXT, "
                + COLUMN5 + " TEXT, "
                + COLUMN6 + " TEXT, "
                + COLUMN7 + " TEXT, "
                + COLUMN8 + " TEXT, "
                + COLUMN9 + " TEXT, "
                + COLUMN10 + " TEXT, "
                + COLUMN11 + " TEXT, "
                + COLUMN12 + " TEXT, "
                + COLUMN13 + " TEXT, "
                + COLUMN14 + " TEXT, "
                + COLUMN15 + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, user.getUserName());

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }


    public void deleteFlights(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN1 + " = ?", new String[]{String.valueOf(user.getId())});
        db.close();

    }

    public ArrayList<User> getAllFlights(String userName) {
        ArrayList<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + "  where user= '" + userName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                //user.setId(cursor.getString(1));
                user.setUserName(cursor.getString(1));
                //user.setDepartureLocation(cursor.getString(2));
                //user.setArrivalLocation(cursor.getString(3));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }
}

