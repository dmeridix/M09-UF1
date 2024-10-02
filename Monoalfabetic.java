
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Monoalfabetic {
    public final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();

    public static void main(String[] args) {
        System.out.println("Introdueix una cadena per a xifrar:");
        Scanner scanner = new Scanner(System.in);
        String cadena = scanner.nextLine();
        Monoalfabetic mono = new Monoalfabetic();
        char [] mezclado = mono.permutaAlfabet();
        
    }

    public char[] permutaAlfabet(){
        List<Character> permutat = new ArrayList();
        for (char i: abecedari){
            permutat.add(i);
        }
        Collections.shuffle(permutat);
        char [] llistaFinal = new char[permutat.size()];
        for (int j = 0; j < permutat.size(); j++){
            llistaFinal[j] = permutat.get(j);
        }
        return llistaFinal;
    }

    public String xifraMonoAlfa(String cadena, char[] mezclado){
        StringBuilder resultat = new StringBuilder();
        for (char i: cadena.toCharArray()){
            char ch = cadena.charAt(i);
            int pos = i;
            boolean maj = false;
            if (Character.isLetter(i)){
                if (Character.isUpperCase(i)) {
                    maj = true;
                    for (int j = 0; j < mezclado.length;j++){
                        
                    }
                }
            }
            else{
                resultat.append(i);
            }
        }
        return resultat.toString();
    }
    /* public String xifraRotX(String cadena, int des) {
        StringBuilder resultat = new StringBuilder();
        for (char i : cadena.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    for (int j = 0; j < abecedariMaj.length; j++) {
                        if (abecedariMaj[j] == i) {
                            int pos = (j + des) % abecedariMaj.length;
                            resultat.append(abecedariMaj[pos]);
                            break;
                        }
                    }
                } else {
                    for (int j = 0; j < abecedari.length; j++) {
                        if (abecedari[j] == i) {
                            int pos = (j + des) % abecedari.length;
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
    } */

    /* public String desxifraRotX(String cadena, int des) {
        StringBuilder resultat = new StringBuilder();
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
    } */
}
