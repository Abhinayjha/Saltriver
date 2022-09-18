package com.Signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	private EmailService es;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "This is welcome page for emailsend";
	}
	
	@PostMapping("/sendemail")
	public ResponseEntity<?> send(@RequestBody EmailRequest req)
	{
		//this.emailservice.send();
		System.out.println(req);
		boolean result=this.es.emailservice(req.getMessage(), req.getSubject(), req.getTo());
		if(result)
		{
			return ResponseEntity.ok("Email is send successfully");
		}else
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not send");
		}
	}
}




