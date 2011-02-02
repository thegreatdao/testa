package test.hello.world.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TestDatabaseHelperAdapter
{
	private static final int DATABASE_VERSION = 1;
	private static final String CONTACT_TABLE = "contact";
	private static final String PRIMARY_KEY = "_id";
	private static final String CONTACT_NAME = "name";
	private static final String CONTACT_ADDRESS = "address";
	private static final String CREATE_CONTACT_TABLE = "CREATE TABLE " + CONTACT_TABLE + " ("
													+ PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
													+ CONTACT_NAME + " TEXT NOT NULL, " + CONTACT_ADDRESS
													+ " TEXT NOT NULL);";
	private SQLiteDatabase sQLiteDatabase;
	private TestDatabaseHelper testDatabaseHelper;
	
	public TestDatabaseHelperAdapter(Context context)
	{
		testDatabaseHelper = new TestDatabaseHelper(context, CONTACT_TABLE, null, DATABASE_VERSION);
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
	
}
