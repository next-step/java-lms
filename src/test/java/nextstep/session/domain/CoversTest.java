package nextstep.session.domain;

import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoversTest {

    private Cover cover1;
    private Cover cover2;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        Course course = new Course("Course1", 1L, 3);

        cover1 = new Cover(1L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());
        cover2 = new Cover(1L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());
    }

    @DisplayName("삭제 시 삭제 이력 객체를 반환한다.")
    @Test
    void getDeleteHistoryWhenDelete() {
        // given
        Covers covers = new Covers();

        // when
        covers.add(cover1);
        covers.add(cover2);
        DeleteHistoryTargets deleteHistoryTargets = covers.deleteAll(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistoryTargets.asList()).hasSize(2);
    }
}
