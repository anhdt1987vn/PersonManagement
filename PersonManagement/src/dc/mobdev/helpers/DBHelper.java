package dc.mobdev.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBHelper {

	private static Context mainContext;
	private static SQLiteDatabase sqliteDb;
	private static String databaseName;

	public static void init(Context context) {
		mainContext = context;
		openOrCreateDatabase(databaseName);
	}

	public static void selectionDatabase(String dbName) {
		databaseName = dbName;
	}

	public static boolean openOrCreateDatabase(String dbName) {
		try {
			sqliteDb = mainContext.openOrCreateDatabase(dbName,
					mainContext.MODE_PRIVATE, null);
			return true;
		} catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
	}

	public static boolean upgradeDatabase(String dbName, String dbVerson) {
		return false;
	}

	public static boolean deleteDatabase(String dbName) {
		String deleteDB = "drop database " + dbName;
		execute(deleteDB);
		return false;
	}

	public static boolean openOrCreateTable(String tableName, String[] columns,
			String[] dataTypes, String[] conditions) {
		String createTable = "create table if not exists " + tableName + "(";
		for (int i = 0; i < columns.length; i++) {
			if (i < columns.length - 1) {
				createTable += columns[i] + " " + dataTypes[i] + " "
						+ conditions[i] + ",";
			} else {
				createTable += columns[i] + " " + dataTypes[i] + " "
						+ conditions[i] + ")";
			}
		}
		execute(createTable);
		return true;
	}

	public static boolean deleteTable(String tableName) {
		String deleteTable = "drop table " + tableName;
		try {
			execute(deleteTable);
			return true;
		} catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
	}

	public static boolean execute(String query) {
		try {
			sqliteDb.execSQL(query);
			return true;
		} catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
			return false;
		}
	}

	public static Cursor executeQuery(String query) {
		try {
			return sqliteDb.rawQuery(query, null);
		} catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
			return null;
		}
	}

	public static void test() {
		
		
	}

}
