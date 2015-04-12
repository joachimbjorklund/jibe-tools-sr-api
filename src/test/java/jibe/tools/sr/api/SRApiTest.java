package jibe.tools.sr.api;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class SRApiTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SRApiTest.class);

    @Test
    public void testChannel() {
        String json = "{\n"
                + "  \"copyright\": \"Copyright Sveriges Radio 2015. All rights reserved.\",\n"
                + "  \"channel\": {\n"
                + "    \"image\": \"http://sverigesradio.se/sida/images/132/2186745_512_512.jpg?preset=api-default-square\",\n"
                + "    \"imagetemplate\": \"http://sverigesradio.se/sida/images/132/2186745_512_512.jpg\",\n"
                + "    \"color\": \"31a1bd\",\n"
                + "    \"siteurl\": \"http://sverigesradio.se/p1\",\n"
                + "    \"liveaudio\": {\n"
                + "      \"id\": 132,\n"
                + "      \"url\": \"http://sverigesradio.se/topsy/direkt/132.mp3\",\n"
                + "      \"statkey\": \"/app/direkt/p1[k(132)]\"\n"
                + "    },\n"
                + "    \"scheduleurl\": \"http://sverigesradio.se/api/v2/scheduledepisodes?channelid=132\",\n"
                + "    \"channeltype\": \"Rikskanal\",\n"
                + "    \"xmltvid\": \"p1.sr.se\",\n"
                + "    \"id\": 132,\n"
                + "    \"name\": \"P1\"\n"
                + "  }\n"
                + "}";

        Gson gson = new GsonBuilder().create();
        SRObject srObject = gson.fromJson(json, SRObject.class);

        assertTrue(srObject.getChannel().isPresent());
        assertTrue(srObject.getChannels().isEmpty());
        SRChannel srChannel = srObject.getChannel().get();
        assertNotNull(srChannel.getImage().get());
        assertNotNull(srChannel.getColor().get());
        assertNotNull(srChannel.getSiteUrl().get());
        assertNotNull(srChannel.getScheduleurl().get());
    }

    @Test
    public void testChannels() {
        String json = "{\n"
                + "  \"copyright\": \"Copyright Sveriges Radio 2015. All rights reserved.\",\n"
                + "  \"channels\": [\n"
                + "    {\n"
                + "      \"image\": \"http://sverigesradio.se/sida/images/132/2186745_512_512.jpg?preset=api-default-square\",\n"
                + "      \"imagetemplate\": \"http://sverigesradio.se/sida/images/132/2186745_512_512.jpg\",\n"
                + "      \"color\": \"31a1bd\",\n"
                + "      \"siteurl\": \"http://sverigesradio.se/p1\",\n"
                + "      \"liveaudio\": {\n"
                + "        \"id\": 132,\n"
                + "        \"url\": \"http://sverigesradio.se/topsy/direkt/132.mp3\",\n"
                + "        \"statkey\": \"/app/direkt/p1[k(132)]\"\n"
                + "      },\n"
                + "      \"scheduleurl\": \"http://sverigesradio.se/api/v2/scheduledepisodes?channelid=132\",\n"
                + "      \"channeltype\": \"Rikskanal\",\n"
                + "      \"xmltvid\": \"p1.sr.se\",\n"
                + "      \"id\": 132,\n"
                + "      \"name\": \"P1\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"image\": \"http://sverigesradio.se/sida/images/163/2186754_512_512.jpg?preset=api-default-square\",\n"
                + "      \"imagetemplate\": \"http://sverigesradio.se/sida/images/163/2186754_512_512.jpg\",\n"
                + "      \"color\": \"ff5a00\",\n"
                + "      \"siteurl\": \"http://sverigesradio.se/p2\",\n"
                + "      \"liveaudio\": {\n"
                + "        \"id\": 163,\n"
                + "        \"url\": \"http://sverigesradio.se/topsy/direkt/163.mp3\",\n"
                + "        \"statkey\": \"/app/direkt/p2[k(163)]\"\n"
                + "      },\n"
                + "      \"scheduleurl\": \"http://sverigesradio.se/api/v2/scheduledepisodes?channelid=163\",\n"
                + "      \"channeltype\": \"Rikskanal\",\n"
                + "      \"xmltvid\": \"p2.sr.se\",\n"
                + "      \"id\": 163,\n"
                + "      \"name\": \"P2\"\n"
                + "    }\n"
                + "  ]"
                + "}";

        Gson gson = new GsonBuilder().create();
        SRObject srObject = gson.fromJson(json, SRObject.class);
        assertTrue(srObject.getChannels().size() == 2);
    }

    @Test
    public void testRightNow() throws Exception {
        String json = "{\n"
                + "  \"copyright\": \"Copyright Sveriges Radio 2015. All rights reserved.\",\n"
                + "  \"channel\": {\n"
                + "    \"id\": 164,\n"
                + "    \"name\": \"P3\",\n"
                + "    \"previousscheduledepisode\": {\n"
                + "      \"episodeid\": 526624,\n"
                + "      \"title\": \"Ekonyheter\",\n"
                + "      \"starttimeutc\": \"\\/Date(1428778800000)\\/\",\n"
                + "      \"endtimeutc\": \"\\/Date(1428778980000)\\/\",\n"
                + "      \"program\": {\n"
                + "        \"id\": 4540,\n"
                + "        \"name\": \"Ekot\"\n"
                + "      },\n"
                + "      \"socialimage\": \"http://sverigesradio.se/sida/images/164/2186756_512_512.jpg?preset=api-default-square\"\n"
                + "    },\n"
                + "    \"currentscheduledepisode\": {\n"
                + "      \"episodeid\": 526624,\n"
                + "      \"title\": \"Humorhimlen Lab\",\n"
                + "      \"starttimeutc\": \"\\/Date(1428778980000)\\/\",\n"
                + "      \"endtimeutc\": \"\\/Date(1428782400000)\\/\",\n"
                + "      \"program\": {\n"
                + "        \"id\": 3389,\n"
                + "        \"name\": \"Humorhimlen\"\n"
                + "      },\n"
                + "      \"socialimage\": \"http://sverigesradio.se/sida/images/3389/3126901_512_512.jpg?preset=api-default-square\"\n"
                + "    },\n"
                + "    \"nextscheduledepisode\": {\n"
                + "      \"episodeid\": 531080,\n"
                + "      \"title\": \"Ekonyheter\",\n"
                + "      \"starttimeutc\": \"\\/Date(1428782400000)\\/\",\n"
                + "      \"endtimeutc\": \"\\/Date(1428782700000)\\/\",\n"
                + "      \"program\": {\n"
                + "        \"id\": 4540,\n"
                + "        \"name\": \"Ekot\"\n"
                + "      },\n"
                + "      \"socialimage\": \"http://sverigesradio.se/sida/images/164/2186756_512_512.jpg?preset=api-default-square\"\n"
                + "    }\n"
                + "  }\n"
                + "}";

        Gson gson = new GsonBuilder().create();
        SRObject srObject = gson.fromJson(json, SRObject.class);

        assertTrue(srObject.getChannel().isPresent());
        assertTrue(srObject.getChannel().get().getCurrentScheduledEpisode().isPresent());
        Optional<SRScheduledEpisode> currentScheduledEpisode = srObject.getChannel().get().getCurrentScheduledEpisode();
        assertTrue(currentScheduledEpisode.isPresent());
        assertTrue(currentScheduledEpisode.get().getEndTimeUTC().isPresent());

    }

    @Test
    public void testProgram() throws Exception {
        String json = "{\n"
                + "  \"copyright\": \"Copyright Sveriges Radio 2015. All rights reserved.\",\n"
                + "  \"program\": {\n"
                + "    \"description\": \"Vi bevakar gotländska idrottsprestationer i många olika sammanhang. Ofta sänder vi direkt.\",\n"
                + "    \"programcategory\": {\n"
                + "      \"id\": 10,\n"
                + "      \"name\": \"Sport\"\n"
                + "    },\n"
                + "    \"broadcastinfo\": \"\",\n"
                + "    \"email\": \"gotlandssporten@sverigesradio.se\",\n"
                + "    \"phone\": \"\",\n"
                + "    \"programurl\": \"http://sverigesradio.se/sida/gruppsida.aspx?programid=94&grupp=3826\",\n"
                + "    \"programimage\": \"http://sverigesradio.se/sida/images/23/2791166_512_512.jpg?preset=api-default-square\",\n"
                + "    \"programimagetemplate\": \"http://sverigesradio.se/sida/images/23/2791166_512_512.jpg\",\n"
                + "    \"socialimage\": \"http://sverigesradio.se/sida/images/23/2791166_512_512.jpg?preset=api-default-square\",\n"
                + "    \"socialimagetemplate\": \"http://sverigesradio.se/sida/images/23/2791166_512_512.jpg\",\n"
                + "    \"socialmediaplatforms\": [\n"
                + "      {\n"
                + "        \"platform\": \"Facebook\",\n"
                + "        \"platformurl\": \"https://www.facebook.com/pages/SR-Gotland/366076305584\"\n"
                + "      },\n"
                + "      {\n"
                + "        \"platform\": \"Twitter\",\n"
                + "        \"platformurl\": \"https://twitter.com/p4gotland/\"\n"
                + "      }\n"
                + "    ],\n"
                + "    \"channel\": {\n"
                + "      \"id\": 205,\n"
                + "      \"name\": \"P4 Gotland\"\n"
                + "    },\n"
                + "    \"archived\": false,\n"
                + "    \"hasondemand\": true,\n"
                + "    \"haspod\": false,\n"
                + "    \"responsibleeditor\": \"Helena Bremberg\",\n"
                + "    \"id\": 23,\n"
                + "    \"name\": \"Sporten P4 Gotland\"\n"
                + "  }\n"
                + "}";

        Gson gson = new GsonBuilder().create();
        SRObject srObject = gson.fromJson(json, SRObject.class);

        assertTrue(srObject.getProgram().isPresent());
    }
}
