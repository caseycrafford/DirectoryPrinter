package za.co.workpool.CollectionsPractice;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

public class App 
{
	static int tabbingInt = -1;
	static Map<String, Integer> extensions= new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		menu();
	}

	
	public static void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					tabbingInt++;
					System.out.println(tabbing(tabbingInt) + "["+ file.getName()+ "]");
					displayDirectoryContents(file);
					tabbingInt--;
				} else {
					System.out.println(tabbing(tabbingInt+1) + "-"+ file.getName());
					extensions.merge(FilenameUtils.getExtension(file.getName()), 1, Integer::sum);
				}
			}
			
		} catch (Exception e) {
		}
	}
	
	public static Set<String> findFileInDirectory(File dir,String fileName, Set<String> results) {
		try {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					findFileInDirectory(file,fileName,results);
				} else {
					if(file.getName().contains(fileName)) {
						results.add(file.getPath());
					}
				}
			}
			
		} catch (Exception e) {
		}
		return results;
	}

	public static String tabbing(int tabs) {
		String tabbing = "";
		for(int i=0;i<tabs;i++) {
			tabbing+="\t";
		}
		return tabbing;
	}
	
	public static boolean testContentEquals() {
		String search = "bar";
		String toSearch = "barcelona";
		return toSearch.contains(search);
	}
	
	public static void menu() {
		Scanner sysIn = new Scanner(System.in);
		try  {
			String choice = "";
			Set<String> results = new HashSet<String>();
			
			System.out.println("1. View a directory \n2. Find a file in a directory");
			choice = sysIn.next();
			
			if (choice.equals("1")) {
				System.out.println("Paste the directory to be viewed below:");
				File currentDir = new File(sysIn.next());
				displayDirectoryContents(currentDir);
			} else if (choice.equals("2")) {
				System.out.println("Paste the directory you would like to search below:");
				File currentDir = new File(sysIn.next());
				System.out.print("Enter search term:");
				String searchString = sysIn.next();
				findFileInDirectory(currentDir, searchString, results);
				System.out.println("========================================================\n\t\t\tResults\n========================================================");
				for(String result:results) {
					System.out.println(result);
				}
				System.out.println("========================================================\n\t\t\tEnd\n========================================================");
			} else 
				throw new InputMismatchException(); 
		} catch (InputMismatchException e) {
			System.out.println("Only 1 or 2 is allowed.");
			menu();
		} catch (NoSuchElementException e) {
			System.out.println("Only 1 or 2 is allowed.");
			menu();
		} finally {
			sysIn.close();
		}
	}
}
