package dc.mobdev.models;

import java.util.ArrayList;

import dc.mobdev.entities.Person;
import dc.mobdev.helpers.DBHelper;
import android.content.Context;
import android.database.Cursor;

public class PersonModel {

	public PersonModel() {
	}

	public PersonModel(Context context) {
		init(context);
	}

	public void init(Context context) {
		this.selectionDatabase("dbPerson");
		DBHelper.init(context);

		String tbName = "persons";
		String columns[] = { "id", "name", "yearOfBirth", "gender",
				"interested", "description" };
		String dataTypes[] = { "integer", "text", "text", "text", "text",
				"text" };
		String conditions[] = { "primary key autoincrement", "not null",
				"not null", "not null", "not null", "not null" };
		DBHelper.openOrCreateTable(tbName, columns, dataTypes, conditions);
	}

	private void selectionDatabase(String dbName) {
		DBHelper.selectionDatabase(dbName);
	}

	public boolean add(Person person) {
		String query = "insert into persons(name, yearOfBirth, gender, interested, description) values(";
		query += "'" + person.getName() + "',";
		query += person.getYearOfBirth() + ",";
		query += "'" + person.getGender() + "',";
		query += "'" + person.getInterested() + "',";
		query += "'" + person.getDescription() + "')";
		return DBHelper.execute(query);
	}

	public void add(Person[] persons) {
		for (int i = 0; i < persons.length; i++) {
			// System.out.println(persons[i].toString());
			this.add(persons[i]);
		}
	}

	public boolean add(String name, int yearOfBirth, String gender,
			String interested, String description) {
		String query = "insert into persons(name, yearOfBirth, gender, interested, description) values(";
		query += "'" + name + "',";
		query += yearOfBirth + ",";
		query += "'" + gender + "',";
		query += "'" + interested + "',";
		query += "'" + description + "')";
		return DBHelper.execute(query);
	}

	public void add(String[] names, int[] yearOfBirths, String[] genders,
			String[] interesteds, String[] descriptions) {
		for (int i = 0; i < names.length; i++) {
			this.add(names[i], yearOfBirths[i], genders[i], interesteds[i],
					descriptions[i]);
		}
	}

	public ArrayList<Person> getAll() {
		System.out.println("#MODEL__GET ALL PERSON");
		String query = "select * from persons";
		Cursor cursor = DBHelper.executeQuery(query);
		ArrayList<Person> personList = null;
		Person person = null;
		if (cursor != null) {
			personList = new ArrayList<Person>();
			if (cursor.moveToFirst()) {
				do {
					person = new Person();
					person.setId(cursor.getInt(cursor.getColumnIndex("id")));
					person.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					person.setYearOfBirth(cursor.getInt(cursor
							.getColumnIndex("yearOfBirth")));
					person.setGender(cursor.getString(cursor
							.getColumnIndex("gender")));
					person.setInterested(cursor.getString(cursor
							.getColumnIndex("interested")));
					person.setDescription(cursor.getString(cursor
							.getColumnIndex("description")));
					// System.out.println(person.toString());
					personList.add(person);
				} while (cursor.moveToNext());
			}
		}
		return personList;
	}

