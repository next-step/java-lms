package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @DisplayName("질문을 삭제할 수 있다.")
    @Test
    void delete() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.GREEN, "title", "content");
        List<DeleteHistory> result = question.delete(NsUserTest.GREEN);
        assertThat(result).isEqualTo(
                List.of(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.GREEN, LocalDateTime.now()))
        );
    }
}
