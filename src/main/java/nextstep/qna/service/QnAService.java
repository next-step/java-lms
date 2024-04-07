package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.question.AnswerRepository;
import nextstep.qna.domain.history.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.qna.domain.question.QuestionRepository;
import nextstep.users.domain.NsUser;

@Service("qnaService")
public class QnAService {

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(final NsUser loginUser, final long questionId) throws CannotDeleteException {
        final Question question = questionRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);

        final List<DeleteHistory> deleteHistories = question.deleteBy(loginUser, LocalDateTime.now());

        deleteHistoryService.saveAll(deleteHistories);
    }
}
