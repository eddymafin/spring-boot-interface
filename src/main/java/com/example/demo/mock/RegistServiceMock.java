package com.example.demo.mock;

import com.example.demo.service.RegistService;

public class RegistServiceMock implements RegistService {

	@Override
	public String regist() {
		return "テスト的に動作";
	}

}
