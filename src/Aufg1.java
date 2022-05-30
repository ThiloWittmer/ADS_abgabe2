import java.util.Scanner;

public class Aufg1 {

	public static void main(String[] args) {
		int[] besteAnzahl = new int[1];
		String[] besteAusgabe = {"", ""}; //[0] ist beste und [1] ist carry
		int[] muenzen = {1,5,11};
		int eingabe;
		int index = muenzen.length-1;
		int anzahlMuenzen = 0;
		int carry = 0;
		int carryAnzahl = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("Eingabe: ");
		eingabe = input.nextInt();
		besteAnzahl[0] = eingabe;

		loesungFinden(besteAnzahl, besteAusgabe, eingabe, muenzen, anzahlMuenzen, index, carry, carryAnzahl);

		System.out.println("Beste Lösung: " + besteAusgabe[0]);
		System.out.println("Mit " + besteAnzahl[0] + " Münzen.");

	}
	
	public static boolean loesungFinden(int[] besteAnzahl, String[] besteAusgabe, int eingabe, int[] muenzen, int anzahlMuenzen, int index, int carry, int carryAnzahl) {
		String aktAusgabe;
		int aktAnzahl;
		int sum;
		
		if(index > 1){
			loesungFinden(besteAnzahl, besteAusgabe, eingabe, muenzen, 0, index-1, carry, carryAnzahl);
		}

		if(muenzen[index]+carry > eingabe) {
			return true;
		}

		aktAnzahl = carryAnzahl;

		switch(anzahlMuenzen){
			case 0:
				sum = carry;
				aktAusgabe = besteAusgabe[1];
			break;
			default: 
				sum = muenzen[index] + carry;
				aktAusgabe = besteAusgabe[1] + muenzen[index] + " + ";
				carry = sum;
				besteAusgabe[1] = aktAusgabe;
		}

		for(int i = index-1; i >= 0; i--) {
			while((eingabe - sum) >= muenzen[i]) {
				sum += muenzen[i];
				aktAusgabe += muenzen[i] + " + ";
				aktAnzahl++;
			}
		}

		if(aktAnzahl < besteAnzahl[0]){
			besteAnzahl[0] = aktAnzahl;
			besteAusgabe[0] = aktAusgabe;
		}

		if (loesungFinden(besteAnzahl, besteAusgabe, eingabe, muenzen, anzahlMuenzen+1, index, carry, carryAnzahl+1)) {
			besteAusgabe[1] = "";
			return true;
		}

		return false;
	}
}
