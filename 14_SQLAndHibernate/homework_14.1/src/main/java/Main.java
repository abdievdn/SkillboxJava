import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "skillbox140821";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(
                    "select course_name, " +
                            "count(course_name)/(max(month(subscription_date)) - min(month(subscription_date)) + 1) as average_count_sale " +
                            "from purchaselist where year(subscription_date) = 2018 " +
                            "group by course_name order by course_name");
            while (resultset.next()) {
                System.out.format("%-34s\t%8s\n",
                        resultset.getString("course_name"),
                        resultset.getString("average_count_sale"));
            }
            resultset.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
