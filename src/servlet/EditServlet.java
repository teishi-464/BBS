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
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String view;
		if(request.getParameter("eid") != null){
			session.setAttribute("eid", request.getParameter("eid"));
			view = "/WEB-INF/view/edit.jsp";
		}else if(request.getParameter("did") != null){
			view = "/WEB-INF/view/top.jsp";
			CommentDao.deleteComment(Integer.parseInt(request.getParameter("did")));
			ArrayList<Comment> ac = new ArrayList<Comment>(CommentDao.allComment());
			session.setAttribute("ac", ac);
		}else{
			view = "/WEB-INF/view/top.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		if(request.getParameter("name") != null){
			String sid = (String)session.getAttribute("eid");
			int id = Integer.parseInt(sid);
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");

			comment = comment.replaceAll("&", "&amp;");
			comment = comment.replaceAll("<", "&lt;");
			comment = comment.replaceAll(">", "&gt;");
			comment = comment.replaceAll("\r\n", "<br>");

			LocalDateTime datetime =  LocalDateTime.now();
			System.out.println(datetime);

			Comment c = new Comment(id,name,email,comment,datetime.toString());

			CommentDao.updateComment(c);

			ArrayList<Comment> ac = new ArrayList<Comment>(CommentDao.allComment());
			session.setAttribute("ac", ac);
		}

		String view = "/WEB-INF/view/top.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
