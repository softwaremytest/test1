package myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myblog.bean.Post;
import myblog.util.DBUtil;

public class PostDao {
	public boolean create(Post post) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_post(author, title, content) VALUES(?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getAuthor());
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return result==1;
	}
	
	public List<Post> queryPosts() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM tb_post ORDER BY posttime DESC";
		ResultSet rs = null;
		List<Post> posts = new ArrayList<Post>();		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setPostId(rs.getInt(1));
				post.setAuthor(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				post.setPosttime(rs.getTimestamp(5));
				post.setPv(rs.getInt(6));
				posts.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return posts;
	}
	
	public List<Post> queryPostsByAuthor(String author) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM tb_post WHERE author=? ORDER BY posttime DESC";
		ResultSet rs = null;
		List<Post> posts = new ArrayList<Post>();		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Post post = new Post();
				post.setPostId(rs.getInt(1));
				post.setAuthor(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				post.setPosttime(rs.getTimestamp(5));
				post.setPv(rs.getInt(6));
				posts.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return posts;
	}
	
	public Post queryPostById(int postId) {
		Post post = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql1 = "UPDATE tb_post set pv=pv+1 WHERE _id=?";
		String sql2 = "SELECT * FROM tb_post WHERE _id=?";
		ResultSet rs = null;
		int result = 0;
		try {
			// 禁用自动提交
			conn.setAutoCommit(false); 
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, postId);
			result = pstmt.executeUpdate();
			if (result == 0) throw new Exception();
			pstmt.close();			
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				post = new Post();
				post.setPostId(rs.getInt(1));
				post.setAuthor(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setContent(rs.getString(4));
				post.setPosttime(rs.getTimestamp(5));
				post.setPv(rs.getInt(6));
			}

			// 手动提交事务
			conn.commit(); 
		} catch (Exception e) {
			// 事务回滚 
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 启用自动提交
			try {
				conn.setAutoCommit(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return post;
	}
}
