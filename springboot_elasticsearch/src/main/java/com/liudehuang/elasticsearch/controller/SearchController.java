package com.liudehuang.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-09-24
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-09-24
 * @UpdateRemark:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private RestHighLevelClient highLevelClient;

    @PostMapping("/query")
    public String queryIndex() throws IOException {
        //1.创建搜索请求，如果没有参数，则查询所有的索引
        SearchRequest searchRequest = new SearchRequest("test");
        //2.大多数搜索参数已添加到SearchSourceBuilder,它为搜索请求正文中的所有内容提供设置器。
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("name","刘德煌"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            // do something with the SearchHit
            System.out.println(hit.getSourceAsString());
        }
        return Arrays.stream(hits.getHits()).map(x->{
            String str = x.getSourceAsString();
            return str;
        }).collect(Collectors.toList()).toString();
    }

    @PostMapping("/match")
    public String matchIndex() throws IOException {
        SearchRequest searchRequest = new SearchRequest("book");
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("description","四大名著 事项");
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits.getHits()) {
            // do something with the SearchHit
            System.out.println(hit.getSourceAsString());
        }
        return Arrays.stream(hits.getHits()).map(x->{
            String str = x.getSourceAsString();
            return str;
        }).collect(Collectors.toList()).toString();
    }

    @PostMapping("/scroller")
    public String scrollerIndex() throws Exception{
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest(".kibana_1");
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQuery("visualization.title", "Unique Visitors by Country"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println(JSONObject.toJSONString(Arrays.asList(searchHits)));
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = highLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
            System.out.println(JSONObject.toJSONString(Arrays.asList(searchHits)));
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = highLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
        return "success";
    }
}
