package com.wxw.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wxw.model.Admin;

@Mapper
public interface AdminService {

	@Select("SELECT * FROM `moxi`.`admin` where userName = #{userName} and password = #{password} and state = 2;")
	Admin findByNameAndPassword(Admin admin);

	@Select("SELECT * FROM `moxi`.`admin` where userName = #{userName}  and state = 2;")
	Admin checkName(Admin admin);
	
	@Insert("INSERT INTO `moxi`.`admin` (`userName`, `password`, `email`, `birthday`, `phoneNumber`, `addDate`, `updateDate`, `state`) VALUES (#{userName}, #{password}, #{email}, #{birthday}, #{phoneNumber}, now(), now(), 2);")
	void insert(Admin admin);

}
