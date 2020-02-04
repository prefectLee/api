package com.lee.api;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class TestRedis {

    public static  final String GATEWAY_ROUTES="secsws_routes";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
     public void addTemplateRoute(){
        RouteDefinition definition = new RouteDefinition();
        definition.setId("zjgws");
        URI uri = UriComponentsBuilder.fromHttpUrl("http://www.tmall.com/").build().toUri();
        definition.setUri(uri);
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName("Path");
        Map<String,String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern","/zjgws/**");
        predicateDefinition.setArgs(predicateParams);

        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String,String> filterParams = new HashMap<>(8);
        filterDefinition.setName("RequestRateLimiter");
        filterParams.put("redis-rate-limiter.replenishRate","2");
        filterParams.put("redis-rate-limiter.burstCapacity","3");
        filterParams.put("key-resolver","#{@remoteAddrKeyResolver}");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Arrays.asList(predicateDefinition));
        definition.setFilters(Arrays.asList(filterDefinition));
        System.out.println("definition:"+ JSON.toJSONString(definition));
        stringRedisTemplate.opsForHash().put(GATEWAY_ROUTES,"TEST",JSON.toJSONString(definition));

     }

     @Test
    public void testSave(){
        String temp = "{\n" +
                "  \"filters\": [\n" +
                "    {\n" +
                "      \"args\": {\n" +
                "        \"key-resolver\": \"#{@remoteAddrKeyResolver}\",\n" +
                "        \"redis-rate-limiter.burstCapacity\": \"3\",\n" +
                "        \"redis-rate-limiter.replenishRate\": \"2\"\n" +
                "      },\n" +
                "      \"name\": \"RequestRateLimiter\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"id\": \"wjws\",\n" +
                "  \"metadata\": {\n" +
                "    \n" +
                "  },\n" +
                "  \"order\": 0,\n" +
                "  \"predicates\": [\n" +
                "    {\n" +
                "      \"args\": {\n" +
                "        \"pattern\": \"/wjws/**\"\n" +
                "      },\n" +
                "      \"name\": \"Path\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"uri\": \"http://www.taobao.com\"\n" +
                "}";
         RestTemplate restTemplate = new RestTemplate();
         String url="http://127.0.0.1:8080/route/add";
         //String result = restTemplate.postForObject(url,JSON.parse(temp),String.class);
         //System.out.println(result);
     }
}
