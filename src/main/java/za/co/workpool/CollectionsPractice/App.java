package za.co.workpool.CollectionsPractice;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

public class App 
{
	static int tabbingInt = -1;
	static Map<String, Integer> extensions= new HashMap<String, Integer>();
	static Scanner sysIn;
	static String resultStart="========================================================         Results         ========================================================";
	static String resultEnd=  "========================================================      End of Results      ========================================================";
	
	
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

	public static Set<String> displayExtensionInDirectory(File dir,String extName, Set<String> results) {
		try {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					displayExtensionInDirectory(file,extName,results);
				} else {
					if(FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(extName)) {
						results.add(file.getPath());
					}
				}
			}
		} catch (Exception e) {
		}
		return results;
	}
	
	public static Map<String, Double> displayFilesBetweenSize(File dir,long sizeMin,long sizeMax, Map<String, Double> results) {
		try {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					displayFilesBetweenSize(file,sizeMin,sizeMax,results);
				} else {
					if(Files.size(Paths.get(file.getPath()))/1024 >= sizeMin && Files.size(Paths.get(file.getPath()))/1024 <= sizeMax)
						results.put(file.getPath(), Files.size(Paths.get(file.getPath()))/1024.0);
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
	
	public static void menu() {
		sysIn = new Scanner(System.in);
		String choice;
		boolean repeatMenu = true;
		
		while (repeatMenu) {
			System.out.println("\n1. View a directory \n2. Find a file in a directory \n3. Search by file extension \n4. Search by file size(KB) \n5. Exit");
			choice = sysIn.next();
			try {
				switch (choice) {
				case "1":
					viewDirectoryMenu();
					break;

				case "2":
					findFileMenu();
					break;
					
				case "3":
					findByExtensionMenu();
					break;
					
				case "4":
					findBySizeMenu();
					break;
				case "5":
					System.exit(0);
					
				default:
					System.out.println("\nPlease enter 1, 2 or 3\n");
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Only 1 or 2 is allowed.");
			} 
		}
		sysIn.close();
	}
	
	public static void viewDirectoryMenu() {
		System.out.println("Paste the directory to be viewed below:");
		File displayDir = new File(sysIn.next());
		System.out.println(resultStart);
		displayDirectoryContents(displayDir);
		System.out.println(resultEnd);
	}
	
	public static void findFileMenu() {
		Set<String> results = new HashSet<String>();
		
		System.out.println("Paste the directory you would like to search below:");
		File currentDir = new File(sysIn.next());
		
		System.out.print("Enter search term:");
		String searchString = sysIn.next();
		
		findFileInDirectory(currentDir, searchString, results);
		System.out.println(resultStart);
		for (String result : results) {
			System.out.println(result);
		}
		System.out.println(resultEnd);
	}
	
	public static void findByExtensionMenu() {
		Set<String> results = new HashSet<String>();
		
		System.out.println("Paste the directory you would like to search below:");
		File extDir = new File(sysIn.next());
		System.out.print("Enter extension:");
		String searchString1 = sysIn.next();
		if(searchString1.startsWith(".")) {
			searchString1=searchString1.substring(1);
		}
		displayExtensionInDirectory(extDir, searchString1, results);
		System.out.println(resultStart);
		for (String result : results) {
			System.out.println(result);
		}
		System.out.println(resultEnd);
	}
	
	public static void findBySizeMenu() {
		Map<String,Double> results = new HashMap<String,Double>();
		long sizeMin = 0;
		long sizeMax = 0;
		System.out.println("Paste the directory you would like to search below:");
		File currentDir = new File(sysIn.next());
		
		System.out.print("Enter minimum file size(KB):");
		sizeMin = sysIn.nextLong();
		System.out.println("Enter maximum file size(KB)");
		sizeMax = sysIn.nextLong();
		
		displayFilesBetweenSize(currentDir, sizeMin, sizeMax, results);
		System.out.println(resultStart);
		results.forEach((k,v) -> { 
			System.out.printf("%-110s %110s %n",k,v );
		});
		System.out.println(resultEnd);
	}
	
}
