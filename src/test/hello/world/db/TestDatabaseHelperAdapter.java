package test.hello.world.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TestDatabaseHelperAdapter
{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "test.db";
	private static final String CONTACT_TABLE = "contact";
	private static final String PRIMARY_KEY = "_id";
	public static final String CONTACT_NAME = "name";
	public static final String CONTACT_ADDRESS = "address";
	private static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + CONTACT_TABLE + " ("
													+ PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
													+ CONTACT_NAME + " TEXT NOT NULL, " + CONTACT_ADDRESS
													+ " TEXT NOT NULL);";
	private SQLiteDatabase sQLiteDatabase;
	private TestDatabaseHelper testDatabaseHelper;
	
	public TestDatabaseHelperAdapter(Context context)
	{
		testDatabaseHelper = new TestDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		sQLiteDatabase = testDatabaseHelper.getWritableDatabase();
	}
	
	public void closeDatabase()
	{
		sQLiteDatabase.close();
	}
	/*
	 * could've made all CRUD as generic as possible, as this is for demostration purpose,
	 * we create CRUD talirored to CONTACT tables only
	 */
	public long createContact(String name, String address)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(CONTACT_NAME, name);
		contentValues.put(CONTACT_ADDRESS, address);
		return sQLiteDatabase.insert(CONTACT_TABLE, PRIMARY_KEY, contentValues);
	}
	
	public boolean deleteContact(long id)
	{
		return sQLiteDatabase.delete(CONTACT_TABLE, PRIMARY_KEY + "=" + id, null) > 0;
	}
	
	public boolean updateContact(long id, String name, String address)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(CONTACT_NAME, name);
		contentValues.put(CONTACT_ADDRESS, address);
		return sQLiteDatabase.update(CONTACT_TABLE, contentValues, PRIMARY_KEY + "=" + id, null) > 0;
	}
	
	public Cursor selectAllContancts()
	{
		String[] columns = {CONTACT_NAME, CONTACT_ADDRESS, PRIMARY_KEY};
		return sQLiteDatabase.query(CONTACT_TABLE, columns, null, null, null, null, null);
	}
	
	public Contact selectOneById(long id)
	{
		Contact contact = new Contact();
		Cursor cursor = sQLiteDatabase.query(CONTACT_TABLE, new String[]{CONTACT_NAME, CONTACT_ADDRESS}, PRIMARY_KEY + "=" + id, null, null, null, null);
		if(cursor.getCount() != 0)
		{
			cursor.moveToFirst();
			contact.setName(cursor.getString(0));
			contact.setAddress(cursor.getString(1));
		}
		return contact;
	}
	/*
	 * use this class life cycle methods to manage database updates
	 */
	private static class TestDatabaseHelper extends SQLiteOpenHelper
	{
		public TestDatabaseHelper(Context context, String name, CursorFactory factory, int version)
		{
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase sQLiteDatabase)
		{
			sQLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion)
		{
			//update schemas here or do something meaning
		}
		
	}
	
	/*
	 * you can use public fields instead of private onew with getters and setters
	 * to speed up a bit for Android applicatoins
	 */
	public static class Contact
	{
		private String name;
		private String address;
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getAddress()
		{
			return address;
		}
		
		public void setAddress(String address)
		{
			this.address = address;
		}
	}
}
