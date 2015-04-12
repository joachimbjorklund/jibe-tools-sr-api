package jibe.tools.sr.api;

import com.google.common.base.Optional;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 *
 */
public class SRApiTestIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(SRApiTestIT.class);
    private static final int P1 = 132;

    @Test
    public void testFetchChannels() throws Exception {
        Collection<SRChannel> foundSRChannels = SRApi.fetchChannels();
        Assert.assertFalse(foundSRChannels.isEmpty());
    }

    @Test
    public void testFetchChannelP1() throws Exception {
        Optional<SRChannel> foundChannel = SRApi.fetchChannelById(P1);
        Assert.assertTrue(foundChannel.isPresent());
    }

    @Test
    public void testFetchChannelUnknown() throws Exception {
        Optional<SRChannel> foundChannel = SRApi.fetchChannelById(12345);
        Assert.assertFalse(foundChannel.isPresent());
    }

    @Test
    public void testFetchRightNow() throws Exception {
        Optional<SRChannel> foundChannel = SRApi.fetchRightNowById(P1);
        Assert.assertTrue(foundChannel.isPresent());
        Optional<DateTime> startTime = foundChannel.get().getCurrentScheduledEpisode().get().getStartTimeUTC();
        Optional<DateTime> endTime = foundChannel.get().getCurrentScheduledEpisode().get().getEndTimeUTC();

        LOGGER.debug(startTime.toString());
        LOGGER.debug(endTime.toString());
    }
}
