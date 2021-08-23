import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class PurchaseList {

    @Getter
    @Setter
    private String studentName;

    @Getter
    @Setter
    private String courseName;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    private Date subscriptionDate;
}
