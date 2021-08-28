import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    private Key id;

    @Getter
    @Setter
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Getter
    @Setter
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Id
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Key implements Serializable {

        @Getter
        @Setter
        @Column(name = "student_name")
        private String studentId;

        @Getter
        @Setter
        @Column(name = "course_name")
        private String courseId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(studentId, key.studentId) && Objects.equals(courseId, key.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
