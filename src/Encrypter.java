import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by AhmedA on 2/19/2015.
 */
public class Encrypter {
    public static char[] alpha;

    public static char[] EncryptByCaesar(int key, char[] plainText){
        char[] cipher = new char[plainText.length];
        for (int i = 0; i <plainText.length ; i++) {
            cipher[i] = (char)(plainText[i]+key);
        }
        return cipher;
    }
    public static char[] EncryptByAffine(int sumKey, int producyKey, char[] plainText){
        char[] cipher = new char[plainText.length];
        for (int i = 0; i <plainText.length ; i++) {
            cipher[i] = (char)((((plainText[i]-97)*producyKey + sumKey)%26)+97);
        }
        return cipher;
    }
    public static char[] EncryptByPlayFair(char[] key, char[] plainText){
        char[][] keyMatrix = new char[5][5];
        char[] cipher = new char[plainText.length];
        fillFullMatrix();
        int startX = 0 , startY = 0;
        boolean first = true;
        char[] currentKey = generateValidKey(key);

        System.out.println(currentKey);
        for (int i = 0; i < currentKey.length ; i++) {
            markAlpha(currentKey[i]);
        }
        System.out.println(alpha);
        for (int i = 0, k = 0 ; i < 5 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                if(k >= currentKey.length){
                    if(first){
                        first = false;
                        startX = i ;
                        startY = j;
                    }
                }
                else {
                    keyMatrix[i][j] = currentKey[k];
                    k++;
                }
            }

        }
        ArrayList<Character> currentAlpha = new ArrayList<Character>();
        for (int i = 0; i < alpha.length ; i++) {
            if(alpha[i] == '0'){

            }
            else{
                currentAlpha.add(alpha[i]);
            }
        }
        for (int i = startX, k = 0; i < 5 ; i++) {
            for (int j = startY; j < 5  ; j++,k++) {
                keyMatrix[i][j] += currentAlpha.get(k);
            }
        }
        for (int i = 0; i < plainText.length ; i++) {
        //    findLOcation();
        }
        return key;
    }

    private static void fillFullMatrix() {
        alpha = new char[25];
        int j = 0;
        for (int i = 0; i < 25 ; i++) {
            if (i == 'j'-'a'){
                j++;
            }
            alpha[i] = (char) ('a' + j + i);
        }
    }

    private static void markAlpha(char c) {
        for (int i = 0; i < 25 ; i++) {
            if(alpha[i] == c){
                alpha[i] = '0';
                break;
            }
        }
    }

    private static char[] generateValidKey(char[] key) {
        ArrayList<Character> keys = new ArrayList<Character>();
        for (int i = 0; i <key.length ; i++) {
            for (int j = i+1; j <key.length ; j++) {
                if (key[i] == key[j]) {
                    key[j] = '0';
                }
            }
        }
        int k = 0;
        for (int i = 0; i < key.length ; i++) {
            if (key[i] == '0'){
                continue;
            }
            else{
                keys.add(key[i]);
            }
        }
        char[] valid = new char[keys.size()];
        for (int i = 0; i < keys.size() ; i++) {
            valid[i] = keys.get(i);
        }
        return valid;
    }

    public static void main(String[] args){
        String x = "playfairexample";
        char[] y = x.toCharArray();
        EncryptByPlayFair(y,y);
    }
}
