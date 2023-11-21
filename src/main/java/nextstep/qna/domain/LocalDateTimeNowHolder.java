package nextstep.qna.domain;

import java.time.LocalDateTime;

public class LocalDateTimeNowHolder implements LocalDataTimeHolder {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
