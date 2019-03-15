<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${posts }" var="post">
	<div class="blog-post">
		<div>
			<div style="float: right; margin-top: 8px">
				<a href="index.jsp?author=${post.author }">
				<img src="./images/upload/${postsAuthorAvatar[post.postId] }" width="64"></a>
			</div>
			<div>
				<a href="post.jsp?postid=${post.postId }">
						<h2 class="blog-post-title">${post.title }</h2></a>
				<p class="blog-post-meta">${post.posttime }
					by <a href="#">${post.author }</a>.&nbsp;&nbsp; 
					<img src="./images/font-visited.png" height="16">(${post.pv }) 
					<img src="./images/font-comment.png" height="16">(${postsCommentsCount[post.postId] })
				</p>

				<p>${post.content }</p>
			</div>
			<!-- /.blog-post -->
</c:forEach>