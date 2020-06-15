package ml.socshared.service.support.service.sentry;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SentrySender {

    private final SentryService sentry;

    public void sentryMessage(String message, Map<String, Object> additionalData, List<SentryTag> tags) {
        Map<String, String> tm = new HashMap<>();
        for(SentryTag tag : tags) {
            tm.put(tag.type(), tag.value());
        }
        sentry.logMessage(message, tm, additionalData);
    }
}