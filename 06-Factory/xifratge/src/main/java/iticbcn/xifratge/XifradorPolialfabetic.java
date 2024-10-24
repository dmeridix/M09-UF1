package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    public static final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public Random ran;
    public char[] permutat;

    // Inicialitza el random amb el valor que agafem com a parametre 
    public void initRandom(int contrasenya) {
        ran = new Random(contrasenya);
    }

    // Permuta l'alfabet utilitzant el random (ran)
    public void permutaAlfabet() {
        List<Character> mezclar = new ArrayList<Character>();
        for (char i : abecedari) {
            mezclar.add(i);
        }
        Collections.shuffle(mezclar, ran);  
        char[] llistaFinal = new char[mezclar.size()];
        for (int j = 0; j < mezclar.size(); j++) {
            llistaFinal[j] = mezclar.get(j);
        }
        permutat = llistaFinal;  
    }

    public String xifraPoliAlfa(String msg) {
        return xifraCadena(msg, true);  
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        return xifraCadena(msgXifrat, false);  
    }

    public String xifraCadena(String cadena, boolean xifra) {
        // Utilizem el StringBuilder en comptes del StringBuffer ja que és més ràpid 
        StringBuilder resultat = new StringBuilder();
        for (char i : cadena.toCharArray()) {
            // Permuta l'alfabet per cada lletra
            permutaAlfabet();  
            char[] base = permutat;
            char[] desti = abecedari;
            if (!xifra) {
                base = abecedari;
                desti = permutat;
            }
            int pos = i;
            boolean maj = false;
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    // Converteix a minúscula si és majúscula i posem com a valor true a la variable maj
                    maj = true;
                    i = Character.toLowerCase(i);  
                }
                for (int j = 0; j < base.length; j++) {
                    if (base[j] == i) {
                        pos = j;
                        break;
                    }
                }
                char caracterXifrat = desti[pos];
                // Torna a convertir en majúscula si la variable maj es true
                if (maj) {
                    caracterXifrat = Character.toUpperCase(caracterXifrat);  
                }
                resultat.append(caracterXifrat);
            } else {
                resultat.append(i);
            }
        }
        return resultat.toString();
    }
}
