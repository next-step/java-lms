package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class SessionTest {

    private Session session;
    @BeforeEach
    void setUp() {
        session = new Session(1L,"2021-01-01", "2021-01-02", Image.DEFAULT_IMAGE_URL,Cost.FREE, Status.OPENED);
    }

    @Test
    void test_강의는_기간을_갖는다() {
        //when
        Period period = session.getPeriod();

        //then
        assertThat(period).isNotNull();
    }

    @Test
    void test_강의_생성시_이미지_정보를_갖는다() {
        // when
        Image image = session.getImage();

        // then
        assertThat(image).isNotNull();
    }

    @Test
    void test_강의는_비용정보를_갖는다() {
        // when
        Cost cost = session.getCost();

        // then
        assertThat(cost).isNotNull();
    }

    @Test
    void test_강의는_상태를_갖는다() {
        // when
        Status status = session.getStatus();

        // then
        assertThat(status).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"READY", "CLOSED"})
    void test_수강신청_강의상태_모집중_아닐시_실패(Status status) {
        // given
        Session session = new Session(1L,"2021-01-01", "2021-01-02", Image.DEFAULT_IMAGE_URL, Cost.FREE, status);

        // then
        assertThatCode(() -> session.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("수강신청은 강의 모집 중에만 가능합니다.");
    }

    @Test
    void test_수강신청_강의상태_모집중_성공() {
        // when
        session.register(NsUserTest.JAVAJIGI);

        // then
        assertThat(session.hasStudent(NsUserTest.JAVAJIGI)).isTrue();
    }
}
