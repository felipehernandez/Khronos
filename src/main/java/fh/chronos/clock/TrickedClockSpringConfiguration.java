package fh.chronos.clock;

import org.springframework.context.annotation.Bean;

public class TrickedClockSpringConfiguration {

    @Bean
    public TrickedClock clock() {
        return new TrickedClockImpl();
    }
}
