package nextstep.courses.domain.fixture;

import nextstep.courses.domain.status.ProgressStatus;
import nextstep.courses.domain.status.RecruitmentStatus;
import nextstep.courses.domain.status.SessionStatus;

import static nextstep.courses.domain.status.ProgressStatus.*;
import static nextstep.courses.domain.status.RecruitmentStatus.*;

public class SessionStatusFixture {

    public static SessionStatus status(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        return new SessionStatus(progressStatus, recruitmentStatus);
    }

    public static SessionStatus status() {
        return new SessionStatus(PREPARING, RECRUITMENT);
    }

}
