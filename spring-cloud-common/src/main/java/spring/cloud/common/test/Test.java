package spring.cloud.common.test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {
    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";

    public static void main(String[] args) throws Exception {
        //测试token的失效时间
//        User user = new User();
//        User cloneUser = (User)user.clone();
//        String[] name1 = {"1","2","3","4"};
//        String[] name2 = new String[8];
//        System.arraycopy(name1,1,name2,2,3);
//        String old = generateToken("zhangsan", 1);
//        Map<String, String> stringStringMap = validateToken(old);
        String new2 = generateToken("zhangsan", -1);
        Map<String, String> stringStringMap2 = validateToken(new2);

    }

    public static String generateToken(String user, int k) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", new Random().nextInt());
        map.put("user", user);
        String jwt;
        if (k > 0) {
            jwt = getString(map);
        } else {
            jwt = getStringNoTim(map);
        }
        return jwt;
    }

    private static String getString(HashMap<String, Object> map) {
        return Jwts.builder()
                .setSubject("user info")
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getStringNoTim(HashMap<String, Object> map) {
        //当前时间 + 1分钟
//        LocalDate localDate = LocalDate.now();
//        localDate.minusDays(1);
//        Date date1 = localDate2Date(localDate);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.format(date);



        return Jwts.builder()
                .setSubject("user info").setClaims(map)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Map<String, String> validateToken(String token) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (token != null) {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            String id = String.valueOf(body.get("id"));
            String user = (String) (body.get("user"));
            // todo 获取user和id之后可以结合数据库？或者请求中的user信息？ 对用户做校验 -ps 如何判断用户是否窃取别人的token呢？
            map.put("id", id);
            map.put("user", user);
            if (StringUtils.isEmpty(user)) {
                System.out.println("user is error, please check");
            }
        } else {
            System.out.println("user is error, please check");
        }
        return map;
    }


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate
     */
    public static Date localDate2Date(LocalDate localDate) {
        //定义格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串格式转为LocalDate格式
        LocalDateTime parse = LocalDateTime.parse(localDate.toString(), dateTimeFormatter);
        //获取时间地区ID
        ZoneId zoneId = ZoneId.systemDefault();
        //转换为当地时间
        ZonedDateTime zonedDateTime = parse.atZone(zoneId);
        //转为Date类型
        return Date.from(zonedDateTime.toInstant());

    }


}
