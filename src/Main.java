
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new File("dict.txt"));
        ArrayList<String> dict = new ArrayList<>();

        // put the words in an arraylist
        while (scan.hasNext()) {
            dict.add(scan.next());
        }


        TechniqueOne(dict);
        TechniqueTwo(dict);
        TechniqueThree(dict);


    }

    // for each letter in word1, search word2 for letter. If found, delete from word 2
    private static void TechniqueOne(ArrayList<String> dict) {
        long start = System.nanoTime();
        HashMap<String, Integer> anagramsMap = new HashMap<String, Integer>();
        for (int i = 0; i < dict.size(); i++) {
            String word1 = dict.get(i).toLowerCase();
            int count = 0;
            for (int j = i + 1; j < dict.size(); j++) {
                String word2 = dict.get(j).toLowerCase();

                if (word1.length() != word2.length()) continue;

                for (int k = 0; k < word1.length(); k++) {
                    for (int l = 0; l < word2.length(); l++) {
                        //found a letter in word2 that is in word1, delete it
                        if (word1.charAt(k) == word2.charAt(l)) {
                            StringBuilder sb = new StringBuilder(word2);
                            word2 = sb.deleteCharAt(l).toString();
                            break; // if not checking for length of the word, remove this
                        }
                    }

                }
                if (word2.equals("")) {
                    count++;
                }

            }
            // here make a dict with the word and the count
            anagramsMap.put(word1, count);

        }
        //find the largest value entry set
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : anagramsMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE ONE TIME: " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(maxEntry + "\n");
    }

    // sort word 1, sort word 2, compare the two sorted words
    private static void TechniqueTwo(ArrayList<String> dict) {
        long start = System.nanoTime();

        HashMap<String, Integer> anagramsMap = new HashMap<String, Integer>();

        for (int i = 0; i < dict.size(); i++) {
            String word1Str = dict.get(i).toLowerCase();
            char[] word1 = dict.get(i).toLowerCase().toCharArray();
            Arrays.sort(word1);
            int count = 0;
            for (int j = i + 1; j < dict.size(); j++) {
                char[] word2 = dict.get(j).toLowerCase().toCharArray();
                Arrays.sort(word2);

                if (word1.length != word2.length) continue;
                if (Arrays.equals(word1, word2)) {
                    count++;
                }
            }
            // here make a dict with the word and the count
            anagramsMap.put(word1Str, count);

        }
        //find the largest value entry set
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : anagramsMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE TWO TIME: " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(maxEntry + "\n");
    }

    //create a letter array for word1 and word2. Compare the letter array
    private static void TechniqueThree(ArrayList<String> dict) {
        long start = System.nanoTime();

        HashMap<String, Integer> anagramsMap = new HashMap<String, Integer>();

        for (int i = 0; i < dict.size(); i++) {
            String word1 = dict.get(i).toLowerCase();
            int count = 0;
            for (int j = i + 1; j < dict.size(); j++) {
                int array[] = new int[128];

                String word2 = dict.get(j).toLowerCase();
                StringBuilder sb = new StringBuilder();
                if (word1.length() != word2.length()) continue;

                for (int k = 0; k < word1.length(); ++k) {
                    array[(int) word1.charAt(k)]++;
                }

                for (int k = 0; k < word1.length(); ++k) {
                    array[(int) word2.charAt(k)]--;
                }

                // check each element if they are 0
                if (Tech3Helper(array)) count++;

            }
            // here make a dict with the word and the count
            anagramsMap.put(word1, count);

        }
        //find the largest value entry set
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : anagramsMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE THREE TIME : " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(maxEntry);

    }

    private static boolean Tech3Helper(int[] array) {
        for (int in : array) {
            if (in != 0) {
                return false;
            }
        }
        return true;
    }

}

