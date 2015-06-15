package net.zhigang.dante.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.zhigang.dante.domain.Video;
import net.zhigang.dante.repositories.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Video> getAll() {
	return videoRepository.findAll();
    }

    public Page<Video> getAll(int page, int size) {
	PageRequest pageRequest = new PageRequest(page - 1, size);
	return videoRepository.findAll(pageRequest);
    }

    public void deleteVideo(Long id) {
	videoRepository.delete(id);
    }

    public Video addVideo(Video video) {
	video.setCreatedAt(new DateTime());
	return videoRepository.save(video);
    }

    public Video updateVideo(Video video) {
	video.setUpdatedAt(new DateTime());
	return videoRepository.save(video);
    }

    public Video getVideo(Long id) {
	return videoRepository.findOne(id);
    }

    public List<Video> findVideosWithLike(String content) {

	return videoRepository.findAllByTitleWithLike(content, null);
    }
}
