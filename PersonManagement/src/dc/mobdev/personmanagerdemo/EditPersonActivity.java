package dc.mobdev.personmanagerdemo;

import dc.mobdev.controllers.PersonController;
import dc.mobdev.entities.Person;
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

public class EditPersonActivity extends ActionBarActivity {

	protected PersonController pController;
	private final Context context = this;
	private TextView tvName, tvDescription;
	private Spinner spinnerYearOfBirth;
	private RadioGroup rgGender;
	private RadioButton rdMale, rdFemale;
	private CheckBox cbMusic, cbSport, cbGame;
	private Button btnUpdate, btnBackToMain, btnReset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_person);

		this.init();
	}

	private void init() {
		this.pController = new PersonController(this);
		this.tvName = (TextView) findViewById(R.id.editTextNamePerson);
		this.tvDescription = (TextView) findViewById(R.id.editTextDescription);
		this.spinnerYearOfBirth = (Spinner) findViewById(R.id.spinnerBirth);
		this.rgGender = (RadioGroup) findViewById(R.id.radioGender);
		this.rdMale = (RadioButton) findViewById(R.id.radioBtMale);
		this.rdFemale = (RadioButton) findViewById(R.id.radioBtFemale);
		this.cbMusic = (CheckBox) findViewById(R.id.checkBoxMusic);
		this.cbSport = (CheckBox) findViewById(R.id.checkBoxSport);
		this.cbGame = (CheckBox) findViewById(R.id.checkBoxGame);
		this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
		this.btnReset = (Button) findViewById(R.id.btnReset);
		this.btnBackToMain = (Button) findViewById(R.id.btnBackToMain);

		final int id = getIntent().getIntExtra("id", -1);
		Person person = this.pController.getById(id);
		// System.out.println(person.toString());
		String name = person.getName();
		String description = person.getDescription();
		int yearOfBirth = person.getYearOfBirth();
		String gender = person.getGender();
		String interested = person.getInterested();
		String[] tmpInterested = interested.split(",");

		this.tvName.setText(name);
		this.tvDescription.setText(description);

		for (int i = 0; i < this.spinnerYearOfBirth.getCount(); i++) {
			if (Integer.parseInt(this.spinnerYearOfBirth.getItemAtPosition(i)
					.toString()) == yearOfBirth) {
				this.spinnerYearOfBirth.setSelection(i);
			}
		}

		if (gender.equalsIgnoreCase("Male")) {
			this.rgGender.check(R.id.radioBtMale);
		} else if (gender.equalsIgnoreCase("Female")) {
			this.rgGender.check(R.id.radioBtFemale);
		}

		for (int i = 0; i < tmpInterested.length; i++) {
			if (tmpInterested[i].equalsIgnoreCase("Music")) {
				this.cbMusic.setChecked(true);
			} else if (tmpInterested[i].equalsIgnoreCase("Game")) {
				this.cbGame.setChecked(true);
			} else if (tmpInterested[i].equalsIgnoreCase("Sport")) {
				this.cbSport.setChecked(true);
			}
		}

		this.btnBackToMain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
			}
		});

		this.btnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (update()) {
					System.out.println("Update Sucessful!");
					Intent intent = new Intent(context,
							PersonDetailActivity.class);
					intent.putExtra("id", id);
					startActivity(intent);
				} else {
					System.out.println("Update Unsucessful!");
				}

			}
		});
	}

	private boolean update() {
		int id = getIntent().getIntExtra("id", -1);
		String name = this.tvName.getText().toString();
		System.out.println("Name :" + name);
		String description = this.tvDescription.getText().toString();
		System.out.println("Des : " + description);
		int yearOfBirth = Integer.parseInt(this.spinnerYearOfBirth
				.getSelectedItem().toString());

		String gender = "";
		int rgID = this.rgGender.getCheckedRadioButtonId();
		if (rgID == this.rdMale.getId()) {
			gender = "Male";
		} else if (rgID == this.rdFemale.getId()) {
			gender = "Female";
		}

		String interested = "";
		if (this.cbMusic.isChecked()) {
			interested += this.cbMusic.getText().toString() + ",";
		}
		if (this.cbSport.isChecked()) {
			interested += this.cbSport.getText().toString() + ",";
		}
		if (this.cbGame.isChecked()) {
			interested += this.cbGame.getText().toString() + ",";
		}

		interested = interested.substring(0, interested.length() - 1);

		return this.pController.update(id, new Person(name, yearOfBirth,
				gender, interested, description));
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
