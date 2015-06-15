package net.zhigang.dante.repositories;

import java.util.List;

import net.zhigang.dante.domain.Video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VideoRepository extends CrudRepository<Video, Long>,
	PagingAndSortingRepository<Video, Long> {
    public List<Video> findAll();

    public Page<Video> findAll(Pageable pageable);

    @Query(value = "from net.zhigang.dante.domain.Video where title like %?1%")
    public List<Video> findAllByTitleWithLike(String tilte, Pageable pageable);

    public List<Video> findBySubjectId(Long subjectId);
}
