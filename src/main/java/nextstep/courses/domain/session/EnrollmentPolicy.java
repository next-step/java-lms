package nextstep.courses.domain.session;

import java.util.OptionalLong;

import nextstep.courses.domain.Amount;

/**
 * 수강 신청에 대한 정책
 */
public interface EnrollmentPolicy {
    /* 남은 좌석 수 */
    OptionalLong remainingSeats(long enrolledCount);

    /** 자리 있는지 체크 (무료는 항상 true) */
    boolean hasCapacity(long enrolledCount);

    /** 결제 금액 일치 여부 (무료는 항상 true) */
    boolean matchesPayment(Amount paidAmount);

    /** 추가 수강신청 시 검증(잔여좌석 없으면 예외) */
    void validateEnrollment(long enrolledCount);

    /** 수강료 */
    Amount price();

    /** 무료 강의 여부 */
    boolean isFree();
}
