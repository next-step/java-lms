package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.enroll.Enrolment;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.dto.EnrolmentInfo;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class PaySession extends Session {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    private Long amount;
    private int studentsCapacity;

    public PaySession(Long id, PayType payType, SessionStatus sessionStatus, CoverImage coverImage, Enrolment enrolment, LocalDate startDate, LocalDate endDate, Long amount, int studentsCapacity) {
        super(id, payType, sessionStatus, coverImage, enrolment, startDate, endDate);
        this.amount = amount;
        this.studentsCapacity = studentsCapacity;
    }

    @Override
    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateSessionStatus();
        validatePayAmount(enrolmentInfo);
        validateCapacity();

        return enrolment.enroll(enrolmentInfo);
    }

    private void validatePayAmount(EnrolmentInfo enrolmentInfo) {
        if (enrolmentInfo.isNotSameAmount(amount)) {
            throw new IllegalArgumentException(String.format("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: %s원", formatter.format(amount)));
        }
    }

    private void validateCapacity() {
        if (isExceed()) {
            throw new IllegalArgumentException("현재 수강 가능한 모든 인원수가 채워졌습니다.");
        }
    }

    private boolean isExceed() {
        return enrolment.studentsSize() >= studentsCapacity;
    }
}
