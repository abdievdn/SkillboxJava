import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class LinkedPurchaseList {

    @Getter
    @Setter
    @EmbeddedId
    private Key id;

    @Getter
    @Setter
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @ManyToOne
    private Student studentId;

    @Getter
    @Setter
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @ManyToOne
    private Course courseId;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Key implements Serializable {

        @Getter
        @Setter
        @Column(name = "student_id")
        private int studentId;

        @Getter
        @Setter
        @Column(name = "course_id")
        private int courseId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return studentId == key.studentId && courseId == key.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }

}
