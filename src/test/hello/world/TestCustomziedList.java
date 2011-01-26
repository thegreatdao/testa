package test.hello.world;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class TestCustomziedList extends ListActivity
{

	private String[] items = {"green", "red", "blue", "ivory", "pink"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.customized_list_view, R.id.label, items));		
	}
	
}
