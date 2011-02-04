package test.hello.world.contentProvider;

import static test.hello.world.db.TestDatabaseHelperAdapter.CONTACT_ADDRESS;
import static test.hello.world.db.TestDatabaseHelperAdapter.CONTACT_NAME;
import test.hello.world.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class TestContentProviderActivity extends ListActivity
{
	private Cursor cursor;
	private static final int ADD = Menu.NONE + 1;
	private static final int DELETE = ADD + 1;
	private static final int FIRST = Menu.NONE + 1;
	private static final int SECOND = FIRST + 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		cursor = managedQuery(ContactsContentProvider.CONTENT_URI, null, null, null, null);
		String[] from = new String[]{CONTACT_NAME, CONTACT_ADDRESS};
		int[] to = new int[]{R.id.name, R.id.address};
		ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.contact, cursor, from, to);
		setListAdapter(listAdapter);
		registerForContextMenu(getListView());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(Menu.NONE, ADD, Menu.NONE, "Add Contact");
		menu.add(Menu.NONE, DELETE, Menu.NONE, "Delete all contacts");
		return(super.onCreateOptionsMenu(menu));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case ADD:
				showContactForm(null, null, -1);
				return Boolean.TRUE;
			case DELETE:
				getContentResolver().delete(ContactsContentProvider.CONTENT_URI, null, null);
				cursor.requery();
				return Boolean.TRUE;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		menu.add(Menu.NONE, FIRST, Menu.NONE, R.string.edit);
		menu.add(Menu.NONE, SECOND, Menu.NONE, R.string.delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		long id = menuInfo.id;
		Uri uri = ContentUris.withAppendedId(ContactsContentProvider.CONTENT_URI, id);
		ContentResolver contentResolver = getContentResolver();
		switch (item.getItemId())
		{
			case FIRST:
				Cursor contactCursor = contentResolver.query(uri, null, null, null, null);
				showContactForm(contactCursor.getString(0), contactCursor.getString(1), id);
				return Boolean.TRUE;
			case SECOND:
				contentResolver.delete(uri, null, null);
				cursor.requery();
				return Boolean.TRUE;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void showContactForm(String name, String address, final long id)
	{
		final View contactForm = initContactFormView(name, address);
		
		new AlertDialog.Builder(this).setTitle("Contact Form")
		.setView(contactForm).setPositiveButton(R.string.addOrEdit, new OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				String name = ((EditText)contactForm.findViewById(R.id.name)).getText().toString();
				String address = ((EditText)contactForm.findViewById(R.id.address)).getText().toString();
				saveContact(name, address, id);
				cursor.requery();
			}
		}).setNegativeButton(R.string.cancel, new OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
			}
		}).show();
	}
	
	private View initContactFormView(String name, String address)
	{
		LayoutInflater layoutInflator = LayoutInflater.from(this);
		View contactForm = layoutInflator.inflate(R.layout.contact_form, null);
		EditText nameEditText = (EditText)contactForm.findViewById(R.id.name);
		EditText addressEditText = (EditText)contactForm.findViewById(R.id.address);
		if(name != null && name.trim().length()!=0)
		{
			nameEditText.setText(name);
		}
		if(address != null && address.trim().length()!=0)
		{
			addressEditText.setText(address);
		}
		return contactForm;
	}
	
	private void saveContact(String name, String address, long id)
	{
		ContentValues contact = new ContentValues();
		contact.put(CONTACT_NAME, name);
		contact.put(CONTACT_ADDRESS, address);
		if(id == -1)
		{
			getContentResolver().insert(ContactsContentProvider.CONTENT_URI, contact);
		}
		else
		{
			getContentResolver().update(ContentUris.withAppendedId(ContactsContentProvider.CONTENT_URI, id), contact, null, null);
		}
	}
}
