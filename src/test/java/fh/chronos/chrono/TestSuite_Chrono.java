package fh.chronos.chrono;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ChronoSpringConfiguration.class})
public class TestSuite_Chrono {

    protected static final String TESTING_IDENTIFIER = "TEST_ID";
    protected static final String TESTING_IDENTIFIER_EXTRA = "TEST_ID2";

    @Autowired
    protected Chrono chrono;

    @Before
    public void setUp() {
        chrono.reset();
    }

    @Test
    public void implementation_is_injected() {

        Assert.assertNotNull("Spring is not injecting correctly.", chrono);
    }

    @Test
    public void no_entries() {

        Assert.assertTrue(chrono.getSummaries().isEmpty());
    }

    @Test
    public void just_start() {

        chrono.recordStart(TESTING_IDENTIFIER);
        Assert.assertTrue(chrono.getSummaries().isEmpty());
    }

    @Test
    public void just_end() {

        chrono.recordStart(TESTING_IDENTIFIER);
        Assert.assertTrue(chrono.getSummaries().isEmpty());
    }

    @Test
    public void both_start_and_end() throws InterruptedException {

        chrono.recordStart(TESTING_IDENTIFIER);
        Thread.sleep(10);
        chrono.recordEnd(TESTING_IDENTIFIER);

        Assert.assertTrue(chrono.getSummaries().size() == 1);
        printAllSummaries(chrono.getSummaries());
    }

    @Test
    public void two_occureces_for_the_same_entry() throws InterruptedException {

        chrono.recordStart(TESTING_IDENTIFIER);
        Thread.sleep(10);
        chrono.recordEnd(TESTING_IDENTIFIER);
        chrono.recordStart(TESTING_IDENTIFIER);
        Thread.sleep(20);
        chrono.recordEnd(TESTING_IDENTIFIER);

        Assert.assertTrue(chrono.getSummaries().size() == 1);
        Assert.assertTrue(chrono.getSummaries().get(0).getAverage() > 10);
        Assert.assertTrue(chrono.getSummaries().get(0).getAverage() < 20);
        printAllSummaries(chrono.getSummaries());
    }

    @Test
    public void multiple_entries() throws InterruptedException {

        chrono.recordStart(TESTING_IDENTIFIER);
        Thread.sleep(10);
        chrono.recordEnd(TESTING_IDENTIFIER);
        chrono.recordStart(TESTING_IDENTIFIER_EXTRA);
        Thread.sleep(10);
        chrono.recordEnd(TESTING_IDENTIFIER_EXTRA);

        Assert.assertTrue(chrono.getSummaries().size() == 2);
        printAllSummaries(chrono.getSummaries());
    }

    private void printAllSummaries(List<ChronoEntrySummary> summaries) {

        summaries.stream()
                .forEach(
                        summary -> System.out.println(summary.toString()
                        )
                );
    }
}
