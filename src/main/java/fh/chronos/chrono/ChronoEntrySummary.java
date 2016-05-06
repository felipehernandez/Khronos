package fh.chronos.chrono;

import lombok.Getter;
import lombok.Setter;

public class ChronoEntrySummary {

    @Getter
    private String identifier;

    @Getter
    @Setter
    private int occurences;

    @Getter
    @Setter
    private long average;

    @Getter
    @Setter
    private long maxElapsedTime = Long.MIN_VALUE;

    @Getter
    @Setter
    private long minElapsedTime = Long.MAX_VALUE;

    public ChronoEntrySummary(String identifier) {
        this.identifier = identifier;
        this.occurences = 0;
        this.average = 0L;
    }

    public void addOccurence(long elapsedTime) {

        this.average = ((this.average * occurences) + elapsedTime) / (this.occurences + 1);
        this.occurences++;

        this.maxElapsedTime = Long.max(this.maxElapsedTime, elapsedTime);
        this.minElapsedTime = Long.min(this.minElapsedTime, elapsedTime);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.identifier);
        sb.append(" [" + this.occurences + " occurences] ");
        sb.append(" average: " + this.average + " ms");
        sb.append("{" + minElapsedTime + "-" + maxElapsedTime + "}");
        sb.append(".");

        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String identifier;
        private int occurences = 0;
        private long average = 0;
        private long max = Long.MIN_VALUE;
        private long min = Long.MAX_VALUE;

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public ChronoEntrySummary build() {
            return new ChronoEntrySummary(this.identifier);
        }
    }
}
