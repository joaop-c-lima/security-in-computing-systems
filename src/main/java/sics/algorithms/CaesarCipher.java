package sics.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Lima
 */
public class CaesarCipher {

    private int key;
    private String plainText;
    private String cipherText;
    private String cryptAnalysis;
    private final char[] lettersRelFreq = {'a', 'e', 'o', 's', 'r', 'i', 'n',
        'd', 'm', 'u', 't', 'c', 'l', 'p', 'v', 'g', 'h', 'q', 'b', 'f', 'z',
        'j', 'x', 'k', 'y', 'w'};

    public CaesarCipher() {
        this.key = 0;
    }

    public void setKey(int key) {
        this.key = key;
        if (plainText != null) {
            encryptText();
            cryptAnalysis();
        }
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
        encryptText();
        cryptAnalysis();
    }

    public int getKey() {
        return key;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getCipherText() {
        return cipherText;
    }

    public String getCryptAnalysis() {
        return cryptAnalysis;
    }

    /**
     * Encrypts the {@link #plaintext} using a simple Caesar cipher with the
     * given key. Only alphabetic characters are encrypted; all other characters
     * remain unchanged. Case is preserved in the output.
     */
    private void encryptText() {
        StringBuilder cipherBuilder = new StringBuilder();
        char c;
        for (int i = 0; i < plainText.length(); i++) {
            c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = (char) ((c + key - 'A') % 26 + 'A');
                } else {
                    c = (char) ((c + key - 'a') % 26 + 'a');
                }
            }
            cipherBuilder.append(c);
        }
        cipherText = cipherBuilder.toString();
    }

    /**
     * Performs a cryptanalysis on the {@link #ciphertext} to decrypt it using
     * frequency analysis. Assumes the {@link #ciphertext} is encrypted with a
     * simple substitution cipher. The decrypted text is stored in the
     * 'cryptAnalysis' field.
     */
    private void cryptAnalysis() {
        Map<Character, Integer> sortedLetterFreqMap = makeSortedLetterFreqMap();
        Map<Character, Character> translator = makeTranslator(sortedLetterFreqMap);
        StringBuilder cryptAnalysisBuilder = new StringBuilder();
        char c;

        for (int i = 0; i < cipherText.length(); i++) {
            c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = Character.toUpperCase(translator.get(Character.toLowerCase(c)));
                } else {
                    c = translator.get(c);
                }
            }
            cryptAnalysisBuilder.append(c);
        }

        cryptAnalysis = cryptAnalysisBuilder.toString();
    }

    /**
     * Generates a map of letter frequencies from the {@link #ciphertext},
     * sorted by frequency in ascending order. Only considers alphabetic
     * characters in the {@link #ciphertext}.
     *
     * @return A map where keys are lowercase letters and values are their
     * frequencies.
     */
    private Map<Character, Integer> makeSortedLetterFreqMap() {
        Map<Character, Integer> letterOccur = new HashMap<>();
        char c;

        for (int i = 'a'; i <= 'z'; i++) {
            letterOccur.put((char) i, 0);
        }

        for (int i = 0; i < cipherText.length(); i++) {
            c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                letterOccur.replace(Character.toLowerCase(c), letterOccur.get(c) + 1);
            }
        }

        return sortByValue(letterOccur);
    }

    /**
     * Sorts a map by the values in descending order.
     *
     * @param map The map to be sorted.
     * @return A new LinkedHashMap sorted by the values of the input map in
     * descending order.
     */
    private Map<Character, Integer> sortByValue(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        Map<Character, Integer> sortedMap = new LinkedHashMap<>();

        Collections.sort(entryList, Collections.reverseOrder(Map.Entry.comparingByValue()));

        for (Map.Entry<Character, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /**
     * Generates a translator map based on the sorted letter frequency map. The
     * translator map is used to map {@link #ciphertext} characters to a
     * probable {@link #plaintext} characters based on frequency analysis.
     *
     * @param sortedLetterFreqMap A map of letter frequencies sorted by
     * frequency in ascending order.
     * @return A map where keys are {@link #ciphertext} letters and values are
     * corresponding to a probable {@link #plaintext} letters.
     */
    public Map<Character, Character> makeTranslator(Map<Character, Integer> sortedLetterFreqMap) {
        List<Character> sortedLetterFreqArray = new ArrayList<>();
        Map<Character, Character> translator = new HashMap<>();

        sortedLetterFreqMap.forEach((ch, freq) -> sortedLetterFreqArray.add(ch));

        for (int i = 0; i < lettersRelFreq.length; i++) {
            translator.put(sortedLetterFreqArray.get(i), lettersRelFreq[i]);
        }

        return translator;
    }

}