	public Person getById(int id) {
		String query = "select * from persons where id=" + id;
		Cursor cursor = DBHelper.executeQuery(query);
		Person person = null;
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				person = new Person();
				person.setId(cursor.getInt(cursor.getColumnIndex("id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setGender(cursor.getString(cursor
						.getColumnIndex("gender")));
				person.setYearOfBirth(Integer.parseInt(cursor.getString(cursor
						.getColumnIndex("yearOfBirth"))));
				person.setInterested(cursor.getString(cursor
						.getColumnIndex("interested")));
				person.setDescription(cursor.getString(cursor
						.getColumnIndex("description")));
			}
		}
		return person;
	}

	public ArrayList<Person> getByIds(int[] ids) {
		System.out.println("#MODEL_getByIds Start");
		String idsStr = "";
		for (int i = 0; i < ids.length; i++) {
			System.out.println("MODEL_" + i);
			if (i < (ids.length - 1)) {
				idsStr += ids[i] + ",";
			} else {
				idsStr += ids[i];
			}
		}
		String query = "select * from persons where id in (" + idsStr + ")";
		System.out.println("MODEL_QUERY : " + query);
		Cursor cursor = DBHelper.executeQuery(query);
		ArrayList<Person> personsList = null;
		Person person = null;
		if (cursor != null) {
			personsList = new ArrayList<Person>();
			if (cursor.moveToFirst()) {
				do {
					person = new Person();
					person.setId(cursor.getInt(cursor.getColumnIndex("id")));
					person.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					person.setGender(cursor.getString(cursor
							.getColumnIndex("gender")));
					person.setYearOfBirth(cursor.getInt(cursor
							.getColumnIndex("yearOfBirth")));
					person.setInterested(cursor.getString(cursor
							.getColumnIndex("interested")));
					person.setDescription(cursor.getString(cursor
							.getColumnIndex("description")));
					personsList.add(person);
				} while (cursor.moveToNext());
			}
		}
		return personsList;
	}

	public ArrayList<Person> getByCondition(String condition) {
		String query = "select * from persons where " + condition;
		Cursor cursor = DBHelper.executeQuery(query);
		ArrayList<Person> personsList = null;
		Person person = null;
		if (cursor != null) {
			personsList = new ArrayList<Person>();
			if (cursor.moveToFirst()) {
				do {
					person = new Person();
					person.setId(cursor.getInt(cursor.getColumnIndex("id")));
					person.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					person.setGender(cursor.getString(cursor
							.getColumnIndex("gender")));
					person.setYearOfBirth(cursor.getInt(cursor
							.getColumnIndex("yearOfBirth")));
					person.setInterested(cursor.getString(cursor
							.getColumnIndex("interested")));
					person.setDescription(cursor.getString(cursor
							.getColumnIndex("description")));
					personsList.add(person);
				} while (cursor.moveToNext());
			}
		}
		return personsList;
	}

	public boolean update(int id, Person newPerson) {
		String query = "update persons set ";
		query += "name" + "=" + "'" + newPerson.getName() + "',";
		query += "yearOfBirth" + "=" + "'" + newPerson.getYearOfBirth() + "',";
		query += "gender" + "=" + "'" + newPerson.getGender() + "',";
		query += "interested" + "=" + "'" + newPerson.getInterested() + "',";
		query += "description" + "=" + "'" + newPerson.getDescription() + "' ";
		query += "where id=" + id;
		return DBHelper.execute(query);
	}

	public void update(int[] ids, Person[] persons) {
		for(int i = 0; i < ids.length; i++){
			this.update(ids[i], persons[i]);
		}
	}
	
	public boolean update(String column, String value, String condition){
		String query = "update persons set " + column + "=" + "'" + value + "'" + " where " + condition;
		return DBHelper.execute(query);
	}

	public boolean update(String[] columns, String[] values, String condition) {
		String str = "";
		for(int i = 0; i < columns.length; i++){
			if(i < columns.length - 1){
				str += columns[i] + "=" + "'" + values[i] + "',";
			}else{
				str += columns[i] + "=" + "'" + values[i] + "'";
			}
		}
		String query = "update person set " + str + " where " + condition ;
		return DBHelper.execute(query);
	}

	public boolean update(String column, String value) {
		String query = "update persons set " + column + "=" + "'" + value + "'";
		return DBHelper.execute(query);
	}
	
	public void update(String[] columns, String[] values) {
		for(int i = 0; i < columns.length; i++){
			this.update(columns[i], values[i]);
		}
	}

	public boolean deleteById(int id) {
		String query = "delete from  persons where id=" + id;
		return DBHelper.execute(query);
	}

	public boolean deleteByIds(int[] ids) {
		String str = "";
		for(int i = 0; i < ids.length; i++){
			if(i < ids.length - 1){
				str += ids[i] + ",";
			}else{
				str += ids[i];
			}
		}
		String query = "delete from persons where id in (" + str + ")";
		
		return false;
	}

	public boolean deleteByCondition(String condition) {
		String query = "delete from persons where " + condition;
		return DBHelper.execute(query);
	}

	public boolean deleteAll() {
		String query = "delete from  persons";
		return DBHelper.execute(query);
	}
}
