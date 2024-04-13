package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.exception.CoverException;
import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoverServiceImplTest {

    @Mock
    private CoverRepository coverRepository;

    @InjectMocks
    private CoverServiceImpl coverService;

    private long sessionId;
    private long coverId;
    private Cover cover;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        ImageFilePath imageFilePath = new ImageFilePath("/home", "mapa", "jpg");
        sessionId = 3L;
        coverId = 4L;

        cover = new Cover(coverId, sessionId, resolution, imageFilePath, 10000, NsUserTest.JAVAJIGI.getUserId(), new BaseEntity());
    }

    @DisplayName("생성자와 삭제 요청자가 동일하다면, 삭제할 수 있다.")
    @Test
    void userMatchDelete() {
        when(coverRepository.findById(coverId)).thenReturn(cover);
        when(coverRepository.updateDeleteStatus(coverId, true)).thenReturn(1);

        DeleteHistory deleteHistory = coverService.delete(coverId, NsUserTest.JAVAJIGI);
        assertThat(cover.toVO().isDeleted()).isTrue();
    }

    @DisplayName("생성자와 삭제 요청자가 동일하지 않다면, 삭제할 수 없다.")
    @Test
    void userNotMatchDelete() {
        when(coverRepository.findById(coverId)).thenReturn(cover);

        assertThatThrownBy(() -> coverService.delete(coverId, NsUserTest.SANJIGI))
                .isInstanceOf(CoverException.class);
    }
}
