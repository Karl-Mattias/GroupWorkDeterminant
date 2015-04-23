import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	public class valikud extends StackPane{
		private ComboBox<String> combo = new ComboBox<String>();
		private Label label = new Label();
		
		public valikud(String[] options){
			StackPane.setAlignment(label, Pos.CENTER_LEFT);
			StackPane.setAlignment(combo, Pos.CENTER_LEFT);
			
			label.textProperty().bind(
					combo.getSelectionModel().selectedItemProperty()
					);
			label.visibleProperty().bind(
					combo.visibleProperty().not()
					);
			label.setPadding(new Insets(0, 0, 0, 9));

			combo.getItems().setAll(options);
			combo.getSelectionModel().select(0);
			combo.setVisible(false);

			label.setOnMouseEntered(event -> combo.setVisible(true));
			combo.showingProperty().addListener(observable -> {
				if (!combo.isShowing()) {
					combo.setVisible(false);
				}
			});
			combo.setOnMouseExited(event -> {
				if (!combo.isShowing()) {
					combo.setVisible(false);
				}
			});
			
			getChildren().setAll(label, combo);
		}
		public String get_currentActiveM(){
			return combo.getSelectionModel().getSelectedItem();
		}
	}


	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			valikud val = new valikud(new String[] {"1","2","3","4", "5"});
			VBox menüü = new VBox(val);
			root.setTop(menüü);
			System.out.println(val.get_currentActiveM());
			
			int RIDA = 3;//Integer.parseInt(val.get_currentActiveM());
			int VEERG = 3;//Integer.parseInt(val.get_currentActiveM());
			
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
			//int D = 0;
			//Label vastus = new Label("Determinant on " + D);
			
			
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
