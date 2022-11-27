<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/fontello.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Сборник квестов</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center text-white">Квест... Квест...Квест...</h1>
                    <!--<div class="itd_play">
                        <div class="itd_triangle"></div>
                    </div>
                    -->
                    <!--a href="#footer" class="btn btn-itd btn-lg text-uppercase">Старт</a-->
                    <!--<input type="hidden" name="nextQ" value="1">-->
                    <a href="hello-servlet?nextQ=1" class="itd_play">
                        <div class="itd_triangle"></div>
                    </a>
                    <a href="hello-servlet?nextQ=1" class="btn btn-itd btn-lg text-uppercase">Старт</a>
                </div>
            </div>
        </div>
    </header>


<!--
    <h1>Готовы поиграть?</h1>
    <form action="hello-servlet">
        Name: <input name="username" />
        <input type="hidden" name="nextQ" value="1">
        <br><br>
        <input type="radio" name="nextQ" value="1" checked />Я принимаю вызов!
        <br><br>
        <input type="radio" name="nextQ" value="-1" />В другой раз...
        <br><br>
        <input type="submit" value="Submit" />
    </form>
    -->

    <footer id="footer">

    </footer>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>