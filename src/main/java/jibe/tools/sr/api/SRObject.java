package jibe.tools.sr.api;

import com.google.common.base.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.Collections;

/**
 *
 */
public class SRObject {
    private String copyright;

    private SRChannel channel;
    private Collection<SRChannel> channels = Collections.emptyList();

    private SRProgram program;

    public Optional<SRChannel> getChannel() {
        return Optional.fromNullable(channel);
    }

    public Optional<SRProgram> getProgram() {
        return Optional.fromNullable(program);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Collection<SRChannel> getChannels() {
        return channels;
    }

    public String getCopyright() {
        return copyright;
    }
}
