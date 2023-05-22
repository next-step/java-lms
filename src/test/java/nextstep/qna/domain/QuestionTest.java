package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.fixture.QuestionFixture;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    @DisplayName("로그인 사용자와 질문 작성자가 다른 경우 삭제할 수 없다.")
    void validateWriter() {
        Question question = QuestionFixture.create(NsUserTest.JAVAJIGI);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.")
    void validateAnswerWriter() {
        Question question = QuestionFixture.create(NsUserTest.JAVAJIGI);
        Answer answer = QuestionFixture.createAnswer(NsUserTest.SANJIGI, question);
        question.addAnswer(answer);

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제 가능하다.")
    void delete() throws CannotDeleteException {
        Question question = QuestionFixture.create(NsUserTest.JAVAJIGI);
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 작성자와 답변글의 모든 답변자가 같은 경우 삭제 가능하다.")
    void deleteAll() throws CannotDeleteException {
        Question question = QuestionFixture.create(NsUserTest.JAVAJIGI);
        Answer answer1 = QuestionFixture.createAnswer(NsUserTest.JAVAJIGI, question);
        Answer answer2 = QuestionFixture.createAnswer(NsUserTest.JAVAJIGI, question);
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        question.delete(NsUserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer1.isDeleted()).isTrue(),
                () -> assertThat(answer2.isDeleted()).isTrue()
        );
    }
}
