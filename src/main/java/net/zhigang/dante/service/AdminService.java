package net.zhigang.dante.service;

import net.zhigang.dante.domain.Admin;
import net.zhigang.dante.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRespository;

    public Admin getAdminByName(String username) {
        return adminRespository.findByUsername(username);
    }

    public List<Admin> getAll() {
        return adminRespository.findAll();
    }

    public Admin save(Admin admin) {
        return adminRespository.save(admin);
    }
    
    /**
     * delete the seleted admin.
     * @param admin
     * @author zhanglei.
     * @serialData 2014年12月24日.
     */
    public void delete(Admin admin){
   	 	adminRespository.delete(admin);
   }
}
