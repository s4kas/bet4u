package com.bmp.bet4u.client.footer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/footer")
public class FooterController {

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public String get() {
		return "footer.get";
    }
	
}
