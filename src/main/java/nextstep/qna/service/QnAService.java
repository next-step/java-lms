package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.*;
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
        Question question = getQuestion(loginUser, questionId);

        question = question.delete(loginUser);

        List<DeleteHistory> deleteHistories = deletedQuestionAndAnswers(question);
        deleteHistoryService.saveAll(deleteHistories);
    }

    private Question getQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return question;
    }

    private List<DeleteHistory> deletedQuestionAndAnswers(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        DeleteHistory deleteHistoryFromQuestion = deleteHistoryFromQuestion(question);
        List<DeleteHistory> deleteHistoriesFromAnswers = deleteHistoryFromAnswers(question.getAnswers());

        deleteHistories.add(deleteHistoryFromQuestion);
        deleteHistories.addAll(deleteHistoriesFromAnswers);
        return deleteHistories;
    }

    private DeleteHistory deleteHistoryFromQuestion(Question question) {
        return DeleteHistory.from(question);
    }

    private List<DeleteHistory> deleteHistoryFromAnswers(List<Answer> answers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(DeleteHistory.from(answer));
        }

        return deleteHistories;
    }
}
