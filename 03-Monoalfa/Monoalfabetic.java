
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

        String cadenaCifrada = mono.xifraMonoAlfa(cadena, mezclado);
        System.out.println("Cadena xifrada: " + cadenaCifrada);
        
        String cadenaDesxifrada = mono.desxifraMonoAlfa(cadenaCifrada, mezclado);
        System.out.println("Cadena desxifrada: " + cadenaDesxifrada);
        scanner.close();
    }

    public char[] permutaAlfabet(){
        List<Character> permutat = new ArrayList<Character>();
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

    public String xifraCadena(String cadena, char[] mezclado, char[] abecedari){
        StringBuilder resultat = new StringBuilder();
        for (char i: cadena.toCharArray()){
            int pos = i;
            boolean maj = false;
            if (Character.isLetter(i)){
                if (Character.isUpperCase(i)) {
                    maj = true;
                    i = Character.toLowerCase(i);
                }
                for (int j = 0; j < abecedari.length;j++){
                    if (abecedari[j] == i){
                        pos = j;
                        break;
                    }
                }
                char caracterXifrat = mezclado[pos];
                if (maj){
                    caracterXifrat = Character.toUpperCase(caracterXifrat);
                }
                resultat.append(caracterXifrat);
            }
            else{
                resultat.append(i);
            }
        }
        return resultat.toString();
    }
    public String xifraMonoAlfa(String cadena, char[] mezclado){
        return xifraCadena(cadena, mezclado, abecedari);
    }
    public String desxifraMonoAlfa(String cadena, char[] mezclado){
        return xifraCadena(cadena, abecedari, mezclado);
    }
}