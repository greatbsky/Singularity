package com.singularity.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import xyz.xysc.databinding.base.BaseEntity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author architect.bian
 * @date 2017-12-03 1:09 PM
 */
@Entity
public class Video extends BaseEntity {

//    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String summary;
    private String content;
    private String uri;
    private String img;
    private String authorID;
    @Transient
    private User author;
    private long viewCount;

    @Generated(hash = 109467181)
    public Video(Long id, String title, String summary, String content, String uri,
            String img, String authorID, long viewCount) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.uri = uri;
        this.img = img;
        this.authorID = authorID;
        this.viewCount = viewCount;
    }

    @Generated(hash = 237528154)
    public Video() {
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorID() {
        return this.authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
