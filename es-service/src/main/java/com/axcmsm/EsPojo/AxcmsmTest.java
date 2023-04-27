package com.axcmsm.EsPojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@AllArgsConstructor
@ToString
public class AxcmsmTest {
    private String id;
    private String info;
    private Name name;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Name{
        String firstName;
        String lastName;
    }
}
