package fh.chronos.clock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TrickedClockSpringConfiguration.class})
public class TestSuite_TrickedClockSpringConfiguration {

    @Autowired
    protected Clock clock;

    @Test
    public void injection_is_done() {
        Assert.assertNotNull("Spring configuration is not injecting a Clock implementation.", clock);
    }

    @Test
    public void correct_injection_is_done() {
        Assert.assertTrue("Spring is not injecting a TrickedClock implementation.", clock instanceof TrickedClock);
    }
}
