package com.company.adt.output;

import com.company.adt.shifter.CircularShift;
import com.company.adt.sorter.Sorter;

import java.util.List;

public class KwicOutput implements Output {

    private final Sorter sorter;

    public KwicOutput(Sorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public void showOutput() {
        List<CircularShift> circularShifts = (List<CircularShift>) sorter.getSortedList();
        circularShifts.forEach(System.out::println);
    }
}
