package net.zhigang.dante.service;

import java.util.List;




import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.zhigang.dante.domain.Device;
import net.zhigang.dante.repositories.DeviceRepository;

@Service
public class DeviceService {
	@Autowired
	private DeviceRepository deviceRepository;
	public List<Device> getAll()
	{
		return deviceRepository.findAll();
	}
	public Page<Device> getAll(int page , int size)
	{
		PageRequest pageRequest = new PageRequest(page-1, size);
		return deviceRepository.findAll(pageRequest);
	}
	public void delete(Device device)
	{
		deviceRepository.delete(device);
	}
	public void delete(long id)
	{
		deviceRepository.delete(id);
	}
	public Device update(Device device)
	{
		device.setUpdated_at(new DateTime());
		return deviceRepository.save(device);
	}
	public Device add(Device device)
	{
		device.setCreated_at(new DateTime());
		return deviceRepository.save(device);
	}
	public Device get(long id)
	{
		return deviceRepository.findOne(id);
	}
}
