package myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myblog.bean.Comment;
import myblog.bean.User;
import myblog.dao.CommentDao;
import myblog.dao.PostDao;

/**
 * Servlet implementation class NewCommentAction
 */
@WebServlet("/NewCommentAction")
public class NewCommentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCommentAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String content = request.getParameter("comment");
		String postId = request.getParameter("postid");
		String author = ((User)request.getSession().getAttribute("User")).getUsername();
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setAuthor(author);
		comment.setPostId(Integer.parseInt(postId));
		CommentDao commentDao = new CommentDao();
		boolean result = commentDao.create(comment);
		
		if (result == true) {
			out.println("<script>alert('new comment successfully.');location='index.jsp';</script>");
		} else {
			out.println("<script>alert('new comment failed.');location='javascript:history.go(-1)';</script>");
		}
	}

}
