package com.atguigu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Sdaer
 * @Date: 2020-08-10
 * @Desc:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {

    private String id;
    private String name;
    private int sex;
    private String birth;

}
