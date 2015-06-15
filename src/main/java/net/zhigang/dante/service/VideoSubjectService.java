package net.zhigang.dante.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.zhigang.dante.domain.Video;
import net.zhigang.dante.domain.VideoSubject;
import net.zhigang.dante.repositories.VideoRepository;
import net.zhigang.dante.repositories.VideoSubjectRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class VideoSubjectService {
    final static Logger logger = LoggerFactory.getLogger(VideoSubjectService.class);

    @Autowired
    private VideoSubjectRepository video_subjectRepository;
    
    @Autowired
    private VideoRepository videoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public VideoSubject getVedio_SubjectName(String Vedio_SubjectName){
        return video_subjectRepository.findByTitle(Vedio_SubjectName);
    }
    public List<VideoSubject> getAll() {
        return video_subjectRepository.findAll();
    }
    public VideoSubject save(VideoSubject video_subject) {
        return video_subjectRepository.save(video_subject);
    }
    public void delete(VideoSubject video_subject){
        video_subjectRepository.delete(video_subject);
    }
    public void delete(long id){
        video_subjectRepository.delete(id);
    }
    public VideoSubject get(long id){
        
        return video_subjectRepository.findOne(id);
    }
    
    /**
     * 实现分页动态查询；
     */    
    public Page<VideoSubject> getVideo_Subject(PageRequest page1){       
        return video_subjectRepository.findAll(page1);       
    }
    
    /**
     * 模糊查找title；
     * @param title
     * @param page1
     * @return
     */
    public Page<VideoSubject>  search(String title,PageRequest page1) {
        Page<VideoSubject> subjects = video_subjectRepository.findByTitleContaining(title, page1);
        
        return subjects;
    }
    
    /**
     * 根据subject_id查找video的所有明细；
     * @param subjectid
     * @param pageNow
     * @param pageSize
     * @return
     */
    public Page<Video>  searchvideo(int subjectid,int pageNow , int pageSize) {
        List<Video> videos = videoRepository.findBySubjectId(new Long(subjectid));
        return new PageImpl<>(videos);

    }
    
   
}
