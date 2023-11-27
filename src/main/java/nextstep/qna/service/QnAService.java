package nextstep.qna.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionRepository;
import nextstep.qna.error.CannotDeleteException;
import nextstep.qna.error.NotFoundException;
import nextstep.users.domain.NsUser;

@Service
public class QnAService {

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionRepository.findById(questionId)
                                              .orElseThrow(NotFoundException::new);
        deleteHistoryService.saveAll(question.delete(loginUser, questionId));
    }
}
