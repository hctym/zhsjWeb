package com.zhsj.service;

import java.util.List;

import com.zhsj.model.CityCode;

public interface CityCodeService {

	List<CityCode> getListByCode(String code) throws Exception;
}
