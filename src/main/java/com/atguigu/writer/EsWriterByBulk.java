package com.atguigu.writer;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */
public class EsWriterByBulk {
    public static void main(String[] args) throws IOException {

        //1.Getting the JsetClientFactory
        JestClientFactory jestClientFactory = new JestClientFactory();

        //2.The conf must be setted to jestClientFactory
        HttpClientConfig conf = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(conf);

        //3.Create the client
        JestClient client = jestClientFactory.getObject();

        //4.Create the object of bulk
        Stu stu1 = new Stu("1011", "zs", 1, "2000-01-01");
        Stu stu2 = new Stu("1012", "ls", 0, "2001-01-01");
        Stu stu3 = new Stu("1013", "ww", 0, "2002-01-01");

        Index index1 = new Index.Builder(stu1).id("1011").build();
        Index index2 = new Index.Builder(stu2).id("1012").build();
        Index index3 = new Index.Builder(stu3).id("1013").build();

        Bulk bulk = new Bulk.Builder()
                .defaultIndex("stu")
                .defaultType("_doc")
                .addAction(index1)
                .addAction(index2)
                .addAction(index3)
                .build();

        //5.Start
        client.execute(bulk);

        //6.close
        client.shutdownClient();

    }
}
