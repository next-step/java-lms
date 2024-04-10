package nextstep.session.infrastructure;

import nextstep.common.domain.BaseEntity;
import nextstep.courses.domain.Course;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionDto;
import nextstep.session.dto.SessionUpdateBasicPropertiesDto;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionRepository sessionRepository;
    private Resolution resolution;
    private ImageFilePath imageFilePath;
    private Course course;
    private Session freeSession;
    private Session paidSession;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);

        resolution = new Resolution(300, 200);
        imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        course = new Course("Course1", 1L, 3);

        freeSession = new FreeSession(
                1L,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI),
                "얼른 배우자 객체지향",
                course,
                new Tutor(NsUserTest.JAVAJIGI)
        );

        paidSession = new PaidSession(
                1L,
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI),
                "얼른 배우자 객체지향",
                course,
                100,
                1_000_000L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("무료 세션에 대한 저장과 조회를 할 수 있다.")
    @Test
    void saveAndFindForFreeSession() {
        // when
        long savedId = sessionRepository.save(freeSession.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(sessionDto.getId())
                .isEqualTo(savedId);
    }

    @DisplayName("유료 세션에 대한 저장과 조회를 할 수 있다.")
    @Test
    void saveAndFindForPaidSession() {
        // when
        long savedId = sessionRepository.save(paidSession.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(sessionDto.getId())
                .isEqualTo(savedId);
    }

    @DisplayName("세션에 대한 기본 프로퍼티를 변경할 수 있다.")
    @Test
    void editBasicProperties() {
        // when
        String changeSessionName = "천천히 배우자 객체지향";
        long savedId = sessionRepository.save(freeSession.toDto());

        SessionDto sessionDto = sessionRepository.findById(savedId);
        SessionUpdateBasicPropertiesDto updateDto =
                new SessionUpdateBasicPropertiesDto(null, null, changeSessionName);
        int updateCount = sessionRepository.updateSessionBasicProperties(sessionDto, updateDto);
        SessionDto updatedSessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(updateCount)
                .isEqualTo(1);
        assertThat(updatedSessionDto.getSessionName())
                .isEqualTo(changeSessionName);
    }

    @DisplayName("변경요소가 없는 상태로 변경을 시도하는 것은 불가능하다.")
    @Test
    void cannotEditWithNothing() {
        // when
        long savedId = sessionRepository.save(freeSession.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        SessionUpdateBasicPropertiesDto updateDto =
                new SessionUpdateBasicPropertiesDto(null, null, null);

        // then
        Assertions.assertThatThrownBy(() -> sessionRepository.updateSessionBasicProperties(sessionDto, updateDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("커버 이미지를 변경할 수 있다.")
    @Test
    void canChangeCover() {
        // when
        long savedId = sessionRepository.save(freeSession.toDto());
        SessionDto sessionDto = sessionRepository.findById(savedId);

        Cover newCover = new Cover(100L, resolution, imageFilePath, 100_000L, NsUserTest.JAVAJIGI, new BaseEntity());

        int updateCount = sessionRepository.updateCover(sessionDto, newCover);
        SessionDto updatedSessionDto = sessionRepository.findById(savedId);

        // then
        assertThat(updatedSessionDto.getCoverId())
                .isEqualTo(newCover.getId());
    }
}
