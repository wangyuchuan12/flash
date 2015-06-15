package net.zhigang.dante.controller.admin;

import javax.servlet.http.HttpServletRequest;

import net.zhigang.dante.domain.Video;
import net.zhigang.dante.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    /**
     * 视频列表，默认显示第一页，一页显示十条记录
     * 
     * @author wangyuchuan
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/list")
    public String videoList(Model model) {
	return defaultDeviceListOfPage(model);
    }

    /**
     * 分页显示视频列表
     * 
     * @author wangyuchuan
     * @param page
     * @param size
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/list_page")
    public String videoListOfPage(@RequestParam(value = "page") int page,
	    @RequestParam(value = "size") int size, Model model) {
	Page<Video> videoPage = videoService.getAll(page, size);
	model.addAttribute("videos", videoPage.getContent());
	model.addAttribute("totalElements", videoPage.getTotalElements());
	model.addAttribute("isFirst", videoPage.isFirst());
	model.addAttribute("totalPage", videoPage.getTotalPages());
	model.addAttribute("page", page);
	model.addAttribute("count", videoPage.getSize());
	return "admin/video/video";
    }

    /**
     * 跳转到更新视频界面
     * 
     * @author wangyuchuan
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/update")
    public String updateVideoView(@RequestParam(value = "id") long id,
	    Model model) {
	Video video = videoService.getVideo(id);
	model.addAttribute("video", video);
	return "admin/video/update_video";
    }

    /**
     * 跳转到添加视频记录界面
     * 
     * @author wangyuchuan
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/add")
    public String addVideoView(Model model) {
	return "admin/video/add_video";
    }

    /**
     * 提交更新视频记录操作
     * 
     * @author wangyuchuan
     * @param httpRequest
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/do_update")
    public String doUpdateVideo(HttpServletRequest httpRequest, Model model) {
	String id = httpRequest.getParameter("id");
	String title = httpRequest.getParameter("title");
	String cover = httpRequest.getParameter("cover");
	String config = httpRequest.getParameter("config");
	Video video = videoService.getVideo(Long.parseLong(id));
	video.setTitle(title);
	video.setCover(cover);
	video.setConfig(config);
	videoService.updateVideo(video);
	return "redirect:/admin/video/list";
    }

    /**
     * 提交新增视频记录操作
     * 
     * @author wangyuchuan
     * @param httpRequest
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/do_add")
    public String doAddVideo(HttpServletRequest httpRequest, Model model) {
	String title = httpRequest.getParameter("title");
	String cover = httpRequest.getParameter("cover");
	String config = httpRequest.getParameter("config");
	Video video = new Video();
	video.setTitle(title);
	video.setCover(cover);
	video.setConfig(config);
	videoService.addVideo(video);
	return "redirect:/admin/video/list";
    }

    /**
     * 执行删除记录操作
     * 
     * @author wangyuchuan
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/admin/video/do_delete")
    public String doDeleteVideo(@RequestParam(value = "id") long id, Model model) {
	videoService.deleteVideo(id);
	return "redirect:/admin/video/list";
    }

    private String defaultDeviceListOfPage(Model model) {
	return videoListOfPage(1, 10, model);
    }

}
