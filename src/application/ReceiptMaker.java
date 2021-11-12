
package application;

import java.io.File;
import java.nio.file.Path;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.ITesseract;

public class ReceiptMaker {
	public ReceiptMaker() {

	}

	public ArrayList<Item> getReceipt(File file) {
		String euro = "\u20AC";  //UNICODE for the € symbol to avoid complications
		ITesseract instance = new Tesseract();

		instance.setDatapath("src/tessdata"); //location for the Tesseract language assets
		instance.setLanguage("fin");

		ArrayList<Item> kuitti = new ArrayList<>(); //temporary array for the OCR scan
		int i = 7; //relevant information on the example receipt starts on row 7. further development would require tailored specs to be assigned according to the shop/store 
		String result;

		try {
			result = instance.doOCR(file); //the method that runs the actual OCR operation
			String[] myarray = result.split("\n"); //the read receipt is split by line break

			while (i < myarray.length) {
				if (myarray[i].contains("YHTEENS")) { //the receipt is read line by line until the line with the total cost leaving just the relevant lines. again, further development would require tailoring for differently structured receipts.
					break;
				} else {
					if (!myarray[i].contains(euro)) { //special rule that leaves out unwanted lines (in this case the itemization of a bottle deposit)

						//separating the name of the list item using the last instance of a space as a separator
						String separator = " ";
						int separatorPos = myarray[i].lastIndexOf(separator);
						String name = myarray[i].substring(0, separatorPos);

						String y = "";

						//separating the cost of the list item and replacing the commas with periods so the costs work as doubles.
						//this requires sorting the actual cost from other information, such as the volume of a soda bottle.
						String toReplace = ",";
						String replacement = ".";
						int start = myarray[i].lastIndexOf(toReplace); //only the last comma is replaced
						StringBuilder builder = new StringBuilder();
						builder.append(myarray[i].substring(0, start)); //append the part before the last occurence
						builder.append(replacement); //append the period
						builder.append(myarray[i].substring(start + toReplace.length())); //append the part after the last occurence
						Matcher m = Pattern.compile("(\\d+(?:\\.\\d+))").matcher(builder); //finds the double from the string
						while (m.find()) {
							double d = Double.parseDouble(m.group(1)); //assigns the double into variable d
							
							//checking whether the amount is income or an expense
							if (d >= 0) { 
								y = "Income";
							} else {
								y = "Expense";
							}

							kuitti.add(new Item(name, d, y));

						}

					}
				}
				i++;
			}

		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		return kuitti;
	}
}
