package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class XifradorMonoalfabetic implements Xifrador{
    public static final char[] abecedari = "aáàäbcçdeéèëfghiíìïjklmnñoóòöpqrstuúùüvwxyz".toCharArray();

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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        char[] mezclado = permutaAlfabet();
        try{
            return new TextXifrat(xifraCadena(msg, mezclado, abecedari));
        }
        catch (Exception e){
            throw new ClauNoSuportada("Error al xifrar amb la funcio XifradorMonoalfabetic: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}