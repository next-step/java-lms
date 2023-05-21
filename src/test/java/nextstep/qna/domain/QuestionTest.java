package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문한 유저가 아닌 다른 유저가 질문을 삭제 시도할 경우 예외가 발생한다.")
    public void 질문_유저가_다른_경우_예외_발생_테스트() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, 0))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문을 삭제하면 질문의 상태도 삭제로 변경")
    public void 질문_삭제하면_상태를_TRUE로_변경_테스트() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI, 0);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제를 성공하면 올바른 삭제 이력을 반환한다.")
    public void 삭제_성공하면_삭제_이력_반환_테스트() throws CannotDeleteException {
        List<DeleteHistory> delete = Q1.delete(NsUserTest.JAVAJIGI, 0);
        assertThat(delete).contains(
                new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
