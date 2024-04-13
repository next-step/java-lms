package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.courses.domain.Course;
import nextstep.session.domain.*;
import nextstep.session.type.SessionStatusType;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private CoverService coverService;

    @Mock
    private StudentService studentService;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    private Cover cover;
    private Course course;
    private Student student;
    private Students students;
    private Tutor tutor;
    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        Duration duration = new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        LocalDateTime now = LocalDateTime.now();

        cover = new Cover(3L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());
        course = new Course(3L, "course1", 3L, 1, LocalDateTime.now(), LocalDateTime.now());
        student = new Student(3L, NsUserTest.JAVAJIGI);
        students = new Students(List.of(student));
        tutor = new Tutor(NsUserTest.SANJIGI);

        session = new FreeSession(
                3L,
                duration,
                cover,
                SessionStatus.of(SessionStatusType.valueOf("READY")),
                "얼른 배우자 객체지향",
                course.getId(),
                tutor,
                students,
                new BaseEntity()
        );
    }

    @DisplayName("세션을 삭제한다.")
    @Test
    void delete() {
        // Given
        long sessionId = 3L;

        when(sessionRepository.findById(sessionId)).thenReturn(session);
        when(coverService.delete(cover.getId(), NsUserTest.JAVAJIGI)).thenReturn(
                DeleteHistory.createCover(cover.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())
        );
        when(studentService.findBySessionId(sessionId)).thenReturn(
                students
        );
        when(studentService.deleteAll(students, NsUserTest.JAVAJIGI)).thenReturn(
                new DeleteHistoryTargets(List.of(DeleteHistory.createStudent(student.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())))
        );

        // When
        sessionService.delete(sessionId, NsUserTest.JAVAJIGI);

        // Then
        assertThat(session.toVO().isDeleted()).isTrue();
    }
}
