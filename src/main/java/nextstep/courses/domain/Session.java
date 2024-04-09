package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

import static nextstep.courses.domain.SessionState.PREPARING;
import static nextstep.courses.domain.SessionState.RECRUITING;

public class Session {
    private Long id;
    private Course course;
    private SessionDuration sessionDuration;
    private Image coverImage;
    private SessionPayType sessionPayType;
    private SessionState state;
    private Long sessionFee;
    private SessionStudent sessionStudent;

    public static Session defaultOf(Long id, Course course, LocalDate startDate, LocalDate endDate, Image coverImage, SessionPayType sessionPayType, Integer maxStudent, Long sessionFee) {
        return new Session(id, course, new SessionDuration(startDate, endDate), coverImage, sessionPayType, PREPARING, maxStudent, sessionFee, new SessionStudent(maxStudent));
    }

    public Session(Long id, Course course, SessionDuration sessionDuration, Image coverImage,
        SessionPayType sessionPayType, SessionState state, Integer maxStudent, Long sessionFee,
        SessionStudent sessionStudent) {
        validatePayType(sessionPayType, maxStudent, sessionFee);
        this.id = id;
        this.course = course;
        this.sessionDuration = sessionDuration;
        this.coverImage = coverImage;
        this.sessionPayType = sessionPayType;
        this.state = state;
        this.sessionFee = sessionFee;
        this.sessionStudent = sessionStudent;
    }

    public void openRegister(){
        state = RECRUITING;
    }

    public void addStudent(NsUser newStudent, Payment payment){
        checkRegisterableState();
        checkSessionCapacity();
        checkAlreadyPaid(payment);
        this.sessionStudent.add(newStudent);
    }

    private void validatePayType(SessionPayType sessionPayType, Integer maxStudent, Long sessionFee) {
        if(sessionPayType.isPaid() && maxStudent < 1){
            throw new IllegalArgumentException("유료 강의는 최대 수강인원 설정이 필요합니다.");
        }

        if(sessionPayType.isPaid() && sessionFee <= 0){
            throw new IllegalArgumentException("유료 강의는 수강료 설정이 필요합니다.");
        }
    }

    private void checkSessionCapacity() {
        if(sessionPayType.isPaid() && !sessionStudent.canAcceptNewStudent()){
            throw new IllegalArgumentException("더이상 신규 학생을 받을 수 없습니다.");
        }
    }

    private void checkRegisterableState() {
        if(!state.isRecruiting()){
            throw new IllegalArgumentException("현재 모집 중이 아닙니다.");
        }
    }


    private void checkAlreadyPaid(Payment payment){
        if(!payment.isName(sessionFee)){
            throw new IllegalArgumentException("수강료가 일치하지 않습니다.");
        }
    }

    public static class SessionDuration {
        private LocalDate startDate;
        private LocalDate endDate;

        public SessionDuration(LocalDate startDate, LocalDate endDate) {
            validateSessionDate(startDate, endDate);
            this.startDate = startDate;
            this.endDate = endDate;
        }

        private void validateSessionDate(LocalDate startDate, LocalDate endDate){
            if(startDate.isAfter(endDate)){
                throw new IllegalArgumentException("시작일보다 종료일이 먼저올 수 없습니다.");
            }
        }

    }
}
