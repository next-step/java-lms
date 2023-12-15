package nextstep.enrolment.controller;

import nextstep.courses.domain.Course;
import nextstep.enrolment.facade.EnrolmentFacade;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.dto.SessionResponse;
import nextstep.sessions.dto.SessionsResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnrolmentController {

    private final EnrolmentFacade enrolmentFacade;

    public EnrolmentController(EnrolmentFacade enrolmentFacade) {
        this.enrolmentFacade = enrolmentFacade;
    }

    public SessionsResponse findPossibleSessionList(int cardinalNumber) {
        Course course = enrolmentFacade.findCourseByCardinalNumber(cardinalNumber);
        return new SessionsResponse(course.getPossibleSessionList().stream()
                .map(SessionResponse::of)
                .collect(Collectors.toList()));
    }

    public SessionResponse findSessionInfo(long sessionId) {
        Session session = enrolmentFacade.findSessionById(sessionId);
        return SessionResponse.of(session);
    }

    public void enrollCourse(Long sessionId, Long nsUserId) {
        Session session = enrolmentFacade.findSessionById(sessionId);
        if (!session.isRecruitingStatus()) {
            throw new IllegalArgumentException("수강 신청이 불가능한 강의 입니다.");
        }

        List<Payment> payments = enrolmentFacade.findPaymentsBySessionId(sessionId);
        Payment payment = payments.stream().filter(p -> Objects.equals(p.nsUserId(), nsUserId))
                .findAny()
                .orElse(null);

        if (Objects.nonNull(payment) && !session.isPossibleToRegister(payment.amount(), payments.size())) {
            //수강 신청 불가
            throw new IllegalArgumentException("수강 신청이 불가능합니다.");
        }

        //수강 신청

    }
}
