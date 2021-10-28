module Rahanseuranta {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires tess4j;
	
	opens application to javafx.graphics, javafx.fxml;
}
