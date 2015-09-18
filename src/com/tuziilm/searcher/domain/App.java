package com.tuziilm.searcher.domain;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-8-25
 * Time: ÏÂÎç4:23
 */
public class App extends RemarkId {
    private String name;
    private String link;
    private String imgPath;
    private String imgFileName;
    private Integer type;
    private Integer status;
    private Integer uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
