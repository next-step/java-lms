package nextstep.courses.domain.session.enroll;

import nextstep.courses.domain.session.PayType;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;

import java.text.DecimalFormat;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.SessionStatus.isNotProgressing;
import static nextstep.courses.domain.session.enroll.RecruitingStatus.*;
import static nextstep.courses.domain.session.student.SelectionStatus.*;

public class Enrolment {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    private PayType payType;
    private SessionStudents sessionStudents;
    private SessionStatus sessionStatus;
    private RecruitingStatus recruitingStatus;
    private Long amount;
    private int studentsCapacity;

    private Enrolment(PayType payType, SessionStudents sessionStudents, SessionStatus sessionStatus, RecruitingStatus recruitingStatus, Long amount, int studentsCapacity) {
        this.payType = payType;
        this.sessionStudents = sessionStudents;
        this.sessionStatus = sessionStatus;
        this.recruitingStatus = recruitingStatus;
        this.amount = amount;
        this.studentsCapacity = studentsCapacity;
    }

    public static Enrolment fromFreeSession(SessionStudents sessionStudents, SessionStatus sessionStatus, RecruitingStatus recruitingStatus) {
        return new Enrolment(FREE, sessionStudents, sessionStatus, recruitingStatus, 0L, 0);
    }

    public static Enrolment fromPaySession(SessionStudents sessionStudents, SessionStatus sessionStatus, RecruitingStatus recruitingStatus, Long amount, int studentsCapacity) {
        return new Enrolment(PAY, sessionStudents, sessionStatus, recruitingStatus, amount, studentsCapacity);
    }

    public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
        validateCommon();

        if (PAY.equals(payType)) {
            validatePaySession(enrolmentInfo);
        }

        SessionStudent student = new SessionStudent(enrolmentInfo.getSessionId(), enrolmentInfo.getNsUserId(), WAITING);
        sessionStudents.add(student);

        return student;
    }

    public void validateCommon() {
        validateRecruitingStatus();
        validateSessionStatus();
    }

    private void validatePaySession(EnrolmentInfo enrolmentInfo) {
        validatePayAmount(enrolmentInfo);
        validateCapacity();
    }

    private void validateRecruitingStatus() {
        if (isNotRecruiting(recruitingStatus)) {
            throw new IllegalArgumentException("해당 강의는 현재 모집중이 아닙니다.");
        }
    }

    private void validateSessionStatus() {
        if (isNotProgressing(sessionStatus)) {
            throw new IllegalArgumentException(String.format("해당 강의는 현재 %s입니다.", sessionStatus.description()));
        }
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
        return sessionStudents.numOfSelectedStudents() >= studentsCapacity;
    }
}
