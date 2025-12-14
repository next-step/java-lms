package nextstep.qna.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.QnADomainService;
import nextstep.qna.domain.Question;
import nextstep.qna.exception.unchecked.NotFoundException;
import nextstep.qna.service.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("qnaService")
public class QnAService {

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(long requesterId, long questionId, LocalDateTime fixedDeletedDateTime) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(NotFoundException::new);

        List<DeleteHistory> deleteHistories = new QnADomainService()
                .deleteQuestion(requesterId, question, fixedDeletedDateTime);

        deleteHistoryService.saveAll(deleteHistories);
    }
}
