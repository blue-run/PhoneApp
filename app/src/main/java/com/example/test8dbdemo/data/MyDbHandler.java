package com.example.test8dbdemo.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.test8dbdemo.Contacts_List;
import com.example.test8dbdemo.MainActivity;
import com.example.test8dbdemo.model.Contact;
import com.example.test8dbdemo.params.Params;
import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public  MyDbHandler(FragmentActivity context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

//  ..................................................................................................................

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + " ( " + Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_NAME + " TEXT, " + Params.KEY_PHONE + " TEXT " + ")";
        Log.d("DB", "created" + create);
        db.execSQL(create);
    }

//    ................................................................................................................

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    ................................................................................................................

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase( );
        ContentValues contentValues = new ContentValues( );
        contentValues.put(Params.KEY_NAME, contact.getName( ));
        contentValues.put(Params.KEY_PHONE, contact.getPhoneNumber( ));

        db.insert(Params.TABLE_NAME, null, contentValues);
        Log.d("insertion:", "success");
        db.close( );
    }

//    ................................................................................................................

    public List<Contact> getAllContacts() {
        ArrayList<Contact> contactArrayList = new ArrayList<>( );
        SQLiteDatabase db = this.getReadableDatabase( );
        String select = "SELECT * FROM " + Params.TABLE_NAME + " ORDER BY " + Params.KEY_NAME + " ASC";

        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst( );

        if (cursor.moveToFirst( )) {
            do {
                Contact contact = new Contact( );
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactArrayList.add(contact);
            } while (cursor.moveToNext( ));
        }
        db.close( );
        return contactArrayList;
    }

//    .................................................................................................................

    public void updateContacts(Contact contact, String newName, String newNumber) {
        SQLiteDatabase db = this.getWritableDatabase( );
        ContentValues contentValues = new ContentValues( );

        if (newName != null && newNumber != null) {
            contentValues.put(Params.KEY_NAME, newName);
            contentValues.put(Params.KEY_PHONE, newNumber);
            db.update(Params.TABLE_NAME, contentValues, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId( ))});
        } else if (newName != null) {
            contentValues.put(Params.KEY_NAME, newName);
            db.update(Params.TABLE_NAME, contentValues, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId( ))});
        } else if (newNumber != null) {
            contentValues.put(Params.KEY_NAME, newNumber);
            db.update(Params.TABLE_NAME, contentValues, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId( ))});
        }
        db.close( );
    }

//    ....................................................................................................................

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase( );
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId( ))});
        db.close( );
    }

//    ....................................................................................................................

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase( );
        String deleteAll = "DELETE FROM " + Params.TABLE_NAME;
        db.execSQL(deleteAll);
        db.close( );
    }

    public int totalContactsCount() {
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase( );
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount( );
    }

}