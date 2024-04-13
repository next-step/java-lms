package nextstep.session.domain;

import nextstep.common.domain.DeleteHistory;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverTest {

    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    @Test
    void throwIllegalArgumentExceptionIfImageSizeOverThan1MB() {
        // given
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "temp", "jpg");

        // then
        assertThatThrownBy(() -> new Cover(0L, resolution, imageFilePath, 10000000, NsUserTest.JAVAJIGI.getUserId()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("삭제하면 상태가 변경된다.")
    @Test
    void changeStatusWhenDelete() {
        // given
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "temp", "jpg");
        Cover cover = new Cover(0L, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId());

        // when
        cover.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(cover.toVO().isDeleted()).isTrue();
    }
}
