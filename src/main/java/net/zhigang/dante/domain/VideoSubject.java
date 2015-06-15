package net.zhigang.dante.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity(name="video_subject")
public class VideoSubject {
    
    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="title",length = 255)
    private String title;
    
    @Column(name="cover", length=45)
    private String cover;
    
    @Column(name="description")
    private String description;
    
    @Column(name="source",length=255) 
    private String source;
    
    @Column(name="matedata")
    private String matedata ;
     
    @Column(name="tags",length=255)
    private String tags;
    
    
    @Column(name="category",length=255)
    private String category;
    
    @Column(name="created_at")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdat;
    
    @Column(name="updated_at")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime updatedat;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
       
          return source;
    }
    
    public String getSourceName() {
        
//      String str="iqiyi";
//      if(source.equals(source.trim())){
//          return "爱奇艺";
//      }else{
//          return source;
//      }
      String str = null;
      switch(source){
      case "iqiyi" :
          str="爱奇艺";
      }
      return str;
  }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMatedata() {
        return matedata;
    }

    public void setMatedata(String matedata) {
        this.matedata = matedata;
    }

    public String getTags() {
 
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public DateTime getCreatedat() {
        return createdat;
    }

    public void setCreatedat(DateTime createdat) {
        this.createdat = createdat;
    }

    public DateTime getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(DateTime updatedat) {
        this.updatedat = updatedat;
    }

   
 
}
