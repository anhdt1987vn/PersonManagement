package dc.mobdev.personmanagerdemo;

import java.io.File;

import dc.mobdev.controllers.PersonController;
import dc.mobdev.entities.Person;
import dc.mobdev.helpers.DBHelper;
import dc.mobdev.helpers.FileHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonDetailActivity extends ActionBarActivity{
	
	private Button btnEdit, btnBackToMain, btnExportToXml;
	private TextView tvPersonDetail;
	private final Context context = this;
	protected PersonController pController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_detail);
		
		this.init();
		
	}
	
	private void init(){
		this.pController = new PersonController(this);
		this.tvPersonDetail = (TextView)findViewById(R.id.personInfo);
		this.btnEdit = (Button)findViewById(R.id.btnEdit);
		this.btnExportToXml = (Button)findViewById(R.id.btnExportToXml);	
		this.btnBackToMain = (Button)findViewById(R.id.btnBackToMain);
		
		int id = getIntent().getIntExtra("id", -1);
		
		final Person person = this.pController.getById(id);
		this.tvPersonDetail.setText(person.toString());
		
		this.btnBackToMain.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});
		
		this.btnEdit.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(context, EditPersonActivity.class);
				intent.putExtra("id", person.getId());
				startActivity(intent);
			}
		});
		
		this.btnExportToXml.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FileHelper.init(context);
				File file = FileHelper.createFileOnSDcard("personDemo", "PersonManagerDemo", true);
				
				FileHelper.writeToFile(file, person.toXML());
				
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
