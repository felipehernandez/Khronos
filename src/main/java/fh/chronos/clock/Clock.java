package fh.chronos.clock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Clock {

    LocalDateTime now();

    LocalDate today();

    LocalTime time();
}
