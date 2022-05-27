
public class Aufg1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int besteAnzahl;
		String[] besteAusgabe = new String[1];
		int[] muenzen = {1,5,11};
		int eingabe;
		int index = 0;
		int anzahlMuenzen = 1;
		int carry = 0;
		
		
		
	}
	
	public static boolean loesungFinden(int besteAnzahl, String[] besteAusgabe, int eingabe, int[] muenzen, int index, int anzahlMuenzen, int carry, int carryAnzahl) {
		String aktAusgabe;
		int aktAnzahl;
		int sum;
		
		if(muenzen[index]*anzahlMuenzen+carry > eingabe) {
			return false;
		}

		aktAnzahl = carryAnzahl;

		return false;
	}

}
