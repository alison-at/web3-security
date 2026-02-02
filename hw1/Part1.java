import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;



public class Part1 {
    // -----------------------------
    // Part 1: Message Digests
    // -----------------------------
    
    /**
     * Compute the message digest of a message using specified hash function.
     * 
     * @param message the message to hash
     * @param hashFunction which hash to use:
     *                     1 = full SHA-256 (256 bits)
     *                     2 = SHA-256 truncated to 8 bits
     *                     3 = SHA-256 truncated to 16 bits
     *                     other = print error and return null
     * @return the message digest as a byte array
     */
    public static byte[] computeDigest(byte[] message, int hashFunction)  throws NoSuchAlgorithmException {
        
        // TODO 
        if (hashFunction == 1) {
            byte[] hashOne = Utils.sha256(message);
            return hashOne;
        } else if (hashFunction == 2) {
            byte[] hashTwo = Utils.hashTruncated(message, 8);
            return hashTwo;
        } else if (hashFunction == 3) {
            byte[] hashThree = Utils.hashTruncated(message, 16);
            return hashThree;
        } else {
            //change what exactly is thrown, indicate that input was not valid
            throw new UnsupportedOperationException("No supported hash format");
        }
        
    }
    public static Boolean arrayComparison(byte[] message, byte[] computedMessage) {
        if (message.length != computedMessage.length) {
            return false;
        }
        for (int i = 0; i < message.length; i++) {
           
            if (message[i] != computedMessage[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verify that a message matches an expected digest.
     * 
     * @param message the message to verify
     * @param expectedDigest the expected digest
     * @param hashFunction which hash function to use (1, 2, or 3)
     * @return true if message's digest matches expectedDigest
     */
    public static boolean verifyIntegrity(byte[] message, byte[] expectedDigest, 
                                          int hashFunction) throws NoSuchAlgorithmException {
        if (hashFunction == 1) {
            byte[] hashOne = Utils.sha256(message);
            return arrayComparison(expectedDigest, hashOne);
        } else if (hashFunction == 2) {
            byte[] hashTwo = Utils.hashTruncated(message, 8);
            return arrayComparison(expectedDigest, hashTwo);
        } else if (hashFunction == 3) {
            byte[] hashThree = Utils.hashTruncated(message, 16);
            return arrayComparison(expectedDigest, hashThree);
        } else {
            //change what exactly is thrown, indicate that input was not valid
            throw new UnsupportedOperationException("No supported hash format");
        }

    }
    
}
