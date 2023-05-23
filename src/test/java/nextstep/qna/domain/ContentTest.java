package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ContentTest {

    private static final Content C1 = new Content("title", "contents", NsUserTest.JAVAJIGI);

    @DisplayName("컨텐츠 생성 시 작성자가 없을 경우 에러를 던진다.")
    @Test
    void 작성자가없는컨텐츠_생성시_에러() {
        assertThatThrownBy(() -> {
            new Content("title", "contents", null);
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 작성자여부_검증() {
        assertAll(
                () -> assertThat(C1.isOwner(NsUserTest.JAVAJIGI)).isTrue(),
                () -> assertThat(C1.isOwner(NsUserTest.SANJIGI)).isFalse()
        );
    }
}