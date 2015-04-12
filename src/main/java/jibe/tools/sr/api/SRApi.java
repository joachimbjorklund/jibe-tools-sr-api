package jibe.tools.sr.api;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class SRApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SRApi.class);

    private static final String API_SR_V2_BASE = "http://api.sr.se/api/v2";
    private static final String COMMON_PARAMS = "?format=json&pagination=false";
    private static final String CHANNELS_BASE_URL = API_SR_V2_BASE + "/channels";
    private static final String PROGRAMS_BASE_URL = API_SR_V2_BASE + "/programs";
    private static final String RIGHTNOW_BASE_URL = API_SR_V2_BASE + "/scheduledepisodes/rightnow" + COMMON_PARAMS;
    private static final String FETCH_CHANNELS_URL = CHANNELS_BASE_URL + COMMON_PARAMS;
    private static final String FETCH_CHANNEL_BY_ID_TMPL = CHANNELS_BASE_URL + "/%d" + COMMON_PARAMS;
    private static final String FETCH_PROGRAM_BY_ID_TMPL = PROGRAMS_BASE_URL + "/%d" + COMMON_PARAMS;
    private static final String FETCH_RIGHTNOW_BY_ID_TMPL = RIGHTNOW_BASE_URL + "&channelid=%d";

    private static Optional<String> httpGet(String url) {
        try {
            Response response = newHttpClient().newCall(new Request.Builder().url(url).build()).execute();
            if (!response.isSuccessful() || response.body().contentLength() == 0) {
                return Optional.absent();
            }
            return Optional.fromNullable(Strings.emptyToNull(response.body().string()));
        } catch (IOException e) {
            LOGGER.error("e: " + e);
        }
        return Optional.absent();
    }

    private static OkHttpClient newHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(2, TimeUnit.SECONDS);
        httpClient.setReadTimeout(2, TimeUnit.SECONDS);
        httpClient.setFollowRedirects(true);
        return httpClient;
    }

    public static Optional<SRProgram> fetchProgramById(int programId) throws IOException {
        Optional<String> json = httpGet(String.format(FETCH_PROGRAM_BY_ID_TMPL, programId));
        if (!json.isPresent()) {
            return Optional.absent();
        }

        Optional<SRObject> parsed = parseJson(json.get(), SRObject.class);
        if (!parsed.isPresent()) {
            return Optional.absent();
        }

        return parsed.get().getProgram();
    }

    private static Optional<SRObject> parseSRObjectFromHttpGet(String url) {
        Optional<String> json = httpGet(url);
        if (!json.isPresent()) {
            return Optional.absent();
        }

        return parseJson(json.get(), SRObject.class);
    }

    private static <T> Optional<T> parseJson(String json, Class<T> typeClass) {
        try {
            return Optional.of(new GsonBuilder().create().fromJson(json, typeClass));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Optional.absent();
        }
    }

    public static Optional<SRChannel> fetchChannelById(int channelId) throws IOException {
        Optional<SRObject> srObject = parseSRObjectFromHttpGet(String.format(FETCH_CHANNEL_BY_ID_TMPL, channelId));
        if (!srObject.isPresent()) {
            return Optional.absent();
        }
        return srObject.get().getChannel();
    }

    public static Collection<SRChannel> fetchChannels() throws IOException {
        Optional<SRObject> srObject = parseSRObjectFromHttpGet(FETCH_CHANNELS_URL);
        if (!srObject.isPresent()) {
            return Collections.emptySet();
        }
        return srObject.get().getChannels();
    }

    public static Optional<SRChannel> fetchRightNowById(int channelId) throws IOException {
        Optional<SRObject> srObject = parseSRObjectFromHttpGet(String.format(FETCH_RIGHTNOW_BY_ID_TMPL, channelId));
        if (!srObject.isPresent()) {
            return Optional.absent();
        }
        return srObject.get().getChannel();
    }

    static Optional<Long> extractDateMillisFromSRDate(String dateStr) {
        if (Strings.isNullOrEmpty(dateStr)) {
            return Optional.absent();
        }

        Matcher matcher = Pattern.compile("\\/Date\\((.*)\\)\\/").matcher(dateStr);
        if (!matcher.matches()) {
            return Optional.absent();
        }

        long answer;
        try {
            answer = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            return Optional.absent();
        }
        return Optional.of(answer);
    }
}
