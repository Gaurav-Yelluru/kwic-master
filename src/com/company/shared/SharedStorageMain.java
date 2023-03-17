package com.company.shared;

import java.io.File;
import java.util.*;

import static com.company.Main.INDEX_FIRST_ARGUMENT;
import static com.company.Main.INVALID_KWIC_ARGS;

/**
 * Master control class that creates all the objects and invokes all the methods needed to get the KWIC output.
 */
public class SharedStorageMain {

    private List<Line> inputLines;
    private Set<String> wordsToIgnore;
    private List<CircularShift> circularShifts;
    private File inputFile;

    private List<String> noiseWords = Arrays.asList("a","an","the","and","or");
    private Set<String> noiseEliminate = new HashSet<>(noiseWords);

    public SharedStorageMain() {
        inputLines = new ArrayList<>();
        wordsToIgnore = new HashSet<>();
        circularShifts = new ArrayList<>();
    }

    public void run(String[] inputArgs) throws Exception {
        input(inputArgs);
        circularShift();
        alphabetize();
        output();
    }

    /**
     * Reads data lines from input text file and stores them for processing by other modules.
     */
    public void input(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception(INVALID_KWIC_ARGS);
        }

        inputFile = new File(args[INDEX_FIRST_ARGUMENT]);

        // Remaining args are the keywords that should be ignored.
        setWordsToIgnore(args);

        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLines.add(new Line(line));
        }
    }

    private void setWordsToIgnore(String[] args) {
        int numberOfArguments = args.length;
        if (numberOfArguments > 1) {
            for (int i = INDEX_FIRST_ARGUMENT + 1; i < numberOfArguments; i++) {
                wordsToIgnore.add(args[i].toLowerCase());
            }
        }
    }

    public void circularShift() {
        inputLines.forEach(line -> {
            int numberOfWords = line.getNumberOfWords();

            for (int wordIndex = 0; wordIndex < numberOfWords; wordIndex++) {
                String startWord = line.getWord(wordIndex);

                startWord = strip(startWord);

                if(noiseEliminate.contains(startWord.toLowerCase())){
                    line.removeWord(wordIndex);
                    wordIndex--;
                    numberOfWords--;
                }

                if (!wordsToIgnore.contains(startWord.toLowerCase())) {
                    circularShifts.add(new CircularShift(line, wordIndex));
                }
            }
        });
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

    /**
     * Sorts all the circular shifts in reverse lexicographical order.
     */
    public void alphabetize() {
        Collections.sort(circularShifts, Collections.reverseOrder());
    }

    /**
     * Outputs the list of all valid circular shifts of all lines in lexicographical order.
     */
    public void output() {
        circularShifts.forEach(System.out::println);
    }

}
