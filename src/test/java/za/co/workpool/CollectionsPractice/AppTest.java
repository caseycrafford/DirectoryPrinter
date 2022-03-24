package za.co.workpool.CollectionsPractice;

import static org.junit.jupiter.api.Assertions.assertTrue;  

import java.util.InputMismatchException;
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

}
