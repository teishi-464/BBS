package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Comment;
import dao.CommentDao;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		ArrayList<Comment> ac = new ArrayList<Comment>(CommentDao.allComment());

		HttpSession session =request.getSession();
		session.setAttribute("ac", ac);

		if(request.getParameter("edit") != null){
			System.out.println(1);
			session.setAttribute("edit", request.getParameter("edit"));
		}

		String view = "/WEB-INF/view/top.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("name") != null){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");

			comment = comment.replaceAll("&", "&amp;");
			comment = comment.replaceAll("<", "&lt;");
			comment = comment.replaceAll(">", "&gt;");
			comment = comment.replaceAll("\r\n", "<br>");

			LocalDateTime datetime =  LocalDateTime.now();
			System.out.println(datetime);

			Comment c = new Comment(name,email,comment,datetime.toString());

			CommentDao.insertComment(c);
		}

		ArrayList<Comment> ac = new ArrayList<Comment>(CommentDao.allComment());

		HttpSession session =request.getSession();
		session.setAttribute("ac", ac);

		String view = "/WEB-INF/view/top.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
