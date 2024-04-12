package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.CourseService;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionVO;
import nextstep.users.domain.NsUserTest;
import nextstep.users.infrastructure.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setExtractBareNamePropertyMethods;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private CoverService coverService;

    @Mock
    private CourseService courseService;

    @Mock
    private StudentService studentService;

    @Mock
    private UserService userService;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    private Cover cover;
    private Course course;
    private Student student;
    private Tutor tutor;
    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        LocalDateTime now = LocalDateTime.now();

        cover = new Cover(3L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI, now, now);
        course = new Course(3L, "course1", 3L, 1, LocalDateTime.now(), LocalDateTime.now());
        student = new Student(3L, NsUserTest.JAVAJIGI);
        tutor = new Tutor(NsUserTest.SANJIGI);

        session = new FreeSession(
                3L,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                cover,
                "얼른 배우자 객체지향",
                course,
                tutor
        );
    }

    @DisplayName("유료 세션에 대해 조회한다.")
    @Test
    void testFindByIdForPaidSession() {
        // Given
        SessionVO sessionVO = new SessionVO(
                3L,
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(5),
                "READY",
                1L,
                10,
                0,
                100,
                "JAVAJIGI",
                3L,
                "test session",
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(sessionRepository.findById(3L)).thenReturn(sessionVO);
        when(coverService.findById(sessionVO.getCoverId())).thenReturn(cover);
        when(courseService.findById(sessionVO.getCourseId())).thenReturn(course);
        when(studentService.findBySessionId(3L)).thenReturn(new Students(List.of(student)));
        when(userService.findByUserId("JAVAJIGI")).thenReturn(student.getUser());

        // When
        Session session = sessionService.findById(3L);

        // Then
        assertThat(session.toVO().getId()).isEqualTo(3L);
        assertThat(session.toVO().getCoverId()).isEqualTo(cover.getId());
        assertThat(session.toVO().getCourseId()).isEqualTo(course.getId());
        assertThat(session).isInstanceOf(PaidSession.class);
    }

    @DisplayName("무료 세션에 대해 조회한다.")
    @Test
    void testFindByIdForFreeSession() {
        // Given
        SessionVO sessionVO = new SessionVO(
                3L,
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(5),
                "READY",
                1L,
                Integer.MAX_VALUE,
                0,
                0,
                "JAVAJIGI",
                3L,
                "test session",
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(sessionRepository.findById(3L)).thenReturn(sessionVO);
        when(coverService.findById(sessionVO.getCoverId())).thenReturn(cover);
        when(courseService.findById(sessionVO.getCourseId())).thenReturn(course);
        when(studentService.findBySessionId(3L)).thenReturn(new Students(List.of(student)));
        when(userService.findByUserId("JAVAJIGI")).thenReturn(student.getUser());

        // When
        Session session = sessionService.findById(3L);

        // Then
        assertThat(session.toVO().getId()).isEqualTo(3L);
        assertThat(session.toVO().getCoverId()).isEqualTo(cover.getId());
        assertThat(session.toVO().getCourseId()).isEqualTo(course.getId());
        assertThat(session).isInstanceOf(FreeSession.class);
    }

    @DisplayName("세션 삭제 테스트.")
    @Test
    void delete() {
        // Given
        long sessionId = 3L;

        SessionVO sessionVO = new SessionVO(
                sessionId,
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(5),
                "READY",
                course.getId(),
                Integer.MAX_VALUE,
                0,
                0,
                "JAVAJIGI",
                cover.getId(),
                "test session",
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Students students = new Students(List.of(student));
        DeleteHistory deleteHistory = DeleteHistory.createStudent(student.getUserId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        // sessionService.findById
        when(sessionRepository.findById(sessionId)).thenReturn(sessionVO);
        when(coverService.findById(sessionVO.getCoverId())).thenReturn(cover);
        when(courseService.findById(sessionVO.getCourseId())).thenReturn(course);
        when(studentService.findBySessionId(sessionId)).thenReturn(students);
        when(userService.findByUserId("JAVAJIGI")).thenReturn(student.getUser());
        when(studentService.deleteAll(students, NsUserTest.JAVAJIGI))
                .thenReturn(new DeleteHistoryTargets(List.of(deleteHistory)));
        when(coverService.delete(cover, NsUserTest.JAVAJIGI))
                .thenReturn(DeleteHistory.createCover(cover.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));

        // When
        sessionService.delete(sessionId, NsUserTest.JAVAJIGI);

        // Then
        verify(coverService, times(1)).delete(any(), any());
        verify(studentService, times(1)).deleteAll(any(), any());
        verify(deleteHistoryService, times(1)).saveAll(anyList());
    }
}
