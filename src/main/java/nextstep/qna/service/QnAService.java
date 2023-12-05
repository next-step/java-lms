package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<DeleteHistory> deleteHistories = questionRepository.findById(questionId)
            .orElseThrow(NotFoundException::new)
            .deleteQuestionAndRelatedAnswer(loginUser, LocalDateTime.now());

        deleteHistoryService.saveAll(deleteHistories);
    }
}
