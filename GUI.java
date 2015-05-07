import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SplitPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class GUI extends Application {
    static int RIDA = 2;
    static int VEERG = 2;
    static GridPane lahtrid;

    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            ChoiceBox menüü = new ChoiceBox(FXCollections.observableArrayList(
                    "1", "2", "3", "4","5"));
            Label text = new Label("Vali soovitud maatriksi suurus: ");
            text.setFont(Font.font(18));
            SplitPane splitpane = new SplitPane();
            root.setTop(splitpane);
            splitpane.getItems().addAll(text, menüü);
            root.setMargin(splitpane, new Insets(30, 0, 0, 0));



            menüü.getSelectionModel().selectedIndexProperty().addListener(
                    (ov, value, new_value) -> {

                        lahtrid = new GridPane();
                        lahtrid.setAlignment(Pos.CENTER);
                        lahtrid.setVgap(10);
                        lahtrid.setHgap(10);

                        RIDA = (int) new_value + 1;
                        VEERG = (int) new_value + 1;
                        for (int i = 0; i < RIDA; i++) {
                            for (int j = 0; j < VEERG; j++) {
                                lahtrid.add(new TextField("0"), i, j);
                            }
                        }
                        root.setCenter(lahtrid);

                    });


            root.setCenter(lahtrid);
            int D = 0;
            Label vastus = new Label("Determinant on " + D);
            vastus.setFont(Font.font(18));

            root.setBottom(vastus);

            Scene scene = new Scene(root,400,400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    static void nupuMeetod(GridPane lahtrid) {
		
		ArrayList A = new ArrayList();
		for (Node TF : lahtrid.getChildren()){
			int x = lahtrid.getColumnIndex(TF);
			int Y = lahtrid.getRowIndex(TF);
			String s = ((TextField)TF).getText();
			System.out.println(s);
	
		}
		//Maatriks MatA = new Maatriks(ArrayList<ArrayList<Double>> MatA);
		//MatA.arvutaDeterminant();
		
	}
}
