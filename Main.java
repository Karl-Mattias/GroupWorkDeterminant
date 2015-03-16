class Main{
    public static void main(String[] args){

        Permutatsioon tulemus = new Permutatsioon(4);
        System.out.println(tulemus.vastus());
        
        String message = "Tere tulemast kasutama n-mõõtmelise ruutmaatriksi determinandi arvutamise programmi.";
		JOptionPane.showMessageDialog(null, message,"Tere!",1);
		message = "Valige '1' Demo programmiks,\n '2' Andmete sisestamiseks,\n '3' Eelnevalt sisestatud andmetega programm.";
		int valik = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Valik", 1));
		
		if (valik == 1) {
			
			Demo();
		} else if (valik == 2) {
			ArrayList<ArrayList<Double>> andmed = new ArrayList<ArrayList<Double>>();
			andmed = sisestamine();
			for (ArrayList<Double> n : andmed){
				System.out.println(n);
			}
			Maatriks X = new Maatriks(andmed);
			System.out.println(X.arvutaDeterminant());
		}
    }
    public static ArrayList<ArrayList<Double>> sisestamine(){
		ArrayList<ArrayList<Double>> loodav = new ArrayList<ArrayList<Double>>();
		String arv = JOptionPane.showInputDialog(null, "Sisesta maatriksi suurus", "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
		int suurus = Integer.parseInt(arv);
		
		for (int i = 0; i<suurus; i++){
			
			ArrayList<Double> Rida = new ArrayList<Double>();		
			String teade = "Palun sisesta "+(i+1)+". rea arvud kujul 1.0 eraldatud tühikuga.";
			String Sisse = JOptionPane.showInputDialog(null, teade, "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
			
			String[] osad = Sisse.split(" ");
			for (String n: osad){

				Rida.add(Double.parseDouble(n));
			}
			
			loodav.add(Rida);
		}
		
		return loodav;		
	}
	
	public static void Demo() {
		ArrayList<ArrayList<Double>> demoAndmed = new ArrayList<ArrayList<Double>>();
		
		//genereeri demo maatriks
		int suurus = 4;
		for (int i = 0; i < suurus; i++){
			ArrayList<Double> rida = new ArrayList<Double>();
			
			for (int j = 0; j <suurus; j++){
				//random double d
				Random r = new Random();
				Double d = r.nextDouble();
				rida.add(d);
			}
			demoAndmed.add(rida);
		}
		
		//Näita maatriksi
		for (ArrayList<Double> n: demoAndmed){
			System.out.println(n);
		}
		//näita tu{lemust
		Maatriks demoMaatriks = new Maatriks(demoAndmed);
		System.out.println(demoMaatriks.arvutaDeterminant());
	}
	
	
}
