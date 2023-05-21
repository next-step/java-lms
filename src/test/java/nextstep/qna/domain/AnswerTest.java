package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

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

    @DisplayName("글 작성자를 판별해서 boolean 값을 반환한다")
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

    @DisplayName("Answer 에 다른 Question 연관관계를 맺으면 연관관계가 업데이트된다")
    @Test
    public void relateToQuestion() {
        //given
        //when
        //then
        fail();
    }

    @DisplayName("DeleteHistory 객체를 생성해서 반환해야 한다")
    @Test
    public void toDeleteHistory() {
        //given
        //when
        //then
        fail();
    }
}
