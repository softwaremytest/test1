<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-10 blog-main">

        <div class="blog-post">
          <h2 class="blog-post-title">${post.title }</h2>
          <p class="blog-post-meta">${post.posttime }
            <a href="#">${post.author }</a>.&nbsp;&nbsp;
            <img src="./images/font-visited.png" height="16">(${post.pv })
            <img src="./images/font-comment.png" height="16">(${postsCommentsCount[post.postId] })</p>

          <p>${post.content }</p>
        </div>
        <!-- /.blog-post -->

        <div class="blog-post">
          <a class="btn btn-outline-primary" href="index.html">Return</a>
          
          <a class="btn btn-outline-primary" href="editpost.html">Edit</a>
        </div>

      </div>
      <!-- /.blog-main -->

    </div>
    <!-- /.row -->