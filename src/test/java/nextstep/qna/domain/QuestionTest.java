package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.qna.exception.QuestionDeleteAnswerExistedException;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionTest {
    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("Answer 가 존재하지 않는 경우 삭제에 성공한다")
    @Test
    public void noAnswerDeleteSuccess() {
        //given
        NsUser loginUser = TestFixture.BADAJIGI;
        Question question = TestFixture.BADAJIGI_QUESTION;
        Answer answer = TestFixture.BADAJIGI_ANSWER;

        //when
        question.delete(loginUser);
        //then
        assertAll("삭제성공을 검증한다",
                () -> assertThat(question.isDeleted())
                        .as("삭제 질의시 True 를 리턴해야한다")
                        .isTrue(),
                () -> assertThat(answer.isDeleted())
                        .as("연관되어있지 않은 Answer 는 삭제되지 않는다")
                        .isFalse()
        );
    }

    @DisplayName("작성자 본인이 작성한 Answer 만 존재하는 경우 삭제에 성공한다")
    @Test
    public void selfAnswerDeleteSuccess() {
        //given
        NsUser user = TestFixture.JAVAJIGI;
        Question question = TestFixture.JAVAJIGI_QUESTION;
        Answer answer = TestFixture.JAVAJIGI_ANSWER;
        question.addAnswer(answer);

        //when
        question.delete(user);

        //then
        assertAll("삭제성공을 검증한다",
                () -> assertThat(question.isDeleted())
                        .as("Question 삭제에 성공해야한다")
                        .isTrue(),
                () -> assertThat(answer.isDeleted())
                        .as("연관된 Answer 도 모두 삭제되어야한다")
                        .isTrue()
        );
    }

    @DisplayName("타인이 작성한 Answer 이 존재하는 경우 QuestionDeleteAnswerExistedException 를 던진다")
    @Test
    public void validateAnswers() {
        //given
        NsUser user = TestFixture.JAVAJIGI;
        Question question = TestFixture.JAVAJIGI_QUESTION;
        Answer answer1 = TestFixture.JAVAJIGI_ANSWER;
        question.addAnswer(answer1);
        Answer answer2 = TestFixture.SANJIGI_ANSWER;
        question.addAnswer(answer2);
        Answer answer3 = TestFixture.BADAJIGI_ANSWER;
        question.addAnswer(answer3);

        //when
        //then
        assertThatThrownBy(() -> {
            question.delete(user);
        }).isInstanceOf(QuestionDeleteAnswerExistedException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("작성자가 아닌경우 QuestionDeleteUnauthorizedException 를 던진다")
    @Test
    public void validateQuestionOwner() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        NsUser user = TestFixture.SANJIGI;
        //when
        //then
        assertThatThrownBy(() -> {
            question.delete(user);
        }).isInstanceOf(QuestionDeleteUnauthorizedException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("자신의 정보에 맞게 DeleteHistory 를 생성한다")
    @Test
    public void toDeleteHistory() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        Answer answer = TestFixture.SANJIGI_ANSWER;

        //when
        String questionDeleteHistory = question.toDeleteHistory().toString();
        String answerDeleteHistory = answer.toDeleteHistory().toString();

        //then
        assertAll("",
                () -> assertThat(questionDeleteHistory)
                        .as("PK 가 존재하는지 검증한다")
                        .contains(question.getId().toString()),
                () -> assertThat(answerDeleteHistory)
                        .as("PK 가 존재하는지 검증한다")
                        .contains(answer.getId().toString())
        );
    }
}
