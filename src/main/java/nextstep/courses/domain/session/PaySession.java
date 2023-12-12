package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static nextstep.courses.domain.session.SessionStatus.*;

public class PaySession extends Session {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    private Long amount;
    private int studentsCapacity;

    public PaySession(Long id, PayType payType, SessionStatus sessionStatus, CoverImage coverImage, LocalDate startDate, LocalDate endDate, Long amount, int studentsCapacity, Students students) {
        super(id, payType, sessionStatus, coverImage, students, startDate, endDate);
        this.amount = amount;
        this.studentsCapacity = studentsCapacity;
    }

    @Override
    public Student enroll(EnrolmentInfo enrolmentInfo) {
        validateStatus();
        validatePayAmount(enrolmentInfo);
        validateCapacity();

        Student student = new Student(id, enrolmentInfo.getNsUserId());
        students.add(student);

        return student;
    }

    private void validateStatus() {
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
        return students.size() >= studentsCapacity;
    }

}
