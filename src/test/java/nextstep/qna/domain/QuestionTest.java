package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 로그인한_사용자와_질문한_사용자가_같은경우_삭제_가능하다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    void 로그인한_사용자와_질문한_사용자가_다른경우_삭제할_수_없다() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문이_삭제되면_DeleteHistory를_반환한다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(),
                NsUserTest.JAVAJIGI, LocalDateTime.now());

        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).contains(expectedDeleteHistory);
    }
}
