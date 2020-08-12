package com.atguigu.writer;

import com.atguigu.bean.Student1;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */
public class Exer01_Writer {

    public static void main(String[] args) throws IOException {

        //1.获取工厂
        JestClientFactory jestClientFactory = new JestClientFactory();

        //2.进行配置连接信息
        HttpClientConfig conf = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        conf = conf;
        jestClientFactory.setHttpClientConfig(conf);

        //3.获取客户端
        JestClient client = jestClientFactory.getObject();

        //插入
/*        Index index = new Index.Builder("{\n" +
                "  \"class_id\":\"0317\",\n" +
                "  \"stu_id\":\"100\",\n" +
                "  \"name\":\"test\",\n" +
                "  \"sex\":\"female\",\n" +
                "  \"age\":18,\n" +
                "  \"favo\":\"女,洗脚,男\"\n" +
                "}")
                .index("student1")
                .type("_doc")
                .id("100")
                .build();
*/
        Student1 student1 = new Student1("0317", "1001", "test1001", "male", 18, "球,洗脚");
        Index index = new Index.Builder(student1)
                .index("student1")
                .type("_doc")
                .id("1001")
                .build();
        client.execute(index);

        //关闭客户端
        client.shutdownClient();

    }

}
