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
    private AnswerUser answerUser;
    private DeleteUserInformation deleteUser;

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException {
        Question question = questionRepository.findById(questionId).orElseThrow(NotFoundException::new);

        question.isOwner(loginUser);

        answerUser = new AnswerUser(question.getAnswers());
        answerUser.isOwner(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, new DeleteUserInformation(question)));
        for (Answer answer : answerUser.getUsers()) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, new DeleteUserInformation(answer)));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }

}
