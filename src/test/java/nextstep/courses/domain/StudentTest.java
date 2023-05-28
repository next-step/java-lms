package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;

public class StudentTest {

    @BeforeEach
    void setUp() {
        Student june1 = new Student(0L, 10L);
        Student june2 = new Student(0L, 10L);
        Student june3 = new Student(0L, 10L);
        Session session = SessionCreator.create(2L, SessionStatus.OPENED);
    }
}
