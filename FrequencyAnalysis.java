package MTE;
import java.util.*;

public class FrequencyAnalysis {
    public static void main(String[] args) {
        String ciphertext = "LXFOPVEFRNHR"; 
        int keyLength = findKeyLength(ciphertext);
        System.out.println("Estimated Key Length: " + keyLength);
        char[] decryptedText = decrypt(ciphertext, "KEY"); 
        System.out.println("Decrypted Text: " + new String(decryptedText));
    }

    
    public static int findKeyLength(String text) {
        int[] matchCounts = new int[text.length()];
        for (int shift = 1; shift < text.length(); shift++) {
            int matches = 0;
            for (int i = 0; i < text.length() - shift; i++) {
                if (text.charAt(i) == text.charAt(i + shift)) {
                    matches++;
                }
            }
            matchCounts[shift] = matches;
        }

        int maxIndex = 1;
        for (int i = 2; i < matchCounts.length; i++) {
            if (matchCounts[i] > matchCounts[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex; 
    }

    
    public static char[] decrypt(String text, String key) {
        char[] decrypted = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            decrypted[i] = (char) ((text.charAt(i) - key.charAt(i % key.length()) + 26) % 26 + 'A');
        }
        return decrypted;
    }
}
