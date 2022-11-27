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
    <title>Первый квест. Потеря памати.</title>
</head>


<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center text-white">${question}</h1>
                    <p></p>
                    <a href=${buttonYes} class="btn btn-game btn-lg text-uppercase"> ${answerYes}</a>
                    <p></p>
                    <a href=${buttonNo} class="btn btn-game btn-lg text-uppercase"> ${answerNo}</a>
                </div>
            </div>
        </div>
    </header>
<!--
    <p>${question}</p>
    <div>
        <p></p>
        <a href = ${buttonYes}>${answerYes}</a>
        <p></p>
        <a href = ${buttonNo}>${answerNo}</a>
        <p></p>
    </div>
    -->
    <footer id="footer">

    </footer>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
