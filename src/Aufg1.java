
public class Aufg1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int besteAnzahl;
		String[] besteAusgabe = new String[2]; //[0] ist beste und [1] ist carry
		int[] muenzen = {1,5,11};
		int eingabe;
		int index = 0;
		int anzahlMuenzen = 0;
		int carry = 0;
		int carryAnzahl = 0;
		
		
		
	}
	
	public static boolean loesungFinden(int besteAnzahl, String[] besteAusgabe, int eingabe, int[] muenzen, int anzahlMuenzen, int index, int carry, int carryAnzahl) {
		String aktAusgabe;
		int aktAnzahl;
		int sum;
		
		if(muenzen[index]*anzahlMuenzen+carry > eingabe) {
			anzahlMuenzen = 0;
			loesungFinden(besteAnzahl, besteAusgabe, eingabe, muenzen, anzahlMuenzen, index-1, carry, carryAnzahl);
		}

		aktAnzahl =  + carryAnzahl;
		
		sum = muenzen[index] + carry;
		aktAusgabe = besteAusgabe[1] + muenzen[index] + " + ";

		for(int i = index-1; i >= 0; i--) {
			while((eingabe - sum) >= muenzen[i]) {
				sum += muenzen[i];
				aktAusgabe += muenzen[i] + " + ";
				aktAnzahl++;
			}
		}

		if(aktAnzahl < besteAnzahl){
			besteAnzahl = aktAnzahl;
			besteAusgabe[0] = aktAusgabe;
		}

		return false;
	}
}
