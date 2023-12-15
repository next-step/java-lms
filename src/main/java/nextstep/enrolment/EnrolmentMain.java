package nextstep.enrolment;

import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.service.CourseService;
import nextstep.enrolment.controller.EnrolmentController;
import nextstep.enrolment.facade.EnrolmentFacade;
import nextstep.payments.service.PaymentService;
import nextstep.sessions.dto.SessionResponse;
import nextstep.sessions.dto.SessionsResponse;
import nextstep.sessions.infrastructure.JdbcSessionRepository;
import nextstep.sessions.service.SessionService;
import org.springframework.jdbc.core.JdbcTemplate;

public class EnrolmentMain {

    public static void main(String[] args) {
        EnrolmentFacade enrolmentFacade = new EnrolmentFacade(
                new CourseService(new JdbcCourseRepository(new JdbcTemplate())),
                new SessionService(new JdbcSessionRepository(new JdbcTemplate())),
                new PaymentService()
        );
        EnrolmentController enrolmentController = new EnrolmentController(enrolmentFacade);

        //수강 신청 전에 현재 오픈된 기수의 과정을 볼 수 있는 로직
        SessionsResponse sessionsResponse = enrolmentController.findPossibleSessionList(17);
        //특정 강의에 대한 세부 내용을 확인하는 로직
        SessionResponse sessionInfo = enrolmentController.findSessionInfo(1L);
        //수강 신청
        Long nsUserId = 1L;
        enrolmentController.enrollCourse(sessionInfo.id(), nsUserId);
    }
}
