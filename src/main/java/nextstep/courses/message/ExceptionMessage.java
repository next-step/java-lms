package nextstep.courses.message;

public enum ExceptionMessage {
    IMAGE_NAME_TYPE_EXCEPTION("이미지 이름이 잘못되었습니다."),
    IMAGE_SIZE_EXCEPTION("이미지 파일용량을 확인하세요."),
    IMAGE_PIXEL_SIZE_EXCEPTION("이미지 최소 크기는 300X200입니다."),
    IMAGE_PIXEL_RATIO_EXCEPTION("이미지 비율은 3:2여야 합니다."),
    MAX_PARTICIPANTS_EXCEPTION("최대 참가자 수를 초과하였습니다."),
    ALREADY_REGISTER_USER_EXCEPTION("이미 등록된 사용자입니다."),
    ALREADY_ACCEPTED_USER_EXCEPTION("이미 수락된 사용자입니다."),
    ALREADY_REJECTED_USER_EXCEPTION("이미 거절된 사용자입니다."),
    END_SESSION_EXCEPTION("종료된 강의입니다."),
    MISS_MATCH_PRICE_EXCEPTION("결제 금액이 다릅니다."),
    NOT_RECRUITING_EXCEPTION("모집중인 강의가 아닙니다."),
    NOT_FOUND_USER_EXCEPTION("사용자를 찾을 수 없습니다."),
    NOT_FOUND_ENROLMENT_EXCEPTION("수강정보를 찾을 수 없습니다."),
    NOT_ACCEPTED_USER_EXCEPTION("강의에 선발되지 않은 사용자입니다."),
    NOT_REJECTED_USER_EXCEPTION("강의에 선발탈락된 사용자가 아닙니다."),
    NOT_APPROVED_USER_EXCEPTION("수강신청이 승인된 사용자입니다."),
    NOT_CANCELLED_USER_EXCEPTION("수강신청이 취소된 사용자입니다.");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
