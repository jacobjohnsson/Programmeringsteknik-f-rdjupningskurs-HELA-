package Sudoku;


import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Interface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// varje TextField ruta är en ruta i vårt grid.

		SudokuSolver ss = new SudokuSolver();
		AnchorPane root = new AnchorPane();

		TilePane grid = new TilePane();
		grid.setHgap(20);
		grid.setVgap(-5.0);
		grid.setPadding(new Insets(20, 10, 10, 40));
		grid.setPrefRows(9);
		grid.setPrefColumns(9);

		for (int row = 0; row < 9; row++) {
			for(int col = 0; col < 9 ; col++) {
				OneLetterTextField textField = new OneLetterTextField();

				// sets background color to pink
				if (row < 3 && col < 3) {
					textField.setStyle("-fx-background-color: #ff66ff;");
				} else if (row < 3 && col > 5 ) {
					textField.setStyle("-fx-background-color: #ff66ff;");
				} else if (row > 2 && row < 6 && col > 2 && col < 6) {
					textField.setStyle("-fx-background-color: #ff66ff;");
				} else if (row > 5 && col < 3) {
					textField.setStyle("-fx-background-color: #ff66ff;");
				} else if(row > 5 && col > 5) {
					textField.setStyle("-fx-background-color: #ff66ff;");
				}

				textField.setId(row + "," + col);
				grid.getChildren().add(textField);
			}
		}

		grid.setPrefTileWidth(250/9);
		grid.setPrefTileHeight(400/9);
		
		
		HBox hbox = new HBox();
		Button buttonSolve = new Button("Solve");
		buttonSolve.setDefaultButton(true);
		Button buttonClear = new Button("Clear");
		hbox.getChildren().addAll(buttonSolve, buttonClear);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(0, 0, 0, 160));


		buttonSolve.setOnAction(event -> {
			for(Node tf: grid.getChildren()) {
				try {
					OneLetterTextField current = ((OneLetterTextField) tf);
					// Fall:  null pointer och "" tom sträng.
					if(current.getText() != null && !current.getText().isEmpty()) {
						
						int value = Integer.parseInt(current.getText());
						String[] coordinates = current.getId().split(",");
						int row = Integer.parseInt(coordinates[0]);
						int col = Integer.parseInt(coordinates[1]);
						ss.setValue(value, row, col);
					
					} else if (current.getText() == null || current.getText().isEmpty()) {
						
						String[] coordinates = current.getId().split(",");
						int row = Integer.parseInt(coordinates[0]);
						int col = Integer.parseInt(coordinates[1]);
						ss.setValue(0, row, col);					
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			boolean result = ss.solve();
			if(result) {
				for(Node tf: grid.getChildren()) {
					
					OneLetterTextField current = ((OneLetterTextField) tf);
					String[] coordinates = current.getId().split(",");
					int row = Integer.parseInt(coordinates[0]);
					int col = Integer.parseInt(coordinates[1]);
					String value = Integer.toString(ss.getValue(row, col)); 
					current.replaceText(0, 0, value);
				}
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "Saknas lösning eller isUserRetarded == true.");
				alert.showAndWait();
			}
		});

		buttonClear.setOnAction(event -> {
			for(Node tf: grid.getChildren()) {
				try {
					OneLetterTextField current = ((OneLetterTextField) tf);
					String[] coordinates = current.getId().split(",");
					int row = Integer.parseInt(coordinates[0]);
					int col = Integer.parseInt(coordinates[1]);
					if(current.getText() != null && !current.getText().isEmpty()) {
						current.replaceText(0, 1, "");
					}
					ss.setValue(0, row, col);	
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} 
		});




		root.getChildren().addAll(grid, hbox);
		AnchorPane.setTopAnchor(grid, 10.0);
		AnchorPane.setBottomAnchor(hbox, (double) 0);




		Scene scene = new Scene(root, 500, 450);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
