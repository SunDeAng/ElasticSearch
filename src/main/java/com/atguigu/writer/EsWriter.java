package com.atguigu.writer;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */
public class EsWriter {

    public static void main(String[] args) throws IOException {

        //创建客户端对象
        JestClientFactory jestClientFactory = new JestClientFactory();

        //设置连接参数
        HttpClientConfig conf = new HttpClientConfig
                .Builder("http://hadoop102:9200")
                .build();
        jestClientFactory.setHttpClientConfig(conf);

        //获取客户端对象
        JestClient clinet = jestClientFactory.getObject();

        //创建index对象
        Index index = new Index.Builder("{\n" +
                "  \"id\":\"003\",\n" +
                "  \"name\":\"lisi\",\n" +
                "  \"sex\":1,\n" +
                "  \"brith\":\"2002-01-01\"\n" +
                "}")
                .index("stu")
                .type("_doc")
                .id("1007")
                .build();

//        Stu stu = new Stu("1004", "wangwu", 1, "2020-01-01");
//        Index index = new Index.Builder(stu)
//                .index("stu")
//                .type("_doc")
//                .id("1005")
//                .build();

        //写入数据
        clinet.execute(index);

        //关闭
        clinet.shutdownClient();

    }

}
