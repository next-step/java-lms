package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = Question.of(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("삭제할 경우 질문의 삭제 상태를 변경")
    void 질문_삭제_상태_변경() {
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문자가 같은 경우 삭제 가능하다.")
    void 로그인_사용자와_질문자가_같은_경우_삭제_가능() {
        Q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

}
