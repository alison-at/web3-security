import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.Object;
import org.apache.commons.lang3.RandomStringUtils;
//should turn this to junit
public class TestHashes {
    public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
    }

    public static void printBytes(byte[] message) {
        for (int i = 0; i < message.length; i++) {
            System.out.print(message[i]);
        }
    }

    public static void testOne() {
        String message = "Hello World";
        
        byte[] messageBytes = message.getBytes();
        try {
            byte[] hashedMessageOne = Part1.computeDigest(messageBytes, 1);
            System.out.println(Part1.verifyIntegrity( messageBytes, hashedMessageOne, 1));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        

    }

    //TODO
    public static void findCollisionSha256() {
        //make a random number generator for ascii chars
        for (int i - )
        HashMap<String, String> map = new HashMap<>();
        RandomStringUtils stringUtils = new RandomStringUtils();
        String asciiString = stringUtils.nextAscii(10);
        
        byte[] asciiBytes = asciiString.getBytes(StandardCharsets.UTF_8);

        byte[] hashedBytes = Utils.sha256(asciiBytes);
        String hashedString = new String(hashedBytes, StandardCharsets.UTF_8);
        if (map.get(hashedString) == null) {
            map.put(hashedString, asciiString);
        } else {
            System.out.println()
            return hashedString;
        }

        
        //add chars to an array, concatenate to string, transform to bytes
        //make a map of key value pairs, wait until there is a collision
    }

    public static void testTwo() {
        String message = "Emperor Penguin";
        byte[] messageBytes = message.getBytes();
        try {
            Commitment commit = Part2.commit(messageBytes, 1);
            System.out.println(Part2.verify(commit, messageBytes, 1));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void testThree() {
        byte[] puzzleID = Utils.genSalt();
        try {
            long nonce = Part3.solvePuzzle( puzzleID, 10);
            System.out.println(nonce);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
}
