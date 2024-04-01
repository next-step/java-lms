package nextstep.qna.domain;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component("localDateTimeProvider")
public class LocalDateTimeProvider implements CurrentDateTimeProvider {

    @Override
    public LocalDateTime get() {
        return LocalDateTime.now();
    }
}
