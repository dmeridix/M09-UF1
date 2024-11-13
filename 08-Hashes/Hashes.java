import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.InvalidKeySpecException;

public class Hashes {
    private int npass = 0; // Contador de passwords probadas en fuerza bruta

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest missatge = MessageDigest.getInstance("SHA-512");
        missatge.update(salt.getBytes());
        byte[] hashByte = missatge.digest(pw.getBytes());
        return HexFormat.of().formatHex(hashByte);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return HexFormat.of().formatHex(hash);
    }

    public String forcaBruta(String alg, String hash, String salt) {
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        char[] attempt = new char[6];  // Array reutilizable para combinaciones hasta 6 caracteres

        // Probar todas las combinaciones de longitud 1 a 6
        for (char c1 : charset) {
            attempt[0] = c1;
            if (checkPassword(attempt, 1, alg, hash, salt)) return new String(attempt, 0, 1);
            for (char c2 : charset) {
                attempt[1] = c2;
                if (checkPassword(attempt, 2, alg, hash, salt)) return new String(attempt, 0, 2);
                for (char c3 : charset) {
                    attempt[2] = c3;
                    if (checkPassword(attempt, 3, alg, hash, salt)) return new String(attempt, 0, 3);
                    for (char c4 : charset) {
                        attempt[3] = c4;
                        if (checkPassword(attempt, 4, alg, hash, salt)) return new String(attempt, 0, 4);
                        for (char c5 : charset) {
                            attempt[4] = c5;
                            if (checkPassword(attempt, 5, alg, hash, salt)) return new String(attempt, 0, 5);
                            for (char c6 : charset) {
                                attempt[5] = c6;
                                if (checkPassword(attempt, 6, alg, hash, salt)) return new String(attempt, 0, 6);
                            }
                        }
                    }
                }
            }
        }
        return null;  // No se encontró coincidencia
    }

    private boolean checkPassword(char[] attempt, int length, String alg, String hash, String salt) {
        try {
            npass++; // Incrementa npass sólo cuando se genera un hash
            String generatedHash = alg.equals("SHA-512") ?
                getSHA512AmbSalt(new String(attempt, 0, length), salt) :
                getPBKDF2AmbSalt(new String(attempt, 0, length), salt);
            return generatedHash.equals(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        millis %= 1000;
        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "b";
        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String[] algorismes = {"SHA-512", "PBKDF2"};
        
        for (int i = 0; i < aHashes.length; i++) {
            h.npass = 0; // Reset contador de intentos antes de cada algoritmo
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            String pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
