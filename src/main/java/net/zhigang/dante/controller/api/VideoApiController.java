package net.zhigang.dante.controller.api;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoApiController 
{
	/*@Autowired
	private VideoService videoService;
	@Autowired
	private VideoSubjectService videoSubjectService;
	*//**
	 * 根据模糊搜索获取video的json数据
	 * @author wangyuchuan
	 * @param content 模糊搜索的内容，其实是根据video的title搜索的
	 * @param pageSize 每页数量
	 * @param pageNow 当前第几页
	 * @return
	 *//*
	@RequestMapping("/api/video_list")
	public Map<String, Object> findVideoPageList(
			@RequestParam(value = "like")String content , 
			@RequestParam(value="page_size",defaultValue = "5") int pageSize,
			@RequestParam(value="page_now",defaultValue="1") int pageNow)
	{
		List<Video> videos =  videoService.findVideosWithLikeByPage(content,pageNow,pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page_now", pageNow);
		if(videos!=null&&videos.size()>0)
		{
			
			int total = videos.size();
			int totalPage = 0;
			if(total%pageSize>0)
			{
				totalPage = total/pageSize+1;
			}
			else{
				totalPage = total/pageSize;
			}
			map.put("total", total);
			map.put("totalPage", totalPage);
			List<Map<String, Object>> videoDataList = new ArrayList<>();
			for(Video video:videos)
			{
				VideoSubject video_Subject = videoSubjectService.get(video.getSubject_id());
				Map<String, Object> videoMap = new HashMap<>();
				if(video_Subject!=null)
				{
					videoMap.put("source_name", video_Subject.getSourceName());
					videoMap.put("source", video_Subject.getSource());
					videoMap.put("category",video_Subject.getCategory());
				}
				videoMap.put("id", video.getId());
				videoMap.put("title", video.getTitle());
				videoMap.put("conver", video.getCover());
				videoMap.put("config", video.getConfig());
				videoMap.put("subject_id", video.getSubject_id());
				videoDataList.add(videoMap);
			}
			map.put("videos", videoDataList);
		}
		return map;
	}*/
}