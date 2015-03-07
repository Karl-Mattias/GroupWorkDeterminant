import java.util.ArrayList;

public class Permutatsioon {

    private int suurus;
    private ArrayList<Integer> arvud = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> väljund = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> vahe = new ArrayList<Integer>();
    private boolean [] kasutatud;
    private int koht=0;


    Permutatsioon(int suurus){
        this.suurus=suurus;
        this.väljund = väljund();
        for (int i=1;i<=suurus;i++){
            this.arvud.add(i);
        }
        this.kasutatud = new boolean[suurus];
    }

    // See meetod teeb vajaliku suurusega tühja listi mille sisse permutatsioone koguda
    private ArrayList<ArrayList<Integer>> väljund (){
        ArrayList<ArrayList<Integer>> uus = new ArrayList<ArrayList<Integer>>();
        for (int i = 0;i<factorial(this.suurus);i++){
            ArrayList<Integer> sisemine = new ArrayList<Integer>();
            uus.add(sisemine);
        }
        return uus;
    }

    //See on ainuke public meetod, see tagastab soovitud permutatsiooni
    public ArrayList<ArrayList<Integer>>vastus(){
        return rekursioon(this.arvud,this.vahe,this.väljund,this.kasutatud,0);
    }

    //arvutab faktoriaali
    private static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    //rekursiivne meetod, mis leiab soovitud suurusega permutatsiooni
    private ArrayList<ArrayList<Integer>> rekursioon(ArrayList<Integer> arvud,ArrayList<Integer> vahe,
                                                     ArrayList<ArrayList<Integer>> väljund, boolean[] kasutatud, int tase){
        if (tase == arvud.size()){
            //tase märgib kui mitmes arv ühte permutatsiooni on lisatud.
            //kui permutatsioon on valmis, siis suurendab kohta, mis näitab mitmendat permutatsiooni täidetakse
            this.koht++;
            return väljund;
        }

        //tsükkel, mis permutatsiooni täidab
        for (int i=0;i<arvud.size();i++){
            //kasutatud on boolean list, milles on kohal i true, kui i's element on juba permutatsioonis olemas
            if (kasutatud[i]) continue;

            //lisab väljundisse ja vahesse arvu, märgib kasutatud listis ära, et kasutatud
            väljund.get(this.koht).add(arvud.get(i));
            vahe.add(arvud.get(i));
            kasutatud[i] = true;

            //rekursioon, et täita kõikvõimalikel viisidel ülejäänusid kohtasid permutatsioonides
            rekursioon(arvud, vahe, väljund, kasutatud, tase + 1);

            //märgib kasutuse listis vastupidiseks ja siis lisab vahe listi sisu (v.a. viimane element)
            //järgmisesse permutatsiooni, sest muudetakse permutatsiooni lõpust ainult nii vähe elemente kui võimalik
            kasutatud[i]=false;
            vahe.remove(vahe.size()-1);
            if(this.koht != factorial(arvud.size())){
                väljund.get(this.koht).clear();
                väljund.get(this.koht).addAll(vahe);
            }



        }

        return väljund;

    }
}