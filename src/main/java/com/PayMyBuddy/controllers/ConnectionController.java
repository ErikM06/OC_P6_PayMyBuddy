package com.PayMyBuddy.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionController.class);
	
	@Autowired
	private IConnectionService IConnectionService;

	@Autowired
	private GetCurrentUser currentUser;
	
	@GetMapping("/user/connection")
	private ModelAndView getAddConnection(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("connection", new ConnectionDTO());
		if (null != error && error.equalsIgnoreCase("true")) {
			model.addAttribute("error", "Unable to launch /connection");
		}
		return new ModelAndView("connectionPage");

	}

	@PostMapping("/user/add_connection")
	private ModelAndView addConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws NullPointerException {
		try {
			IConnectionService.addConnections(connectionDto,currentUser);
		} catch (NullPointerException e) {
			logger.info(e.getMessage());
			return new ModelAndView("notAConnectionPage");
		}
		return new ModelAndView("successPage");

	}

	@GetMapping(value = "/user/delete_connection")
	private ModelAndView deleteConnection(@ModelAttribute("connection") ConnectionDTO connectionDto,
			HttpServletRequest request, Errors errors) throws Exception, NullPointerException {
		try {
			IConnectionService.deleteConnection(connectionDto.getConnectionUsername(),currentUser);
		} catch (NullPointerException e) {
			logger.info(e.getMessage());
			return new ModelAndView("notAConnectionPage");
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ModelAndView("500");
		}
		return new ModelAndView("successPage");

	}

}
