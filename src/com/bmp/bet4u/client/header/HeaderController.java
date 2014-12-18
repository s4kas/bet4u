package com.bmp.bet4u.client.header;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/header")
public class HeaderController {
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String get() {
		return "header.get";
    }
}
