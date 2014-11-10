package dc.mobdev.controllers;

import java.util.ArrayList;

import dc.mobdev.entities.Person;
import dc.mobdev.models.PersonModel;
import android.content.Context;

public class PersonController {

	protected PersonModel pModel;
	public PersonController(){
	}
	
	public PersonController(Context context){
		this.init(context);
	}
	
	public void init(Context context){
		this.pModel = new PersonModel(context);
	}
	
	public boolean add(Person person){
		return this.pModel.add(person);
	}
	
	public void add(Person[] persons){
		this.pModel.add(persons);
	}
	
	public boolean add(String name, int yearOfBirth, String gender, String interested, String description){
		return this.pModel.add(name, yearOfBirth, gender, interested, description);
	}
	
	public void add(String[] names, int[] yearOfBirths, String[] genders, String[] interesteds, String[] descriptions){
		this.pModel.add(names, yearOfBirths, genders, interesteds, descriptions);
	}
	
	public ArrayList<Person> getAll(){
		return this.pModel.getAll();
	}
	
	public Person getById(int id){
		//System.out.println("#CONTROLLER_getByID :" + id);
		return this.pModel.getById(id);
	}
	
	public ArrayList<Person> getByIds(int[] ids) {
		return this.pModel.getByIds(ids);
	}
	
	public boolean update(int id, Person newPerson){
		return this.pModel.update(id, newPerson);
	}
	
	public void update(int[] ids, Person[] persons){
		this.pModel.update(ids, persons);
	}
	
	public boolean update(String column, String value){
		return this.pModel.update(column, value);
	}
	
	public void update(String[] columns, String[] values){
		this.pModel.update(columns, values);
	}
	
	public boolean update(String column, String value, String condition){
		return this.pModel.update(column, value, condition);
	}
	
	public boolean update(String[] columns, String[] values, String condition){
		return this.pModel.update(columns, values, condition);
	}
	
	public boolean deleteById(int id){
		return this.pModel.deleteById(id);
	}
	
	public boolean deleteByIds(int[] ids){
		return this.pModel.deleteByIds(ids);
	}
	
	public boolean deleteByCondition(String condition){
		return this.pModel.deleteByCondition(condition);
	}
	
	public boolean deleteAll(){
		return this.pModel.deleteAll();
	}
	
	public void test(){
		
		/*Person quang = new Person("Quang", 1986, "Male", "Music,Sex", "mad man");
		Person linh = new Person("Linh", 1992, "Female", "Music", "good girl");
		Person ngoc = new Person("Ngoc", 1995, "Male", "Music, Game", "good boy");
		Person tuyen = new Person("Tuyen", 1986, "Male", "Sport,Sex", "good man");
		Person[] persons = new Person[4];
		persons[0] = linh;
		persons[1] = ngoc;
		persons[2] = quang;
		persons[3] = tuyen;
		this.add(persons);*/
		
		/*String[] names = {"Nam", "Hoa", "Minh"};
		int[] yearOfBirths = {1987, 1990, 1988};
		String[] genders = {"Male", "Female", "Male"};
		String[] interesteds = {"play, music", "abc,bca", "somking"};
		String[] descriptions = {"good", "bad", "so bad"};*/
	
		
		//this.add("Susan", 1985, "Female", "Sex, BlowJob", "fucking girl");
		//this.add(names, yearOfBirths, genders, interesteds, descriptions);
		
		/*Person person = this.getById(3);
		System.out.println(person.toString());*/
		/*int[] idsInt = new int[2];
		idsInt[0] = 1;
		idsInt[1] = 3;*/	
		//ArrayList<Person> list = this.getByIds(idsInt);
		
		Person newPerson = new Person("Huy", 1985, "Male", "picnic", "normal");
		this.update(1, newPerson);
		
		ArrayList<Person> personList = this.getAll();

		System.out.println("#_FOREACH");
		for (Person person : personList) {
			
			System.out.println(person.toString());
		}

		
	}
}
