package test.hello.world.launcher;

import test.hello.world.R;
import test.hello.world.TestRemoteService;
import test.hello.world.activities.Parent;
import test.hello.world.activities.TestImplicitIntent;
import test.hello.world.animations.TestAnimation;
import test.hello.world.broadcast.TestBroadcastReceviers;
import test.hello.world.color.TestColorStateList;
import test.hello.world.contentProvider.TestContentProviderActivity;
import test.hello.world.controls.AssortedWidgets;
import test.hello.world.controls.TestCustomizedList;
import test.hello.world.controls.TestCustomizedListWithOtherControls;
import test.hello.world.controls.TestGridView;
import test.hello.world.controls.TestListView;
import test.hello.world.controls.TestScrollView;
import test.hello.world.controls.TestSlidingDrawer;
import test.hello.world.controls.TestTab;
import test.hello.world.db.TestDatabaseActivity;
import test.hello.world.i10n.TestI10N;
import test.hello.world.io.TestReadAndWriteFiles;
import test.hello.world.io.TestReadROF;
import test.hello.world.menu.TestBasicMenu;
import test.hello.world.menu.TestInflatingMenu;
import test.hello.world.multimedia.TestVideo;
import test.hello.world.notifications.TestNotifications;
import test.hello.world.popup.TestAlertDialogWithAList;
import test.hello.world.popup.TestAlertDialogWithAListOfSingleItems;
import test.hello.world.popup.TestCustomizedDialog;
import test.hello.world.popup.TestPopUp;
import test.hello.world.popup.TestProgressBarUpdating;
import test.hello.world.popup.TestProgressBarUpdatingWithAsynTask;
import test.hello.world.popup.TestProgressBars;
import test.hello.world.preferences.TestPreferences;
import test.hello.world.services.TestLocalService;
import test.hello.world.stateManagement.TestDestructionByRotation;
import test.hello.world.stateManagement.TestOnConfigurationChanged;
import test.hello.world.stateManagement.TestOnRetainNonConfigurationInstance;
import test.hello.world.webview.TestWebView;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TesterLauncher extends ListActivity
{

	private static final String[] ACTIVITIES = 
			new String[]{"SubActivity", "Implicit Intent", "Animation", "Color state",
						"Content provider", "Assorted widgets", "Tab view", "Hello, World", "Customzied list", "Customzied list second"
						, "Grid view", "List view", "Scroll view", "Sliding view", "Database", "I18N and L10N", "I/O" , "I/O readonly"
						, "Basic Menus", "Inflating Menus", "Notifications", "Alert dialog with list", "Alert dialog with list of single items"
						, "Customized dialog", "Simple pop up", "Progress bar", "Test progress bar with updating", " Test progress bar updating with asyntask"
						, "Preferences" , "Local service", "Destrution by rotation", "On configuration change", "RetainNonConfigurationInstance", "Web view"
						, "Remote services IPC", "Broadcast with notification", "Play video"};
	private static final Class<?>[] COMPONENT_NAMES = new Class<?>[]{Parent.class, TestImplicitIntent.class
					, TestAnimation.class, TestColorStateList.class, TestContentProviderActivity.class, AssortedWidgets.class, TestTab.class, test.hello.world.controls.Test.class
					, TestCustomizedList.class, TestCustomizedListWithOtherControls.class, TestGridView.class, TestListView.class, TestScrollView.class
					, TestSlidingDrawer.class, TestDatabaseActivity.class, TestI10N.class, TestReadAndWriteFiles.class, TestReadROF.class
					, TestBasicMenu.class, TestInflatingMenu.class, TestNotifications.class, TestAlertDialogWithAList.class, TestAlertDialogWithAListOfSingleItems.class
					, TestCustomizedDialog.class, TestPopUp.class, TestProgressBars.class, TestProgressBarUpdating.class, TestProgressBarUpdatingWithAsynTask.class
					, TestPreferences.class, TestLocalService.class, TestDestructionByRotation.class, TestOnConfigurationChanged.class, 
					TestOnRetainNonConfigurationInstance.class, TestWebView.class, TestRemoteService.class, TestBroadcastReceviers.class, TestVideo.class};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, ACTIVITIES));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				TesterLauncher.this.startActivity(new Intent(TesterLauncher.this, COMPONENT_NAMES[position]));
			}
		});
	}
	
}
