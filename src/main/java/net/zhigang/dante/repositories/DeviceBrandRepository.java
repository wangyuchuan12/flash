package net.zhigang.dante.repositories;

import java.util.List;

import net.zhigang.dante.domain.DeviceBrand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DeviceBrandRepository extends CrudRepository<DeviceBrand, Long>{
    
    public DeviceBrand findByName(String name);
    public List<DeviceBrand> findAll();
    //public DeviceBrand findById(String id);
    
    //分页查询；
    public Page<DeviceBrand> findAll(Pageable pageable);
        
    
}
