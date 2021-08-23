import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Getter
    @Setter
    @EmbeddedId
    private Key id;

    @Getter
    @Setter
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Getter
    @Setter
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
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
    }
}
