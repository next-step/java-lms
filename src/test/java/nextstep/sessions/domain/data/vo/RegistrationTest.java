package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.registration.*;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RegistrationTest {

    @Test
    void 이미_선발된_인원_선발() {
        Registration registration = registration(SelectionType.SELECTION, ApprovalType.BEFORE_APPROVAL);
        assertThatThrownBy(registration::select)
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미 선발된 인원입니다.");
    }

    @Test
    void 선발되지_않은_인원_승인() {
        Registration registration = registration(SelectionType.BEFORE_SELECTION, ApprovalType.BEFORE_APPROVAL);
        assertThatThrownBy(registration::approve)
            .isInstanceOf(SessionsException.class)
            .hasMessage("선발된 인원만 승인할 수 있습니다.");
    }

    @Test
    void 이미_승인된_인원_승인() {
        Registration registration = registration(SelectionType.SELECTION, ApprovalType.APPROVAL);
        assertThatThrownBy(registration::approve)
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미 승인된 인원입니다.");
    }

    @Test
    void 미선발되지_않은_인원_수강_취소() {
        Registration registration = registration(SelectionType.SELECTION, ApprovalType.BEFORE_APPROVAL);
        assertThatThrownBy(registration::cancel)
            .isInstanceOf(SessionsException.class)
            .hasMessage("미선발된 인원이 아닙니다.");
    }

    private Registration registration(SelectionType selectionType, ApprovalType approvalType) {
        return new Registration(null, null, null, null, selectionType, approvalType);
    }
}
