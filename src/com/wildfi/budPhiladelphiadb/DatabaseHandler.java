package com.wildfi.budPhiladelphiadb;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{
	
	// Database Version
	private static final int DATABASE_VERSION = 3;
	// Database Name
	private static final String DATABASE_NAME = "BudPhiladelphia_DB";
	// Employee table name
	private static final String TABLE_USERS = "clientes";

	private static String USER_ID =  "cliente_id";
	
	
	
	private static String NOMBRE = "nombre";


	private static String EMAIL = "email";
	private static String CEDULA = "cedula";
	private static String CELULAR = "celular";
	
	private static String NACIMIENTO = "nacimiento";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
				+ USER_ID + " INTEGER PRIMARY KEY,"
				+ NOMBRE + " TEXT,"
			
				+ CEDULA + " TEXT,"
				+ EMAIL + " TEXT,"
				+ CELULAR + " TEXT,"
		
				+ NACIMIENTO + " TEXT"
				+ ")";
		db.execSQL(CREATE_USER_TABLE);
		//db.close();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		// Create tables again
		onCreate(db);
		
	}
	
	// Adding new user
	public int addUser(User user) 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		
		
		values.put(NOMBRE, user.getNombre());
	
		values.put(CEDULA, user.getCedula());
		values.put(EMAIL, user.getEmail());
		values.put(CELULAR, user.getCelular());
	
		values.put(NACIMIENTO, user.getNacimiento());
		// Inserting Row
		
		
		
		
		int id = (int) db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
		return id;
	}
	
	//updating a existing user
	public int editUser(User user, String position) {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(NOMBRE, user.getNombre());
		
			values.put(CEDULA, user.getCedula());
			values.put(EMAIL, user.getEmail());
			values.put(CELULAR, user.getCelular());
	
			values.put(NACIMIENTO, user.getNacimiento());
			// updating row
			return db.update(TABLE_USERS, values, USER_ID + " = ?",
					new String[] { String.valueOf(position) });
	}
	
	public User getUser(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_USERS, new String[] { USER_ID,
				NOMBRE , CEDULA,EMAIL, CELULAR,NACIMIENTO  }, USER_ID + "=?",
				new String[] {String.valueOf(id)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User(cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
		db.close();
		// return user
		return user;
		
	}
	
		// LIst all users
		public String[] getAllUsers() {
			ArrayList<String> usersList = new ArrayList<String>();
			String selectQuery = "SELECT  * FROM " + TABLE_USERS;
			try {
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);
				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						String user = cursor.getString(1) + ", " + cursor.getString(2) +  ", " + cursor.getString(3)+ ", " + cursor.getString(4) +  ", " + cursor.getString(5)+ ", " + cursor.getString(6) ;
						usersList.add(user);
					} while (cursor.moveToNext());
				}
	
				String[] users = new String[usersList.size()];
				db.close();
				return (usersList.toArray(users));
			}
			catch(Exception e) {
				Log.d("Error in getting users from DB", e.getMessage());
				return null;
			}
		}
		
}
