<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Poll Site"/>
    <meta name="author" content="Franek Antoniak"/>
    <title>Home page</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="/assets/homepage.ico"/>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/homepage.css" rel="stylesheet"/>
    <!-- Modal CSS-->
    <link href="/css/modal.css" rel="stylesheet"/>
    <!-- Log out scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-5PGH8YYN7C"></script>
    <script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }

        gtag('js', new Date());

        gtag('config', 'G-5PGH8YYN7C');
    </script>
</head>
<body class="d-flex flex-column min-vh-100">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand">Głosowanie</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                    class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/">Głosuj</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/results">Wyniki</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/user/creator">Kreator</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="/admin/settings">Ustawienia</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="https://github.com/Franek-Antoniak"
                                        target="_blank">Github</a></li>
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
            <h1 class="display-4 fw-bolder">Oddaj swój głos na najlepsze obrazy</h1>
            <p class="lead fw-normal text-white-50 mb-0">Ilość głosów do oddania: ${3 - user.votes?size}</p>
        </div>
    </div>
</header>
<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 justify-content-center">
            <!-- box-start -->
            <#list imageList as image>
                <div class="col mb-5">
                    <div class="card h-70">
                        <!-- Click badge-->
                        <button class="badge btn-light text-dark position-absolute"
                                style="top: 0.5rem; right: 0.5rem"
                                id="view-${image.id}"
                                onClick='renderModal("${image.id}")'>View
                        </button>
                        <!-- Product image-->
                        <img class="card-img-top img-to-modal" src="/images/${image.fileName}" alt="..." id="${image
                        .id}"/>
                        <#if image.author.equals(user)>
                            <!-- Delete badge-->
                            <button class="badge btn-danger text-white position-absolute"
                                    style="top: 0.5rem; left: 0.5rem"
                                    onclick="deleteImage('${image.uniqueId}');">Delete
                            </button>
                        </#if>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-4 border-top-0 bg-transparent">
                            <#if !image.voters?seq_contains(user)>
                                <div class="text-center">
                                    <button class="btn btn-outline-dark mt-auto"
                                            onclick="vote('${image.uniqueId}')">Zagłosuj
                                    </button>
                                </div>
                            <#else>
                                <div class="text-center">
                                    <button class="btn btn-outline-dark mt-auto"
                                            onclick="unvote('${image.uniqueId}')">Odbierz głos
                                    </button>
                                </div>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
            <!-- box-end -->
        </div>
    </div>
    <!-- The Modal -->
    <div id="modal" class="modal">
        <!-- The Close Button -->
        <span class="close">&times;</span>
        <!-- Modal Content (The Image) -->
        <img class="modal-content" id="img01">
    </div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark mt-auto">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Paco 2021</p></div>
</footer>
</body>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="/js/gallery.js"></script>
</html>
