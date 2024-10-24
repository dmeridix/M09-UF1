package iticbcn.xifratge;

import javax.crypto.*;
import java.security.*;
import java.util.Arrays;


import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_XifradorAES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];    

    public byte[] xifraXifradorAES(String msg, String password) 
    throws Exception {
            //Obtenir els bytes de l’String
            byte[] separat = msg.getBytes("UTF-8");

            // Genera IvParameterSpec
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Genera hash
            MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hash = digest.digest(password.getBytes("UTF-8"));

            // Encrypt.
            byte[] primeraPart = Arrays.copyOf(hash, MIDA_IV);
            SecretKeySpec clauSpect = new SecretKeySpec(primeraPart, ALGORISME_XIFRAT);

            Cipher xifrat = Cipher.getInstance(FORMAT_XifradorAES);
            xifrat.init(Cipher.ENCRYPT_MODE, clauSpect, ivSpec);
            byte[] missatgeXifrat = xifrat.doFinal(separat);

            // Combinar IV i part xifrada.
            byte[] combined = new byte[iv.length + missatgeXifrat.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(missatgeXifrat, 0, combined, iv.length, missatgeXifrat.length);
            
            // return iv+msgxifrat
            return combined;
    }

    public String desxifraXifradorAES(byte[] bMsgXifrat, String password) 
    throws Exception {
        // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0,MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada.
        byte[] missatgeXifrat = Arrays.copyOfRange(bMsgXifrat, 16,bMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        // Desxifrar.
        byte[] primeraPart = Arrays.copyOf(hash, MIDA_IV);
        SecretKeySpec clauSpect = new SecretKeySpec(primeraPart, ALGORISME_XIFRAT);
        Cipher xifrat = Cipher.getInstance(FORMAT_XifradorAES);
        xifrat.init(Cipher.DECRYPT_MODE, clauSpect, ivSpec);
        byte[] missatgeDesxifrat = xifrat.doFinal(missatgeXifrat);

        // return String desxifrat
        return new String(missatgeDesxifrat, "UTF-8");
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try{
            return new TextXifrat(xifraXifradorAES(msg, clau));
        }
        catch (Exception e){
            throw new ClauNoSuportada("Error al xifrar amb la funcio XifradorAES: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraXifradorAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al desxifrar amb la funció XifradorAES: " + e.getMessage());
        }
    }
}
