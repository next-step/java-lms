package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(new TextBody(NsUserTest.JAVAJIGI, "contents1", false), "title1");
    public static final Question Q2 = new Question(new TextBody(NsUserTest.SANJIGI, "contents2", false), "title2");

    private Question question;

    @BeforeEach
    void create() {
        question = new Question(new TextBody(NsUserTest.JAVAJIGI, "contents1", false), "title1");
        question.addAnswer(new Answer(new TextBody(NsUserTest.JAVAJIGI, "Answers Contents1", false), QuestionTest.Q1));
    }

    @Test
    @DisplayName("로그인한 사용자와 질문자가 다르면 에러를 던진다.")
    void delete_권한_확인() {
        Assertions.assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI, question.getId()))
            .isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("로그인한 사용자와 질문자가 같은 경우 질문 상태를 변경하고 삭제 히스토리를 리턴")
    void delete_성공() {
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI, question.getId());

        assertThat(deleteHistories).containsOnly(
            new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now())
        );
        assertThat(question.getTextBody().isDeleted()).isTrue();

    }
}
