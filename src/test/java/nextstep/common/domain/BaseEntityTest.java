package nextstep.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class BaseEntityTest {

    @DisplayName("삭제를 진행한다. 삭제 진행 후 최종수정일자가 변경된다.")
    @Test
    void canChangeModified() {
        // given
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        BaseEntity baseEntity = new BaseEntity();

        // when
        baseEntity.delete(localDateTime);

        // then
        assertThat(baseEntity.isDeleted())
                .isTrue();
        assertThat(baseEntity.getLastModifiedAt())
                .isEqualTo(localDateTime);
    }
}
