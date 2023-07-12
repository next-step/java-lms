package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.SessionBuilder.session;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        session = session()
                .build();
        students = new ArrayList<>();
        students.add(new Student(1L, 1L));
        students.add(new Student(1L, 2L));
        session.updateStudents(students);
    }

    @Test
    void registerStudent() {
        session.recruiting();
        when(sessionRepository.findById(session.getId())).thenReturn(session);
        when(studentRepository.findBySessionId(session.getId())).thenReturn(students);

        sessionService.registerStudent(1, 1);

        assertThat(session.currentStudentCount()).isEqualTo(3);
    }

    @Test
    void saveSession() {
        sessionService.saveSession(session);
    }

    @Test
    void findSession() {
        when(sessionRepository.findById(session.getId())).thenReturn(session);
        when(studentRepository.findBySessionId(session.getId())).thenReturn(students);

        Session session = sessionService.findById(1L);

        assertThat(session.getId()).isEqualTo(1);
        assertThat(session.currentStudentCount()).isEqualTo(2);
    }
}
