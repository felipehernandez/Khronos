package fh.chronos.clock;

import org.springframework.context.annotation.Bean;

public class ClockSpringConfiguration {

    @Bean
    public Clock clock() {
        return new ClockImpl();
    }
}
