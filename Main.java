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
			
			Maatriks X = new Maatriks(andmed);
			double tulemus = X.arvutaDeterminant();
			
			String esitus = "";
			for (ArrayList<Double> n : andmed){
				esitus += n+"\n";
			};
			
			JOptionPane.showMessageDialog(null, esitus + "maatriksi determinant on " + tulemus ,"Tere!",1);
		}
    }
    public static ArrayList<ArrayList<Double>> sisestamine(){
		ArrayList<Double> Rida = new ArrayList<Double>();		
			String teade = "Palun sisesta "+(i+1)+". rea arvud tühikuga eraldatult.";
			String sisse = JOptionPane.showInputDialog(null, teade, "Andmete sisestamine",JOptionPane.QUESTION_MESSAGE);
			
			String[] osad = sisse.split(" ");
			while(osad.length!= suurus){
				teade = "Vigane sisestus, sisesta "+ (i+1)+". rida uuesti.";
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
		
		//genereeri demo maatriks
		int suurus = 4;
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
		
		
		//näita tu{lemust
		Maatriks demoMaatriks = new Maatriks(demoAndmed);
		double tulemus = demoMaatriks.arvutaDeterminant();
		String esitus = "";
		for (ArrayList<Double> n : demoAndmed){
			esitus += n+"\n";
		};
		JOptionPane.showMessageDialog(null, esitus + "maatriksi determinant on " + tulemus ,"Tere!",1);
	}
}
