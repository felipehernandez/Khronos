package fh.chronos.clock;

public interface TrickedClock extends Clock {

    /**
     * Sets the reference time of the clock from the string given as an input such as {@code 2007-12-03T10:15:30}.
     *
     * @param date expected format: YYYY-mm-DDTHH:MM:SS.
     */
    void setClock(String date);

    void reset();

    void goesForwards(long amount, ClockUnit unit);

    void goesBackwards(long amount, ClockUnit unit);
}
