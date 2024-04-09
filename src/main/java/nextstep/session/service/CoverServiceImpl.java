package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.exception.CoverException;
import nextstep.session.domain.Cover;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.session.dto.CoverDto;
import nextstep.session.infrastructure.CoverRepository;
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
        CoverDto coverDto = coverRepository.findById(coverId);
        NsUser nsUser = userService.findByUserId(coverDto.getWriterId());

        return new Cover(
                coverDto.getId(),
                new Resolution(coverDto.getWidth(), coverDto.getHeight()),
                new ImageFilePath(coverDto.getFilePath(), coverDto.getFileName(), coverDto.getFileExtension()),
                coverDto.getByteSize(),
                coverDto.isDeleted(),
                nsUser,
                coverDto.getCreatedAt(),
                coverDto.getLastModifiedAt()
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
        return coverRepository.save(cover.toDto());
    }
}
