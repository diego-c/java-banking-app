package src.main.java;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Password {
    private static final int ITERATIONS = 100000;
    private static final int KEY_LENGTH = 2048;

    public static String hashPassword (String password, String salt) {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(
                passwordChars,
                saltBytes,
                ITERATIONS,
                KEY_LENGTH);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashedPassword = keyFactory.generateSecret(spec).getEncoded();
            return String.format("%x", new BigInteger(hashedPassword));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not generate key factory");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not generate hashed password");
            e.printStackTrace();
        }
        return null;
    }
}
