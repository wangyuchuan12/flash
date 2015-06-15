package net.zhigang.dante.repositories;

import net.zhigang.dante.domain.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    public Admin findByUsername(String username);

    public List<Admin> findAll();

    public Admin findByRealname(String realname);
}
