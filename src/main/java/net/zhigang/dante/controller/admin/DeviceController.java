package net.zhigang.dante.controller.admin;

import java.util.ArrayList;
import java.util.List;

import net.zhigang.dante.domain.Device;
import net.zhigang.dante.domain.ajax.DataTableData;
import net.zhigang.dante.service.DeviceService;
import net.zhigang.dante.util.TypeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController extends BaseController {
    final static Logger logger = LoggerFactory
	    .getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;

    /**
     * 跳转更新设备型号页面
     * 
     * @param id
     *            设备型号id
     * @param model
     * @author wangyuchuan
     * @return
     */
    @RequestMapping("/admin/device/update")
    public String updateDeviceView(@RequestParam(value = "id") long id,
	    Model model) {
	Device device = deviceService.get(TypeUtil.getLong(id));
	model.addAttribute("device", device);
	return "admin/device/update_device";
    }

    /**
     * 跳转新增设备型号页面
     * 
     * @param model
     * @author wangyuchuan
     * @return
     */
    @RequestMapping("/admin/device/add")
    public String addDeviceView(Model model) {
	return "admin/device/add_device";
    }

    /**
     * 提交添加设备型号操作
     * 
     * @param model
     * @param name
     *            产品名称
     * @author wangyuchuan
     * @return
     */
    @RequestMapping("/admin/device/do_add")
    public String doAddDevice(@RequestParam(value = "name") String name,
	    Model model) {
	Device device = new Device();
	device.setName(name);
	deviceService.add(device);
	return "redirect:/admin/device/list";
    }

    /**
     * 提交更新产品型号操作
     * 
     * @param id
     *            产品型号id
     * @param name
     *            产品名称
     * @param model
     * @author wangyuchuan
     * @return
     */
    @RequestMapping("/admin/device/do_update")
    public String doUpdateDevice(@RequestParam(value = "id") long id,
	    @RequestParam(value = "name") String name, Model model) {
	Device device = deviceService.get(id);
	device.setName(name);
	deviceService.update(device);
	return "redirect:/admin/device/list";
    }

    /**
     * 执行删除产品型号操作
     * 
     * @param id
     *            产品型号id
     * @param model
     * @return
     * @author wangyuchuan
     */
    @RequestMapping("/admin/device/do_delete")
    public String doDeleteDevice(@RequestParam(value = "id") long id,
	    Model model) {
	deviceService.delete(id);
	;
	return "redirect:/admin/device/list";
    }

    /**
     * 产品型号列表，默认显示第一页，每页10条记录
     * 
     * @param model
     * @return
     */
    @RequestMapping("/admin/device/list")
    public String deviceList(Model model) {
	return defaultDeviceListOfPage(model);
    }

    /**
     * 产品型号分页列表
     * 
     * @param page
     *            当前页
     * @param size
     *            每页数量
     * @param model
     * @return
     */
    @RequestMapping("/admin/device/list_page")
    public String deviceListOfPage(@RequestParam(value = "page") int page,
	    @RequestParam(value = "size") int size, Model model) {
	Page<Device> devicePage = deviceService.getAll(page, size);
	model.addAttribute("devices", devicePage.getContent());
	model.addAttribute("totalElements", devicePage.getTotalElements());
	model.addAttribute("isFirst", devicePage.isFirst());
	model.addAttribute("totalPage", devicePage.getTotalPages());
	model.addAttribute("page", page);
	model.addAttribute("count", devicePage.getSize());
	return "admin/device/device";
    }

    @RequestMapping("/admin/device/get_device_json")
    @ResponseBody
    public DataTableData getDeviceListOfPage(
	    @RequestParam(value = "page") int page,
	    @RequestParam(value = "size") int size, Model model) {

	Page<Device> devicePage = deviceService.getAll(page, size);
	DataTableData dataTableData = new DataTableData();
	dataTableData.setRecordsTotal(devicePage.getTotalElements());
	List<List<?>> list = new ArrayList<>();
	for (Device device : devicePage.getContent()) {
	    List<String> list2 = new ArrayList<String>();
	    // list2.add(device.getId());
	    list2.add(device.getName());
	    list2.add("<a href=\"/admin/devicexxedit?id=" + device.getId() + "");
	}
	dataTableData.setData(list);
	dataTableData.setDraw(1);
	dataTableData.setRecordsFiltered(devicePage.getTotalElements());
	logger.debug("data", devicePage.getContent());
	return dataTableData;
    }

    private String defaultDeviceListOfPage(Model model) {
	return deviceListOfPage(1, 10, model);
    }
}
