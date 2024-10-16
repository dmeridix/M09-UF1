import javax.crypto.*;
import java.security.*;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                + e.getLocalizedMessage ());
            }
        
        System.out.println("--------------------" );
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String password) 
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

            Cipher xifrat = Cipher.getInstance(FORMAT_AES);
            xifrat.init(Cipher.ENCRYPT_MODE, clauSpect, ivSpec);
            byte[] missatgeXifrat = xifrat.doFinal(separat);

            // Combinar IV i part xifrada.
            byte[] combined = new byte[iv.length + missatgeXifrat.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(missatgeXifrat, 0, combined, iv.length, missatgeXifrat.length);
            
            // return iv+msgxifrat
            return combined;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password) 
    throws Exception {
        // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bMsgXifrat, 0,MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada.
        byte[] missatgeXifrat = Arrays.copyOfRange(bMsgXifrat, 16,bMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes());

        // Desxifrar.
        byte[] primeraPart = Arrays.copyOf(hash, MIDA_IV);
        SecretKeySpec clauSpect = new SecretKeySpec(primeraPart, ALGORISME_XIFRAT);
        Cipher xifrat = Cipher.getInstance(FORMAT_AES);
        xifrat.init(Cipher.DECRYPT_MODE, clauSpect, ivSpec);
        byte[] missatgeDesxifrat = xifrat.doFinal(missatgeXifrat);

        // return String desxifrat
        return new String(missatgeDesxifrat, "UTF-8");
    }
}
