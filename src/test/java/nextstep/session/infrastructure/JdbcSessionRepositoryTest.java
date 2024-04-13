package nextstep.session.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {

    private final long sessionId = 3L;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionRepository sessionRepository;
    private Resolution resolution;
    private ImageFilePath imageFilePath;
    private Course course;
    private Session freeSession;
    private Session paidSession;
    private Cover cover;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);

        resolution = new Resolution(300, 200);
        imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        course = new Course("Course1", 1L, 3);
        cover = new Cover(sessionId, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());

        freeSession = new FreeSession(
                sessionId,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                cover,
                "얼른 배우자 객체지향",
                course.getId(),
                new Tutor(NsUserTest.JAVAJIGI)
        );

        paidSession = new PaidSession(
                sessionId,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                cover,
                "얼른 배우자 객체지향",
                course.getId(),
                100,
                1_000_000L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("무료 세션에 대한 저장과 조회를 할 수 있다.")
    @Test
    void saveAndFindForFreeSession() {
        // when
        long savedId = sessionRepository.save(freeSession);

        Session session = sessionRepository.findById(savedId);

        // then
        assertThat(session.toVO().getId())
                .isEqualTo(savedId);
    }

    @DisplayName("유료 세션에 대한 저장과 조회를 할 수 있다.")
    @Test
    void saveAndFindForPaidSession() {
        // when
        long savedId = sessionRepository.save(paidSession);
        Session session = sessionRepository.findById(savedId);

        // then
        assertThat(session.toVO().getId())
                .isEqualTo(savedId);
    }

    @DisplayName("세션에 대한 기본 프로퍼티를 변경할 수 있다.")
    @Test
    void editBasicProperties() {
        // when
        String changeSessionName = "천천히 배우자 객체지향";
        long savedId = sessionRepository.save(freeSession);

        SessionUpdateBasicPropertiesVO updateDto =
                new SessionUpdateBasicPropertiesVO(null, null, changeSessionName);
        int updateCount = sessionRepository.updateSessionBasicProperties(savedId, updateDto);
        Session session = sessionRepository.findById(savedId);

        // then
        assertThat(updateCount)
                .isEqualTo(1);
        assertThat(session.toVO().getSessionName())
                .isEqualTo(changeSessionName);
    }

    @DisplayName("변경요소가 없는 상태로 변경을 시도하는 것은 불가능하다.")
    @Test
    void cannotEditWithNothing() {
        // when
        long savedId = sessionRepository.save(freeSession);

        SessionUpdateBasicPropertiesVO updateDto =
                new SessionUpdateBasicPropertiesVO(null, null, null);

        // then
        Assertions.assertThatThrownBy(() -> sessionRepository.updateSessionBasicProperties(savedId, updateDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("학생을 등록할 수 있다.")
    @Test
    void applyStudent() {
        // given
        long savedId = sessionRepository.save(freeSession);
        Student student = new Student(savedId, NsUserTest.JAVAJIGI);

        // when
        sessionRepository.apply(savedId, student);
        Session foundSession = sessionRepository.findById(savedId);
        Optional<Student> optionalStudent = foundSession.getStudents().findStudent(student);

        // then
        assertThat(optionalStudent).isPresent();
    }

    @DisplayName("학생의 등록을 취소할 수 있다.")
    @Test
    void unapplyStudent() {
        // given
        long savedId = sessionRepository.save(freeSession);
        Student student = new Student(savedId, NsUserTest.JAVAJIGI);

        // when
        sessionRepository.apply(savedId, student);
        sessionRepository.unapply(savedId, student);
        Session foundSession = sessionRepository.findById(savedId);
        Optional<Student> optionalStudent = foundSession.getStudents().findStudent(student);

        // then
        assertThat(optionalStudent).isNotPresent();
    }

    @DisplayName("커버를 추가할 수 있다.")
    @Test
    void addMultipleCovers() {
        // given
        long savedId = sessionRepository.save(freeSession);
        Cover newCover = new Cover(savedId, resolution, imageFilePath, 500, NsUserTest.JAVAJIGI.getUserId());
        Cover newCover2 = new Cover(savedId, resolution, imageFilePath, 500, NsUserTest.JAVAJIGI.getUserId());

        // when
        sessionRepository.addCover(savedId, newCover);
        sessionRepository.addCover(savedId, newCover2);
        Session session = sessionRepository.findById(savedId);

        // then
        assertThat(session.getCovers().size())
                .isEqualTo(3);
    }
}
