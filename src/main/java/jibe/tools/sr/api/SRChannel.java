package jibe.tools.sr.api;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@SuppressWarnings("unused")
public class SRChannel {
    private String image;
    private String color;
    private String siteurl;
    private String scheduleurl;
    private int id;
    private String name;
    private SRScheduledEpisode previousscheduledepisode;
    private SRScheduledEpisode currentscheduledepisode;
    private SRScheduledEpisode nextscheduledepisode;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Optional<Integer> getColor() {
        if (color == null) {
            return Optional.absent();
        }
        return Optional.of(Integer.parseInt(color, 16));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<URL> getScheduleurl() {
        if (Strings.isNullOrEmpty(scheduleurl)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(scheduleurl));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }

    public Optional<SRScheduledEpisode> getCurrentScheduledEpisode() {
        return Optional.fromNullable(currentscheduledepisode);
    }

    public Optional<SRScheduledEpisode> getNextScheduledEpisode() {
        return Optional.fromNullable(nextscheduledepisode);
    }

    public Optional<SRScheduledEpisode> getPreviousScheduledEpisode() {
        return Optional.fromNullable(previousscheduledepisode);
    }

    public Optional<URL> getImage() {
        if (Strings.isNullOrEmpty(image)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(image));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }

    public Optional<URL> getSiteUrl() {
        if (Strings.isNullOrEmpty(scheduleurl)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(scheduleurl));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }
}
