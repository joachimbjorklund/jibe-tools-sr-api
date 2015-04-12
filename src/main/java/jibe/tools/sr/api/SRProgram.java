package jibe.tools.sr.api;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;

/**
 *
 */
public class SRProgram {
    private String description;
    private String email;
    private String phone;
    private String programurl;
    private String programimage;
    private Collection<SRSocialMediaPlatform> socialmediaplatforms = Collections.emptySet();
    private SRChannel channel;
    private Boolean hasondemand;
    private Boolean haspod;
    private String responsibleeditor;
    private int id;
    private String name;

    public Optional<SRChannel> getChannel() {
        return Optional.fromNullable(channel);
    }

    public Optional<String> getDescription() {
        return Optional.fromNullable(Strings.emptyToNull(description));
    }

    public Optional<String> getEmail() {
        return Optional.fromNullable(Strings.emptyToNull(email));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getPhone() {
        return Optional.fromNullable(Strings.emptyToNull(phone));
    }

    public Optional<URL> getProgramimage() {
        if (Strings.isNullOrEmpty(programimage)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(programimage));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }

    public Optional<URL> getProgramurl() {
        if (Strings.isNullOrEmpty(programurl)) {
            return Optional.absent();
        }
        try {
            return Optional.of(new URL(programurl));
        } catch (MalformedURLException e) {
            return Optional.absent();
        }
    }

    public Collection<SRSocialMediaPlatform> getSocialmediaplatforms() {
        return socialmediaplatforms;
    }
}
