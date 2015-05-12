package application;
	
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUI extends Application {
	static int suurus; //Maatriksi suurus
	static GridPane lahtrid;
	static String FILENAME = "MatrixLogFile.dat";
	
	public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            //Rippmenüü maatriksi suuruse valimiseks
            ChoiceBox menüü = new ChoiceBox<>(FXCollections.observableArrayList(
                    "1", "2", "3", "4","5", "6", "7", "8", "9", "10"));
            Label text = new Label("Vali soovitud maatriksi suurus: ");
            text.setFont(Font.font(18));
            SplitPane splitpane = new SplitPane();
            root.setTop(splitpane);
            splitpane.getItems().addAll(text, menüü);
            splitpane.setStyle("-fx-background-color:transparent;");
            BorderPane.setMargin(splitpane, new Insets(30, 0, 0, 0));

            //Loeb rippmenüüst valiku ja loob selle põhjal tühja maatriksi/ruudustiku
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

            //Nupud arvutamiseks ja taastamiseks, ning lahter vastuse kuvamiseks
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
            
            Button nupp2 = new Button("Taasta");
            nupp2.setAlignment(Pos.BOTTOM_RIGHT);
            nupp2.setOnAction(EventHandler -> {
            	try {
            		täidaLahtridFailist();
            	} catch (Exception e) {}
            });
            
            splitpane2.getItems().addAll(nupp, vastus,nupp2);
            splitpane2.setStyle("-fx-background-color:transparent;");
            root.setBottom(splitpane2);

            Scene scene = new Scene(root,600,600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Determinandi arvutamine");

            //Kuular programmi sulgemiseks
            primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            	if (event.getCode() == KeyCode.ESCAPE){
            		System.exit(0);
            	}
            });
            
            primaryStage.show();
        } catch(Exception e) {
            teade(e.toString());
        }
    }	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Determinandi arvutamise nupu meetod
	static double nupuMeetod(GridPane lahtrid) {
		kirjutaFaili();
		ArrayList<ArrayList<Double>> peamine = hangiAndmed();
		Maatriks MatA = new Maatriks(peamine);
		return MatA.arvutaDeterminant();
	}
	
	//Harilik veateade
	static void teade(String tekst){
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
	
	//Kirjutab hetkeseisu faili
	static void kirjutaFaili () {
		ArrayList<ArrayList<Double>> peamine = hangiAndmed();
		File file = new File(FILENAME);
		try ( ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(file));) {
			OOS.writeObject(peamine);
		} catch (Exception e) {
			String tekst = "Probleem andmete faili kirjutamisel.";
      		teade(tekst);
		}
	}
	
	//Loeb failist viimase (ainukese) seisu
	static ArrayList<ArrayList<Double>> loeAndmedFailist() {
		ArrayList<ArrayList<Double>> peamine = new ArrayList<ArrayList<Double>>();
		File file = new File(FILENAME);
		if (file.exists()) {
			System.out.println("Faili lugemine");
			try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(file));)
			{
				while (OIS.available() == 0) {
					ArrayList<ArrayList<Double>> loe = (ArrayList<ArrayList<Double>>)(OIS.readObject());
					peamine = loe;
					System.out.println(loe);
				}//võtame viimase
			
			} catch (EOFException e) {}
			catch (Exception e) {
				System.out.println(e);
				String tekst = "Probleem failist lugemisel.";
          		teade(tekst);
			}	
		}
		
		return peamine;
	}
	
	//Hangib andmed ruudustikust
	static ArrayList<ArrayList<Double>> hangiAndmed() {
		ArrayList<ArrayList<Double>> peamine = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < suurus; i++){
			peamine.add(new ArrayList<Double>());
		}
		for (Node TF : lahtrid.getChildren()){
			int y = GridPane.getRowIndex(TF);
			String s = ((TextField)TF).getText();
			Double d = Double.parseDouble(s);
			peamine.get(y).add(d);
		}
		return peamine;
	}
	
	//Täidab ruudustiku
	static void täidaLahtridFailist () {
		ArrayList<ArrayList<Double>> peamine = loeAndmedFailist();
		for (int i = 0; i < peamine.size(); i++) {
			ArrayList<Double> D = peamine.get(i);
			for (int j = 0; j < D.size(); j++) {
				double d = D.get(j);
				((TextField)lahtrid.getChildren().get((i*D.size()) + j)).setText(Double.toString(d));
			}
		}
			
	}
}
