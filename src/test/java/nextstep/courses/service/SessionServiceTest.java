package nextstep.courses.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionStudentsRepository;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Thumbnail;
import nextstep.courses.exception.SessionException.SessionFeeNotEqualException;
import nextstep.courses.exception.SessionException.SessionFullException;
import nextstep.courses.exception.SessionException.SessionNotOpenException;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.service.DeleteHistoryService;
import nextstep.qna.service.QnAService;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionStudentsRepository studentsRepository;

    @InjectMocks
    private SessionService sessionService;

    private final Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25));
    private final Thumbnail thumbnail = new Thumbnail(1, "thumbnail.png", 1024L * 1024L, 300, 200);
    private final Student javajigi = new Student(1L, NsUserTest.JAVAJIGI.getId());
    private final Student sanjigi = new Student(1L, NsUserTest.SANJIGI.getId());

    private SessionType sessionType = SessionType.determineSessionType(true, 1L, 2, 100);
    private SessionStatus sessionStatus = SessionStatus.RECRUITING;

    private Session session;
    private SessionStudents students;


    @BeforeEach
    public void setUp() {
        session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);
        students = new SessionStudents(new ArrayList<>(List.of(javajigi)));
    }

    @Test
    public void enroll_정원_남았을_시_성공() {
        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        when(studentsRepository.findBySessionId(session.getSessionId())).thenReturn(students);

        assertThat(session.getSessionId()).isEqualTo(1L);
        sessionService.enroll(session.getSessionId(), NsUserTest.SANJIGI, 100);

        assertThat(students.getStudents().size()).isEqualTo(2);

        verify(studentsRepository).save(sanjigi);
    }

    @Test
    public void enroll_정원_찼을_시_실패() {
        sessionType = SessionType.determineSessionType(true, 1L, 1, 100);

        session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);

        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        when(studentsRepository.findBySessionId(session.getSessionId())).thenReturn(students);

        assertThat(session.getSessionId()).isEqualTo(1L);
        assertThatThrownBy(() -> sessionService.enroll(session.getSessionId(), NsUserTest.SANJIGI, 100))
                .isInstanceOf(SessionFullException.class);
    }

    @Test
    public void enroll_모집_중_아닐_시_실패() {
        sessionStatus = SessionStatus.PREPARING;

        session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);

        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        when(studentsRepository.findBySessionId(session.getSessionId())).thenReturn(students);

        assertThat(session.getSessionId()).isEqualTo(1L);
        assertThatThrownBy(() -> sessionService.enroll(session.getSessionId(), NsUserTest.SANJIGI, 100))
                .isInstanceOf(SessionNotOpenException.class);
    }


    @Test
    public void enroll_강의료_불일치_시_실패() {
        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        when(studentsRepository.findBySessionId(session.getSessionId())).thenReturn(students);

        assertThat(session.getSessionId()).isEqualTo(1L);
        assertThatThrownBy(() -> sessionService.enroll(session.getSessionId(), NsUserTest.SANJIGI, 300))
                .isInstanceOf(SessionFeeNotEqualException.class);
    }

}
