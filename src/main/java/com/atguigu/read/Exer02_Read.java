package com.atguigu.read;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */
public class Exer02_Read {
    public static void main(String[] args) throws IOException {

        //1.创建客户端工厂对象
        JestClientFactory jestClientFactory = new JestClientFactory();

        //2.设置连接参数
        HttpClientConfig httpClientConfig = new HttpClientConfig
                .Builder("http://hadoop102:9200")
                .build();
        jestClientFactory.setHttpClientConfig(httpClientConfig);

        //3.获取客户端对象
        JestClient jestClient = jestClientFactory.getObject();

        //4.查询数据
        Search search = new Search.Builder("{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": {\n" +
                "        \"term\": {\n" +
                "          \"sex\": \"male\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "    \"groupByClass\": {\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"class_id\",\n" +
                "        \"size\": 10\n" +
                "      }\n" +
                "    },\n" +
                "    \"groupByAge\":{\n" +
                "      \"max\": {\n" +
                "        \"field\": \"age\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"from\": 0,\n" +
                "  \"size\": 2\n" +
                "}")
                .addIndex("student1")
                .addType("_doc")
                .build();

        SearchResult res = jestClient.execute(search);

        //5.解析数据
        Long total = res.getTotal();
        System.out.println("查询总数为" + total);

        List<SearchResult.Hit<Map, Void>> hits = res.getHits(Map.class);
        for (SearchResult.Hit<Map, Void> hit : hits) {
            Map source = hit.source;
            System.out.println("++++++++++++++++++++++++++++");
            for (Object key : source.keySet()) {
                System.out.println("key:" + key + ",value:" + source.get(key) );
            }
        }

        //获取聚合组
        System.out.println("---------------------");
        MetricAggregation aggregations = res.getAggregations();
        TermsAggregation groupByClass = aggregations.getTermsAggregation("groupByClass");
        List<TermsAggregation.Entry> buckets = groupByClass.getBuckets();
        for (TermsAggregation.Entry bucket : buckets) {
            System.out.println("key:" + bucket.getKey() + ",value:" + bucket.getCount());
        }

    }
}
