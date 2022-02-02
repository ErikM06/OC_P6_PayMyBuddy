package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.PayMyBuddy.dto.ConnectionDTO;
import com.PayMyBuddy.interfaces.IConnectionService;
import com.PayMyBuddy.services.util.GetCurrentUser;

@Controller
public class ConnectionController {
	
	@Autowired
	private IConnectionService IConnectionService;

	@Autowired
	private GetCurrentUser currentUser;
	
	@GetMapping("/user/connection")
	private ModelAndView getAddConnection(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("connection", new ConnectionDTO());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("Error", "Unable to launch /connection");
		}
		return new ModelAndView("connectionPage");

	}

	@PostMapping("/user/add_connection")
	private ModelAndView addConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws NullPointerException {
		try {
			IConnectionService.addConnections(connectionDto,currentUser);
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home", "connection", connectionDto);

	}

	@GetMapping(value = "/user/delete_connection")
	private ModelAndView deleteConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws Exception, NullPointerException {
		try {
			IConnectionService.deleteConnection(connectionDto.getConnectionUsername(),currentUser);
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			errors.getGlobalErrors();
		}
		return new ModelAndView("home");

	}

}
