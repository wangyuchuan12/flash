package net.zhigang.dante.repositories;

import java.util.List;

import net.zhigang.dante.domain.Device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    public List<Device> findAll();

    public Page<Device> findAll(Pageable pageable);
}
