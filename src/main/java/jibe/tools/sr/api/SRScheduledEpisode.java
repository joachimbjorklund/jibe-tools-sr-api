package jibe.tools.sr.api;

import com.google.common.base.Optional;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 */
public class SRScheduledEpisode {

    private int episodeid;
    private String title;
    private String starttimeutc;
    private String endtimeutc;
    private SRProgram program;

    public Optional<SRProgram> getProgram() {
        return Optional.fromNullable(program);
    }

    public Optional<DateTime> getEndTimeUTC() {
        Optional<Long> millis = SRApi.extractDateMillisFromSRDate(endtimeutc);
        if (!millis.isPresent()) {
            return Optional.absent();
        }

        return Optional.of(new DateTime(millis.get(), DateTimeZone.UTC));
    }

    public int getEpisodeid() {
        return episodeid;
    }

    public Optional<DateTime> getStartTimeUTC() {
        Optional<Long> millis = SRApi.extractDateMillisFromSRDate(starttimeutc);
        if (!millis.isPresent()) {
            return Optional.absent();
        }

        return Optional.of(new DateTime(millis.get(), DateTimeZone.UTC));
    }

    public String getTitle() {
        return title;
    }
}
