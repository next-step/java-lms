package nextstep.qna.service;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.qna.exception.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.qna.domain.repository.AnswerRepository;
import nextstep.qna.domain.repository.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestion(questionId);
        List<DeleteHistory> deleteHistories = question.delete(loginUser);
        deleteHistoryService.saveAll(deleteHistories);
    }

    private Question findQuestion(long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);
    }
}
