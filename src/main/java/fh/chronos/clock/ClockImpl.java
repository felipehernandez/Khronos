package fh.chronos.clock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ClockImpl implements Clock {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public LocalDate today() {
        return LocalDate.now();
    }

    public LocalTime time() {
        return LocalTime.now();
    }
}
