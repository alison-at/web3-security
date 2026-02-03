import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;
import java.lang.Object;


//should turn this to junit
public class TestHashes {
    public static void main(String[] args) {
        //testOne();
        //testTwo();
        //findCollision();
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

    public static String generateString() {
        int asciiStart = 32;
        int asciiEnd = 126; 
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomChar = asciiStart + (int) (random.nextFloat() * (asciiEnd - asciiStart + 1));
            buffer.append((char) randomChar);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    //TODO
    public static void findCollision() {
        //make a random number generator for ascii chars
        long start = System.currentTimeMillis();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; (System.currentTimeMillis() - start) < 1800000 ; i++) {
            //RandomStringUtils stringUtils = new RandomStringUtils();
            String asciiString = generateString();
            
            byte[] asciiBytes = asciiString.getBytes();
            try {
                byte[] hashedBytes = Part1.computeDigest(asciiBytes, 3);
                String hashedString = Base64.getEncoder().encodeToString(hashedBytes);
                if (map.get(hashedString) == null ) {
                    map.put(hashedString, asciiString);
                } else {
                    if (map.get(hashedString).equals(asciiString)) {
                        continue;
                    }
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.printf("Collision of %s and %s at %s with time of %d on iteration %d\n", map.get(hashedString), asciiString, hashedString, timeElapsed, i );
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            if (i%100000 == 0) {
                System.out.println(i+ "th iteration");
            }
        }
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
            long nonce = Part3.solvePuzzle( puzzleID, 12);
            System.out.println(nonce);
            Boolean verified = Part3.verifyPuzzle(puzzleID, nonce, 6);
            System.out.println(verified);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
}
