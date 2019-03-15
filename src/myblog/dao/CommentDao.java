package myblog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import myblog.bean.Comment;
import myblog.bean.Post;
import myblog.util.DBUtil;

public class CommentDao {
	public boolean create(Comment comment) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_comment(author, content, post_id) VALUES(?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getAuthor());
			pstmt.setString(2, comment.getContent());
			pstmt.setInt(3, comment.getPostId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return result==1;
	}
	public List<Comment> queryCommentsByPostId(int postId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM tb_comment WHERE post_id=? ORDER BY posttime DESC";
		ResultSet rs = null;
		List<Comment> comments = new ArrayList<Comment>();		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt(1));
				comment.setAuthor(rs.getString(2));
				comment.setContent(rs.getString(3));
				comment.setPostId(rs.getInt(4));
				comment.setPosttime(rs.getTimestamp(5));
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		
		return comments;
	}
}
