
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
                "Test 02 Taüll, DÍA, año",
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

    public void initRandom(int contrasenya) {
        ran = new Random(contrasenya);
    }

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
        StringBuilder resultat = new StringBuilder();
        char[] base = permutat;
        char[] desti = abecedari;
        if (!xifra) {
            base = abecedari;
            desti = permutat;
        }
        for (char i : cadena.toCharArray()) {
            permutaAlfabet();
            int pos = i;
            boolean maj = false;
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
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