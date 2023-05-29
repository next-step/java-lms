package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import nextstep.courses.exceptions.InvalidTermsException;

public class SessionTerm {

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public SessionTerm(LocalDateTime startAt, LocalDateTime endAt) {
        validateTerms(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateTerms(LocalDateTime startAt, LocalDateTime endAt) {
        if (!endAt.isAfter(startAt)) {
            throw new InvalidTermsException();
        }
    }
}
