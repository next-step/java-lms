package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.answer.AnswerRepository;
import nextstep.qna.domain.deleteHistory.DeleteHistory;
import nextstep.qna.domain.question.Question;
import nextstep.qna.domain.question.QuestionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("qnaService")
public class QnAService {

  private static final String NOT_FOUND_USER_ID = "요청온 유저가 작성한 질문이 존재하지 않습니다. request userId: %s";

  @Resource(name = "questionRepository")
  private QuestionRepository questionRepository;

  @Resource(name = "answerRepository")
  private AnswerRepository answerRepository;

  @Resource(name = "deleteHistoryService")
  private DeleteHistoryService deleteHistoryService;

  @Transactional
  public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
    Question question = questionRepository.findById(questionId)
        .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_USER_ID, questionId)));
    List<DeleteHistory> histories = question.delete(loginUser);
    deleteHistoryService.saveAll(histories);
  }
}
