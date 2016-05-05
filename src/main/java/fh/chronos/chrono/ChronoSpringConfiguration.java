package fh.chronos.chrono;

import org.springframework.context.annotation.Bean;

public class ChronoSpringConfiguration {

    @Bean
    public Chrono chrono() {
        return new Chrono();
    }
}
