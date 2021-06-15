<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Error 403" />
    <meta name="author" content="Franek Antoniak" />
    <meta name="description" content="Error Page" />
    <meta charset="UTF-8">
    <title>Error 403 - Only For Admin</title>
    <link href="/css/error403.css" rel="stylesheet"/>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/assets/homepage.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/homepage.css" rel="stylesheet" />
</head>
<body>
<style>
    a, a:hover, a:visited, a:active {
      color: inherit;
      text-decoration: none;
     }
    .logout-right {
       position:fixed;
       right:10px;
       top:5px;
    }
    .logout-left {
       position:fixed;
       left:10px;
       top:5px;
    }
</style>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" bg-light style="background-color: #f3f2ee;">
  <div class="container px-4 px-lg-5">
    <a class="navbar-brand">Głosowanie</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
        <li class="nav-item"><a class="nav-link" aria-current="page" href="/">Głosuj</a></li>
        <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/results">Wyniki</a></li>
        <li class="nav-item"><a class="nav-link" aria-current="page" href="/user/creator">Kreator</a></li>
        <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/settings">Ustawienia</a></li>
        <li class="nav-item"><a class="nav-link" aria-current="page" href="https://github.com/Franek-Antoniak" target="_blank">Github</a></li>
        <li class="nav-item dropdown">
      </ul>
      <a href="/perform_logout" class="btn btn-outline-dark logout">Wyloguj się</a>
    </div>
  </div>
</nav>
<a href="/">
    <div class='hover'>
        <div class='background'>
            <div class='door'>403</div>
            <div class='rug'></div>
        </div>
        <div class='foreground'>
            <div class='bouncer'>
                <div class='head'>
                    <div class='neck'></div>
                    <div class='eye left'></div>
                    <div class='eye right'></div>
                    <div class='ear'></div>
                </div>
                <div class='body'></div>
                <div class='arm'></div>
            </div>
            <div class='poles'>
                <div class='pole left'></div>
                <div class='pole right'></div>
                <div class='rope'></div>
            </div>
        </div>
    </div>
</a>
</body>
</html>