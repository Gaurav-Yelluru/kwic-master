package com.company.adt.input;

import java.io.*;
import java.util.*;

/**
 * Processes the arguments and stores the KWIC input for processing by other modules.
 */
public class KwicInput implements Input {

    private List<Line> lines;

    /**
     * Collection of unique lowercase words that are to be ignored during the circular shifter process.
     */
    private Set<String> keywordsToIgnore;

    public KwicInput() {
        lines = new ArrayList<>();
        keywordsToIgnore = new HashSet<>();
    }

    private boolean isFileExists(File file) {
        return file.exists() && file.isFile();
    }

    /**
     * Reads data lines from input text file and stores them for processing by other modules.
     */
    private void readFile(String filePath) throws FileNotFoundException, IOException {
        File inputFile = new File(filePath);

        if (!isFileExists(inputFile)) {
            throw new FileNotFoundException();
        }

        try {
            FileReader fr = new FileReader(filePath);
            try (BufferedReader br = new BufferedReader(fr)) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    lines.add(new Line(inputLine));
                }
            }
        } catch (IOException ioe) {
            System.out.println("IOException while reading file." + ioe.getStackTrace());
            throw ioe;
        }
    }

    private void readWordsToIgnore(String[] keywords) {
        for (String keyword: keywords) {
            keywordsToIgnore.add(keyword.toLowerCase());
        }
    }

    @Override
    public void processArguments(String fileName, String[] keywordsToIgnore) throws FileNotFoundException, IOException {
        readFile(fileName);
        readWordsToIgnore(keywordsToIgnore);
    }

    @Override
    public List<Line> getInputLines() {
        return new ArrayList<>(lines);
    }

    @Override
    public Set<String> getKeywords() {
        return new HashSet<>(keywordsToIgnore);
    }

}
