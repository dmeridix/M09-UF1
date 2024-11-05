package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    public static final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();
    public static final char[] abecedariMaj = "AÁÀÄBCÇDEÉÈËFGHIÍÌÏJKLMNÑOÓÒÖPQRSTUÚÙÜVXYZ".toCharArray();

    private int validarClau(String clau) throws ClauNoSuportada {
        try {
            int valor = Integer.parseInt(clau);
            if (valor < 0 || valor > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    // Implementación de xifra(String, String) según la interfaz Xifrador
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = validarClau(clau);
        return new TextXifrat(xifraRotX(msg, desplaçament).getBytes());
    }

    // Implementación de desxifra(TextXifrat, String) según la interfaz Xifrador
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = validarClau(clau);
        return desxifraRotX(new String(xifrat.getBytes()), desplaçament);
    }

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
