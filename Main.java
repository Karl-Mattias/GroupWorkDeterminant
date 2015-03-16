import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		// tervitus
		String message = "Tere tulemast kasutama n-mõõtmelise ruutmaatriksi determinandi arvutamise programmi.";
		JOptionPane.showMessageDialog(null, message,"Tere!",1);
		// kuidas soovite programmi kasutada? 1) Random 2) sisestate andmed
		message = "Valige '1' Demo programmiks,\n '2' Andmete sisestamiseks.";
		int valik = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Valik", 1));
		
		if (valik == 1) {
			Demo();
		} else if (valik == 2) {
			ArrayList<ArrayList<Double>> andmed= sisestamine();
			
			Maatriks X = new Maatriks(andmed);
			double tulemus = X.arvutaDeterminant();
			
			String esitus = "";
			for (ArrayList<Double> n : andmed){
				esitus += n+"\n";
			}
			
			JOptionPane.showMessageDialog(null, esitus + "maatriksi determinant on " + tulemus ,"Vastus",1);
		}
        else{
            while (valik !=1 && valik != 2){
                valik = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Valik", 1));
            }
        }
	}
	
	public static ArrayList<ArrayList<Double>> sisestamine(){
		ArrayList<ArrayList<Double>> loodav = new ArrayList<ArrayList<Double>>();
		String arv = JOptionPane.showInputDialog(null, "Sisesta maatriksi ridade arv", "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
		int suurus = Integer.parseInt(arv);
		
		for (int i = 0; i<suurus; i++){
			
			ArrayList<Double> Rida = new ArrayList<Double>();		
			String teade = "Palun sisesta "+(i+1)+". rea arvud tühikuga eraldatult.";
			String sisse = JOptionPane.showInputDialog(null, teade, "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
			
			String[] osad = sisse.split(" ");
			while(osad.length!= suurus){
				teade = "Vigane sisestus, reas peab olema "+suurus+" arvu, sisesta "+ (i+1)+". rida uuesti.";
				sisse = JOptionPane.showInputDialog(null, teade, "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
				osad = sisse.split(" ");
			}
			for (String n: osad){

				Rida.add(Double.parseDouble(n));
			}
			
			loodav.add(Rida);
		}
		
		return loodav;		
	}
	
	public static void Demo() {
		ArrayList<ArrayList<Double>> demoAndmed = new ArrayList<ArrayList<Double>>();
		
		//loob demo maatriksi
		int suurus = Integer.parseInt(JOptionPane.showInputDialog(null, "Sisesta  demo maatriksi ridade arv", "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE));
		for (int i = 0; i < suurus; i++){
			ArrayList<Double> rida = new ArrayList<Double>();
			
			for (int j = 0; j <suurus; j++){
				//random double d
				Random r = new Random();
				Double d = (double)Math.round((r.nextDouble())*10);
				rida.add(d);
			}
			demoAndmed.add(rida);
		}
		
		
		//näita tulemust
		Maatriks demoMaatriks = new Maatriks(demoAndmed);
		double tulemus = demoMaatriks.arvutaDeterminant();
		String esitus = "";
		for (ArrayList<Double> n : demoAndmed){
			esitus += n+"\n";
		}
		JOptionPane.showMessageDialog(null, esitus + "maatriksi determinant on " + tulemus ,"Vastus",1);
	}

}
