package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;

public class Session {

    private Long id;
    private Long courceId;
    private SessionDate sessionDate;
    private SessionType type;
    private int price;
    private int maxStudent;
    private CoverImage coverImage;
    private Students students = new Students(new ArrayList<>());
    private SessionStatus status = SessionStatus.PREPARE;

    public Session() {
    }

    public Session(Long id, Long courceId, SessionDate sessionDate, SessionType type, int price, int maxStudent, CoverImage coverImage, SessionStatus status) {
        this.id = id;
        this.courceId = courceId;
        this.sessionDate = sessionDate;
        this.type = type;
        this.price = price;
        this.maxStudent = maxStudent;
        this.coverImage = coverImage;
        this.status = status;
    }

    public Session(Long id, Long courceId, SessionDate sessionDate, SessionType type, int price, int maxStudent, SessionStatus status) {
        this.id = id;
        this.courceId = courceId;
        this.sessionDate = sessionDate;
        this.type = type;
        this.price = price;
        this.maxStudent = maxStudent;
        this.status = status;
    }

    public void addStudent(NsUser student, int payPrice) {
        if(status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 가능합니다.");
        }
        if (type == SessionType.CHARGE) {
            if (checkMaxStudent()) {
                throw new IllegalArgumentException("수강생이 초과되었습니다.");
            }
            if (!checkPrice(payPrice)) {
                throw new IllegalArgumentException("수강료가 일치하지 않습니다.");
            }
        }
        students.add(student);
    }

    private boolean checkMaxStudent() {
        return students.size() >= maxStudent;
    }


    boolean checkPrice(int payPrice) {
        if(type == SessionType.CHARGE) {
            return price == payPrice;
        }
        return true;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.status = sessionStatus;
    }

//    public void setSessionType(SessionType sessionType) {
//        this.type = sessionType;
//    }

//    public void setMaxStudent(int maxStudent) {
//        this.maxStudent = maxStudent;
//    }

//    public void setChargeSessionInfo(int maxStudent, int price) {
//        this.type = SessionType.CHARGE;
//        this.maxStudent = maxStudent;
//        this.price = price;
//    }

    public Students students() {
        return students;
    }

    public void setCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }
}
