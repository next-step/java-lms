package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.Session;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcSession extends BaseEntity {
    private Long courseId;
    private Integer capacity;
    private String status;
    private BigDecimal price;
    private Date startDate;
    private Date endDate;
    private String imagePath;
    private Blob imageFile;
    private Long creatorId;

    public JdbcSession() {
        super();
    }

    public JdbcSession(Long id, Long courseId, Integer capacity, String status,
                       BigDecimal price, Date startDate, Date endDate,
                       String imagePath, Blob imageFile, Long creatorId,
                       Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.capacity = capacity;
        this.status = status;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
        this.imageFile = imageFile;
        this.creatorId = creatorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }


    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Session toDomain() {
        try {
            return new Session(getId(), courseId, startDate, endDate, imagePath, imageFile, status, price.longValue(), capacity, creatorId, getCreatedAt(), getUpdatedAt());
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Failed to convert SessionEntity to Session", e);
        }
    }
}
