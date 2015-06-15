package net.zhigang.dante.controller.admin;

import net.zhigang.dante.domain.DeviceBrand;
import net.zhigang.dante.service.DeviceBrandService;

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

@Controller
public class DeviceBrandController {
    
    final static Logger logger=LoggerFactory.getLogger(DeviceBrandController.class);
    @Autowired
    DeviceBrandService devicebrandService;
    
    /**
     * @function 设备品牌信息一览主页
     * @author Zhanglei;
     * @serialData 2015年1月7日
     * @param m
     * @return
     */
    @RequestMapping("/admin/devicebrand/list")
    public String brandList(         
            Model m) {

        return devicebrandconvert(m);
    }
    
    /**
     * @function 对页面数据 初始化时默认显示条数设置；
     * @author ZhangLei；
     * @serialData 2015年1月7日；
     * @param m
     * @return
     */
    private String devicebrandconvert(Model m){
        
        //对页面初始化条数默认显示10条；
        return devicebrandListOfPage(1,10,m);
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
    @RequestMapping("/admin/devicebrand/list_page")
    public String devicebrandListOfPage(
                                        @RequestParam(value = "page")int page ,
                                        @RequestParam(value = "size")int size ,
                                        Model m){  
 
        PageRequest page1 = new PageRequest(page-1,size);  
        Page<DeviceBrand> devicebrandPage=devicebrandService.getDeviceBrand(page1);
              
        m.addAttribute("devicebrand",devicebrandPage.getContent());
        m.addAttribute("totalElements",devicebrandPage.getTotalElements());
        m.addAttribute("isFirst",devicebrandPage.isFirst());
        m.addAttribute("totalPage",devicebrandPage.getTotalPages());
        m.addAttribute("count",devicebrandPage.getSize());
        m.addAttribute("page",page);
          return "admin/devicebrand/DeviceBrand";
    }
    
    /**
     * @Function 设备品牌删除操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandid 品牌id
     * @param m
     * @return
     */
    @RequestMapping("/admin/devicebrand/delete")
    public String brandDelete(
                            @RequestParam(value = "brandid") long brandid,
                            Model m) {

            devicebrandService.delete(brandid);
            return "redirect:/admin/devicebrand/list";
    }
    
    /**
     * @Function 设备品牌修改操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandid 品牌id
     * @param m
     * @return
     */
    @RequestMapping("/admin/devicebrand/update")
    public String brandUpdate(
                            @RequestParam(value = "devicebrandid") long devicebrandid,
                            Model m) {

            DeviceBrand devicebrand = devicebrandService.get(devicebrandid);
            m.addAttribute("devicebrand", devicebrand);
            return "admin/devicebrand/DeviceBrandUpdate";
    }
    
    @RequestMapping("/admin/devicebrand/update_brand")
    public String brandUpdate_brand(
                            @RequestParam(value = "brandname_new") String brandname_new,
                            @RequestParam(value="brandid") long brandname_id,
                            Model m) {
        
            DeviceBrand devicebrand = devicebrandService.get(brandname_id);
            devicebrand.setName(brandname_new);
            devicebrand.setUpdatedAt(new DateTime());
            devicebrandService.save(devicebrand);
            return "redirect:/admin/devicebrand/list";
    }
    
    /**
     * @Function 设备品牌添加操作；
     * @author zhanglei；
     * @serialData 2015年1月7日；
     * @param brandname 品牌名字；
     * @param m
     * @return
     */
    @RequestMapping("/admin/devicebrand/add")
    public String brandadd(Model m){
        
        return "admin/devicebrand/DeviceBrandAdd";
    }
    
    @RequestMapping("/admin/devicebrand/add_brand")
    public String brand_add(
            @RequestParam(value ="brandname") String brandname,
            Model m
            ){
        DeviceBrand devicebrand=new DeviceBrand();
        devicebrand.setName(brandname);
        devicebrand.setCreatedAt(new DateTime());
        devicebrandService.save(devicebrand);
        return "redirect:/admin/devicebrand/list";
    }
    
}
