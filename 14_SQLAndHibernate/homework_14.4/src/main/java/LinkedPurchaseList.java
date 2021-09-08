import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "linked_purchaselist")
@NoArgsConstructor
@AllArgsConstructor
public class LinkedPurchaseList {

    @Getter
    @Setter
    @EmbeddedId
    private Key id;

    public LinkedPurchaseList(Key id) {
        this.id = id;
    }

    @Getter
    @Setter
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student studentId;

    @Getter
    @Setter
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
