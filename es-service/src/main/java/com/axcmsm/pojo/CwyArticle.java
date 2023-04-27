package com.axcmsm.pojo;

import org.springframework.data.annotation.Id;

/**
 * ClassName: com.axcmsm.pojo.CwyArticle
 * 微信公众号：代码飞快
 * Description:
 *
 * @author 须贺
 * @version 2023/4/26
 */

public class CwyArticle {
   private String id;
   private String title;
   private String content;
   private String top;
   private String collect_count;
   private String mdtext;
   private String type;
   private String views;
   private String user_id;
   private String real_name;
   private String head_url;
   private String essence;
   private String section_id;
   private String create_date;
   private String update_date;

    @Override
    public String toString() {
        return "CwyArticle{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", top='" + top + '\'' +
                ", collect_count='" + collect_count + '\'' +
                ", mdtext='" + mdtext + '\'' +
                ", type='" + type + '\'' +
                ", views='" + views + '\'' +
                ", user_id='" + user_id + '\'' +
                ", real_name='" + real_name + '\'' +
                ", head_url='" + head_url + '\'' +
                ", essence='" + essence + '\'' +
                ", section_id='" + section_id + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }

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

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getMdtext() {
        return mdtext;
    }

    public void setMdtext(String mdtext) {
        this.mdtext = mdtext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getEssence() {
        return essence;
    }

    public void setEssence(String essence) {
        this.essence = essence;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
