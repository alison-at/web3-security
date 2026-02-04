import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.security.SecureRandom;

public class Part2 {

    // -----------------------------
    // Part 2: Commitments
    // -----------------------------
    /** TODO: return (c, r) where r is random 32 bytes and c = SHA256(r || message). */
    public static Commitment commit(byte[] message, int hashFunction) throws NoSuchAlgorithmException {
        // TODO 
        try {
            byte[] salt = Utils.genSalt();
            //SecureRandom.getInstanceStrong().nextBytes(salt);
            byte[] messageSalted = Utils.concat(salt, message);
            byte[] encoded = Part1.computeDigest(messageSalted, hashFunction);
            Commitment commit = new Commitment(encoded, salt);
            return commit;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Exception: " + e.toString());
        }
       
        
    }

    /** TODO: return true iff c.c equals SHA256(c.r || message). */
    public static boolean verify(Commitment c, byte[] message, int hashFunction) throws NoSuchAlgorithmException {
        //retrieve the salt
        try {
            byte[] salt = c.getSalt();
            //retrieve the sha encoded message which includes the salt and original message
            byte[] messageEncoded = c.getCom();
            
            //recreate the messageSalted
            byte[] computedSaltedMessage = Utils.concat(salt, message);
            //byte[] computedEncoded = Part1.computeDigest(computedSaltedMessage, hashFunction);


            return Part1.verifyIntegrity(computedSaltedMessage, messageEncoded, hashFunction);
            
        } catch (Exception e) {
            throw new UnsupportedOperationException("Exception: " + e.toString());
        }
        
    }

}
