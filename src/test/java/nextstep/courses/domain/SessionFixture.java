package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.Students;

public class SessionFixture {

    public static Session session(int maximumStudent, int studentSize, SessionStatus sessionStatus, SessionType sessionType, String path, String name) {

        SessionParticipant participant = new SessionParticipant(maximumStudent, students(studentSize));

        SessionCoverImage coverImage = new SessionCoverImage(path, name);
        SessionCondition condition = new SessionCondition(sessionStatus, sessionType, coverImage);

        SessionTerm term = new SessionTerm(LocalDateTime.now(), LocalDateTime.now());

        SessionRequired mandatory = new SessionRequired(participant, condition, term);
        SessionOptional additional = new SessionOptional(LocalDateTime.now());

        return new Session(mandatory, additional);
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
