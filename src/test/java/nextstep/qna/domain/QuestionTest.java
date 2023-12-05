package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.service.QnAService;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문을 삭제하면 삭제 히스토리에 담긴다")
    @Test
    void deleteQuestionHistory() {
        DeleteHistory deleteHistory = Q1.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("질문을 삭제할 때 로그인된 유저와 질문의 작성자가 다르면 예외를 던진다")
    @Test
    void deleteQuestionException() {
        assertThatThrownBy(() -> Q1.deleteQuestion(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
