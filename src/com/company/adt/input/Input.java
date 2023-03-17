package com.company.adt.input;

import java.util.List;
import java.util.Set;

/**
 * Module that reads and stores the input to the program for processing by other modules.
 */
public interface Input {

    void processArguments(String fileName, String[] keywordsToIgnore) throws Exception;

    List<Line> getInputLines();

    Set<String> getKeywords();
}
