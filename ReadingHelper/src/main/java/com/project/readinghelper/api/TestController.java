package com.project.readinghelper.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.readinghelper.domain.TestDomain;

@Controller
@RequestMapping("/api")
public class TestController {
	
	@GetMapping("/test")
	public void test() {
		System.out.println("test");
	}

	@GetMapping("/getList")
	@ResponseBody
	public Map<String,Object> getList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TestDomain> list = new ArrayList<TestDomain>();
		for(int i = 0; i < 10; i++) {
			TestDomain dto = new TestDomain();
			dto.setIndex(i);
			dto.setName("devyoung"+i);
			dto.setAge(Integer.toString(i));
			list.add(dto);
		}
		resultMap.put("result", list);
		return resultMap;
	}
}
