package nextstep.qna.service;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.domain.QuestionTest;
import nextstep.qna.exception.QuestionDeleteAnswerExistedException;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
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

    private static Question FIXTURE_QUESTION_JAVAJIGI;
    private static Answer FIXTURE_ANSWER_JAVAJIGI;

    private static Question FIXTURE_QUESTION_BADAJIGI;
    private static Answer FIXTURE_ANSWER_SANJIGI;

    @BeforeEach
    public void setUp(){
        FIXTURE_QUESTION_JAVAJIGI = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        FIXTURE_ANSWER_JAVAJIGI = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        FIXTURE_QUESTION_JAVAJIGI.addAnswer(FIXTURE_ANSWER_JAVAJIGI);

        FIXTURE_QUESTION_BADAJIGI = new Question(2L, NsUserTest.BADAJIGI, "deep deep sea", "giant octopus");
        FIXTURE_ANSWER_SANJIGI = new Answer(22L, NsUserTest.SANJIGI, FIXTURE_QUESTION_BADAJIGI, "냉무");
        FIXTURE_QUESTION_BADAJIGI.addAnswer(FIXTURE_ANSWER_SANJIGI);
    }

    @DisplayName("자")
    @Test
    public void delete_성공(){
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;
        //when
        when(questionRepository.findById(loginUser.getId())).thenReturn(Optional.of(FIXTURE_QUESTION_JAVAJIGI));
        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, FIXTURE_QUESTION_JAVAJIGI.getId());

        //then
        assertAll("",
                () -> assertThat(FIXTURE_QUESTION_JAVAJIGI.isDeleted()).isFalse(),
                () -> assertThat(FIXTURE_QUESTION_JAVAJIGI.isDeleted()).isTrue(),
                () -> verifyDeleteHistories()
        );
    }

    @DisplayName("타인이 작성한 글 & 타인이 작성한 댓글이 달려있는 경우 예외를 던진다")
    @Test
    public void delete_다른_사람이_쓴_글과댓글() throws Exception {
        when(questionRepository.findById(FIXTURE_QUESTION_JAVAJIGI.getId())).thenReturn(Optional.of(FIXTURE_QUESTION_JAVAJIGI));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, FIXTURE_QUESTION_JAVAJIGI.getId());
        }).isInstanceOf(QuestionDeleteUnauthorizedException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findById(FIXTURE_QUESTION_JAVAJIGI.getId())).thenReturn(Optional.of(FIXTURE_QUESTION_JAVAJIGI));

        qnAService.deleteQuestion(NsUserTest.JAVAJIGI, FIXTURE_QUESTION_JAVAJIGI.getId());

        assertThat(FIXTURE_QUESTION_JAVAJIGI.isDeleted()).isTrue();
        assertThat(FIXTURE_ANSWER_JAVAJIGI.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        when(questionRepository.findById(FIXTURE_QUESTION_JAVAJIGI.getId())).thenReturn(Optional.of(FIXTURE_QUESTION_JAVAJIGI));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(NsUserTest.SANJIGI, FIXTURE_QUESTION_JAVAJIGI.getId());
        }).isInstanceOf(QuestionDeleteAnswerExistedException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, FIXTURE_QUESTION_JAVAJIGI.getId(), FIXTURE_QUESTION_JAVAJIGI.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, FIXTURE_ANSWER_JAVAJIGI.getId(), FIXTURE_ANSWER_JAVAJIGI.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
