package nextstep.qna.domain;

import java.time.LocalDateTime;

public interface CurrentDateTimeProvider {

    LocalDateTime now();
}
