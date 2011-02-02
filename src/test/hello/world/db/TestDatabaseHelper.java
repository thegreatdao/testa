package test.hello.world.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class TestDatabaseHelper extends SQLiteOpenHelper
{

	public TestDatabaseHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0)
	{
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		
	}
	
}
