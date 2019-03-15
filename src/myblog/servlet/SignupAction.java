package myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import myblog.bean.User;
import myblog.dao.UserDao;

import java.io.File;

/**
 * Servlet implementation class SignupAction
 */
@WebServlet("/SignupAction")
@MultipartConfig
public class SignupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("signup.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		
		String username=request.getParameter("inputEmail");
		String password=request.getParameter("inputPassword");
		String gender=request.getParameter("gender");
		String bio=request.getParameter("introduce");
		Part part=request.getPart("avatar");
		
		String fileName=part.getSubmittedFileName();
		String newFileName=UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf('.'));
		String filepath=getServletContext().getRealPath("./images/upload");
		File f=new File(filepath);
		if(!f.exists()) {
			f.mkdirs();
		}
		part.write(filepath+"/"+newFileName);
		String avatar=newFileName;
		
		User user=new User(username,password,avatar,gender,bio);
		UserDao userDao=new UserDao();
		boolean result=userDao.create(user);
		if(result==true) {
			out.println("<script>alert('register successful.');location='signin.html';</script>");
		}else {
			out.println("<script>alert('register failed.');location='signup.html';</script>");
		}
	}

}
