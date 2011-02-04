package test.hello.world.contentProvider;

import test.hello.world.db.TestDatabaseHelperAdapter;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

public class ContactsContentProvider extends ContentProvider
{

	private TestDatabaseHelperAdapter testDatabaseHelperAdapter;
	private static final String CONTENT_PREFIX = "content://";
	private static final String AUTHORITY = "test.hello.world.contentprovider.contactscontentprovider";
	public static final Uri CONTENT_URI = Uri.parse(CONTENT_PREFIX + AUTHORITY + "/contacts");
	private static final String CONTACTS_CONTENT_TYPE_SINGULAR = "vnd.android.cursor.item/vnd.hello.contacts";
	private static final String CONTACTS_CONTENT_TYPE_PLURAL = "vnd.android.cursor.dir/vnd.hello.contacts";
	private static final UriMatcher URI_MATCHER;
	private static final int CONTACTS = 1;
	private static final int CONTACTS_ID = 2;
	
	static
	{
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(AUTHORITY, "contacts", CONTACTS);
		URI_MATCHER.addURI(AUTHORITY, "contacts/#", CONTACTS_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		switch (URI_MATCHER.match(uri))
		{
			case CONTACTS:
				return testDatabaseHelperAdapter.deleteAllContacts();
			case CONTACTS_ID:
				boolean successful = testDatabaseHelperAdapter.deleteContact(Long.parseLong(uri.getPathSegments().get(1)));
				return successful ? 1:0;
			default:
				return 0;
		}
	}

	@Override
	public String getType(Uri uri)
	{
		switch (URI_MATCHER.match(uri))
		{
			case CONTACTS:
				return CONTACTS_CONTENT_TYPE_PLURAL;
			case CONTACTS_ID:
				return CONTACTS_CONTENT_TYPE_SINGULAR;
			default:
				throw new IllegalArgumentException("Invalid URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentValues)
	{
		switch (URI_MATCHER.match(uri))
		{
			case CONTACTS:
				long newId = testDatabaseHelperAdapter.createContact(contentValues);
				if(newId > 0)
				{
					return ContentUris.withAppendedId(CONTENT_URI, newId);
				}
				else
				{
					Toast.makeText(getContext(), "CREATE CONTACT FAILED", Toast.LENGTH_SHORT).show();
					return uri;
				}
			default:
				throw new IllegalArgumentException("Invalid URI: " + uri);
		}
	}

	@Override
	public boolean onCreate()
	{
		testDatabaseHelperAdapter = new TestDatabaseHelperAdapter(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
		Cursor cursor;
		switch (URI_MATCHER.match(uri))
		{
			case CONTACTS_ID:
				int id = Integer.parseInt(uri.getPathSegments().get(1));
				cursor = testDatabaseHelperAdapter.selectOneContact(id);
				break;
			case CONTACTS:
				cursor = testDatabaseHelperAdapter.selectAllContancts();
				break;
			default:
				throw new IllegalArgumentException("Invalid URI: " + uri);
		}
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs)
	{
		/*
		 * omit checking the URI, this is not a good practice,
		 * do uri checking on real application
		 */
		long id = Long.parseLong(uri.getPathSegments().get(1));
		testDatabaseHelperAdapter.updateContact(id, contentValues);
		return 1;
	}

}
