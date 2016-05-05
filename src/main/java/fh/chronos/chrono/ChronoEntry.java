package fh.chronos.chrono;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ChronoEntry {

    @Getter
    private String identifier;

    @Getter
    @Setter
    private long start;

    @Getter
    @Setter
    private long end;
}
