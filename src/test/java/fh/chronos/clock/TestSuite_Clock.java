package fh.chronos.clock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ClockSpringConfiguration.class})
public class TestSuite_Clock {

    @Autowired
    protected Clock clock;

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
    public void now_returns_increasing_time() throws InterruptedException {
        LocalDateTime ldt1 = clock.now();
        Thread.sleep(1);
        LocalDateTime ldt2 = clock.now();

        Assert.assertTrue("Time is not passing in Clock.", ldt1.isBefore(ldt2));
    }

    @Test
    public void time_returns_increasing_time() throws InterruptedException {
        LocalTime lt1 = clock.time();
        Thread.sleep(1);
        LocalTime lt2 = clock.time();

        Assert.assertTrue("Time is not passing in Clock.", lt1.isBefore(lt2));
    }

    @Test
    public void today_returns_same_date_for_a_short_time_difference() throws InterruptedException {
        LocalDate ld1 = clock.today();
        Thread.sleep(1);
        LocalDate ld2 = clock.today();

        Assert.assertTrue("Time is not passing in Clock.", ld1.isEqual(ld2));
    }
}
