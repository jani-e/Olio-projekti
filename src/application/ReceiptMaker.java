
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
		String euro = "\u20AC";
		ITesseract instance = new Tesseract();
		
		instance.setDatapath("src/tessdata");
		instance.setLanguage("fin");

		ArrayList<Item> kuitti = new ArrayList<>();
		int i = 7;
		String result;

		try {
			result = instance.doOCR(file);
			String[] myarray = result.split("\n");

			while (i < myarray.length) {
				if (myarray[i].contains("YHTEENS")) {
					break;
				} else {
					if (!myarray[i].contains(euro)) {
						
						//erotetaan tuotteen nimi
						String separator = " ";
						int separatorPos = myarray[i].lastIndexOf(separator);
						String name = myarray[i].substring(0, separatorPos);
						
						String y = "";
						
						//haetaan vain rivin viimeinen desimaali, muutetaan siit� pilkku pisteeksi
						//ja tulostetaan pelkk� liukuluku
						String toReplace = ",";
						String replacement = ".";
						int start = myarray[i].lastIndexOf(toReplace);
						StringBuilder builder = new StringBuilder();
						builder.append(myarray[i].substring(0, start));
						builder.append(replacement);
						builder.append(myarray[i].substring(start + toReplace.length()));
						Matcher m = Pattern.compile("(\\d+(?:\\.\\d+))").matcher(builder);
						while (m.find()) {
							double d = Double.parseDouble(m.group(1));
							if (d >= 0) {
							y = "income";
							} else {
								y = "expense";
							}
							
							kuitti.add (new Item (name, d, y));
							
						
						}
						
					}
				}
				i++;
			}
		//	System.out.println(kuitti);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
		return kuitti;
	}
}
