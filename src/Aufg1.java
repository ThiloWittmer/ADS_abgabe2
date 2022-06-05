import java.util.HashMap;
import java.util.Scanner;

public class Aufg1 {

	static final int[] MUENZEN = {11, 5, 1};
	//static final int[] MUENZEN = {50, 20, 10, 5, 2, 1};

	public static void main(String[] args) {
		int eingabe = eingabe();
		int[][] ergebnis = legeErgebnisArrayAn(eingabe);
		int anzahlMuenze = geldWechseln(eingabe, MUENZEN[0], 0, 0, ergebnis);
		gebeErgebnisAus(anzahlMuenze, ergebnis);
	}

	/**
	 * Gibt die bestmögliche Anzahl der Münzen zurück, für eingegebenen Geldwert. Beim ersten Durchlauf führt den Greedy-Algorithmus durch.
	 * Danach versucht das Ergebnis durch Backtracking zu optimieren, indem die größte verwendete Münze im ersten Durchlauf einmal weniger benutzt wird.
	 * @param eingabe Geldwert
	 * @param startMuenze Mit welcher Münze er anfangen soll
	 * @param grenzeStartMuenze Wie oft die größte Münze maximal verwendet werden soll
	 * @param anzahlMuenze bisheriges Anzahl verwendeter Münzen
	 * @param ergebnis 2-dimensionales Array mit alle Ergebnisse
	 * @return beste Anzahl der Münzen beim Vergleich zwischen den vorherigen und den aktuellen Versuch
	 */
	public static int geldWechseln(int eingabe, int startMuenze, int grenzeStartMuenze, int anzahlMuenze, int[][] ergebnis) {

		HashMap<String, Integer> ergebnisAnalyse;
		int aktEingabe = eingabe;
		int anzahlGleicherMuenze;
		int aktAnzahlMuenzen = 0;
		int pos = 1;

		/* Angepasste Greedy-Algorithmus: berücksichtig bregrentzer Anzahl von größten Münze,
		 *  wenn es beim Optimierungsversuch so gewollt ist */
		for (int muenze : MUENZEN) {
			anzahlGleicherMuenze = 0;
			int frequenzStartMuenze = 0;
			while (eingabe > 0 && muenze <= aktEingabe && muenze <= startMuenze) {
				aktEingabe -= muenze;
				anzahlGleicherMuenze++;
				aktAnzahlMuenzen++;
				frequenzStartMuenze++;
				if (muenze == startMuenze && frequenzStartMuenze == grenzeStartMuenze) break;
			}
			ergebnis[pos][1] = anzahlGleicherMuenze;
			pos++;
		}
		aktualisiereErgebnis(aktAnzahlMuenzen, ergebnis);
		ergebnisAnalyse = ergebnisAnalyse(ergebnis, aktAnzahlMuenzen);

		/* Wenn es nur mit einem Münzenwert möglich ist, dann kann es mit weniger Münze nicht mehr erzielt werden.
		 *  z.B. eingabe = 10 und 2 x Münze 5 verwendet, kann nicht verbessert werden. */
		if (ergebnisAnalyse.get("nurGleicheMuenzeVerwendet") == 1) return aktAnzahlMuenzen;

		// Wenn das Ergebnis nur mit dem kleinsten Münzenwert möglich war, dann kann es nicht verbessert werden.
		if (ergebnisAnalyse.get("groesteMuenze") == 1) return aktAnzahlMuenzen;

		// Wenn die Anzahl der Münzen kleiner ist, dann ist das neue Ergebnis besser
		if (aktAnzahlMuenzen < anzahlMuenze && anzahlMuenze != 0) return aktAnzahlMuenzen;

		// Versuch nur zu optimieren, wenn die Frequenz der größte Münze größer als 1 ist
		// Bei der Optimierung wird die größte Münze 1 Mal weniger verwendet
		int optimierungsVersuch;
		if (ergebnisAnalyse.get("frequenzGrosteMuenze") > 1)
			optimierungsVersuch = geldWechseln(eingabe, ergebnisAnalyse.get("groesteMuenze"), ergebnisAnalyse.get("frequenzGrosteMuenze") - 1, aktAnzahlMuenzen, ergebnis);
		else optimierungsVersuch = aktAnzahlMuenzen;

		return Math.min(optimierungsVersuch, aktAnzahlMuenzen);
	}

