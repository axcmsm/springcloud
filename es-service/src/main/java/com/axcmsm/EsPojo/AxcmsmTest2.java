package com.axcmsm.EsPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: com.axcmsm.EsPojo.Axcmsm_Test
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */

@Data
@NoArgsConstructor
@ToString
public class AxcmsmTest2 {
    private String email;
    private String id;
    private String info;
    private String name;
    private String type;
    //自动补全的内容 例如：将名称和类型放入，作为提示词
    private List<String> suggestion;

    public AxcmsmTest2(String email, String id, String info, String name, String type) {
        this.email = email;
        this.id = id;
        this.info = info;
        this.name = name;
        this.type = type;
        //自动补全的内容 例如：将名称和类型放入，作为提示词
        this.suggestion = Arrays.asList(this.type,this.name);
    }
}
