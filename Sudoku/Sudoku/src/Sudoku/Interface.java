package Sudoku;

import java.awt.Dimension;
import java.awt.Label;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Interface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		final double SIZE = 40;
		// varje TextField ruta är en ruta i vårt grid.
		BorderPane root = new BorderPane();
		HBox hbox = new HBox();
		Button buttonSolve = new Button("Solve");
		Button buttonClear = new Button("Clear");

		TilePane grid = new TilePane();
		grid.setPrefRows(9);
		grid.setPrefColumns(9);
		
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				OneLetterTextField textField = new OneLetterTextField();
				textField.setPrefHeight(SIZE);
				textField.setPrefWidth(SIZE);
				textField.setPrefSize(SIZE, SIZE);
				grid.getChildren().add(new OneLetterTextField());
			}
		}

		grid.setPrefTileWidth(500/9);
		grid.setPrefTileHeight(500/9);
		hbox.getChildren().addAll(buttonSolve, buttonClear);
		
		root.setTop(grid);
		root.setBottom(hbox);

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
