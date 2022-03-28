package za.co.workpool.CollectionsPractice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
 
public class AppTest {

	@Test
	void appTest() {
		boolean bool = false;
		try (Scanner sysIn = new Scanner(System.in)) {
			System.out.println("Enter int:");
			int choice = sysIn.nextInt();

			if (choice != 1 && choice != 2) {
				System.out.println("must be 1 or 2");
				throw new InputMismatchException();
			} else
				bool = true;

		} catch (InputMismatchException e) {
			System.out.println(e);
		}
		assertTrue(bool);
	}

	
	@Test
	void testStuff() {
		Map<String,Double> fileMap = new HashMap<String,Double>();
		long fileSize = 123000;
		long fileMin = 0;
		File file = new File("C:Users/Developer/Downloads");
		App.displayFilesBetweenSize(file, fileMin, fileSize, fileMap);
		fileMap.forEach((k,v) -> { 
			System.out.printf("%-110s %110s %n",k,v );
		});
	}
}
