package com.company.adt.sorter;

import com.company.adt.shifter.CircularShift;
import com.company.adt.shifter.Shifter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KwicAlphabetizer implements Sorter {

    private final Shifter shifter;
    private List<CircularShift> sortedCircularShifts;

    public KwicAlphabetizer(Shifter shifter) {
        this.shifter = shifter;
    }

    /**
     * Sorts all the circular shifts in reverse lexicographical order.
     */
    public void generateSortedList() {
        sortedCircularShifts = shifter.getCircularShifts();
        Collections.sort(sortedCircularShifts, Collections.reverseOrder());
    }

    public List<?> getSortedList() {
        return new ArrayList<>(this.sortedCircularShifts);
    }

}
