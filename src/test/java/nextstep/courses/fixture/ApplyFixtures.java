package nextstep.courses.fixture;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.domain.course.session.apply.ApprovalStatus;

import java.util.List;

public class ApplyFixtures {
    public static Apply apply_one_wait() {
        return new Apply(1L, 1L, ApprovalStatus.WAIT, SessionFixtures.DATETIME_2023_12_5);
    }

    public static Applies applies_two_canceled() {
        return new Applies(List.of(apply_one_canceled(), apply_two_canceled()));
    }

    public static Apply apply_one_canceled() {
        return new Apply(1L, 1L, ApprovalStatus.CANCELED, SessionFixtures.DATETIME_2023_12_5);
    }

    public static Apply apply_two_canceled() {
        return new Apply(1L, 2L, ApprovalStatus.CANCELED, SessionFixtures.DATETIME_2023_12_5);
    }

    public static Applies applies_two_approved() {
        return new Applies(List.of(apply_one_approved(), apply_two_approved()));
    }

    public static Apply apply_one_approved() {
        return new Apply(1L, 1L, ApprovalStatus.APPROVED, SessionFixtures.DATETIME_2023_12_5);
    }

    public static Apply apply_two_approved() {
        return new Apply(1L, 2L, ApprovalStatus.APPROVED, SessionFixtures.DATETIME_2023_12_5);
    }
}
