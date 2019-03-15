package myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import myblog.bean.Post;
import myblog.bean.User;
import myblog.dao.PostDao;
import myblog.dao.UserDao;

/**
 * Servlet implementation class NewPostAction
 */
@WebServlet("/NewPostAction")
public class NewPostAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPostAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("newpost.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		
		String title=request.getParameter("postTitle");
		String content=request.getParameter("postContent");
		String author=((User)request.getSession(true).getAttribute("User")).getUsername();
		
		
		Post post=new Post(0,author,title,content,(new Date()),0);
		PostDao postDao=new PostDao();
		boolean result=postDao.create(post);
		if(result==true) {
			out.println("<script>alert('new post successful.');location='index.jsp';</script>");
		}else {
			out.println("<script>alert('new post failed.');location='javascript:history.go(-1)';</script>");
		}
	}

}
