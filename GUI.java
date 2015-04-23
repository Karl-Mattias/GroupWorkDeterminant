package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	static int RIDA = 2;
	static int VEERG = 2;
	
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			ChoiceBox menüü = new ChoiceBox(FXCollections.observableArrayList(
				    "1", "2", "3"));
			root.setTop(menüü);
			menüü.getSelectionModel().selectedIndexProperty().addListener(
					new ChangeListener<Number>() {
						public void changed(ObservableValue ov, Number value, Number new_value) {
							RIDA = (int)new_value +1;
							VEERG =(int)new_value +1;
				
						}
					});
			
			
			GridPane lahtrid = new GridPane();
			lahtrid.setAlignment(Pos.CENTER);
			lahtrid.setVgap(10);
			lahtrid.setHgap(10);
			
			for(int i = 0; i<RIDA; i++){	
				for(int j = 0; j<VEERG; j++){
					lahtrid.add(new TextField("0"), i, j);
				}
			}
			
			root.setCenter(lahtrid);
			int D = 0;
			Label vastus = new Label("Determinant on " + D);

			root.setBottom(vastus);
			
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
