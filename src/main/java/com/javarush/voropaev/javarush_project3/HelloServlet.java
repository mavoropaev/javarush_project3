package com.javarush.voropaev.javarush_project3;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static java.lang.System.out;
import static java.lang.System.setOut;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    TreeDialog treeDialog = new TreeDialog();

    public void init() {
        message = "Hello World!";
        treeDialog.treeInit();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String nextQStr = request.getParameter("nextQ");
        int nextQ = Integer.parseInt(nextQStr);

        try {
                if (nextQ > 0) {
                    request.setAttribute("question", treeDialog.getQuestion(nextQ).text);
                    if (!(treeDialog.getQuestion(nextQ).isLoose || treeDialog.getQuestion(nextQ).isWin)) {
                        int nextQYes = treeDialog.getQuestion(nextQ).answers.get(0).nextQuestion;
                        int nextQNo = treeDialog.getQuestion(nextQ).answers.get(1).nextQuestion;
                        request.setAttribute("buttonYes", "hello-servlet?nextQ=" + nextQYes + "&answer=yes");
                        request.setAttribute("answerYes", treeDialog.getQuestion(nextQ).answers.get(0).text);
                        request.setAttribute("buttonNo", "hello-servlet?nextQ=" + nextQNo + "&answer=no");
                        request.setAttribute("answerNo", treeDialog.getQuestion(nextQ).answers.get(1).text);
                    }
                    else{
                        request.setAttribute("buttonYes", "hello-servlet?nextQ=1&answer=yes");
                        request.setAttribute("answerYes", "Повторить");
                        request.setAttribute("buttonNo", "hello-servlet?nextQ=-1&answer=no");
                        request.setAttribute("answerNo", "В следующий раз...");

                    }
                }
                else{
                    request.setAttribute("question", "Пока!");
                }

                getServletContext().getRequestDispatcher("/game.jsp").forward(request, response);

        }
        catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}

