package com.string.cli;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.service.MovieService;
import com.spring.service.SessionService;

public class CinemaCLI {
	static SessionService Sessions;
	static MovieService Movies;

	public static void initBeans() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Sessions = context.getBean("SessionService", SessionService.class);
		Movies = context.getBean("MovieService", MovieService.class);
	}

	// print massage and return user's input - string
	public static int getInputInt(String massage) {
		System.out.println(massage);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	// print massage and return user's input - string
	public static String getInputString(String massage) {
		System.out.println(massage);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// print menu and return user's selection - int
	public static int menu() {
		System.out.println("HI! welcome to our Cinema :) \n please select an option from the menu:"
				+ "\n  1. Show movies" + "\n  2. Show sessions" + "\n  3. get movie by id" + "\n  4. Add a movie" + "\n  5. Update a movie"
				+ "\n  6. Delete a movie" + "\n  7. get session by id" + "\n  8. Add a session" + "\n  9. Update a session"
				+ "\n  10. Delete a session" + "\n  0. Exit" + "\n");
		int selection = getInputInt("\nYour selection:");
		// checks if the selection is legal, else re-inputs it
		while (selection < 0 || selection > 10) {
			selection = getInputInt("illegal selection");
		}
		return selection;
	}

	// _____Show Movies_____
	public static void ShowMovies() {
		System.out.println("_____Show Movies_____");
		List<Movie> allMovies = null;
		try {
			allMovies = Movies.getAll();
			for (int i = 0; i < allMovies.size(); i++) {
				System.out.println(allMovies.get(i));
				List<Session> allSession = Sessions.getAll();
				// print session the movie is projected in
				for (int j = 0; j < allSession.size(); j++) {
					if (allSession.get(j).getMovieID().equals(allMovies.get(i).getMovieID()))
						System.out.println("The movie is projected in those sessions: " + allSession.get(i));
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Show Sessions_____
	public static void ShowSessions() {
		System.out.println("_____Show Sessions_____");
		List<Session> allSessions = null;
		try {
			allSessions = Sessions.getAll();
			for (int i = 0; i < allSessions.size(); i++) {
				System.out.println(allSessions.get(i));
				// print the movie projected
				List<Movie> allMovies = Movies.getAll();
				for (int j = 0; j < allMovies.size(); j++) {
					if (allSessions.get(i).getMovieID().equals(allMovies.get(j).getMovieID()))
						System.out.println("The movie is projected n these theater: " + allMovies.get(j));
				}
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Add Session_____
	public static void AddSession() {
		System.out.println("_____Add Session_____");
		String hallID = getInputString("please enter hall id: ");
		String date = getInputString("please enter date: ");
		String time = getInputString("please enter time: ");
		int capacity = getInputInt("please enter capacity: ");
		int ticketPrice = getInputInt("please enter ticket price: ");
		String movieID = getInputString("please enter movie id: ");
		Session toAdd = new Session(hallID, date, time, capacity, (float) ticketPrice, movieID);
		try {
			Sessions.save(toAdd);
			System.out.println("Session added");
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Update Session_____
	public static void UpdateSession() {
		System.out.println("_____Update Session_____");
		String sessionID = getInputString("please enter session id: ");
		String hallID = getInputString("please enter updated hall id: ");
		String date = getInputString("please enter updated date: ");
		String time = getInputString("please enter updated time: ");
		int capacity = getInputInt("please enter updated capacity: ");
		int ticketPrice = getInputInt("please enter updated ticket price: ");
		String movieID = getInputString("please enter updated movie id: ");
		try {
			Session toUpdate = new Session(hallID, date, time, capacity, (float) ticketPrice, movieID);
			toUpdate.setSessionID(sessionID);
			Sessions.update(toUpdate);
			System.out.println("Session updated");
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Show Session by ID_____
	public static void GetSession() {
		System.out.println("_____Show Session by ID_____");
		String sessionID = getInputString("please enter session id: ");
		try {
			Session temp = Sessions.get(sessionID);
			if(temp == null)
				System.out.println("session not found");
			else
				System.out.println(temp);
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	// _____Show Movie by ID_____
		public static void GetMovie() {
			System.out.println("_____Show Movie by ID_____");
			String movieID = getInputString("please enter movie id: ");
			try {
				Movie temp = Movies.get(movieID);
				if(temp == null)
					System.out.println("movie not found");
				else
					System.out.println(temp);
			} catch (Exception e) {
				System.out.println(e);
			} 
		}
	
	// _____Delete Session_____
		public static void DeleteSession() {
			System.out.println("_____Delete Session_____");
			String sessionID = getInputString("please enter session id: ");
			try {
				Sessions.delete(sessionID);
				System.out.println("Session deleted");
			} catch (Exception e) {
				System.out.println(e);
			} 
		}

	// _____Add Movie_____
	public static void AddMovie() {
		System.out.println("_____Add Movie_____");
		String title = getInputString("please enter movies title: ");
		int minAge = getInputInt("please enter movie age limit: ");
		Movie toAdd = new Movie(title, minAge);
		try {
			Movies.save(toAdd);
			System.out.println("movie added");
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Update Movies_____
	public static void UpdateMovies() {
		System.out.println("_____Update Movies_____");
		String movieID = getInputString("please enter movie id: ");
		String title = getInputString("please enter updated movies title: ");
		int minAge = getInputInt("please enter updated movie age limit: ");
		try {
			Movie toUpdate = new Movie(title, minAge);
			toUpdate.setMovieID(movieID);
			Movies.update(toUpdate);
			System.out.println("movie updated");
		} catch (Exception e) {
			System.out.println(e);
		} 

	}

	// _____Delete Movies_____
	public static void DeleteMovies() {
		System.out.println("_____Delete Movies_____");
		String movieID = getInputString("please enter movie id: ");
		try {
			Movies.delete(movieID);
			System.out.println("movie deleted");
		} catch (Exception e) {
			System.out.println(e);
		} 
	}

	public static void main(String args[]) // static method
	{
		//init cinema
		initBeans(); // generate
		// Fix Letters input
		// ------------------------menu-------------------------------------

		int selection;
		while (true) {
			System.out.println("");
			selection = menu();
			switch (selection) {
			case 0:
				System.out.println("--Exit-- \n" + "        GoodBye");
				System.exit(0);
			case 1:
				ShowMovies();
				break;
			case 2:
				ShowSessions();
				break;
			case 3:
				GetMovie();
				break;
			case 4:
				AddMovie();
				break;
			case 5:
				UpdateMovies();
				break;
			case 6:
				DeleteMovies();
				break;
			case 7:
				GetSession();
				break;
			case 8:
				AddSession();
				break;
			case 9:
				UpdateSession();
				break;
			case 10:
				DeleteSession();
				break;
			default:
				System.out.println("Error - menu selection");
				break;
			}
		}
	}
}
