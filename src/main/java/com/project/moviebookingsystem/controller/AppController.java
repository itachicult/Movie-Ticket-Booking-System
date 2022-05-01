package com.project.moviebookingsystem.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.moviebookingsystem.details.CustomUserDetails;
import com.project.moviebookingsystem.model.Movie;
import com.project.moviebookingsystem.model.User;
import com.project.moviebookingsystem.model.movieticket;
import com.project.moviebookingsystem.repository.UserRepository;
import com.project.moviebookingsystem.service.MovieService;
import com.project.moviebookingsystem.service.MovieTicketService;

@Controller
public class AppController {
	
	@Autowired
	private MovieService service;
	@Autowired
	private MovieTicketService ticketservice;
	@Autowired
    private UserRepository userRepo;

	@RequestMapping("/")
	public String displayMainPage() {
		return "mainPage";
	}
	
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "SignUpPage";
    }
    
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_sucess";
    }
	
	@RequestMapping("/home")
	public String displayHomePage() {
		return "HomePage";
	}
	
	@RequestMapping("/movielist")
	public String viewAllMovies(Principal principal,Model model) {
		List<Movie> listMovies = service.listAll();
		String[] admin = new String[] {"arjunv2001@gmail.com", "vkironv@yahoo.com"};
		String currUser = principal.getName();
		model.addAttribute(currUser);
		List<String> adminList = new ArrayList<>(Arrays.asList(admin));
		boolean cond = adminList.contains(currUser);
		model.addAttribute("cond",cond);
		model.addAttribute("listMovies", listMovies);
		return "movielist";
	}
	
	@RequestMapping("/search")
	public String viewMovieListPage(Movie movie, Model model, String keyword) {
		  if(keyword!=null) {
			   List<Movie> listMovies = service.getByKeyword(keyword);
			   model.addAttribute("listMovies", listMovies);
			  }
			  else {
			  List<Movie> listMovies = service.listAll();
			  model.addAttribute("listMovies", listMovies);
			  }
			  return "movielist";
	}
	
	@RequestMapping("/new")
	public String showNewMoviePage(Model model) {
	    Movie Movie = new Movie();
	    model.addAttribute("Movie",Movie);
	    return "new_movie";
	}
	
	@RequestMapping("/profile")
	public String showProfile(Principal principal,Model model,String keyword) {
		    List<movieticket> listmovietickets = ticketservice.getByKeyword(principal.getName());
		    model.addAttribute("listmovietickets", listmovietickets);
	        return "profile";
	}
	
	@RequestMapping("/book")
	public String showmovieticketbookpage(Principal principal,Model model) {
	    movieticket movieticket = new movieticket();
	    String currUser = principal.getName();
	    movieticket.setUser(currUser);
	    model.addAttribute("movieticket",movieticket);
	    List<Movie> listMovies = service.listAll();
	    model.addAttribute("listMovies", listMovies);
		//model.addAttribute("currUser",currUser);
	    return "movie_ticket";
	}
	
	@RequestMapping(value = "/saveticket", method = RequestMethod.POST)
	public String saveMovieTicket(@ModelAttribute("movieticket") movieticket movieticket) {
		List<Movie> movie = service.findByMovieName(movieticket.getMoviename());
		Movie rec = movie.get(0);
		long remTickets = rec.getSeats() - movieticket.getNumberoftickets();
		rec.setSeats(remTickets);
		service.save(rec);
		ticketservice.save(movieticket);
	    return "redirect:/confirm";
	}
	
	@RequestMapping("/confirm")
	public String confirmation() {
		return "confirmation";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMovie(@ModelAttribute("Movie") Movie movie) {
		movie.setSeats(300);
	    service.save(movie);
	    return "redirect:/movielist";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditMoviePage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_movie");
	    Movie movie = service.get(id);
	    mav.addObject("Movie", movie);
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteMovie(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("/deleteticket/{id}")
	public String deleteMovieTicket(@PathVariable(name = "id") int id) {
		movieticket movieticket = ticketservice.get(id);
		List<Movie> movie = service.findByMovieName(movieticket.getMoviename());
		Movie rec = movie.get(0);
		long remTickets = rec.getSeats() + movieticket.getNumberoftickets();
		rec.setSeats(remTickets);
		service.save(rec);
		ticketservice.delete(id);
	    return "redirect:/profile";       
	}
}
