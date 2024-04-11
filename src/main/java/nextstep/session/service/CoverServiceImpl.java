package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.exception.CoverException;
import nextstep.session.domain.*;
import nextstep.session.dto.CoverVO;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("coverService")
public class CoverServiceImpl implements CoverService {

    public static final int NO_UPDATE_COUNT = 0;
    @Resource(name = "coverRepository")
    private CoverRepository coverRepository;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public Cover findById(Long coverId) {
        CoverVO coverVO = coverRepository.findById(coverId);
        NsUser nsUser = userService.findByUserId(coverVO.getWriterId());

        return new Cover(
                coverVO.getId(),
                new Resolution(coverVO.getWidth(), coverVO.getHeight()),
                new ImageFilePath(coverVO.getFilePath(), coverVO.getFileName(), coverVO.getFileExtension()),
                coverVO.getByteSize(),
                nsUser,
                new BaseEntity(coverVO.isDeleted(), coverVO.getCreatedAt(), coverVO.getLastModifiedAt())
        );
    }

    @Override
    public DeleteHistory delete(Cover cover, NsUser requestUser) {
        cover.delete(requestUser);
        int updateResult = coverRepository.updateDeleteStatus(cover.getId(), false);
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
