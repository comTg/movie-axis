package top.vetoer.entity;

import top.vetoer.domain.Entitys;

import javax.persistence.*;

/**
 * movie 集合类,与movie一对多关系
 */
@Entity
public class MovieSet extends Entitys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int type;
    @Column(nullable = false,unique = true)
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = true)
    private Long lastModifyTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
