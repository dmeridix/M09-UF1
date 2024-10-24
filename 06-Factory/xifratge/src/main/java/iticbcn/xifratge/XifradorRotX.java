package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{
    public static final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public static final char[] abecedariMaj = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVXYZ".toCharArray();

    public String xifraRotX(String cadena, int des) {
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
    }

    public String desxifraRotX(String cadena, int des) {
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
    }

    public void forcaBrutaRotX(String cadena) {
        for (int k = 0; k < abecedari.length; k++) {
            System.out.println("Desplaçament " + k + ": " + desxifraRotX(cadena, k));
        }
    }
}
