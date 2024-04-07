package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.AnswerRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        final LocalDateTime requestDatetime = LocalDateTime.now();

        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (!question.isAllSameAnswererWith(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : question.getAnswers()) {
            deleteHistories.add(answer.delete(loginUser, requestDatetime));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }
}