	/**
	 * Analysiert für jedes Ergebnis: Welche war die größte Münze? Frequenz der größte Münze? Nur mit einem Münzenwert erzielt?
	 * @param ergebnis 2-dimensionales Array mit alle Ergebnisse
	 * @param anzahlMuenze Damit wird gezielt nur die Spalte ausgewertet, die interessiert
	 * @return HashMap mit Ergebnisse der Analyse: größte Münze und seine Frequenz, und ob es nur mittels einer Münze erzielt wurde
	 */
	private static HashMap<String, Integer> ergebnisAnalyse(int[][] ergebnis, int anzahlMuenze) {
		HashMap<String, Integer> ergebnisAnalyse = new HashMap<>();
		int groesteMuenze = 0;
		int verwendeteMuenzen = 0;
		int frequenzGroesteMuenze = 0;

		for (int zeile = 1; zeile < MUENZEN.length + 1; zeile++) {
			if (ergebnis[zeile][anzahlMuenze] > groesteMuenze) groesteMuenze = ergebnis[zeile][0];
			if (ergebnis[zeile][0] == groesteMuenze) frequenzGroesteMuenze = ergebnis[zeile][anzahlMuenze];
			if (ergebnis[zeile][anzahlMuenze] != 0) verwendeteMuenzen++;
		}
		ergebnisAnalyse.put("groesteMuenze", groesteMuenze);
		ergebnisAnalyse.put("frequenzGrosteMuenze", frequenzGroesteMuenze);
		if (verwendeteMuenzen == 1) ergebnisAnalyse.put("nurGleicheMuenzeVerwendet", 1); // 1 = true
		else ergebnisAnalyse.put("nurGleicheMuenzeVerwendet", 0); // 0 = false
		return ergebnisAnalyse;
	}

	/**
	 * Schiebt das Ergebnis zu der richtigen Spalte, die mit der Anzahl der Münzen indiziert ist
	 * @param anzahlMuenzen Damit er weiß an welche Spalte das Ergebnis hingeschoben werden muss
	 * @param ergebnis 2-dimensionales Array mit alle Ergebnisse
	 */
	private static void aktualisiereErgebnis(int anzahlMuenzen, int[][] ergebnis) {
		// Kopiere das ergebnis zu der richtige Stelle im Ergebnis-Array
		for (int zeile = 1; zeile < MUENZEN.length + 1; zeile++) {
			if(anzahlMuenzen > 1) {
				ergebnis[zeile][anzahlMuenzen] = ergebnis[zeile][1];
				ergebnis[zeile][1] = 0;
			}
		}
	}

	/**
	 * Hilfsmethode für die Console-Eingabe
	 * @return int mit dem Geldwert
	 */
	private static int eingabe(){
		int eingabe = 0;
		Scanner input = new Scanner(System.in);
		boolean isValidNumber;

		System.out.print("Eingabe des Wechselgeldwertes (Ganze Zahl zwischen 1 und 99): ");

		do {
			if (input.hasNextInt()) {
				eingabe = input.nextInt();
				if ((eingabe < 1 || eingabe > 99)) {
					System.out.println("Bitte eine Zahl zwischen 1 und 99 eingeben:");
					isValidNumber = false;
				} else isValidNumber = true;
			} else {
				System.out.print("Bitte eine Zahl eingeben:" );
				isValidNumber = false;
				input.next();
			}

		} while (!isValidNumber);
		input.close();
		return eingabe;
	}

	/**
	 * Legt ein 2-dimensionales Array an, für die Speicherung aller Ergebnisse.
	 * @param eingabe Geldwert wird für die Anzahl der Spalten verwendet
	 * @return angelegtes Array
	 */
	private static int[][] legeErgebnisArrayAn(int eingabe) {
		int[][] ergebnis;

		// Reihe 0 für Anzahl Münzen, Spalte 0 für Münzenwert
		ergebnis = new int[MUENZEN.length + 1][eingabe + 1];

		// Fühle Spalte 0 mit dem Wert jeder Münze
		for (int reihe = 1; reihe <= MUENZEN.length; reihe++) ergebnis[reihe][0] = MUENZEN[reihe - 1];

		// Fühle Reihe 0 mit Zahlen > 0 und <= Eingabe (maximale Anzahl von Münzen möglich)
		for (int spalte = 1; spalte <= eingabe; spalte++) ergebnis[0][spalte] = spalte;

		return ergebnis;
	}

	/**
	 * Formatiert das Ergebnis und gibt es aus
	 * @param anzahlMuenze
	 * @param ergebnis
	 */
	private static void gebeErgebnisAus(int anzahlMuenze, int[][] ergebnis) {
		String firstMsg = anzahlMuenze == 1
				? "\n" + anzahlMuenze + " Münze zurückgeben:"
				: "\n" + anzahlMuenze + " Münzen zurückgeben:";

		System.out.println(firstMsg);

		for (int zeile = 1; zeile <= MUENZEN.length; zeile++) {
			if(ergebnis[zeile][anzahlMuenze] != 0) {
				if(ergebnis[zeile][0] < 10 ) System.out.print("\n" + ergebnis[zeile][anzahlMuenze] + " x " + ergebnis[zeile][0] + "  ");
				else System.out.print("\n" + ergebnis[zeile][anzahlMuenze] + " x " + ergebnis[zeile][0] + " ");
				for (int i = 0; i < ergebnis[zeile][anzahlMuenze]; i++) System.out.print("\uD83E\uDE99");
			}
		}
		System.out.println("");
	}
}
