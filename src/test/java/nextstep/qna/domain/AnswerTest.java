package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private NsUser user;
    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() throws Exception {
        user = NsUserTest.JAVAJIGI;
        question = new Question(1L, user, "title1", "contents1");
        answer = new Answer(11L, user, question, "Answers Contents1");
    }

    @Test
    void delete_성공() throws Exception {
        LocalDateTime deletedDateTime = LocalDateTime.now();
        DeleteHistory deleteHistory = answer.delete(user, deletedDateTime);

        Assertions.assertTrue(answer.isDeleted());
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), deletedDateTime));
    }

    @Test
    void delete_답변자와_일치하지_않으면_실패() throws Exception {
        NsUser otherUser = NsUserTest.SANJIGI;

        assertThatThrownBy(() -> answer.delete(otherUser, LocalDateTime.now()))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
