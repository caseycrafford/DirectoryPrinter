package za.co.workpool.CollectionsPractice;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

public class App 
{
	static int tabbingInt = -1;
	static Map<String, Integer> extensions= new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		File currentDir = new File("C:/Users/Developer"); 
//		displayDirectoryContents(currentDir);
//		System.out.println(extensions);
		Set<String> searchRes = new HashSet<String>();
		System.out.println(findFileInDirectory(currentDir, "Posit", searchRes));
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
					System.out.println(tabbing(tabbingInt+1) + "-"+ file.getCanonicalPath());
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
}
