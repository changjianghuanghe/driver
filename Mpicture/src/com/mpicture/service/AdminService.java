package com.mpicture.service;


import java.util.List;

import com.mpicture.entity.Admin;
import com.mpicture.entity.PageClass;

public interface AdminService {
	Admin login(Admin admin);
	List<Admin> adminList(PageClass pageClass);
	int adminAdd(Admin admin);
	Admin getAdmin(Integer aid);
	int deleteAdmin(Integer aid);
}
