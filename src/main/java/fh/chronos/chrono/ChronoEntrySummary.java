package fh.chronos.chrono;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ChronoEntrySummary {

    @Getter
    private String identifier;

    @Getter
    @Setter
    private int occurences = 0;

    @Getter
    @Setter
    private long average = 0;

    public void addOccurence(long timeElapsed) {

        this.average = ((this.average * occurences) + timeElapsed) / (this.occurences + 1);
        this.occurences++;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.identifier);
        sb.append(" [" + this.occurences + " occurences] ");
        sb.append(" average: " + this.average + " ms.");

        return sb.toString();
    }
}
