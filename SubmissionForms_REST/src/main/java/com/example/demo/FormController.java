package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class FormController {
	
	@Autowired
	CustomersRepo repo;

	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getCustomers() {
		return repo.findAll();
	}	

	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public Optional<Customers> getCustomersByID(@PathVariable("cid") int cid) {
		return repo.findById(cid);
	}
	
	@PostMapping("/customers")
	public Customers addCustomers(@RequestBody Customers customers) {
		
		repo.save(customers);
		return customers;
	}
	
	@DeleteMapping("/customers/{cid}")
	public void deleteCustomers(@PathVariable("cid") int cid) {		
		repo.deleteById(cid);
	}

	@PutMapping(path="/customers", consumes = {"application/json"})
	public void putCustomers(@RequestBody Customers customers) {
		repo.save(customers);		
	}

	@RequestMapping("/")
	public String customers() {
		return "ui";
	}
	
	@RequestMapping("/details")
	public String customers(Customers customers) {
		repo.save(customers);
		return "ui";
	}
	
	@RequestMapping("/getdetails")
	public String getcustomers(Customers customers) {
		return "ViewCustomers";
	}
	
	@PostMapping("/getdetails") 
	public ModelAndView viewDetails(@RequestParam int cid) {
		
		ModelAndView mv = new ModelAndView("Retrive");
		
		Customers customers = repo.findById(cid).orElse(null);
		
		mv.addObject(customers);
		
		return mv;
		
	}
}
