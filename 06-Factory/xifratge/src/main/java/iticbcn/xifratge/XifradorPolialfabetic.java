package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    public static final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    private Random ran;
    private char[] permutat;

    // Inicializa el generador de números aleatorios con la clave convertida a long
    private void initRandom(String clau) throws ClauNoSuportada {
        try {
            long seed = Long.parseLong(clau);
            ran = new Random(seed);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    // Permuta el alfabeto usando el generador de números aleatorios (ran)
    public void permutaAlfabet() {
        List<Character> mezclar = new ArrayList<>();
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
        for (char i : cadena.toCharArray()) {
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

    // Implementación de xifra(String, String) según la interfaz Xifrador
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        initRandom(clau); // Inicializa el random con la clave validada
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    // Implementación de desxifra(TextXifrat, String) según la interfaz Xifrador
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        initRandom(clau); // Inicializa el random con la clave validada
        return desxifraPoliAlfa(new String(xifrat.getBytes()));
    }
}
