package za.co.workpool.CollectionsPractice;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
	static int tabbingInt = -1;
	
	
	public static void main() {
		File currentDir = new File("C:/Users/Developer"); 
		displayDirectoryContents(currentDir);
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
				}
			}
			
		} catch (NullPointerException e) {
		}
	}
	

	public static String tabbing(int tabs) {
		String tabbing = "";
		for(int i=0;i<tabs;i++) {
			tabbing+="\t";
		}
		return tabbing;
	}
}
