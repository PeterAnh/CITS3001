/*
 * CITS3001 Lab 4
 * Test program for WordChessImp
 * Author: Hoang Tuan Anh
 * Student ID: 21749914
 * The University of Western Australia
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String token = "";

		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(new File("src/corncob_caps.txt")).useDelimiter("\n");
			List<String> temps = new ArrayList<String>();

			while (sc.hasNext()) {
				token = sc.next().trim();
				temps.add(token);
			}

			sc.close();

			String[] dictionary = temps.toArray(new String[0]);

			String firstWord = new String();
			String secondWord = new String();
			String exitString = new String();
			Scanner input = new Scanner(System.in);

			while (!exitString.equals("Y") || !exitString.equals("YES")) {
				firstWord = new String();
				secondWord = new String();
				exitString = new String();
				while (firstWord.isEmpty() || secondWord.isEmpty() || firstWord.length() != secondWord.length()
						|| !temps.contains(firstWord) || !temps.contains(secondWord)) {
					System.out.println("Enter first word:");
					firstWord = input.nextLine().trim().toUpperCase();

					System.out.println("Enter second word:");
					secondWord = input.nextLine().trim().toUpperCase();

					if (firstWord.isEmpty() && secondWord.isEmpty()) {
						System.out.println("Both words are empty. Pleae try again\n");
					} else if (firstWord.isEmpty()) {
						System.out.println("First word is empty. Please try again\n");
					} else if (secondWord.isEmpty()) {
						System.out.println("Second word is empty. Please try again\n");
					} else if (!temps.contains(firstWord) && !temps.contains(secondWord)) {
						System.out.println("Both words are invalid. Please try again\n");
					} else if (!temps.contains(firstWord)) {
						System.out.println("Invalid first word. Please try again\n");
					} else if (!temps.contains(secondWord)) {
						System.out.println("Invalid second word. Please try again\n");
					} else if (firstWord.length() != secondWord.length()) {
						System.out.println("Mismatch word length. Please try again\n");

					}
				}

				System.out.println("First word is: " + firstWord + "\nSecond word is: " + secondWord + "\n");

				WordChessImp w = new WordChessImp();

				long start = System.nanoTime();
				String[] result = w.findPath(dictionary, firstWord, secondWord);
				long end = System.nanoTime();

				for (int i = 0; i < result.length; i++) {
					if (i == result.length - 1) {
						System.out.print(result[i] + "\n");
					} else {
						System.out.print(result[i] + "->");
					}
				}
				System.out.println("Time elapsed findPath: " + (end - start) / 1000000 + " ms\n");

				start = System.nanoTime();
				String[] result2 = w.findPath2(dictionary, firstWord, secondWord);
				end = System.nanoTime();

				for (int i = 0; i < result2.length; i++) {
					if (i == result2.length - 1) {
						System.out.print(result2[i] + "\n");
					} else {
						System.out.print(result2[i] + "->");
					}
				}
				System.out.println("\nTime elapsed findPath2: " + (end - start) / 1000000 + " ms\n");
				System.out.println("Continue: Y/N?");
				exitString = input.nextLine().trim().toUpperCase();
				while(!exitString.equals("Y") || !exitString.equals("N"))
				{
					if(exitString.equals("N") || exitString.equals("Y"))
					{
						break;
					}
					System.out.println("Please enter only Y or N\n");
					System.out.println("Continue: Y/N?");
					exitString = input.nextLine().trim().toUpperCase();
				}
				if (exitString.equals("N")) {
					break;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
