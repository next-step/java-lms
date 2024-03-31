package nextstep.qna.domain;

import java.time.LocalDateTime;

public class LocalDateTimeProvider implements TimeStrategy {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
