package com.axcmsm.mapping;

/**
 * ClassName: com.axcmsm.mapping.EsMappingConstant
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
public class EsMappingConstant {
    public static final String AXCMSM_TEST_MAPPING = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"info\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"email\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false \n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"object\", \n" +
            "        \"properties\": {\n" +
            "          \"firstName\":{\n" +
            "            \"type\":\"keyword\",\n" +
            "            \"index\":false\n" +
            "          },\n" +
            "          \"lastName\":{\n" +
            "            \"type\":\"keyword\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public static final String AXCMSM_TEST_ADD = "{\n" +
            "  \"email\":\"axcmsm@qq.com\",\n" +
            "  \"info\":\"大家好！我是须贺！\",\n" +
            "  \"name\":{\n" +
            "    \"firstName\":\"须\",\n" +
            "    \"lastName\":\"贺\"\n" +
            "  }\n" +
            "}";

}
