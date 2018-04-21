package com.lac.spring.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lac.spring.dao.SpittleRepository;
import com.lac.spring.exception.SpittleNotFoundException;
import com.lac.spring.po.Spittle;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	//@Autowired
	private SpittleRepository spittleRepository;
	
	public SpittleController(){}

	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}

	// @RequestMapping(method = RequestMethod.GET)
	public String spittles(Model model) {
		// without specifying a key, the key is inferred from the type of object
		// being set as the value
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		// model.addAttribute("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE,
		// 20));
		return "spittles";
	}

	// When a handler method returns an object or a collection like this, the
	// value returned is put
	// into the model, and the model key is inferred from its type (spittleList,
	// as in the
	// other examples),view name is inferred from the request path,Because this
	// method handles GET requests for /spittles, the view name is spittles
	@RequestMapping(method = RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value = "max", defaultValue = "10000") long max,
			@RequestParam(value = "count", defaultValue = "20") int count) {
		return spittleRepository.findSpittles(max, count);
	}

	// If no value attribute is given for @PathVariable, it assumes the
	// placeholder’s name is
	// the same as the method parameter name.
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittles(@PathVariable("spittleId") long spittleId,
			Model model) throws Exception {
		//Spittle spittle = spittleRepository.findOne(spittleId);
		Spittle spittle = null;
		if(model.containsAttribute("spittle")){
			System.out.println("spittle is contained in the model that is redirected from an url");
		}
		if(spittle == null) {
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spittle("123",new Date()));
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String handleRegistrationForm(@RequestPart("profilePicture") byte[] profilePicture,Model model,RedirectAttributes redirectAttrs,@Valid Spittle spittle, Errors errors) {
		if (errors.hasErrors()) {
			return "registerForm";
		}
		//spittle = spittleRepository.save(spittle);
		spittle.setId(12l);
		//the result redirect path will be /spitter/23?username=abc.
		//model.addAttribute("userid", 1111);
		//model.addAttribute("usernmae","abc");
		redirectAttrs.addAttribute("userid", 1111);
		redirectAttrs.addAttribute("usernmae","abc");
		redirectAttrs.addFlashAttribute("spittle", spittle);
		return "redirect:/spittles/{userid}";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	//it will handle a DuplicateSpittleException thrown from any
	//method in SpittleController
	@ExceptionHandler(SpittleNotFoundException.class)
	public String handleDuplicateSpittle(){
		return "error/duplicate";
	}
}
