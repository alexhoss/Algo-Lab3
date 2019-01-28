
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
        int maxCount = 0;
        String mostAnagrams = null;

        for (int i = 0; i < dict.size(); i++) {
            String word1 = dict.get(i).toLowerCase();
            int count = 0;
            for (int j = i + 1; j < dict.size(); j++) {

                if (word1.length() != dict.get(j).length()) continue;

                StringBuilder word2 = new StringBuilder(dict.get(j).toLowerCase());

                for (int k = 0; k < word1.length(); k++) {
                    for (int l = 0; l < word2.length(); l++) {
                        //found a letter in word2 that is in word1, delete it
                        if (word1.charAt(k) == word2.charAt(l)) {
                            word2.deleteCharAt(l);
                            break;
                        }
                    }
                }
                if (word2.length() == 0) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostAnagrams = word1;
            }

        }

        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE ONE TIME: " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(mostAnagrams + " : " + maxCount + " matches" + "\n");
    }

    // sort word 1, sort word 2, compare the two sorted words
    private static void TechniqueTwo(ArrayList<String> dict) {
        int maxCount = 0;
        String mostAnagrams = null;
        long start = System.nanoTime();

        for (int i = 0; i < dict.size(); i++) {
            int count = 0;
            char[] word1 = dict.get(i).toLowerCase().toCharArray();
            String word1Str = dict.get(i).toLowerCase();
            Arrays.sort(word1);
            for (int j = i + 1; j < dict.size(); j++) {
                if (word1.length != dict.get(j).length()) continue;

                char[] word2 = dict.get(j).toLowerCase().toCharArray();
                Arrays.sort(word2);

                if (Arrays.equals(word1, word2)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostAnagrams = word1Str;
            }
        }


        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE TWO TIME: " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(mostAnagrams + " : " + maxCount + " matches" + "\n");
    }

    //create a letter array for word1 and word2. Compare the letter array
    private static void TechniqueThree(ArrayList<String> dict) {
        long start = System.nanoTime();

        int maxCount = 0;
        String mostAnagrams = null;

        for (int i = 0; i < dict.size(); i++) {
            String word1 = dict.get(i).toLowerCase();
            int count = 0;
            for (int j = i + 1; j < dict.size(); j++) {
                if (word1.length() != dict.get(j).length()) continue;
                int array[] = new int[128];
                String word2 = dict.get(j).toLowerCase();

                for (int k = 0; k < word1.length(); ++k) {
                    array[(int) word1.charAt(k)]++;
                }
                for (int k = 0; k < word1.length(); ++k) {
                    array[(int) word2.charAt(k)]--;
                }
                // check each element if they are 0
                if (Tech3Helper(array)) count++;
            }
            if (count > maxCount) {
                maxCount = count;
                mostAnagrams = word1;
            }
        }

        long end = System.nanoTime();
        long seconds = (end - start) / 1000000000;
        System.out.println("TECHNIQUE THREE TIME : " + seconds + " SECONDS");
        System.out.println("===================");
        System.out.println(mostAnagrams + " : " + maxCount + " matches" + "\n");

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

