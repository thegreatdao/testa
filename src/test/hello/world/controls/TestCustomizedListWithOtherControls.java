package test.hello.world.controls;

import test.hello.world.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestCustomizedListWithOtherControls extends ListActivity
{
	private String[] countries={"China", "Japan", "India", "Canada", "Brazil"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customized_list_with_other_controls);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, countries));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
}
