package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;

    @BeforeEach
    void create() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    @DisplayName("로그인한 사용자와 질문자가 다르면 에러를 던진다.")
    void delete_권한확인() {
        Assertions.assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("로그인한 사용자와 질문자가 같은 경우 질문 상태를 변경하고 삭제 히스토리를 리턴")
    void delete_성공() {

        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).containsOnly(
                new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI)
        );
        assertThat(question.isDeleted()).isTrue();

    }
}
