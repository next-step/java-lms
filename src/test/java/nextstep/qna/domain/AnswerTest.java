package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("삭제 메시지 전송 시 : 삭제된다")
    @Test
    public void deleteAnswer() {
        //given
        Answer answer = TestFixture.SANJIGI_ANSWER;
        Answer aliveAnswer = TestFixture.JAVAJIGI_ANSWER;

        //when
        answer.doDelete();

        //then
        assertAll("Answer 삭제 기능을 검증한다",
                () -> assertThat(answer.isDeleted())
                        .as("삭제되어야 한다")
                        .isTrue(),
                () -> assertThat(aliveAnswer.isDeleted())
                        .as("다른 Answer 는 삭제되지 않아야 한다")
                        .isFalse()
        );
    }

    @DisplayName("글 작성자를 판별해서 boolean 타입의 값을 반환한다")
    @Test
    public void isOwner() {
        //given
        Answer answer = TestFixture.SANJIGI_ANSWER;
        NsUser user = TestFixture.SANJIGI;

        //when
        boolean isOwner = answer.isOwner(user);

        //then
        assertAll("글작성자 판단 로직을 검증한다",
                () -> assertThat(isOwner)
                        .as("글 작성자에 대하여 판별시 True 리턴된다")
                        .isTrue(),
                () -> assertThat(answer.toString())
                        .as("글 작성자의 Name 이 동일함을 확인한다")
                        .contains(user.getName())
        );
    }

    @DisplayName("Answer 는 하나의 Question 을 갖는다")
    @Test
    public void relateToQuestion() {
        //given
        Answer answer = TestFixture.BADAJIGI_ANSWER;
        Question question = TestFixture.BADAJIGI_QUESTION;

        //when
        answer.relateToQuestion(question);

        //then
        assertThat(answer.toString())
                .as("이 부분도 테스트가 되어야할꺼같은데.. 테스트할수 있는 방법이 떠오르지 않습니다!")
                .contains(answer.getQuestionId().toString());
    }

    @DisplayName("DeleteHistory 객체를 생성해서 반환해야 한다")
    @Test
    public void toDeleteHistory() {
        //given
        Answer answer = TestFixture.BADAJIGI_ANSWER;

        //when
        DeleteHistory deleteHistory = answer.toDeleteHistory();

        //then
        assertThat(deleteHistory.toString())
                .as("")
                .contains(answer.getAnswerId().toString());
        assertThat(deleteHistory.toString())
                .as("")
                .contains(answer.getAnswerId().toString());
    }
}
