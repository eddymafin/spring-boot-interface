package com.example.demo.service;

import org.springframework.stereotype.Service;

//@SpringBootApplicationにより、com.example.demo配下すべてが、Bean化されている。
@Service
public class RegistServiceImpl implements RegistService {

	@Override
	public String regist() {
		return "レビュー登録が完了しました。";
	}

}
