package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dto.ComputerDTO;
import main.java.service.ComputerService;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/dashboard")
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = ComputerService.getInstance();
		ArrayList<ComputerDTO> computerDTO_list = computerService.findAll();

		request.setAttribute("computers", computerDTO_list);
		request.setAttribute("taille", computerDTO_list.size());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		requestDispatcher.forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//
//		getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
