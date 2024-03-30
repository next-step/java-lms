package nextstep.qna.domain;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeProvider implements CreatedDateTimeProvider {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
