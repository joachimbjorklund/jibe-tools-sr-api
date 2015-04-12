package jibe.tools.sr.api;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class SRSocialMediaPlatform {
    private String platform;
    private String platformurl;

    public Optional<String> getPlatform() {
        return Optional.fromNullable(platform);
    }

    public Optional<URL> getPlatformurl() {
        if (Strings.isNullOrEmpty(platformurl)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(platformurl));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }
}
