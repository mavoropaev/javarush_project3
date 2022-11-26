<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1>Готовы поиграть?</h1>
<form action="hello-servlet">
    Name: <input name="username" />
    <!--<input type="hidden" name="nextQ" value="1">-->
    <br><br>
    <input type="radio" name="nextQ" value="1" checked />Я принимаю вызов!
    <br><br>
    <input type="radio" name="nextQ" value="-1" />В другой раз...
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>