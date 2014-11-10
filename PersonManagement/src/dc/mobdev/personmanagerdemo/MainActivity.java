package dc.mobdev.personmanagerdemo;

import dc.mobdev.controllers.PersonController;
import dc.mobdev.helpers.JSonHelper;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	protected PersonController pController;
	private Button btnAdd, btnGet;
	private final Context mainContext = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.pController = new PersonController(this);
		init();	
		//new JSonHelper();
	}
	
	
	
	public void init(){
		this.btnAdd = (Button)findViewById(R.id.btnAddPerson);
		this.btnGet = (Button)findViewById(R.id.btnGetPerson);
		
		this.btnAdd.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mainContext, AddPersonActivity.class);
				startActivity(intent);			
			}
		});
		
		this.btnGet.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mainContext, ListPersonActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
