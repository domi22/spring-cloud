package spring.cloud.gateway.auth;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public static void main(String[] args) {
        String minTime = ZonedDateTime.now().minusHours(1).format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(minTime);
    }
}
