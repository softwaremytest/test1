<%@include file="components/header.jsp" %>

    <div class="container">
        <header class="blog-header py-3">
            <div class="row flex-nowrap justify-content-between align-items-center">
                <div class="col-4 pt-1">
                    <a class="btn btn-sm btn-outline-primary" href="newpost.jsp">New post</a>
                </div>
                <div class="col-4 text-center">
                    <a class="blog-header-logo text-dark" href="index.jsp">Large</a>
                </div>
                <div class="col-4 d-flex justify-content-end align-items-center">
                    <a class="text-muted" href="#">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                            stroke-linecap="round" stroke-linejoin="round" class="mx-3">
                            <circle cx="10.5" cy="10.5" r="7.5"></circle>
                            <line x1="21" y1="21" x2="15.8" y2="15.8"></line>
                        </svg>
                    </a>
                    <!-- <a class="btn btn-sm btn-outline-secondary" href="signin.html">Sign in</a>&nbsp;
              <a class="btn btn-sm btn-outline-secondary" href="signup.html">Sign up</a> -->
                    Welcome,&nbsp;
                    <a href="#">Mark</a>&nbsp;
                    <a class="btn btn-sm btn-outline-secondary" href="signout.html">Sign out</a>
                </div>
            </div>
        </header>

    </div>
    <br>

    <main role="main" class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <div class="row">
                            <label for="postTitle" class="col-sm-2 control-label">
                                Title:</label>
                            <div class="col-sm-10">
                                <input type="text" name="postTitle" id="postTitle" class="form-control" placeholder="Input a title" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label for="postContent" class="col-sm-2 control-label">
                                Content:</label>
                            <div class="col-sm-10">
                                <textarea rows="24" name="postContent" id="postContent" class="form-control" placeholder="Write your contents (less than 3000 words, markdown grammar is supported)"
                                    required></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <div class="offset-sm-2 col-sm-10">
                                <button class="btn btn-lg btn-primary" type="submit">Commit</button>
                                <button class="btn btn-lg btn-secondary" onclick="javascript:history.go(-1);">Cancel</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.row -->


    </main>
    <!-- /.container -->
   <%@include file="components/footer.jsp" %>