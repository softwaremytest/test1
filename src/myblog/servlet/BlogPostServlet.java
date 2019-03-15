package myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import myblog.bean.Post;
import myblog.bean.User;
import myblog.bean.Comment;
import myblog.dao.CommentDao;
import myblog.dao.PostDao;
import myblog.dao.UserDao;

/**
 * Servlet implementation class BlogPostServlet
 */
@WebServlet("/BlogPostServlet")
public class BlogPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");		
		String postId = request.getParameter("postid");
		String author = request.getParameter("author");
		
		PostDao postDao = new PostDao();
		CommentDao commentDao = new CommentDao();
		if (type.equals("listposts")) {
			// 1 查询posts
			List<Post> posts = null;
			if(author != null && !author.equals("")) {
				posts = postDao.queryPostsByAuthor(author);
			} else {
				posts = postDao.queryPosts();
			}
			
			// 2 查询作者头像
			Map<Integer, String> postsAuthorAvatar = getPostAuthorAvator(posts);
			
			// 3 查询评论数量
			Map<Integer, Integer> postsCommentsCount = getPostsCommentsCount(posts);
			
			// 4 设置request.setAttribute
			request.setAttribute("posts", posts);
			request.setAttribute("postsAuthorAvatar", postsAuthorAvatar);
			request.setAttribute("postsCommentsCount", postsCommentsCount);
			
			// 5 请求包含include
			request.getRequestDispatcher("components/index-post-content.jsp").include(request, response);
		} else if (type.equals("listpost")) {
			if (postId != null && !postId.equals("")) {
				// 1 查询post
				Post post = postDao.queryPostById(Integer.parseInt(postId));
				// 2 查询评论数量
				List<Post> posts = new ArrayList<Post>();
				posts.add(post);
				Map<Integer, Integer> postsCommentsCount = getPostsCommentsCount(posts);
				// 3 设置request.setAttribute
				request.setAttribute("post", post);
				request.setAttribute("postsCommentsCount", postsCommentsCount);
				// 4 请求包含include
				request.getRequestDispatcher("components/post-content.jsp").include(request, response);
			}
		} else if (type.equals("listcomments")) {
			if (postId != null && !postId.equals("")) {
				// 1 查询comments
				List<Comment> comments = commentDao.queryCommentsByPostId(Integer.parseInt(postId));
				// 2 查询comment的作者头像
				Map<Integer, String> commentsAuthorAvatar = getCommentAuthorAvator(comments);
				// 3 设置request.setAttribute
				request.setAttribute("comments", comments);
				request.setAttribute("commentsAuthorAvatar", commentsAuthorAvatar);
				// 4 请求包含include
				request.getRequestDispatcher("components/post-comments.jsp").include(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected Map<Integer, String> getPostAuthorAvator(List<Post> posts) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		UserDao userDao = new UserDao();
		for (Post post:posts) {
			User user = userDao.query(post.getAuthor());
			if (user != null) {
				map.put(post.getPostId(), user.getAvatar());
			}
		}
		return map;
	}
	
	protected Map<Integer, Integer> getPostsCommentsCount(List<Post> posts) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		CommentDao commentDao = new CommentDao();
		for (Post post:posts) {
			List<Comment> comments = commentDao.queryCommentsByPostId(post.getPostId());
			map.put(post.getPostId(), comments.size());
		}
		return map;
	}
	
	protected Map<Integer, String> getCommentAuthorAvator(List<Comment> comments) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		UserDao userDao = new UserDao();
		for (Comment comment:comments) {
			User user = userDao.query(comment.getAuthor());
			if (user != null) {
				map.put(comment.getCommentId(), user.getAvatar());
			}
		}
		return map;
	}
}
  