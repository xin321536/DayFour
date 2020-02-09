package com.example.dayfour;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDao {
    @Id(autoincrement = true)
    private Long id;
    private String image;
    private String title;
    @Generated(hash = 152503646)
    public GreenDao(Long id, String image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
