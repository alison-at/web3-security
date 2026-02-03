import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
/*
Notes from assignment: the solution set Y is more than 1, 
if more than one input causes a hash with d leading zeros

Not a maximally hard y, gets harder the more zeros are 
specified (the larger d gets)
*/
public class Part3 {
    // -----------------------------
    // Part 3: Puzzle Friendliness
    // -----------------------------
    public static Boolean checkZeros(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * Find a solution x (nonce) such that H(puzzleID || x) ∈ Y,
     * where Y is the set of all hashes with at least 'difficulty' leading zero bits.
     * 
     * @param puzzleID the puzzle identifier (arbitrary data - high min-entropy value)
     * @param difficulty number of leading zero bits required (defines |Y| = 2^(256-difficulty))
     * @return a nonce x that solves the puzzle, or -1 if not found within reasonable attempts
     */
    public static long solvePuzzle(byte[] puzzleID, int difficulty) throws Exception {
        // TODO
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; (i < (Math.pow(2, difficulty) + Math.pow(difficulty, 4))) || ((System.currentTimeMillis() - start) < 1800000)  ; i++) {
                long nonce = new Random().nextLong();
                //got this off a stackexchange thread, maybe not reliable: https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
                byte[] x = ByteBuffer.wrap(new byte[8]).putLong(nonce).array();
                byte[] concatedValue = Utils.concat(puzzleID, x);
                byte[] hashedVal = Utils.hashTruncated(concatedValue, difficulty);
                if (checkZeros(hashedVal)) {
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.println("found nonce on iteration " + i + " in " + timeElapsed + "seconds");
                    return nonce;
                }

                if (i%1000 == 0) {
                    System.out.println("iteration: " + i);
                }
            }
            return -1;
        } catch (Exception e) {
             throw new UnsupportedOperationException(e.toString());
        }
    }
       
    
    /**
     * Verify that x is a valid solution: H(puzzleID || x) ∈ Y.
     * 
     * @param puzzleID the puzzle identifier
     * @param x the proposed solution (nonce)
     * @param difficulty required number of leading zero bits (defines Y)
     * @return true if H(puzzleID || x) has at least 'difficulty' leading zeros
     */
    public static boolean verifyPuzzle(byte[] puzzleID, long x, int difficulty) 
            throws Exception {
        try {
            byte[] xByte = ByteBuffer.wrap(new byte[8]).putLong(x).array();
            byte[] concatVal = Utils.concat(puzzleID, xByte);
            byte[] solvedPuzzle = Utils.hashTruncated(concatVal, difficulty);
            
            if (checkZeros(solvedPuzzle)) {
                    System.out.println("found nonce on " + x);
                    return true;
            }
            return false;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Invalid input");
        }
        
    }
}
