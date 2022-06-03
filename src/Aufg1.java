import java.util.Scanner;

public class Aufg1 {

	public static void main(String[] args) {
		int[] besteAnzahl = new int[1];
		String[] ausgaben = {"", "", ""}; //[0] ist beste und [1] ist carry und [2] is aktuelle Ausgabe
		int[] muenzen = {1,2,5,10,20,50};
		int eingabe = 0;
		int index = muenzen.length-1;
		int anzahlMuenzen = 0;
		int carry = 0;
		int carryAnzahl = 0;
		Scanner input = new Scanner(System.in);
		boolean isValidNumber;

		System.out.println("Eingabe des Wechselgeldwertes (Ganze Zahl zwischen 1 und 99):");

		do {
			if (input.hasNextInt()) {
				eingabe = input.nextInt();
				if ((eingabe < 1 || eingabe > 99)) {
					System.out.println("Bitte eine Zahl zwischen 1 und 99 eingeben:");
					isValidNumber = false;
				} else isValidNumber = true;
			} else {
				System.out.println("Bitte eine Zahl eingeben:");
				isValidNumber = false;
				input.next();
			}

		} while (!isValidNumber);
		
		input.close();
		besteAnzahl[0] = eingabe;

		loesungFinden(besteAnzahl, ausgaben, eingabe, muenzen, anzahlMuenzen, index, carry, carryAnzahl);

		System.out.println("Beste Lösung: " + ausgaben[0]);
		System.out.println("Mit " + besteAnzahl[0] + " Münzen.");

	}
	
	public static boolean loesungFinden(int[] besteAnzahl, String[] ausgaben, int eingabe, int[] muenzen, int anzahlMuenzen, int index, int carry, int carryAnzahl) {
		int aktAnzahl;
		int sum;
		
		//wenn die Eingabe kleiner als die zweitkleinste Münze ist, wird direkt der greedy ausgeführt
		if (eingabe < muenzen[1]) {
			sum = 0;
			besteAnzahl[0] = greedy(1, (eingabe - sum), muenzen, ausgaben);
			ausgaben[0] = ausgaben[2];
			return true;
		}

		//ruft Methode rekursiv bis zur zweitkleinsten Münze auf, um dann die Münze Schritt für Schritt zu erhöhen
		if(index > 1){
			loesungFinden(besteAnzahl, ausgaben, eingabe, muenzen, 0, index-1, carry, carryAnzahl);
		}

		//gibt true zurück, wenn die maximale Anzahl der aktuellen Münze für die aktuelle Eingabe erreicht wurde
		if(muenzen[index]+carry > eingabe) {
			return true;
		}

		aktAnzahl = carryAnzahl;

		switch(anzahlMuenzen){
			case 0:
				sum = carry;
				ausgaben[2] = ausgaben[1];
			break;
			default: 
				sum = muenzen[index] + carry;
				ausgaben[2] = ausgaben[1] + muenzen[index] + " + ";
				carry = sum;
				ausgaben[1] = ausgaben[2];
		}

		aktAnzahl += greedy(index, (eingabe - sum), muenzen, ausgaben);

		//Vergleich zur bisher besten Variante
		if(aktAnzahl < besteAnzahl[0]){
			besteAnzahl[0] = aktAnzahl;
			ausgaben[0] = ausgaben[2];
		}

		//wenn true backtracken zur nächst größeren Münze und dessen Anzahl Schritt für Schritt erhöhen
		if (loesungFinden(besteAnzahl, ausgaben, eingabe, muenzen, anzahlMuenzen+1, index, carry, carryAnzahl+1)) {
			ausgaben[1] = "";
			return true;
		}

		return false;
	}

	/***
	 * Greedy Algorithmus
	 * @param index alle Münzen unter diesem index werden beachtet
	 * @param value Wert der erreicht werden soll
	 * @param muenzen arr der verfügbaren Münzen
	 * @param ausgabe erweitert den aktuellen Ausgabe-String 
	 * @return Anzahl der verwendeten Münzen
	*/
	public static int greedy(int index, int value, int[] muenzen, String[] ausgabe) {
		int anzahl = 0;
		int sum = 0;
		for(int i = index-1; i >= 0; i--) {
			while((value-sum) >= muenzen[i]) {
				sum += muenzen[i];
				ausgabe[2] += muenzen[i] + " + ";
				anzahl++;
			}
		}
		return anzahl;
	}
}
