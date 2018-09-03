package top.vetoer.entity;

import top.vetoer.domain.Entitys;

import javax.persistence.*;

@Entity
public class Movie extends Entitys implements Cloneable{

    private static final long serialVersionUID = -3038532404298612239L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long categoryId;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false)
    private String title;
    @Column(nullable = true)
    private String season;
    @Column(nullable = true)
    private String episode;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private int progress=100;
    @Column(nullable = false)
    private Long createTime;

    public Movie() {
    }

    public Movie(long categoryId,int type, String title, String season, String episode, String description, int progress, Long createTime) {
        this.categoryId = categoryId;
        this.type = type;
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.description = description;
        this.progress = progress;
        this.createTime = createTime;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public Movie clone(){
        Movie movie = null;
        try {
            movie = (Movie) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
