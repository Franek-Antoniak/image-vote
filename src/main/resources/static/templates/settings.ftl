<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Poll Site" />
    <meta name="author" content="Franek Antoniak" />
    <title>Settings Page</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/assets/homepage.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/homepage.css" rel="stylesheet"/>
    <link href="/css/settings.css" rel="stylesheet"/>
    <!-- Log out scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-5PGH8YYN7C"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());

      gtag('config', 'G-5PGH8YYN7C');
    </script>
</head>
<body class="d-flex flex-column min-vh-100">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand">Głosowanie</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/">Głosuj</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/results">Wyniki</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/creator">Kreator</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/settings">Ustawienia</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="https://github.com/Franek-Antoniak" target="_blank">Github</a></li>
                <li class="nav-item dropdown">
            </ul>
            <a href="/perform_logout" class="btn btn-outline-dark">Wyloguj się</a>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Zmień ustawienia aplikacji</h1>
            <p class="lead fw-normal text-white-50 mb-0">Ustawienia dostępne jedynie dla administratora</p>
        </div>
    </div>
</header>
<!-- Section-->
<section class="py-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-8 mx-auto">
                <h2 class="h3 mb-4 page-title">Ustawienia</h2>
                <div class="my-4">
                    <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Bazy danych</a>
                        </li>
                    </ul>
                    <h5 class="mb-0 mt-5">Ustawienia bazy danych</h5>
                    <hr class="my-4"/>
                    <strong class="mb-0">Operacje na danych</strong>
                    <p>Kliknij przycisk aby wysłać polecenie do bazy danych</p>
                    <!-- Start of Operacje na danych -->
                    <div class="list-group mb-5 shadow">
                        <!-- Start of first setting -->
                        <div class="list-group-item">
                            <div class="row align-items-center">
                                <div class="col">
                                    <strong class="mb-0">Wyczyść wszystkie dane</strong>
                                    <p class="text-muted mb-0">Usuwa wszystkie informacje o użytkownikach oraz ich obrazach</p>
                                </div>
                                <div class="col-auto">
                                    <div class="custom-control custom-switch">
                                        <button class="btn btn-outline-dark" onclick="deleteData()">Wyczyść dane</button>
                                        <span class="custom-control-label"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End of first setting -->
                    </div>
                    <!-- End of Operacje na danych -->
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark mt-auto">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Paco 2021</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/js/sender.js"></script>
</body>
</html>
