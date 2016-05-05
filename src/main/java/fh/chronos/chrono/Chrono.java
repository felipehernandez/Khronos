package fh.chronos.chrono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Chrono {

    private HashMap<String, Collection<ChronoEntry>> completedEntries;
    private HashMap<String, ChronoEntry> activeEntries;

    public Chrono() {
        this.completedEntries = new HashMap<>();
        this.activeEntries = new HashMap<>();
    }

    public void recordStart(String identifier) {

        ChronoEntry entry = ChronoEntry.builder().identifier(identifier).build();

        entry.setStart(getNow());
        activeEntries.put(identifier, entry);
    }

    public void recordEnd(String identifier) {

        long time = getNow();

        ChronoEntry entry = this.activeEntries.remove(identifier);
        entry.setEnd(time);

        if (!completedEntries.containsKey(identifier)) {
            completedEntries.put(identifier, new ArrayList<>());
        }
        completedEntries.get(identifier).add(entry);
    }

    public void reset() {
        this.completedEntries = new HashMap<>();
        this.activeEntries = new HashMap<>();
    }

    public List<ChronoEntrySummary> getSummaries() {

        ArrayList summaries = new ArrayList();

        for (String key : this.completedEntries.keySet()) {
            final ChronoEntrySummary summary = ChronoEntrySummary.builder().identifier(key).build();
            this.completedEntries.get(key).stream()
                    .forEach(chronoEntry -> summary.addOccurence(
                            chronoEntry.getEnd() - chronoEntry.getStart()));
            summaries.add(summary);
        }

        return summaries;
    }

    private long getNow() {
        return System.currentTimeMillis();
    }
}
