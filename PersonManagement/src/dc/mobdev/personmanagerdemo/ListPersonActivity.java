package dc.mobdev.personmanagerdemo;

import java.util.ArrayList;

import dc.mobdev.controllers.PersonController;
import dc.mobdev.entities.Person;
import dc.mobdev.helpers.DBHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;

public class ListPersonActivity extends ActionBarActivity {

	protected PersonController pController;
	private final Context context = this;
	private ListView lvPerson;
	private Button btnBackToMain, btnDeleteAll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_person);
		init();
	}

	public void init() {
		this.pController = new PersonController(this);
		this.btnBackToMain = (Button)findViewById(R.id.btnBackToMain);
		this.btnDeleteAll = (Button)findViewById(R.id.btnDeleteAll);
		this.lvPerson = (ListView) findViewById(R.id.listViewPerson);

		final ArrayList<Person> persons = this.pController.getAll();
		ArrayList<String> listName = new ArrayList<String>();
		for (Person person : persons) {
			listName.add(person.getName());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, listName);
		
		this.lvPerson.setAdapter(adapter);
		this.lvPerson.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(parent.getContext(), PersonDetailActivity.class);
				intent.putExtra("id", persons.get(position).getId());
				startActivity(intent);
			}
		});

		this.btnDeleteAll.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(deleteAll()){
					System.out.println("Delete All Person Sucessful!");
					Intent intent = new Intent(context, ListPersonActivity.class);
					startActivity(intent);
				}else{
					System.out.println("Delete All Person Unsucessful!");
				}
				
			}
		});
		
		this.btnBackToMain.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private boolean deleteAll(){
		return pController.deleteAll();
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
