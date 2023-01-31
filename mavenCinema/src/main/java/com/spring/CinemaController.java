package com.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.service.MovieService;
import com.spring.service.SessionService;

@Controller
public class CinemaController {
	@Autowired
	private SessionService Sessions;
	@Autowired
	private MovieService Movies;

	// login page
	@RequestMapping("/")
	public String showLoginForm(Model model, HttpServletRequest request) {
		User user = new User();

		// Add the user object as a model attribute
		model.addAttribute("user", user);

		String param = request.getParameter("incorrect");
		if (param != null)
			model.addAttribute("message", "Username or password is inncorrect, please try again");
		return "login-page";
	}

	// processLogin - password == abc
	@RequestMapping("/processLogin")
	public String processLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
		System.out.println(user);

		if (user.getPassword().equals("abc")) {
			// create session and put user object on session
			request.getSession().setAttribute("user", user);
			return "redirect:/showMainScreen";
		}

		return "redirect:/?incorrect=true";

	}

	// redirect to main
	@RequestMapping("/showMainScreen")
	public ModelAndView showMainScreen(HttpServletRequest request) {

		if (request.getSession().getAttribute("user") == null) {
			// user has no data on session
			ModelAndView modelANDview = new ModelAndView();
			modelANDview.setViewName("redirect:/");
			return modelANDview;
		}

		return main();
	}

	// logout
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();

		return "redirect:/";
	}

	// --------------------------------------------------------
	// main - tabels
	@RequestMapping("/main")
	public ModelAndView main() {
		List<Movie> allMovies = null;
		try {
			allMovies = Movies.getAll();
		} catch (Exception e) {
			error(e);
		}
		List<Session> allSessions = null;
		try {
			allSessions = Sessions.getAll();
		} catch (Exception e) {
			return error(e);
		}
		ModelAndView modelANDview = new ModelAndView();
		modelANDview.setViewName("main-menu");
		modelANDview.addObject("sessions", allSessions);
		modelANDview.addObject("movies", allMovies);
		return modelANDview;
	}

	// error screen
	@RequestMapping("/error")
	public ModelAndView error(Exception e) {
		ModelAndView modelANDview = new ModelAndView();
		modelANDview.setViewName("errors");
		modelANDview.addObject("error", e);
		return modelANDview;
	}

	// movie form
	@RequestMapping("/showFormMovie")
	public ModelAndView showForm(Model model, @ModelAttribute("id") String id) {
		// Create a movie object
		Movie movie;
		try {
			movie = Movies.get(id);
		} catch (Exception e) {
			movie = new Movie();
		}
		// Add the movie object as a model attribute
		model.addAttribute("movie", movie);
		ModelAndView modelANDview = new ModelAndView();
		modelANDview.setViewName("movie-form");
		return modelANDview;

	}

	// process movie
	@RequestMapping("/processFormMovie")
	public ModelAndView processForm(@ModelAttribute("movie") Movie movie) {
		try {
			Movies.update(movie);
		} catch (Exception e) {
			try {
				Movies.save(movie);
			} catch (Exception q) {
				return error(q);
			}
		}
		return main();
	}

	// session form
	@RequestMapping("/showFormSession")
	public ModelAndView showFormSession(Model model, @ModelAttribute("id") String id) {
		// Create a movie object
		Session session;
		try {
			session = Sessions.get(id);
		} catch (Exception e) {
			session = new Session();
		}
		// Add the movie object as a model attribute
		model.addAttribute("session", session);
		ModelAndView modelANDview = new ModelAndView();
		modelANDview.setViewName("session-form");
		return modelANDview;

	}

	// process session
	@RequestMapping("/processFormSession")
	public ModelAndView processFormSession(@ModelAttribute("session") Session session) {
		try {
			Sessions.update(session);
		} catch (Exception e) {
			try {
				Sessions.save(session);
			} catch (Exception q) {
				return error(q);
			}
		}
		return main();
	}

	// process session
	@RequestMapping("/delete")
	public ModelAndView delete(@ModelAttribute("id") String id) {
		try {
			Sessions.delete(id);
		} catch (Exception e) {
			try {
				Sessions.delete(id);
			} catch (Exception q) {
				return error(q);
			}
		}
		return main();
	}
}
