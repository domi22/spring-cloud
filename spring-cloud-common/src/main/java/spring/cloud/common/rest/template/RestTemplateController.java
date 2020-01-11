package spring.cloud.common.rest.template;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import spring.boot.common.function.rest.template.beans.Person;
import spring.boot.common.function.rest.template.beans.ResultObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring5 推荐使用webClient
 * https://blog.csdn.net/u012881904/article/details/81185231
 * get请求参见：https://blog.csdn.net/itguangit/article/details/78825505
 * 常见请求：https://cloud.tencent.com/developer/article/1343880
 * 通用的请求：主要是指execute()和exchange()方法
 * --------------------------------------------------
 * exchange():主要只针对 get post delete put 四种方法
 * excute():所有的get、post、delete、put、options、head、exchange方法最终调用的都是excute方法，doExecute方法
 * 具体参见：https://www.cnblogs.com/caolei1108/p/6169950.html
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getEntity")
    public Map<String, Object> getRestTemplate() {
        String url = "http://localhost:8887/spring-boot-common-function/rest/person/{name}";
        //这里的参数name也可以是多个，多个也可以用map集合封装。
        //ResponseEntity<Map>写成Map<String,Object>则和Map.class无法映射
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(url, Map.class, "zhangsan");
        //可以先判断响应码，然后再去获取请求体
        Map<String, Object> body = new HashMap<>();
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            body = forEntity.getBody();
        }
        return body;
    }

    /**
     * getForObject 和 getForEntity 用法几乎相同,指示返回值返回的是 响应体,省去了我们 再去 getBody() .
     */
    @GetMapping("/getObj")
    public ResultObj getObjeTemplate() {
        String url = "http://localhost:8887/spring-boot-common-function/rest/person2/{name}/{id}";
        //这里的参数name也可以是多个，多个也可以用map集合封装。
        Map<String, Object> pathParam = new HashMap<>(4);
        pathParam.put("name", "tetsObj");
        pathParam.put("id", 346);
        ResultObj resultObj = restTemplate.getForObject(url, ResultObj.class, pathParam);
        return resultObj;
    }

    @GetMapping("/postObj/{name}/{id}")
    public ResultObj postObjeTemplate(@PathVariable String name, @PathVariable Integer id) {
        String url = "http://localhost:8887/spring-boot-common-function/rest/post";
        Map qmap = new HashMap();
        qmap.put("name", name);
        qmap.put("id", id);
        //请求的body参数qmap需要接口方在controller类用 @RequestBody 注解接收
        ResultObj resultObj = restTemplate.postForObject(url, qmap, ResultObj.class);
        return resultObj;
    }


    @GetMapping("/exchangeG/{name}")
    public Person exchangeGetTemplate(@PathVariable String name) {
        String url = "http://localhost:8887/spring-boot-common-function/rest/person2/{name}/{id}";

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("nb",name);
        headers.set("mn","sd");

        //设置url参数
        Map<String, Object> pathParam = new HashMap<>();
        pathParam.put("name", name);
        pathParam.put("id", 386);

        //承载请求头，不包含请求体的rntity
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Person.class,pathParam);
        Person body = new Person();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            body = responseEntity.getBody();
        }
        return body;
    }

    @GetMapping("/exchangeP/{name}")
    public ResultObj exchangePostTemplate(@PathVariable String name) {
        String url = "http://localhost:8887/spring-boot-common-function/rest/post";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("nb","zhsdangsa");
        headers.set("mn","sd");

        Person person = new Person();
        person.setId(11);
        person.setName(name);
        HttpEntity<Person> httpEntity = new HttpEntity<>(person,headers);
        ResponseEntity<ResultObj> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResultObj.class);
        ResultObj body = new ResultObj();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            body = responseEntity.getBody();
        }
        return body;
    }




   /* -----------下面是模拟远程调用的方法-------------- */
    @GetMapping("/person/{name}")
    public Map<String, Object> getPerson(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName(name);
        person.setId(999);
        list.add(person);
        map.put("list", list);
        return map;
    }

    @GetMapping("/person2/{name}/{id}")
    public Person getPerson2(@PathVariable String name, @PathVariable Integer id,HttpServletRequest request) {
        Person person = new Person();
        person.setName(name);
        person.setId(id);
        return person;
    }

    @PostMapping("/post")
    public Map<String, Object> getPerson2(@RequestBody Person person, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", person.getName());
        map.put("id", person.getId());
        return map;
    }

}
