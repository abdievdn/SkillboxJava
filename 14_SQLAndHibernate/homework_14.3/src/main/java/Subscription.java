import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Getter
    @Setter
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Getter
    @Setter
    private Date subscriptionDate;

    @EqualsAndHashCode
    @Embeddable
    public class Key implements Serializable {

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
