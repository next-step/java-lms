package nextstep.users.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NsUserTypeTest {

    @DisplayName("다양한 NsUserType의 수강 승인 가능/불가능 테스트")
    @ParameterizedTest(name = "{index} => approver={0}, applicant={1}, expected={2}")
    @CsvSource({
        "INSTRUCTOR, WOOAH_TECH_COURSE, true",
        "INSTRUCTOR, WOOAH_TECH_CAMP_PRO, true",
        "INSTRUCTOR, UNSELECTED, false",
        "INSTRUCTOR, INSTRUCTOR, false",
        "INSTRUCTOR, UNKNOWN, false",
        "WOOAH_TECH_COURSE, WOOAH_TECH_CAMP_PRO, false",
        "WOOAH_TECH_CAMP_PRO, WOOAH_TECH_COURSE, false",
        "UNSELECTED, WOOAH_TECH_COURSE, false",
        "UNKNOWN, WOOAH_TECH_COURSE, false"
    })
    void testCanApprove(NsUserType approver, NsUserType applicant, boolean expected) {
        assertThat(approver.canApprove(applicant)).isEqualTo(expected);
    }

    @DisplayName("다양한 NsUserType의 수강 승인 취소 가능/불가능 테스트")
    @ParameterizedTest(name = "{index} => approver={0}, applicant={1}, expected={2}")
    @CsvSource({
        "INSTRUCTOR, WOOAH_TECH_COURSE, false",
        "INSTRUCTOR, WOOAH_TECH_CAMP_PRO, false",
        "INSTRUCTOR, UNSELECTED, true",
        "INSTRUCTOR, INSTRUCTOR, false",
        "INSTRUCTOR, UNKNOWN, false",
        "WOOAH_TECH_COURSE, UNSELECTED, false",
        "WOOAH_TECH_CAMP_PRO, INSTRUCTOR, false",
        "UNSELECTED, WOOAH_TECH_CAMP_PRO, false",
        "UNKNOWN, UNSELECTED, false"
    })
    void testCanCancelApproval(NsUserType approver, NsUserType applicant, boolean expected) {
        assertThat(approver.canCancel(applicant)).isEqualTo(expected);
    }
}
