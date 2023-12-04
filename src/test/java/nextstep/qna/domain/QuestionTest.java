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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("[Question.deleteIfWriter()] 삭제를 요청하면 -> 자신을 삭제 상태로 만든다.")
    public void deleteTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        question.deleteIfWriter(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();

    }

    @Test
    @DisplayName("[Question.deleteIfWriter()] 질문하지 않은 사용자가 삭제를 요청하면 -> CannotDeleteException을 던진다.")
    public void deleteWrongWriterTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");

        assertThatThrownBy(() -> {
            question.deleteIfWriter(NsUserTest.SANJIGI);
        })
                .isInstanceOf(CannotDeleteException.class);

    }

    @Test
    @DisplayName("[Question.deleteIfWriter()] 삭제를 요청하면 -> 자기 자신과 삭제 상태로 바뀐 답변들의 정보를 준다.")
    public void deleteInfoTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        DeleteHistory questionDelete = new DeleteHistory(ContentType.QUESTION, question.getId(), NsUserTest.JAVAJIGI);

        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        DeleteHistory answer1Delete = new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.JAVAJIGI);

        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer2");
        DeleteHistory answer2Delete = new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.JAVAJIGI);

        question.addAnswer(answer1);
        question.addAnswer(answer2);

        assertThat(question.deleteIfWriter(NsUserTest.JAVAJIGI))
                .hasSameElementsAs(List.of(questionDelete, answer1Delete, answer2Delete));
    }

    @Test
    @DisplayName("[Question.deleteIfWriter()] 질문자 외 다른 답변자가 작성한 답변이 달려 있는 질문에게 삭제를 요청할 때 -> CannotDeleteException을 던짐")
    public void deleteAQuestionWhichHasAnotherAnswerTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "hello", "world!");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "answer1");
        question.addAnswer(answer);

        assertThatThrownBy(() -> {
            question.deleteIfWriter(NsUserTest.JAVAJIGI);
        })
                .isInstanceOf(CannotDeleteException.class);
    }
}