package com.company.adt.shifter;

import java.util.List;

/**
 * Module that .
 */
public interface Shifter {

    /**
     *
     */
    void generateCircularShifts();

    /**
     *
     * @return
     */
    List<CircularShift> getCircularShifts();
}
