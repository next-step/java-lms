package nextstep.qna.service;

import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @BeforeEach
    @DisplayName("데이터 SetUp")
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("삭제 성공 테스트")
    public void delete_성공() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());
        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    @DisplayName("다른 사람 쓴 글 삭제 불가 테스트")
    public void delete_다른_사람_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("질문자 답변자 동일 한 사람 삭제 테스트")
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, question.getId());
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    @DisplayName("질문자 답변자 동일 한 사람 삭제 테스트")
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, question.getId());
        }).isInstanceOf(UnAuthorizedException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}