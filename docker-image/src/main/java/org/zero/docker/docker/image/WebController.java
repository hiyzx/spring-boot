package org.zero.docker.docker.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @date 2019/11/2
 */
@RestController
public class WebController {

	@GetMapping("/hello")
	public Map<String,String> hello(){
		Map<String, String> map = new HashMap<>();
		map.put("name", "hiyzx");
		map.put("age", "1");
		return map;
	}
}
