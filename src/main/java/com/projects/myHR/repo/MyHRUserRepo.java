package com.projects.myHR.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myHR.enums.MyHRRoles;
import com.projects.myHR.model.MyHRUser;

@Repository
public interface MyHRUserRepo extends JpaRepository<MyHRUser, Long>{
	public MyHRUser findByUsername(String username);
	public Page<MyHRUser> findByRoleIn(List<MyHRRoles> roles, Pageable page);
	public Page<MyHRUser> findByRole(MyHRRoles role, Pageable page);
}
