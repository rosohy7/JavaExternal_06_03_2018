package external.letiuka.logic;

import external.letiuka.model.Sentence;
import external.letiuka.model.SentenceComponent;
import external.letiuka.model.Text;
import external.letiuka.model.Word;

import java.util.*;

public class TextLib {
    private TextLib() {
    }

    private static int countCharInWord(String word, char chr) {
        int count = 0;
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) == chr) ++count;
        }
        return count;
    }

    public static List<String> sortWordsByLetterCount(Text text, char letter) {
        List<WeightedWord> weightedWords = new ArrayList<WeightedWord>();
        List<String> res = new ArrayList<String>();
        Set<String> taken = new HashSet<String>();
        for (Sentence sen : text.getSentences()) {
            for (SentenceComponent comp : sen.getComponents()) {
                if (comp instanceof Word) {
                    String word = comp.toString().toLowerCase();
                    letter = Character.toLowerCase(letter);
                    if (!taken.contains(word)) {
                        taken.add(word);
                        WeightedWord weightedWord = new WeightedWord(
                                word, countCharInWord(word, letter));
                        weightedWords.add(weightedWord);
                    }
                }
            }
        }
        weightedWords.sort(new Comparator<WeightedWord>() {
            public int compare(WeightedWord a, WeightedWord b) {
                int res = b.weight - a.weight;
                return res == 0 ? a.word.compareTo(b.word) : res;
            }
        });
        for (WeightedWord a : weightedWords) {
            res.add(a.word);
        }
        return res;
    }

    private static class WeightedWord {
        String word;
        int weight;

        public WeightedWord(String word, int weight) {
            this.word = word;
            this.weight = weight;
        }
    }
}
