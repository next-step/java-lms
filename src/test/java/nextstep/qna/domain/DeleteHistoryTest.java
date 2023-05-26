package nextstep.qna.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class DeleteHistoryTest {

    @DisplayName("삭제한 Questions 와 Answers 대상들에 대한 DeleteHistory List 를생성한다")
    @Test
    public void deleteHistoryHelperTest() {
        //given
        List<Answer> answers = List.of(TestFixture.SANJIGI_ANSWER, TestFixture.JAVAJIGI_ANSWER);
        List<Question> questions = List.of(TestFixture.JAVAJIGI_QUESTION, TestFixture.JAVAJIGI_QUESTION, TestFixture.BADAJIGI_QUESTION);

        //when
        List<DeleteHistory> actual = DeleteHistory.deleteHistoryHelper(questions, answers);

        //then
        assertAll("",
                () -> assertThat(actual)
                        .as("삭제한 엔티티의 숫자와 (answers count + questions count) 생성된  DeleteHistory 들의 원소의 갯수가 같음을 검증한다")
                        .hasSize(answers.size() + questions.size()),
                () -> assertThat(actual)
                        .as("answers 에 대한 DeleteHistory 가 존재함을 검증한다")
                        .containsAll(answers.stream()
                                .map(Answer::toDeleteHistory)
                                .collect(Collectors.toSet())
                        ),
                () -> assertThat(actual)
                        .as("questions 에 대한 DeleteHistory 가 존재함을 검증한다")
                        .containsAll(questions.stream()
                                .map(Question::toDeleteHistory)
                                .collect(Collectors.toSet())
                        )
        );
    }
}