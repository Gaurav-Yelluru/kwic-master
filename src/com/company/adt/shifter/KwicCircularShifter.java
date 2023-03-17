package com.company.adt.shifter;

import com.company.adt.input.Input;
import com.company.adt.input.Line;

import java.util.*;
// import java.util.List;
// import java.util.Set;

import static com.company.Main.INDEX_FIRST_ARGUMENT;

/**
 * Represents a circular shifter by {@code offset} of a {@code line}.
 */
public class KwicCircularShifter implements Shifter {

    private final Input input;
    private List<CircularShift> circularShifts;

/**
 * Introducing noise elimination in KWIC. "noiseWords" defines the list of words to be removed from the input.
 */
    private List<String> noiseWords = Arrays.asList("a","an","the","and","or");
    private Set<String> noiseEliminate = new HashSet<>(noiseWords);

    public KwicCircularShifter(Input input) {
        this.input = input;
        circularShifts = new ArrayList<>();
    }

    /**
     * Remove first and/or last characters, if they are not letters or digits.
     * @param word String from which the characters are to be removed.
     * @return String which only consists of letters or digits.
     */
    private String strip(String word) {
        char firstChar = word.charAt(INDEX_FIRST_ARGUMENT);
        char lastChar = word.charAt(word.length() - 1);

        if (!Character.isDigit(firstChar) && !Character.isAlphabetic(firstChar)) {
            word = word.substring(INDEX_FIRST_ARGUMENT + 1);
        }

        if (word.length() > 0 && !Character.isDigit(lastChar) && !Character.isAlphabetic(lastChar)) {
            word = word.substring(INDEX_FIRST_ARGUMENT, word.length() - 1);
        }

        return word;
    }

    @Override
    public void generateCircularShifts() {
        List<Line> inputLines = input.getInputLines();
        Set<String> keywordsToIgnore = input.getKeywords();

        inputLines.forEach(line -> {
            int numberOfWords = line.getNumberOfWords();

            for (int wordIndex = 0; wordIndex < numberOfWords; wordIndex++) {
                String startWord = line.getWord(wordIndex);
                startWord = strip(startWord);

                /**
                * Removing the appropriate word from the input to perform noise elimination.
                */

                if(noiseEliminate.contains(startWord.toLowerCase())){
                    line.removeWord(wordIndex);
                    wordIndex--;
                    numberOfWords--;
                }

                if (!keywordsToIgnore.contains(startWord.toLowerCase())) {
                    circularShifts.add(new CircularShift(line, wordIndex));
                }
            }
        });
    }

    @Override
    public List<CircularShift> getCircularShifts() {
        return new ArrayList<>(this.circularShifts);
    }

}
