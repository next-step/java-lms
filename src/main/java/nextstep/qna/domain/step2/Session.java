package nextstep.qna.domain.step2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Session {

    private SessionDate sessionDate;
    private int maxStudent = 0;
    private Students students = new Students(new ArrayList<>());
    private BufferedImage coverImage = null;
    private SessionType type = SessionType.FREE;
    private int price = 0;
    private SessionStatus status = SessionStatus.PREPARE;

    public Session() {
    }

    public Session(SessionDate sessionDate, SessionType type, int price, int maxStudent) {
        this.sessionDate = sessionDate;
        this.maxStudent = maxStudent;
        this.type = type;
        this.price = price;
    }

    public Session(SessionDate sessionDate, SessionType type, int price, int maxStudent, Students students) {
        this.sessionDate = sessionDate;
        this.maxStudent = maxStudent;
        this.type = type;
        this.price = price;
        this.students = students;
    }

    public void setCoverImage(String path) throws IllegalArgumentException, IOException {
        File coverFile = new File(path);
        checkExtention(coverFile);
        try {
            checkFileSize(coverFile);
            coverImage= coverImage(coverFile);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static void checkExtention(File coverFile) {
        String extension = coverFile.getName().split("\\.")[1];
        if(!("gif".equals(extension) || "png".equals(extension) || "jpg".equals(extension) || "jpeg".equals(extension))) {
            throw new IllegalArgumentException("gif, png, jpg, jpeg 파일만 업로드 가능합니다.");
        }
    }

    private static BufferedImage coverImage(File coverFile) throws IOException {
        BufferedImage image = ImageIO.read(coverFile);
        int width = image.getWidth();
        int height = image.getHeight();
        if(width < 300 || height < 200) {
            throw new IllegalArgumentException("400 * 300 이상의 이미지만 업로드 가능합니다.");
        }
        if(width/height != 3/2) {
            throw new IllegalArgumentException("3:2 비율의 이미지만 업로드 가능합니다.");
        }
        return image;
    }

    private static void checkFileSize(File coverImage) throws IOException {
        Path filePath = Paths.get(coverImage.getAbsolutePath());
        if(Files.size(filePath) > 1024 * 1024) {
            throw new IllegalArgumentException("1MB 이하의 파일만 업로드 가능합니다.");
        }
    }

    public void addStudent(Student student, int payPrice) {
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

    public void setSessionType(SessionType sessionType) {
        this.type = sessionType;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public void setChargeSessionInfo(int maxStudent, int price) {
        this.type = SessionType.CHARGE;
        this.maxStudent = maxStudent;
        this.price = price;
    }

    public Students students() {
        return students;
    }
}
