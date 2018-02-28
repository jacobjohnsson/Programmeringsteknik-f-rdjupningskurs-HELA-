package Sudoku;

import java.awt.Dimension;
import java.awt.Label;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Interface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();
		root.setPrefColumns(9);
		root.setPrefRows(9);
		final int size = 40;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; i++) {
				Label label = new Label();
				label.setPreferredSize(new Dimension(size, size));
				root.getChildren().addAll();
			}

		}

		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
