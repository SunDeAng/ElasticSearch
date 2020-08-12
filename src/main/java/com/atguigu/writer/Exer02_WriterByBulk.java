package com.atguigu.writer;

import com.atguigu.bean.Student1;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */
public class Exer02_WriterByBulk {

    public static void main(String[] args) throws IOException {

        //1.获取工厂
        JestClientFactory jestClientFactory = new JestClientFactory();

        //2.进行配置连接信息
        HttpClientConfig conf = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        conf = conf;
        jestClientFactory.setHttpClientConfig(conf);

        //3.获取客户端
        JestClient client = jestClientFactory.getObject();

        Student1 student1 = new Student1("0318", "1001", "test1001", "male", 18, "球,洗脚");
        Student1 student2 = new Student1("0318", "1002", "test1002", "female", 18, "球,洗脚");
        Student1 student3 = new Student1("0318", "1003", "test1003", "male", 18, "球,洗脚");

        Index index1 = new Index.Builder(student1).id("10001").build();
        Index index2 = new Index.Builder(student1).id("10002").build();
        Index index3 = new Index.Builder(student1).id("10003").build();

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("student1")
                .defaultType("_doc")
                .addAction(index1)
                .addAction(index2)
                .addAction(index3)
                .build();

        client.execute(bulk);
        //关闭客户端
        client.shutdownClient();

    }

}
