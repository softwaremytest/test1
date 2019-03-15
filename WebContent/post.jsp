<%@include file="components/header.jsp" %>

  <div class="container">
    <%@include file="components/nav.jsp" %>

  </div>
  <br>

<main role="main" class="container">
 <jsp:include page='${"BlogPostServlet"}'>
	<jsp:param value="listpost" name="type" />
	<jsp:param value="${param.postid }" name="postid" />
 </jsp:include> 
 <jsp:include page='${"BlogPostServlet"}'>
	<jsp:param value="listcomments" name="type" />
	<jsp:param value="${param.postid }" name="postid" />
 </jsp:include>

  </main>
  <!-- /.container -->
   <%@include file="components/footer.jsp" %>