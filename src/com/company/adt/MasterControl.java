package com.company.adt;

import com.company.adt.input.Input;
import com.company.adt.input.KwicInput;
import com.company.adt.output.KwicOutput;
import com.company.adt.output.Output;
import com.company.adt.shifter.KwicCircularShifter;
import com.company.adt.shifter.Shifter;
import com.company.adt.sorter.KwicAlphabetizer;
import com.company.adt.sorter.Sorter;

import java.util.Arrays;

import static com.company.Main.INDEX_FIRST_ARGUMENT;
import static com.company.Main.INDEX_SECOND_ARGUMENT;

public class MasterControl {

    private Input input;
    private Shifter shifter;
    private Sorter sorter;
    private Output output;

    /**
     * Creates an instance of each of the modules.
     */
    private void initModules() {
        input = new KwicInput();
        shifter = new KwicCircularShifter(input);
        sorter = new KwicAlphabetizer(shifter);
        output = new KwicOutput(sorter);
    }

    public void run(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("No input file specified.\n"
                    + "Usage: java com/company/Main <filename> [word to ignore]...");
        }

        initModules();

        String filename = args[INDEX_FIRST_ARGUMENT];
        String[] keywords = Arrays.copyOfRange(args, INDEX_SECOND_ARGUMENT, args.length);

        input.processArguments(filename, keywords);
        shifter.generateCircularShifts();
        sorter.generateSortedList();
        output.showOutput();
    }
}
