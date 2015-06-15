package net.zhigang.dante.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zhigang.dante.controller.admin.VideoSubjectController;
import net.zhigang.dante.domain.Video;
import net.zhigang.dante.domain.VideoSubject;
import net.zhigang.dante.service.VideoSubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoSubjectApiController {

    final static Logger logger = LoggerFactory
            .getLogger(VideoSubjectController.class);
    @Autowired
    VideoSubjectService video_subjectService;

    // web接口匹配调度；
    @RequestMapping(value = "/api/video_subject")
    private void Video_Subject(
            @RequestParam(value = "size", defaultValue = "size") int video_subject_size,
            @RequestParam(value = "page", defaultValue = "page") int video_subject_page,
            @RequestParam(value = "category") String category,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if ("电影".equals(category)) { // 所有电影
//            this.doVideo_Subject(video_subject_size, video_subject_page,
//                    request, response);
//        }
        if ("综艺".equals(category)) {// 所有综艺
            this.doVideo_Subject_vshows(video_subject_size, video_subject_page,
                    request, response);
        }
        if ("电视剧".equals(category)) {// 所有电视剧
            this.doVideo_Subject_show(video_subject_size, video_subject_page,
                    request, response);
        }
    }

    // 电影接口；
    // @RequestMapping(value = "/api/video_subject")
    private List doVideo_Subject(
            @RequestParam(value = "size", defaultValue = "5") int video_subject_size,
            @RequestParam(value = "page", defaultValue = "1") int video_subject_page,
            @RequestParam(value="vsubjectid") int subjectid)
            throws ServletException, IOException {

//        PageRequest page1 = new PageRequest(video_subject_page-1,
//                video_subject_size);
        Page<Video> vedio_subjectPage = video_subjectService.searchvideo(subjectid, video_subject_page,video_subject_size);
        List<Video> list = vedio_subjectPage.getContent();
        List<Map<String, Object>> maplist_videosubject = new ArrayList<Map<String, Object>>();
        for (Video videoSubject : list) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("Id", videoSubject.getId());
            map.put("title", videoSubject.getTitle());
            map.put("cover", videoSubject.getCover());
            map.put("config", videoSubject.getConfig());
            maplist_videosubject.add(map);
        }
        return maplist_videosubject;

    }

    // 电视剧接口；
    // @RequestMapping(value = "/api/video_subject_tv")
    private void doVideo_Subject_show(
            @RequestParam(value = "size", defaultValue = "5") int video_subject_size,
            @RequestParam(value = "page", defaultValue = "1") int video_subject_page,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PageRequest page1 = new PageRequest(video_subject_page,
                video_subject_size);

        Page<VideoSubject> vedio_subjectPage = video_subjectService
                .getVideo_Subject(page1);
        List<VideoSubject> list = vedio_subjectPage.getContent();
        List<Map<String, Object>> maplist_videosubject = new ArrayList<Map<String, Object>>();
        for (VideoSubject videoSubject : list) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("Id", videoSubject.getId());
            map.put("title", videoSubject.getTitle());
            map.put("cover", videoSubject.getCover());
            map.put("sourcename", videoSubject.getSourceName());
            map.put("category", videoSubject.getCategory());
            maplist_videosubject.add(map);
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().println(maplist_videosubject);

    }

    // 综艺节目接口；
    // @RequestMapping(value = "/api/video_subject_VShows")
    private void doVideo_Subject_vshows(
            @RequestParam(value = "size", defaultValue = "5") int video_subject_size,
            @RequestParam(value = "page", defaultValue = "1") int video_subject_page,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PageRequest page1 = new PageRequest(video_subject_page,
                video_subject_size);
        Page<VideoSubject> vedio_subjectPage = video_subjectService
                .getVideo_Subject(page1);
        List<VideoSubject> list = vedio_subjectPage.getContent();
        List<Map<String, Object>> maplist_videosubject = new ArrayList<Map<String, Object>>();
        for (VideoSubject videoSubject : list) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("Id", videoSubject.getId());
            map.put("title", videoSubject.getTitle());
            map.put("cover", videoSubject.getCover());
            map.put("sourcename", videoSubject.getSourceName());
            map.put("category", videoSubject.getCategory());
            maplist_videosubject.add(map);
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().println(maplist_videosubject);

    }

    // 模糊查询
    @RequestMapping(value = "/api/video_like")
    private Map<String, Object> doContentsearch(
            @RequestParam(value = "size", defaultValue = "5") int video_subject_size,
            @RequestParam(value = "page", defaultValue = "1") int video_subject_page,
            @RequestParam(value = "like") String title,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PageRequest page1 = new PageRequest(video_subject_page - 1,
                video_subject_size);
        Page<VideoSubject> vedio_subjectPage = video_subjectService.search(
                title, page1);
        Map<String, Object> mapping = new LinkedHashMap<String, Object>();
        List<VideoSubject> list = vedio_subjectPage.getContent();
        List<Map<String, Object>> maplist_videosubject = new ArrayList<Map<String, Object>>();
        for (VideoSubject videoSubject : list) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("Id", videoSubject.getId());
            map.put("title", videoSubject.getTitle());
            map.put("cover", videoSubject.getCover());
            map.put("sourcename", videoSubject.getSourceName());
            map.put("category", videoSubject.getCategory());
            maplist_videosubject.add(map);
        }

        // 页数求模；
        int mold = 0;
        if (vedio_subjectPage.getTotalElements() % video_subject_size != 0) {
            mold = 1;
        }
        mapping.put("total", list.size());
        mapping.put(
                "totalpage",
                mold == 0 ? vedio_subjectPage.getTotalElements()
                        / video_subject_size : vedio_subjectPage
                        .getTotalElements() / video_subject_size + 1);
        mapping.put("api", "/api/video_detail");
        mapping.put("videosubject", maplist_videosubject);
        return mapping;

    }

    @RequestMapping(value = "/api/video_detail")
    private Map<String, Object> doContentsearchdetails(
            @RequestParam(value = "video_subjectid") int video_subjectid,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        VideoSubject vedio_subject = video_subjectService.get(video_subjectid);
       // Map<String, Object> mapping_video = new LinkedHashMap<String, Object>();
        Map<String, Object> mapdetaillist = new LinkedHashMap<String, Object>();
        List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
//        if ("电影".equals(vedio_subject.getCategory())) {
//            maplist= doVideo_Subject(10,1,video_subjectid);
//        }
        maplist= doVideo_Subject(10,1,video_subjectid);
        maplist= doVideo_Subject(10,1,video_subjectid);
        logger.debug("vedio_subject.getMatedata():{}", vedio_subject.getMatedata()+"************");
        String metadate=vedio_subject.getMatedata();
        String[] array=metadate.split(",");       
        //简介
        String description=vedio_subject.getDescription();       
        //视频名字；
        String title=vedio_subject.getTitle();       
        //视频类型；
        String tags=vedio_subject.getTags();      
        //视频上映时间；
        String[] arraytime=array[array.length-1].split(":");
        String releasetime=arraytime[1].substring(0, arraytime[1].length()-1);        
        //视频主要演员；
        String[] actorarray=array[0].split(":");
        String actor=actorarray[1];      
        //视频所属区域；
        String[] areaarray=array[1].split(":");
        String area=areaarray[1];  
        System.out.println(area+"******************************************");
        //视频导演；
        String[] dirarray=array[3].split(":");
        String direator=dirarray[1];
        
      //片段时长；
        String[] durationarray=array[2].split(":");
        String duration=durationarray[1];
          
        mapdetaillist.put("title", title);
        mapdetaillist.put("tags", tags);
        mapdetaillist.put("actor", actor);
        mapdetaillist.put("area", area);
        mapdetaillist.put("direator", direator);
        mapdetaillist.put("releasetime", releasetime);
        mapdetaillist.put("duration", duration);
        mapdetaillist.put("description", description);
        mapdetaillist.put("videodetails", maplist);
        
        return mapdetaillist;
    }
}
