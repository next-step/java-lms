package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문자와 로그인 사용자가 동일하지 않다면 삭제할 수 없다.")
    @Test
    void if_user_is_not_same_as_login_user_then_can_not_delete_question() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("답변이 없고 질문만 존재할 경우 삭제할 수 있다.")
    @Test
    void should_delete_if_question_has_no_answer() {
        DeleteHistories expected = new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION,
                                                                                 0L,
                                                                                 NsUserTest.JAVAJIGI,
                                                                                 LocalDateTime.now())));
        DeleteHistories actual = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("질문자와 답변자가 동일하다면 질문 데이터와 답변 데이터 모두 삭제된다.")
    @Test
    void delete_question_and_answers_if_login_user_is_owner() {
        List<DeleteHistory> givenQuestionsAndAnswers = List.of(new DeleteHistory(ContentType.QUESTION,
                                                                                 0L,
                                                                                 NsUserTest.JAVAJIGI,
                                                                                 LocalDateTime.now()),
                                                               new DeleteHistory(ContentType.ANSWER,
                                                                                 null,
                                                                                 NsUserTest.JAVAJIGI,
                                                                                 LocalDateTime.now()),
                                                               new DeleteHistory(ContentType.ANSWER,
                                                                                 null,
                                                                                 NsUserTest.JAVAJIGI,
                                                                                 LocalDateTime.now()));
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        DeleteHistories expected = new DeleteHistories(givenQuestionsAndAnswers);

        DeleteHistories actual = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(actual).isEqualTo(expected);
    }
}
