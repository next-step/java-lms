package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Images;
import nextstep.courses.exception.CanNotApplyException;

import java.util.Objects;

public class SessionInformation {

    private final SessionStatus status;

    private final Period period;

    private final Images images;

    private final Recruitment recruitment;

    public SessionInformation(SessionStatus status,
                              Period period,
                              Images images,
                              Recruitment recruitment) {
        this.status = status;
        this.period = period;
        this.images = images;
        this.recruitment = recruitment;
    }

    public void validateApply() {
        validateRecruiting();
    }

    private void validateRecruiting() {
        if (recruitment.isNotRecruiting()) {
            throw new CanNotApplyException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInformation that = (SessionInformation) o;
        return status == that.status
                && Objects.equals(period, that.period)
                && recruitment == that.recruitment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, period, recruitment);
    }
}
