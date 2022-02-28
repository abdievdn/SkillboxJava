import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UsersQueue {

    public static final int USERS_COUNT = 20;

    public static void main(String[] args) {

        RedisStorage redis = new RedisStorage();
        redis.init();
        for (int i = 1; i <= USERS_COUNT; i++) {
            redis.addUser(i, String.valueOf(i));
        }

//        redis.listUsers();
//        redis.getUserPriority("5");
//        redis.getUserPriority("14");
//        redis.getUserPriority("20");
//        redis.getUserPriority("21");
//        redis.setUserPriority(5, "14");
//        redis.getUserPriority("14");

        for (int i = 0; i < 20; i++) {
            for (int score = 1; score <= USERS_COUNT; score++) {

                printUser(redis, score);

                // Random case for payed queue
                int r = new Random().nextInt(11);
                if (r == 10) {
                    //Random user selection
                    int u = new Random().nextInt(20) + 1;
                    String userId = String.valueOf(u);
                    int uScore = redis.getUserScore(userId);
                    System.out.println(">> User " + userId + "\t payed the queue!");
                    if (uScore > score) {
                        //shift UP from score
                        for (int shift = uScore; shift > score; shift--) {
                            String shiftUser = redis.getUserByScore(shift - 1);
                            redis.setUserScore(shift, shiftUser);
                        }
                        redis.setUserScore(score, userId);
                        printUser(redis, score);
                    }
                    if (uScore < score) {
                        //shift DOWN from score
                        for (int shift = score; shift > uScore; shift--) {
                            String shiftUser = redis.getUserByScore(shift);
                            redis.setUserScore(shift - 1, shiftUser);
                        }
                        redis.setUserScore(score, userId);
                        printUser(redis, score);
                    }
                    if (uScore == score) {
                        printUser(redis, score);
                    }
                }
            }
        }

        redis.shutdown();
    }

    private static void printUser(RedisStorage redis, int score) {
        String user = redis.getUserByScore(score);
        System.out.println("User " + user + "\t\t Score: " + redis.getUserScore(user));
        pause();
    }

    private static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
