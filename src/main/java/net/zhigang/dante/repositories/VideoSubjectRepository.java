package net.zhigang.dante.repositories;

import java.util.List;

import net.zhigang.dante.domain.VideoSubject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface VideoSubjectRepository extends CrudRepository<VideoSubject, Long> {

    /**
     * video_subject视频按名字精确查询不支持分页功能。
     * @param name
     * @return
     */
    public VideoSubject findByTitle(String name);
    
    /**
     *  video_subject视频按名字模糊查询同时不支持分页功能。
     */
    public List<VideoSubject> findAll(); 
      
    /**
     * video_subject视频信息的分页查询；
     * @param pageable
     * @return
     */
    public Page<VideoSubject> findAll(Pageable pageable);
    
    /**
     * 视频title模糊查询同时支持分页功能；
     * @param title
     * @param pageable
     * @return
     */
    public Page<VideoSubject> findByTitleContaining(String title, Pageable pageable);
   
    
}
