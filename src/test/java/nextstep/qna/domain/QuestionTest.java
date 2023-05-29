package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthenticationException;
import nextstep.qna.exception.QnAException;
import nextstep.qna.exception.QnAExceptionCode;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        List<DeleteHistory> histories = Q1.delete(NsUserTest.JAVAJIGI);
        DeleteHistory history = new DeleteHistory(ContentType.QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now());

        Assertions.assertThat(histories).contains(history);
        Assertions.assertThat(Q1.isDeleted()).isTrue();

    }

    @Test
    void delete_이미_삭제된_질문() throws QnAException {
        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(QnAException.class)
                .hasMessage(QnAExceptionCode.NOT_EXIST_QUESTION.message());
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        Assertions.assertThatThrownBy(() -> Q2.delete(NsUser.GUEST_USER))
                .isInstanceOf(QnAException.class)
                .hasMessage(QnAExceptionCode.NOT_EXIST_AUTHENTICATION.message());

    }

    @Test
    void delete_답변이_여러개인_글_삭제_성공() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");

        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.delete(NsUserTest.JAVAJIGI);

        Assertions.assertThat(question.isDeleted()).isTrue();
        Assertions.assertThat(answer1.isDeleted()).isTrue();
        Assertions.assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    void delete_답변이_여러개인_글_중_다른_사용자의_답변이_있는_경우() throws QnAException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents2");

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        Assertions.assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(QnAException.class)
                .hasMessage(QnAExceptionCode.NOT_EXIST_AUTHENTICATION.message());

    }
}
