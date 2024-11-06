package iticbc.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        return pair;
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        // Assignem el metode de xifratge
        Cipher cipher = Cipher.getInstance("RSA");
        
        // Configurem el metode de xifrate amb la clau publica
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        
        // Convertir el missatge en bytes per poder xifrar-lo
        byte[] msgBytes = msg.getBytes("UTF-8");
        
        // Cifrar el missatge i returnar-lo
        return cipher.doFinal(msgBytes);
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception {
        // Crear una instancia de Cipher amb el algoritme RSA
        Cipher cipher = Cipher.getInstance("RSA");
        
        // Configurem el metode de desxifratge amb la clau privada
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        
        // Desxifrar el missatge i transformar-lo en un arrey de bytes
        byte[] msgDesxifratBytes = cipher.doFinal(msgXifrat);
        
        // Convertir el resultat a un String utilitzant la codificació URF-8
        return new String(msgDesxifratBytes, "UTF-8");
    }
}
