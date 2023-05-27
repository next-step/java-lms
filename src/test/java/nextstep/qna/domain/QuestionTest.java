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
        question.delete(loginUser.getUserCode());
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
        question.delete(user.getUserCode());
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

    @DisplayName("타인이 작성한 Answer 이 존재하는 경우 예외를 던진다")
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
            question.delete(user.getUserCode());
        }).isInstanceOf(QuestionDeleteAnswerExistedException.class)
                .as("QuestionDeleteAnswerExistedException 예외를 던져야한다")
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("작성자가 아닌경우 예외를 던진다")
    @Test
    public void validateQuestionOwner() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        NsUser user = TestFixture.SANJIGI;
        //when
        //then
        assertThatThrownBy(() -> {
            question.delete(user.getUserCode());
        }).isInstanceOf(QuestionDeleteUnauthorizedException.class)
                .as("QuestionDeleteUnauthorizedException 예외를 던져야한다")
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("Question 정보를 담은 DeleteHistory 가 생성되야한다")
    @Test
    public void toDeleteHistory() {
        //given
        Question question = TestFixture.BADAJIGI_QUESTION;
        Answer answer = TestFixture.SANJIGI_ANSWER;
        //when
        String questionDeleteHistory = question.toDeleteHistory().toString();
        String answerDeleteHistory = answer.toDeleteHistory().toString();
        //then
        assertAll("DeleteHistory 생성에 대하여 검증한다",
                () -> assertThat(questionDeleteHistory)
                        .as("question PK 가 존재하는지 검증한다")
                        .contains(question.getId().toString()),
                () -> assertThat(answerDeleteHistory)
                        .as("answer PK 가 존재하는지 검증한다")
                        .contains(answer.getAnswerId().toString())
        );
    }

    @DisplayName("Question 메 Answer 연관관계 추가시 반영되어야한다")
    @Test
    public void addAnswer() {
        //given
        Question question = TestFixture.JAVAJIGI_QUESTION;
        Answer answer = TestFixture.JAVAJIGI_ANSWER;
        //when
        question.addAnswer(answer);
        //then
        assertThat(question.isRelated(answer))
                .as("Question 은 Answer 에 연결되어있어야 한다")
                .isTrue();
        assertThat(answer.isRelated(question))
                .as("Answer 의 연관관계도 자동으로 성립된다")
                .isTrue();
    }
}
