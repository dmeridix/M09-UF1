
import java.util.Scanner;

public class RotX {
    public final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public final char[] abecedariMaj = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVXYZ".toCharArray();
    
    public static void main(String[] args) {
        System.out.println("Introdueix una cadena per a xifrar:");
        Scanner scanner = new Scanner(System.in);
        String paraula = scanner.nextLine();
        System.out.println("Introdueix un número per desplaçar el caràcter");
        int des = Integer.parseInt(scanner.nextLine());
        RotX rotx = new RotX();
        paraula = rotx.xifraRotX(paraula, des);
        System.out.println(paraula);
        rotx.forcaBrutaRotX(paraula);
    }

    public String xifraRotX(String cadena, int des) {
        StringBuffer resultat = new StringBuffer();
        for (char i : cadena.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    for (int j = 0; j < abecedariMaj.length; j++) {
                        if (abecedariMaj[j] == i) {
                            int pos = j + des;
                            if (pos >= abecedariMaj.length) {
                                pos -= abecedariMaj.length;
                            }
                            resultat.append(abecedariMaj[pos]);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abecedari.length; j++) {
                        if (abecedari[j] == i) {
                            int pos = j + des;
                            if (pos >= abecedari.length) {
                                pos -= abecedari.length;
                            }
                            resultat.append(abecedari[pos]);
                            break;
                        }
                    }
                }
            } else {
                resultat.append(i);
            }
        }
        return resultat.toString();
    }

    public String desxifraRotX(String cadena, int des) {
        StringBuffer resultat = new StringBuffer();
        for (char i : cadena.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    for (int j = 0; j < abecedariMaj.length; j++) {
                        if (abecedariMaj[j] == i) {
                            int pos = (j - des + abecedariMaj.length) % abecedariMaj.length;
                            resultat.append(abecedariMaj[pos]);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abecedari.length; j++) {
                        if (abecedari[j] == i) {
                            int pos = (j - des + abecedari.length) % abecedari.length;
                            resultat.append(abecedari[pos]);
                            break;
                        }
                    }
                }
            } else {
                resultat.append(i);
            }
        }
        return resultat.toString();
    }

    public void forcaBrutaRotX(String cadena) {
        for (int k = 0; k < abecedari.length; k++) {
            System.out.println("Desplaçament " + k + ": " + desxifraRotX(cadena, k));
        }
    }
}
