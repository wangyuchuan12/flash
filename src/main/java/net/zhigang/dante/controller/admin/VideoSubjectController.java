package net.zhigang.dante.controller.admin;

import net.zhigang.dante.domain.VideoSubject;
import net.zhigang.dante.service.VideoSubjectService;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoSubjectController {

    final static Logger logger=LoggerFactory.getLogger(VideoSubjectController.class);
    @Autowired
    VideoSubjectService vedio_subjectService;
    
    /**
     * @function 视频信息一览主页
     * @author Zhanglei;
     * @serialData 2015年1月7日
     * @param m
     * @return
     */
    @RequestMapping("/admin/subject/list")
    public ModelAndView vedio_subjectList(         
            Model m) {

        return vedio_subjectconvert(m);
    }
    
    /**
     * @function 对页面数据 初始化时默认显示条数设置；
     * @author ZhangLei；
     * @serialData 2015年1月7日；
     * @param m
     * @return
     */
    private ModelAndView vedio_subjectconvert(Model m){
        
        //对页面初始化条数默认显示10条；
        return vedio_subjectListOfPage(1,10,m);
    }
    
    /**
     * @function 对数据分页显示，根据指定的页数和条数显示数据；
     * @author Zhanglei；
     * @serialData 2015年1月7日；
     * @param page 指定加载的页数；
     * @param size 每页的大小；
     * @param m Modelandview
     * @return
     */
    @RequestMapping("/admin/vedio_subject/list_page")
    public ModelAndView vedio_subjectListOfPage(
                                        @RequestParam(value = "page")int page ,
                                        @RequestParam(value = "size")int size ,
                                        Model m){  
 
        //PageRequest page1 = new PageRequest(page,size, Direction.ASC, "created_at");   
        PageRequest page1 = new PageRequest(page-1,size);   
        Page<VideoSubject> vedio_subjectPage=vedio_subjectService.getVideo_Subject(page1);      
        ModelAndView  mv=new ModelAndView ();
        mv.addObject("vedio_subject",vedio_subjectPage.getContent());
        mv.addObject("totalElements",vedio_subjectPage.getTotalElements());
        mv.addObject("isFirst",vedio_subjectPage.isFirst());
        mv.addObject("totalPage",vedio_subjectPage.getTotalPages());
        mv.addObject("count",vedio_subjectPage.getSize());
        mv.addObject("page",page);
        mv.setViewName("admin/video_subject/Video_Subject");
        return mv;
    }
    
    /**
     * @Function 视频信息删除操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandid 品牌id
     * @param m
     * @return
     */
    @RequestMapping("/admin/video_subject/delete")
    public String brandDelete(
                            @RequestParam(value = "vedio_subjectid") long vedio_subjectid,
                            Model m) {
        vedio_subjectService.delete(vedio_subjectid);
            return "redirect:/admin/subject/list";
    }
    
    /**
     * @Function 视频信息修改操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandid 视频id
     * @param m
     * @return
     */
    @RequestMapping("/admin/video_subject/update")
    public String brandUpdate(
                            @RequestParam(value = "video_subjectid") long video_subjectid,
                            Model m) {

            VideoSubject video_subject = vedio_subjectService.get(video_subjectid);
            m.addAttribute("video_subject", video_subject);
            return "admin/video_subject/video_subjectUpdate";
    }
    
    @RequestMapping("/admin/video_subject/do_update")
    public String brandUpdate_brand(
                            @RequestParam(value="title") String title,
                            @RequestParam(value="cover") String cover,
                            @RequestParam(value="description") String description,
                            @RequestParam(value="source") String source,
                            @RequestParam(value="matedata") String matedata,
                            @RequestParam(value="tags") String tags,
                            @RequestParam(value="video_subjectid") long video_subjectid,
                            Model m) {
        
        VideoSubject video_subject = vedio_subjectService.get(video_subjectid);
        video_subject.setTitle(title);
        video_subject.setDescription(description);
        video_subject.setSource(source);
        video_subject.setCover(cover);
        video_subject.setMatedata(matedata);
        video_subject.setTags(tags);
        video_subject.setUpdatedat(new DateTime());
        vedio_subjectService.save(video_subject);
            return "redirect:/admin/subject/list";
    }
    
    /**
     * @Function 视频信息添加操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandname 视频名字；
     * @param m
     * @return
     */
    @RequestMapping("/admin/video_subject/add")
    public String brandadd(Model m){
        
        return "admin/video_subject/video_subjectAdd";
    }
    
    @RequestMapping("/admin/vedio_subject/do_add")
    public String brand_add(
            @RequestParam(value="title") String title,
            @RequestParam(value="cover") String cover,
            @RequestParam(value="description") String description,
            @RequestParam(value="source") String source,
            @RequestParam(value="matedata") String matedata,
            @RequestParam(value="tags") String tags,
            Model m
            ){
        VideoSubject video_subject=new  VideoSubject();
        video_subject.setTitle(title);
        video_subject.setDescription(description);
        video_subject.setSource(source);
        video_subject.setCover(cover);
        video_subject.setMatedata(matedata);
        video_subject.setTags(tags);
        video_subject.setCreatedat(new DateTime());
        vedio_subjectService.save(video_subject);
        return "redirect:/admin/subject/list";
    }
}
