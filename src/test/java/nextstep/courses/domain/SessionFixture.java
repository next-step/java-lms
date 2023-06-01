package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.Students;

public class SessionFixture {

    public static Session session(int maximumStudent, int studentSize, SessionStatus sessionStatus, SessionType sessionType) {
        Long id = 1L;
        SessionParticipant participant = participant(maximumStudent, studentSize);

        SessionCondition condition = condition(sessionStatus, sessionType);

        SessionTerm term = term();

        SessionRequired mandatory = new SessionRequired(participant, condition, term);
        SessionOptional additional = new SessionOptional(LocalDateTime.now());

        return new Session(id, mandatory, additional);
    }

    public static SessionParticipant participant(int maximumStudent, int studentSize) {
        return new SessionParticipant(maximumStudent, students(studentSize));
    }

    public static SessionCondition condition(SessionStatus sessionStatus, SessionType sessionType) {
        return new SessionCondition(sessionStatus, sessionType);
    }

    public static SessionTerm term() {
        return new SessionTerm(LocalDateTime.now(), LocalDateTime.now());
    }

    public static Students students(int size) {
        String studentName = "test";
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            students.add(new Student(i, studentName + i));
        }

        return new Students(students);
    }

}
