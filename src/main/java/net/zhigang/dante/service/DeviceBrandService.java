package net.zhigang.dante.service;

import java.util.List;

import net.zhigang.dante.domain.DeviceBrand;
import net.zhigang.dante.repositories.DeviceBrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DeviceBrandService {
    
    @Autowired
    private DeviceBrandRepository devicebrandRepository;
    
    public DeviceBrand getDeviceBrandName(String DeviceBrandName){
        return devicebrandRepository.findByName(DeviceBrandName);
    }
    public List<DeviceBrand> getAll() {
        return devicebrandRepository.findAll();
    }
    public DeviceBrand save(DeviceBrand devicebrand) {
        return devicebrandRepository.save(devicebrand);
    }
    public void delete(DeviceBrand devicebrand){
        devicebrandRepository.delete(devicebrand);
    }
    public void delete(long id){
        devicebrandRepository.delete(id);
    }
    public DeviceBrand get(long id){
        
        return devicebrandRepository.findOne(id);
    }
    
    /**
     * 实现分页动态查询；
     */ 
//    final PageRequest page1 = new PageRequest(
//            offset, size, Direction.ASC, "created_at"
//          );
//    
    public Page<DeviceBrand> getDeviceBrand(PageRequest page1){       
        return devicebrandRepository.findAll(page1);       
    }
}
