import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.*;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// skapa och fyll en mängd med ord som ska skippas.
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		scan.close();

		// skapa ord-samlingen
		GeneralWordCounter counter = new GeneralWordCounter(stopwords);
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			counter.process(word);
		}
		s.close();

		// creating the list of words
		Set<Map.Entry<String, Integer>> wordSet = counter.getWords();
		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(wordSet);
		ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(words);

		// create buttons.
		HBox hbox = new HBox();

		Button buttonAlphabetic = new Button("Alphabetic");
		Button buttonFrequency = new Button("Frequency");
		Button buttonSearch = new Button("Search");
		TextField searchField = new TextField("text");
		HBox.setHgrow(searchField, Priority.ALWAYS);
		hbox.getChildren().addAll(buttonAlphabetic, buttonFrequency, buttonSearch, searchField);

		// Eventhantering för knapparna.
		buttonAlphabetic.setOnAction(event -> {
			words.sort((s1, s2) -> s1.getKey().compareTo(s2.getKey()));
		});
		buttonFrequency.setOnAction(event -> {
			words.sort((s1, s2) -> s2.getValue().compareTo(s1.getValue()));
		});
		buttonSearch.setOnAction(event -> {
			String searchWord = searchField.getText().toLowerCase().trim();

			for (int i = 0; i < words.size(); i++) {
				if (words.get(i).getKey().equals(searchWord)) {
					listView.scrollTo(i);
					return;
				}
			}
			System.out.println("No such word found.");
		});
		searchField.setOnAction(event -> {
			buttonSearch.fire();
		});

		// // Detta är alltså samma sak som lamba-uttrycket ovan. Vilket i sin tur är samma sak som
		// // att skriva en helt ny privat klass som implementerar EventHandler.
		// buttonAlphabetic.setOnAction(new EventHandler<ActionEvent>() {
		// 	public void handle(ActionEvent event) {
		// 		System.out.println("HEJ!");
		// 	}
		// } );

		// setting up the stage
		BorderPane root = new BorderPane();
		root.setBottom(hbox);
		Scene scene = new Scene(root, 500, 500);

		// place the list in the center
		root.setCenter(listView);

		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
