package nextstep.qna.domain;

import java.time.LocalDateTime;

public class LocalDateTimeTestHolder implements LocalDataTimeHolder {

    private final LocalDateTime localDateTime;

    public LocalDateTimeTestHolder(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public LocalDateTime now() {
        return this.localDateTime;
    }
}
