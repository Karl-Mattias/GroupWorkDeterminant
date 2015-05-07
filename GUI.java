import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class GUI extends Application {
    private static int suurus;
    private static GridPane lahtrid;

    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            ChoiceBox menüü = new ChoiceBox<>(FXCollections.observableArrayList(
                    "1", "2", "3", "4","5"));
            Label text = new Label("Vali soovitud maatriksi suurus: ");
            text.setFont(Font.font(18));
            SplitPane splitpane = new SplitPane();
            root.setTop(splitpane);
            splitpane.getItems().addAll(text, menüü);
            BorderPane.setMargin(splitpane, new Insets(30, 0, 0, 0));



            menüü.getSelectionModel().selectedIndexProperty().addListener(
                    (ov, value, new_value) -> {

                        lahtrid = new GridPane();
                        lahtrid.setAlignment(Pos.CENTER);
                        lahtrid.setVgap(10);
                        lahtrid.setHgap(10);

                        suurus = (int) new_value + 1;
                        for (int i = 0; i < suurus; i++) {
                            for (int j = 0; j < suurus; j++) {
                                lahtrid.add(new TextField("0"), j, i);
                            }
                        }
                        root.setCenter(lahtrid);

                    });
            SplitPane splitpane2 = new SplitPane();
            BorderPane.setMargin(splitpane2, new Insets(0, 0, 30, 0));

            Label vastus = new Label("-");
            vastus.setFont(Font.font(18));

            Button nupp = new Button("Arvuta determinant.");
            nupp.setOnAction(EventHandler->{
                try{
              		vastus.setText("Determinant on " + Double.toString(nupuMeetod(lahtrid)));
              	} catch (NullPointerException e) {
              		String tekst = "Vali eelnevalt maatriksi suurus rippmenüüst.";
              		teade(tekst);
              	} catch (java.lang.NumberFormatException e) {
              		String tekst = "Vigane andmete sisestus - kontrolli andmeid!";
              		teade(tekst);
              	}
            });

            splitpane2.getItems().addAll(nupp, vastus);
            root.setBottom(splitpane2);

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

    static Double nupuMeetod(GridPane lahtrid) {
        ArrayList<ArrayList<Double>> peamine = new ArrayList<>();
        for (int i = 0; i < suurus; i++){
            peamine.add(new ArrayList<>());
        }
        for (Node TF : lahtrid.getChildren()){
            int y = GridPane.getRowIndex(TF);
            String s = ((TextField)TF).getText();
            Double d = Double.parseDouble(s);
            peamine.get(y).add(d);
        }

        Maatriks MatA = new Maatriks(peamine);
        return MatA.arvutaDeterminant();
    }
    
    void teade(String tekst){
		Stage teateAken = new Stage();
        
        Label label = new Label(tekst);
        Button okButton = new Button("Ok");

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                teateAken.hide();
            }
        });
        FlowPane pane = new FlowPane(10, 10);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(okButton);


        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, pane);

         Scene stseen2 = new Scene(vBox);
         teateAken.setScene(stseen2);
         teateAken.setTitle("Viga");
         teateAken.show();
     
	}
}
