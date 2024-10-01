public class Rot13 {
    public char[] abecedari= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','u','v','w','x','y','z'};
    public char[] abecedariCOnt= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','U','V','W','X','Y','Z'};
    public String resultat = "";
    public int pos = 0;
    public int cont = 0;
    public static void main(String[] args) {
        System.out.println("Introdueix una cadena per xifrar");
        String paraula = Entrada.readLine();
        Rot13 rot13 = new Rot13();
        System.out.println(rot13.xifraRot13(paraula));
    }
    public String xifraRot13(String cadena){
        for (char i: cadena.toCharArray()){
            if (Character.isLetter(i)){
                if (Character.isUpperCase(i) == true){
                    for (int j = 0; abecedariCOnt.length; j++){
                        if ((j + 13)> 27){
                            resultat = resultat + j(j+13);
                        }
                        else{
                            resultat = resultat + j(j+13);
                        }
                    }
                    resultat = resultat + abecedariCOnt[pos];
                }
                else {
                    for (int j = 0; abecedariCOnt.length; j++){
                        cont++;
                        if (e == i){
                            pos = cont + 13;
                            if (pos > abecedari.length){
                                pos = abecedari.length - pos;
                            }
                            break;
                        }
                    }
                    resultat = resultat + abecedari[pos];
                }
            }
            else{
                resultat = resultat + i;
            }
        }
        return resultat;
    }
    public String desxifraRot13(String cadena){
        for (char i: cadena.toCharArray()){
            if (Character.isUpperCase(i) == true){
                for (char e: abecedari){
                    if (e == i){
                        pos =  13 - abecedari[e];
                    }
                }
                resultat = resultat + abecedariCOnt[pos];
            }
            else {
                for (char e: abecedariCOnt){
                    if (e == i){
                        pos = 13 - abecedariCOnt[e];
                        break;
                    }
                }
                resultat = resultat + abecedariCOnt[pos];
            }
        }
        return resultat;
    }

}
