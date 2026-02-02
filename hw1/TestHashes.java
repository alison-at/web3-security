import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

//should turn this to junit
public class TestHashes {
    public static void main(String[] args) {
        testOne();
        testTwo();
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
    public static void findCollision() {
        //make a random number generator for ascii chars
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
}
