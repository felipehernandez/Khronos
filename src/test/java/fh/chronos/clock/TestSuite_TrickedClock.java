package fh.chronos.clock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class TestSuite_TrickedClock {

    public static final String RANDOM_DATE_FOR_TESTING = "2007-12-03";
    public static final String RANDOM_TIME_FOR_TESTING = "10:15:30";
    public static final String RANDOM_MOMENT_FOR_TESTING = RANDOM_DATE_FOR_TESTING + "T" + RANDOM_TIME_FOR_TESTING;

    protected Clock clock = new TrickedClockImpl();

    private ClockUnit clockUnit;

    public TestSuite_TrickedClock(ClockUnit clockUnit) {
        this.clockUnit = clockUnit;
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {ClockUnit.NANOS},
                        {ClockUnit.SECONDS},
                        {ClockUnit.MINUTES},
                        {ClockUnit.HOURS},
                        {ClockUnit.DAYS},
                        {ClockUnit.WEEKS},
                        {ClockUnit.MONTHS},
                        {ClockUnit.YEARS}
                }
        );
    }

    @Test
    public void now_does_not_return_null() {
        Assert.assertNotNull("clock.now() returns NULL.", clock.now());
    }

    @Test
    public void today_does_not_return_null() {
        Assert.assertNotNull("clock.now() returns NULL.", clock.today());
    }

    @Test
    public void time_does_not_return_null() {
        Assert.assertNotNull("clock.now() returns NULL.", clock.time());
    }

    @Test
    public void now_returns_same_time_if_time_passed_is_not_triggered() throws InterruptedException {
        LocalDateTime ldt1 = clock.now();
        Thread.sleep(1);
        LocalDateTime ldt2 = clock.now();

        Assert.assertTrue("Time is not passing in Clock.", ldt1.isEqual(ldt2));
    }

    @Test
    public void time_returns_same_time_if_time_passed_is_not_triggered() throws InterruptedException {
        LocalTime lt1 = clock.time();
        Thread.sleep(1);
        LocalTime lt2 = clock.time();

        Assert.assertTrue("Time is not passing in Clock.", lt1.equals(lt2));
    }

    @Test
    public void today_returns_same_date_if_time_passed_is_not_triggered() throws InterruptedException {
        LocalDate ld1 = clock.today();
        Thread.sleep(1);
        LocalDate ld2 = clock.today();

        Assert.assertTrue("Time is not passing in Clock.", ld1.isEqual(ld2));
    }

    @Test
    public void setClock() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        Assert.assertEquals("Clock was wrongly initialised for LocalDateTime",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING),
                clock.now());
        Assert.assertEquals("Clock was wrongly initialised for LocalDate",
                LocalDate.parse(RANDOM_DATE_FOR_TESTING),
                clock.today());
        Assert.assertEquals("Clock was wrongly initialised for LocalTime",
                LocalTime.parse(RANDOM_TIME_FOR_TESTING),
                clock.time());
    }

    @Test
    public void reset() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).reset();

        Assert.assertEquals("Clock reset is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING),
                clock.now()
        );
    }

    @Test
    public void reset_after_time_passed() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);
        ((TrickedClock) clock).goesForwards(1, this.clockUnit);
        ((TrickedClock) clock).reset();

        Assert.assertEquals("Clock reset is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING),
                clock.now()
        );
    }

    @Test
    public void goesForwards_0() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesForwards(0, this.clockUnit);

        Assert.assertEquals("Clock goesForwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING),
                clock.now()
        );
    }

    @Test
    public void goesForwards_1() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesForwards(1, this.clockUnit);

        Assert.assertTrue("Clock goesForwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING).isBefore(clock.now()));
    }

    @Test
    public void goesForwards_2() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesForwards(1, this.clockUnit);
        LocalDateTime ldt1 = clock.now();

        ((TrickedClock) clock).reset();
        ((TrickedClock) clock).goesForwards(2, this.clockUnit);
        LocalDateTime ldt2 = clock.now();

        Assert.assertTrue("Clock goesForwards is wrong.", ldt1.isBefore(ldt2));
    }

    @Test
    public void goesForwards_m1() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesForwards(-1, this.clockUnit);

        Assert.assertTrue("Clock goesForwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING).isAfter(clock.now()));
    }

    @Test
    public void goesBackwards_0() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesBackwards(0, this.clockUnit);

        Assert.assertEquals("Clock goesBackwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING),
                clock.now()
        );
    }

    @Test
    public void goesBackwards_1() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesBackwards(1, this.clockUnit);

        Assert.assertTrue("Clock goesBackwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING).isAfter(clock.now()));
    }

    @Test
    public void goesBackwards_2() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesBackwards(1, this.clockUnit);
        LocalDateTime ldt1 = clock.now();

        ((TrickedClock) clock).reset();
        ((TrickedClock) clock).goesBackwards(2, this.clockUnit);
        LocalDateTime ldt2 = clock.now();

        Assert.assertTrue("Clock goesBackwards is wrong.", ldt1.isAfter(ldt2));
    }

    @Test
    public void goesBackwards_m1() {

        ((TrickedClock) clock).setClock(RANDOM_MOMENT_FOR_TESTING);

        ((TrickedClock) clock).goesBackwards(-1, this.clockUnit);

        Assert.assertTrue("Clock goesBackwards is wrong.",
                LocalDateTime.parse(RANDOM_MOMENT_FOR_TESTING).isBefore(clock.now()));
    }
}
