package nextstep.courses.service;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.dto.EnrolmentInfo;
import nextstep.courses.domain.session.enums.PayType;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.dto.SelectInfo;

import static nextstep.courses.domain.session.enums.PayType.*;

public class FreeSessionService implements SessionService {

    private final SessionRepository sessionRepository;
    private final SessionStudentRepository studentRepository;

    public FreeSessionService(SessionRepository sessionRepository, SessionStudentRepository studentRepository) {
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean supports(PayType payType) {
        return FREE.equals(payType);
    }

    @Override
    public void enroll(EnrolmentInfo enrolmentInfo) {
        FreeSession session = sessionRepository.findFreeSessionById(enrolmentInfo.getSessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + enrolmentInfo.getSessionId()));

        SessionStudent student = session.enroll(enrolmentInfo);
        studentRepository.save(student);
    }

    @Override
    public SessionStudent selection(SelectInfo selectInfo) {
        FreeSession session = sessionRepository.findFreeSessionById(selectInfo.getSessionId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 강의가 없습니다. 강의 아이디 :: " + selectInfo.getSessionId()));
        SessionStudent student = studentRepository.findById(selectInfo.getStudentId())
            .orElseThrow(() -> new IllegalArgumentException("일치하는 수강생이 없습니다. 수강생 아이디 :: " + selectInfo.getSessionId()));

        SessionStudent selectionResult = session.selection(student, selectInfo.getSelectionStatus());
        studentRepository.updateSelectionResult(selectionResult);

        return selectionResult;
    }
}
