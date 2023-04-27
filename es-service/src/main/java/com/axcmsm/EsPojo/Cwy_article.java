package com.axcmsm.EsPojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * ClassName: com.axcmsm.EsPojo.cwy_article
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */
@Document(indexName = "axcmsm")
public class Cwy_article {
    @Id
    private String id;
    private String title;
    private String content;
    private String mdtext;
    private String views;
    private String real_name;
    private String head_url;
    private Date reate_date;
    private Date update_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMdtext() {
        return mdtext;
    }

    public void setMdtext(String mdtext) {
        this.mdtext = mdtext;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public Date getReate_date() {
        return reate_date;
    }

    public void setReate_date(Date reate_date) {
        this.reate_date = reate_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
