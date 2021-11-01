package application;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

	public String getImageText(File file) {
		Tesseract tesseract = new Tesseract();
		String text = "";

		try {

			tesseract.setDatapath("D:/Tess4J/tessdata");

			text = tesseract.doOCR(new File("image.jpg"));

		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return text;
	}

}