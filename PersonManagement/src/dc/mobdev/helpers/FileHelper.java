package dc.mobdev.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class FileHelper {

	private static Context mainContext;
	private static final String SDCARD = "/storage/sdcard/";

	public static void init(Context context) {
		mainContext = context;
	}

	public static File createFileOnSDcard(String name, String path,
			boolean override) {
		File dir = createFolderOnSdCard(path);
		System.out.println("FILE HELPER__Preparing to create sdcard.txt file on SDCARD");
		File file = new File(dir, name);
		System.out.println("FILE HELPER__Contructs new File Sucessful!" + "File.EXISTS :" + file.exists());
		try {
			if (!file.exists()) {
				System.out.println("FILE HELPER__FILE NOT EXISTS");
				//createFolderOnSdCard(path);
				System.out.println("FILE HELPER__CREATE FOLDER SUCESSFUL!");
				file.createNewFile();
				System.out.println("FILE HELPER__Create sdcard.txt file on SDCARD sucessful!");
			} else if (file.exists() && override == true) {
				System.out.println("FILE HELPER__FILE EXISTS");
				file.delete();
				System.out.println("FILE HELPER__DELETE FILE SUCESSFUL!");
				// createFolderOnSdCard(path);
				file.createNewFile();
				System.out.println("FILE HELPER__Create sdcard.txt file on SDCARD sucessful!");
			}
			return file;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	private static File createFolderOnSdCard(String path) {
		File dir = new File(SDCARD + path + "/");
		dir.mkdirs();
		return dir;
	}

	public static File createFileOnInternalStorage(String name, String path,
			boolean override) {
		System.out.println("FILE HELPER__Preparing to create  file on Internal Storage");
		System.out.println("FILE HELPER__Preparing to create folder on Internal Storage");
		File dir = createFolderOnInternalStorage(path);
		System.out.println("FILE HELPER__Create folder on Internal Storage sucessful!");
		File file = new File(dir, name);
		try {
			if (!file.exists()) {
				System.out.println("FILE HELPER__FILE IS NOT EXISTS");
				file.createNewFile();
				System.out.println("FILE HELPER__FILE IS CREATE");
			} else if (file.exists() && override == true) {
				System.out.println("FILE HELPER__FILE IS EXISTS");
				file.delete();
				System.out.println("FILE HELPER__FILE IS DELETED");
				file.createNewFile();
				System.out.println("FILE HELPER__FILE IS CREATE");
			}
			return file;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	private static File createFolderOnInternalStorage(String path) {
		File dir = mainContext.getDir(path, Context.MODE_PRIVATE);
		return dir;
	}

	public static File openFileOnSDcard(String path) {
		return new File(SDCARD + path);
	}

	public static File openFileOnInternalStorage(String path) {
		return new File(path);
	}

	public static InputStream getInputStream(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		}
	}

	public static InputStream getInputStream(String path) {
		try {
			return new FileInputStream(new File(path));
		} catch (FileNotFoundException fnfe) {
			return null;
		}
	}

	public static OutputStream getOutputStream(File file) {
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		}
	}

	public static OutputStream getOutputStream(String path) {
		try {
			return new FileOutputStream(new File(path));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		}
	}

	public static void writeToFile(File file, String contents) {
		try{
		BufferedWriter bfWrite = new BufferedWriter(new FileWriter(file));
		bfWrite.write(contents);
		bfWrite.flush();
		bfWrite.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	
	public static void writeToFile(String path, String contents) {
		try{
			BufferedWriter bfWrite = new BufferedWriter(new FileWriter(new File(path)));
			bfWrite.write(contents);
			bfWrite.flush();
			bfWrite.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
	}
	
	public static void test(Context context){
		mainContext = context;
		
		//createFileOnSDcard("sdcard.txt", "PersonDemo", true);
		createFileOnInternalStorage("personInternal.txt", "personDemo" , false);
	}

}
