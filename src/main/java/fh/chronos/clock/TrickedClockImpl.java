package fh.chronos.clock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TrickedClockImpl implements TrickedClock {

    private LocalDateTime referenceTime = LocalDateTime.now();
    private LocalDateTime current = LocalDateTime.from(this.referenceTime);

    public LocalDateTime now() {
        return LocalDateTime.from(this.current);
    }

    public LocalDate today() {
        return LocalDate.from(this.current);
    }

    public LocalTime time() {
        return LocalTime.from(this.current);
    }

    public void setClock(String date) {
        this.referenceTime = LocalDateTime.parse(date);
        this.current = LocalDateTime.from(this.referenceTime);
    }

    /**
     * Sets the current time to the reference one.
     */
    public void reset(){
        this.current = LocalDateTime.from(this.referenceTime);
    }

    public void goesForwards(long amount, ClockUnit unit) {

        switch (unit) {
            case NANOS:
                this.current = this.current.plusNanos(amount);
                break;
            case SECONDS:
                this.current = this.current.plusSeconds(amount);
                break;
            case MINUTES:
                this.current = this.current.plusMinutes(amount);
                break;
            case HOURS:
                this.current = this.current.plusHours(amount);
                break;
            case DAYS:
                this.current = this.current.plusDays(amount);
                break;
            case WEEKS:
                this.current = this.current.plusWeeks(amount);
                break;
            case MONTHS:
                this.current = this.current.plusMonths(amount);
                break;
            case YEARS:
                this.current = this.current.plusYears(amount);
                break;
        }
    }

    public void goesBackwards(long amount, ClockUnit unit) {

        switch (unit) {
            case NANOS:
                this.current = this.current.minusNanos(amount);
                break;
            case SECONDS:
                this.current = this.current.minusSeconds(amount);
                break;
            case MINUTES:
                this.current = this.current.minusMinutes(amount);
                break;
            case HOURS:
                this.current = this.current.minusHours(amount);
                break;
            case DAYS:
                this.current = this.current.minusDays(amount);
                break;
            case WEEKS:
                this.current = this.current.minusWeeks(amount);
                break;
            case MONTHS:
                this.current = this.current.minusMonths(amount);
                break;
            case YEARS:
                this.current = this.current.minusYears(amount);
                break;
        }
    }
}
