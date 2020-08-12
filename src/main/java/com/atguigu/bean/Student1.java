package com.atguigu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student1 {

    private String class_id;
    private String stu_id;
    private String name;
    private String sex;
    private int age;
    private String favo;

}
