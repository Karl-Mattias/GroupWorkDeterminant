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
// või Maatriks AA = new Maatriks(); //sisestus lausega loomiseks
//AA.arvutaDeterminant();

public class Maatriks {
	
	private int suurus; //Ruutmaatriksi mõõde
	public ArrayList<ArrayList<Double>> MatA= new ArrayList<ArrayList<Double>>(); //Maatriks ise
	
	
	//Generaator, millesse antakse maatriks valmis kujul
	Maatriks( ArrayList<ArrayList<Double>> MatA){      
		this.suurus = MatA.size();
		this.MatA = MatA;
	}
	
	//Alustab determinandi arvutust
	double arvutaDeterminant(){ 
		//Leaib kõik permutatsioonid
		Permutatsioon Perm = new Permutatsioon(suurus);
		ArrayList<ArrayList<Integer>> A = new ArrayList<ArrayList<Integer>>();
		
		A = Perm.vastus();
		double sum = 0;	
		
		//Viib läbi arvutused iga permutatsiooniga
		for (ArrayList<Integer> n : A){ 
			
			double osaLiige = 1.0;
			for (int i = 0; i< MatA.size(); i++ )  {
				
				ArrayList<Double> rida = MatA.get(i);
				int o = (int) n.get(i)-1;
				osaLiige = osaLiige * (double) rida.get(o);
			}
			
			//Liidab summale osaliikme vastava märgiga
			sum += osaLiige*sign(n);
		}
		
		//Tagastab determinandi
		return sum;
	}
	
	//arvutab inversioonid
	double inversioon(ArrayList<Integer> PermR){
		int inver =0;
		for (int i =0;i<PermR.size();i++){
			for (int j= i+1;j<PermR.size(); j++){
			if (PermR.get(i) > PermR.get(j)){
				inver+=1;
				}
			}
		}
		return inver;
	}
	
	//otsustab märgi
	double sign(ArrayList<Integer> PermR){
		double x= inversioon(PermR);
		if (x%2.0==0 || x==0){
			return 1.0;
		}else return -1.0;
	}
}
