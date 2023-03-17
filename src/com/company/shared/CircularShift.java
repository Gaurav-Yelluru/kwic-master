package com.company.shared;

/**
 * Represents a circular shifter by {@code offset} of a {@code line}.
 */
public class CircularShift implements Comparable<CircularShift> {

    private Line line;
    private int offset;

    public CircularShift(Line line, int offset) {
        this.line = line;
        this.offset = offset;
    }

    /**
     * Builds and returns the output string for this line after circular shifting {@code offset} number of times.
     * @return String containing the circualr shifter of the line.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int numberOfWords = line.getNumberOfWords();
        for(int i = 0; i < numberOfWords; i++) {
            builder.append(line.getWord((offset + i) % numberOfWords)).append(" ");
        }

        return builder.toString();
    }

    /**
     * Overriden compareTo() method
     * @param other Circular shifter to compare {@code this} to.
     * @return -1 if {@code this} is lexicographically smaller than {@code other}, 0 if both are lexicographically
     *      equal, and 1 if {@code this} is lexicographically larger.
     */
    @Override
    public int compareTo(CircularShift other) {
        int wordsCount = 0;
        int thisWordIndex = this.offset;
        int otherWordIndex = other.offset;

        int thisLineTotalWords = this.line.getNumberOfWords();
        int otherLineTotalWords = other.line.getNumberOfWords();

        while (wordsCount < thisLineTotalWords && wordsCount < otherLineTotalWords) {

            int compareVal = this.line.getWord(thisWordIndex).compareToIgnoreCase(other.line.getWord(otherWordIndex));
            
            if (compareVal > 0) {
                return 1;
            } else if (compareVal < 0) {
                return -1;
            }
            
            wordsCount++;
            thisWordIndex = (thisWordIndex + 1) % thisLineTotalWords;
            otherWordIndex = (otherWordIndex + 1) % otherLineTotalWords;
        }

        if (wordsCount >= thisLineTotalWords) {
            return -1;
        } else if (wordsCount >= otherLineTotalWords) {
            return 1;
        } else {
            return 0;
        }
    }
}
