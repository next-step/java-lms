package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.exception.CoverException;
import nextstep.session.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("coverService")
public class CoverServiceImpl implements CoverService {

    public static final int NO_UPDATE_COUNT = 0;
    @Resource(name = "coverRepository")
    private CoverRepository coverRepository;

    @Override
    public Cover findById(long coverId) {
        return coverRepository.findById(coverId);
    }

    @Override
    public Cover findBySessionId(long sessionId) {
        return coverRepository.findBySessionId(sessionId);
    }

    @Override
    public DeleteHistory delete(Cover cover, NsUser requestUser) {
        cover.delete(requestUser);
        int updateResult = coverRepository.updateDeleteStatus(cover.getId(), true);
        validateUpdateResult(updateResult);

        return DeleteHistory.createCover(cover.getId(), requestUser, LocalDateTime.now());
    }

    private void validateUpdateResult(int updateResult) {
        if (updateResult <= NO_UPDATE_COUNT) {
            throw new CoverException("이미 삭제 되었거나, 삭제할 대상이 없습니다.");
        }
    }

    @Override
    public Long save(Cover cover) {
        return coverRepository.save(cover);
    }
}
