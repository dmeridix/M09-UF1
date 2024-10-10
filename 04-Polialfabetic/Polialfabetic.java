import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Polialfabetic {
    public final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public Random ran;
    public char[] permutat;

    public static void main(String[] args) {
        Polialfabetic poli = new Polialfabetic();
        int clauSecreta = 532747258;  
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, any",
                "Test 03 Peça, Òrrius, Bòvila" };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            poli.initRandom(clauSecreta);  
            msgsXifrats[i] = poli.xifraPoliAlfa(msgs[i]);  
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            poli.initRandom(clauSecreta);  
            String msg = poli.desxifraPoliAlfa(msgsXifrats[i]);  
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

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
