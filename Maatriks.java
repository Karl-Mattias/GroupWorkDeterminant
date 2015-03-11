import java.util.ArrayList;
import javax.swing.JOptionPane;

//Main klassis välja kutsed
//ArrayList c = new ArrayList();
//ArrayList r1 = new ArrayList();
//ArrayList r2 = new ArrayList();
//r1.add(5.0);
//r1.add(3.0);
//r2.add(1.0);
//r2.add(1.0);
//c.add(r1);
//c.add(r2);
//Maatriks AA = new Maatriks(Maatriks kujul ArrayList ArrayList Double); 
// või Maatriks AA = new Maatriks("suvaline sõne"); //sisestus lausega loomiseks
//AA.arvutaDeterminant();

public class Maatriks {
	
	private int suurus;
	public ArrayList<ArrayList<Double>> MatA= new ArrayList<ArrayList<Double>>();
	
	Maatriks( ArrayList<ArrayList<Double>> MatA){      
		this.suurus = MatA.size()-1;
		this.MatA = MatA;
	}
	
	Maatriks(String s){//s ei oma tähtsust, lihtsalt selleks, et eristada
		String arv = JOptionPane.showInputDialog(null, "Sisesta maatriksi suurus", "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
		this.suurus = Integer.parseInt(arv);
		
		for (int i = 0; i<suurus; i++){ //Küsib rea kaupa andmeid
			
			ArrayList<Double> Rida = new ArrayList<Double>();		
			String teade = "Palun sisesta "+(i+1)+". rea arvud kujul 1.0 eraldatud tühikuga.";
			String Sisse = JOptionPane.showInputDialog(null, teade, "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
			
			String[] osad = Sisse.split(" ");
			for (String n: osad){

				Rida.add(Double.parseDouble(n));
			}
			
			MatA.add(Rida);
		}
	}
	
	//Alustab determinandi arvutust
	double arvutaDeterminant(){  // siia panna viit permutatsioonide arvutusele?
		Permutatsioon Perm = new Permutatsioon(suurus);
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		
		A = Perm.vastus();
		double sum = 0;											//Teine võimalus on välja kutsudes anda kaasa
		
		for (ArrayList<Integer> n : A){                                  //iga permutatsiooni kohta
			
			double osaLiige = 1.0;
			for (int i = 0; i< MatA.size(); i++ )  {            //Selles osas tuleb eelneva <double> seoses vead
				
				ArrayList<Double> rida = MatA.get(i);
				int o = (int) n.get(i)-1;
				osaLiige = osaLiige * (double) rida.get(o);              //ei oska korrutada double * double 
			}
			
			sum += osaLiige*sign(n);                            //arvutab märgi
		}
		
		return sum;                                             //tagastab determinandi
	}
	double substitutsioon(ArrayList<Integer> PermR){            //arvutab substitutsioonid(pole veel katsetanud)
		int subst =0;
		for (int i =0;i<PermR.size();i++){
			for (int j= i+1;j<PermR.size(); j++){
			if (PermR.get(i) > PermR.get(i+j)){
				subst+=1;
				}
			}
		}
		return subst;
		
		
	}
	
	double sign(ArrayList<Integer> PermR){                     //otsustab märgi
		double x= substitutsioon(PermR);
		if (x%2.0==0 || x==0){
			return 1.0;
		}else return -1.0;
	}
}
