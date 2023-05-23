package nextstep.qna.service;

import java.util.List;
import javax.annotation.Resource;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.AnswerRepository;
import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.Question;
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
    Question question = questionRepository.findById(questionId)
        .orElseThrow(NotFoundException::new);

    if (!question.checkQuestionOwner(loginUser)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }
    if (!question.checkAnswersOwner(loginUser)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    List<DeleteHistory> deleteHistories = question.delete();
    deleteHistoryService.saveAll(deleteHistories);
  }
}
