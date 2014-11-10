package dc.mobdev.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import dc.mobdev.entities.Person;

public class JSonHelper {

	public JSonHelper() {
		init();
	}

	public void init() {
		new JSonTask().execute("http://chickencode.com/temp/persons.data");
	}

	public static ArrayList<Person> getPerson(Context context, String fileName) {
		try {
			JSONObject parser = new JSONObject(loadJSonFromAsset(context,
					fileName));

			ArrayList<Person> persons = new ArrayList<Person>();

			Person person = null;

			JSONArray personArr = parser.getJSONArray("persons");
			for (int i = 0; i < personArr.length(); i++) {
				person = new Person();
				JSONObject jsonPerson = personArr.getJSONObject(i);
				person.setId(jsonPerson.getInt("id"));
				person.setName(jsonPerson.getString("name"));
				person.setYearOfBirth(jsonPerson.getInt("yearOfBirth"));
				person.setGender(jsonPerson.getString("gender"));
				person.setInterested(jsonPerson.getString("interested"));
				person.setDescription(jsonPerson.getString("description"));
				persons.add(person);
			}

			return persons;
		} catch (JSONException jsE) {
			jsE.printStackTrace();
			return null;
		}
	}

	private static String loadJSonFromAsset(Context context, String fileName) {
		try {
			InputStream is = context.getAssets().open(fileName);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			String json = new String(buffer, "UTF-8");
			System.out.println("JSON: " + json);
			return json;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}

	public ArrayList<Person> getPerson(String url) {
		try {
			String json = loadJSONFromUrl(url);
			JSONObject parser = new JSONObject(json);

			ArrayList<Person> persons = new ArrayList<Person>();

			Person person = null;

			JSONArray personArr = parser.getJSONArray("persons");
			for (int i = 0; i < personArr.length(); i++) {
				person = new Person();
//				JSONObject jsonPerson = personArr.getJSONObject(i);
//				person.setId(jsonPerson.getInt("id"));
//				person.setName(jsonPerson.getString("name"));
//				person.setYearOfBirth(jsonPerson.getInt("yearOfBirth"));
//				person.setGender(jsonPerson.getString("gender"));
//				person.setInterested(jsonPerson.getString("interested"));
//				person.setDescription(jsonPerson.getString("description"));
//				persons.add(person);
				person.setId(personArr.getJSONObject(i).getInt("id"));
				person.setName(personArr.getJSONObject(i).getString("name"));
				person.setYearOfBirth(personArr.getJSONObject(i).getInt("yearOfBirth"));
				person.setGender(personArr.getJSONObject(i).getString("gender"));
				person.setInterested(personArr.getJSONObject(i).getString("interested"));
				person.setDescription(personArr.getJSONObject(i).getString("description"));
				persons.add(person);
			}
			return persons;
		} catch (JSONException jsE) {
			jsE.printStackTrace();
			return null;
		}
	}

	private class JSonTask extends AsyncTask<String, Void, ArrayList<Person>> {

		@Override
		protected ArrayList<Person> doInBackground(String... params) {
			System.out.println("Prams [0] :" + params[0]);
			return getPerson(params[0]);
		}

		@Override
		protected void onPostExecute(ArrayList<Person> persons) {
			super.onPostExecute(persons);
			for (int i = 0; i < persons.size(); i++) {
				System.out.println(persons.get(i).toString());
			}
		}

	}

	public String loadJSONFromUrl(String url) {
		String jsonStr = null;
		try {
			InputStream inputStream = getInputStream(url);
			BufferedReader bfR = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder strBuilder = new StringBuilder();
			String line = "";
			while ((line = bfR.readLine()) != null) {
				strBuilder.append(line + "\n");
			}
			jsonStr = strBuilder.toString();
			System.out.println("JSONSTR : " + jsonStr);
			return jsonStr;
		} catch (IOException ie) {
			ie.printStackTrace();
			return null;
		}
	}

	private InputStream getInputStream(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			// Starts the query
			conn.connect();
			return conn.getInputStream();
		} catch (IOException ioE) {
			ioE.printStackTrace();
			return null;
		}
	}

	public static void test(Context context) {
		String url = "http://api.androidhive.info/contacts/";
		/*
		 * ArrayList<Person> persons = getPerson(context, "person.json"); for
		 * (Person person : persons) { System.out.println(person.toString());
		 * System.out.println("___"); }
		 */
	}

}
