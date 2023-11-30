package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private static final LocalDateTime FIXED_NOW = LocalDateTime.of(2023, 11, 30, 17, 0, 4);

    @Test
    @DisplayName("[Question.deleteIfWriter()] 삭제를 요청하면 -> 자신을 삭제 상태로 만든다.")
    public void deleteTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        question.deleteIfWriter(NsUserTest.JAVAJIGI, FIXED_NOW);

        assertThat(question.isDeleted()).isTrue();

    }

    @Test
    @DisplayName("[Question.deleteIfWriter()] 질문하지 않은 사용자가 삭제를 요청하면 -> 거부한다.")
    public void deleteWrongWriterTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        question.deleteIfWriter(NsUserTest.SANJIGI, FIXED_NOW);

        assertThat(question.isDeleted()).isFalse();

    }

    @Test
    @DisplayName("[Question.deleteIfWriter()] 삭제를 요청하면 -> 자기 자신과 삭제 상태로 바뀐 답변들의 정보를 준다.")
    public void deleteInfoTest() throws CannotDeleteException {

        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        DeleteHistory questionDelete = new DeleteHistory(ContentType.QUESTION, question.getId(), NsUserTest.JAVAJIGI, FIXED_NOW);

        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        DeleteHistory answer1Delete = new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.JAVAJIGI, FIXED_NOW);

        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer2");
        DeleteHistory answer2Delete = new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.JAVAJIGI, FIXED_NOW);

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        assertThat(question.deleteIfWriter(NsUserTest.JAVAJIGI, FIXED_NOW))
                .hasSameElementsAs(List.of(questionDelete, answer1Delete, answer2Delete));
    }
}