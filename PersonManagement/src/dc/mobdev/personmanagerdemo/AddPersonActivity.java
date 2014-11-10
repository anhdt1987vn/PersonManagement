package dc.mobdev.personmanagerdemo;

import dc.mobdev.controllers.PersonController;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class AddPersonActivity extends ActionBarActivity {

	
	protected PersonController pController;
	private final Context context = this;
	private TextView tvName, tvDescription;
	private Spinner spinnerYearOfBirth;
	private RadioGroup rgGender;
	private RadioButton rdMale, rdFemale;
	private CheckBox cbMusic, cbSport, cbGame;
	private Button btnAdd, btnBackToMain, btnReset;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
		init();
	}

	public void init(){
		this.pController = new PersonController(this);
		this.tvName = (TextView)findViewById(R.id.editTextNamePerson);
		this.tvDescription = (TextView)findViewById(R.id.editTextDescription);
		this.spinnerYearOfBirth = (Spinner)findViewById(R.id.spinnerBirth);
		this.rgGender = (RadioGroup)findViewById(R.id.radioGender);
		this.rdMale = (RadioButton)findViewById(R.id.radioBtMale);
		this.rdFemale = (RadioButton)findViewById(R.id.radioBtFemale);
		this.cbMusic = (CheckBox)findViewById(R.id.checkBoxMusic);
		this.cbSport = (CheckBox)findViewById(R.id.checkBoxSport);
		this.cbGame = (CheckBox)findViewById(R.id.checkBoxGame);
		this.btnAdd = (Button)findViewById(R.id.btnAddPerson);
		this.btnBackToMain = (Button)findViewById(R.id.btnBackToMain);
		this.btnReset = (Button)findViewById(R.id.btnReset);
		
		this.btnAdd.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				System.out.println("ADD PERSON");
				if(add()){
					System.out.println("Add Person Sucessful!");
					Intent intent = new Intent(context, ListPersonActivity.class);
					startActivity(intent);
				}else{
					System.out.println("Add Person Unsucessful!");
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
	
	private boolean add(){
		String name = this.tvName.getText().toString();
		String description = this.tvDescription.getText().toString();
		
		int yearOfBirth = Integer.parseInt(this.spinnerYearOfBirth.getSelectedItem().toString());
		
		String gender = "";
		int rgID = this.rgGender.getCheckedRadioButtonId();
		if(rgID == this.rdMale.getId()){
			gender = "Male";
		}else if(rgID == this.rdFemale.getId()){
			gender = "Female";
		}
		
		String interested = "";
		if(this.cbMusic.isChecked()){
			interested += this.cbMusic.getText().toString() + ",";
		}
		if(this.cbSport.isChecked()){
			interested += this.cbSport.getText().toString() + ",";
		}
		if(this.cbGame.isChecked()){
			interested += this.cbGame.getText().toString() + ",";
		}
		
		interested = interested.substring(0, interested.length() -1 );
		
		return this.pController.add(name, yearOfBirth, gender, interested, description);
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

