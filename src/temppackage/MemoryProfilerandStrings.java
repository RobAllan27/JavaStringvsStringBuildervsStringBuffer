package temppackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MemoryProfilerandStrings {

	private ArrayList<String[]> arrayListOfStrings;
	private Random random;
	private StringBuilder builder;
	private String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
	private String a, b, c;
	private StringBuffer aBuilder;
	private StringBuffer bBuilder;
	private StringBuffer cBuilder;
	private int numberofLoops = 20000000;
	private boolean usingStringBuilder = false;

	public MemoryProfilerandStrings() {

		// int targetStringLength = 10;
		random = new Random();
		// builder = new StringBuilder(targetStringLength);
		builder = new StringBuilder();
		arrayListOfStrings = new ArrayList<String[]>();
		aBuilder = new StringBuffer("");
		bBuilder = new StringBuffer("");
		cBuilder = new StringBuffer("");
	}

	public ArrayList<String[]> makeArrayList() {
		String csvFilePath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\ACEMON\\Sample Data for PPT files\\SampleDataForPPTFiles.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] rowdata = line.split(csvSplitBy);
				this.arrayListOfStrings.add(rowdata);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayListOfStrings;
	}

	private StringBuilder getRandomString() {

		// chose a Character random from this String

		int max = 15;
		int min = 10;

		int sizeOfString = random.nextInt(max - min + 1) + min;
		builder.replace(0, builder.length(), "");
		for (int i = 0; i < sizeOfString; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			builder.append(AlphaNumericString.charAt(index));
		}
		return builder;
	}

	protected void testingofArrayList() {
		int countofexcutions = 0;
		int loops = 0;
		if (!usingStringBuilder) {
			for (int i = 0; i < numberofLoops; i++) {
				for (int j = 0; j < arrayListOfStrings.size(); j++) {
					String[] setofdata = arrayListOfStrings.get(j);
					{
						
						
/* intern - When the intern method is invoked, if the pool already contains a string equal to this String object as determined bythe equals(Object) method, then the string from the pool isreturned. Otherwise, this String object is added to thepool and a reference to this String object is returned. 

It follows that for any two strings s and t, s.intern() == t.intern() is true if and only if s.equals(t) is true. 

*/
						a = setofdata[0].intern();
						b = setofdata[1].intern();
						c = setofdata[2].intern();
						 // System.out.println("Here are some string data based on file data" + a + "___"
						 // + b + "___" + c + "___");
						countofexcutions++;
					}
				}
				System.out.println(i);
				loops++;
			}
		} else {
			for (int i = 0; i < numberofLoops; i++) {

				for (int j = 0; j < arrayListOfStrings.size(); j++) {
					String[] setofdata = arrayListOfStrings.get(j);
					{
						aBuilder.replace(0, aBuilder.length(), setofdata[0]);
						bBuilder.replace(0, bBuilder.length(), setofdata[1]);
						cBuilder.replace(0, cBuilder.length(), setofdata[2]);
						 //System.out.println("Here are some builder data based on file data" + "___" +
						 //aBuilder + "___" + bBuilder + "___" + cBuilder + "___");
						countofexcutions++;
					}

				}
				System.out.println(i);
				loops++;
			}
		}
		System.out.println("Number of Executions" + countofexcutions + " Number of loops " + loops);
	}

	protected void testingofRandomString() {
		int countofexcutions = 0;
		int loops = 0;
		if (!usingStringBuilder) {
			for (int i = 0; i < numberofLoops; i++) {
				for (int j = 0; j < arrayListOfStrings.size(); j++) {
					a = getRandomString().toString();
					b = getRandomString().toString();
					c = getRandomString().toString();
					 //System.out.println("Here are some random string data based on random data " +
					 // a + "___" + b + "___" + c + "___");
					countofexcutions++;
				}
				System.out.println(i);
				loops++;
			}
		} else {
			for (int i = 0; i < numberofLoops; i++) {
				for (int j = 0; j < arrayListOfStrings.size(); j++) {
					{
						aBuilder.replace(0, aBuilder.length(), getRandomString().toString());
						bBuilder.replace(0, bBuilder.length(), getRandomString().toString());
						cBuilder.replace(0, cBuilder.length(), getRandomString().toString());
						//System.out.println("Here are some builder data based on random data" + "___"
						// + aBuilder + "___" + bBuilder + "___" + cBuilder + "___");
						countofexcutions++;
					}
					
				}
				System.out.println(i);
				loops++;
			}
		}
		System.out.println("Number of Executions" + countofexcutions + " Number of loops " + loops);
	}

}
