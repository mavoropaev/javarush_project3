package com.javarush.voropaev.javarush_project3;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    private String message;
    TreeDialog treeDialog = new TreeDialog();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        logger.info("method doGet");
        String idQuestStr = request.getParameter("idQuest");
        if (idQuestStr != null){
            int idQuest = Integer.parseInt(idQuestStr);
            treeDialog.treeInitJSON(idQuest);
        }
        String nextQStr = request.getParameter("nextQ");
        int nextQ = Integer.parseInt(nextQStr);

        try {
                if (nextQ > 0) {
                    request.setAttribute("question", treeDialog.getQuestion(nextQ).text);
                    if (!(treeDialog.getQuestion(nextQ).isLoose || treeDialog.getQuestion(nextQ).isWin)) {

                        Random random = new Random();
                        int randomInt100 = 0;
                        while (randomInt100 == 0) {
                            randomInt100 = random.nextInt(100);
                        }
                        int firstButton = randomInt100 < 50 ? 0 : 1;
                        int secondButton = randomInt100 < 50 ? 1 : 0;

                        int nextQYes = treeDialog.getQuestion(nextQ).answers.get(firstButton).nextQuestion;
                        int nextQNo = treeDialog.getQuestion(nextQ).answers.get(secondButton).nextQuestion;
                        request.setAttribute("buttonYes", "hello-servlet?nextQ=" + nextQYes + "&answer=yes");
                        request.setAttribute("answerYes", treeDialog.getQuestion(nextQ).answers.get(firstButton).text);
                        request.setAttribute("buttonNo", "hello-servlet?nextQ=" + nextQNo + "&answer=no");
                        request.setAttribute("answerNo", treeDialog.getQuestion(nextQ).answers.get(secondButton).text);

                    }
                    else{
                        request.setAttribute("buttonYes", "hello-servlet?nextQ=0&answer=yes");
                        request.setAttribute("answerYes", "Повторить");
                        request.setAttribute("buttonNo", "hello-servlet?nextQ=-1&answer=no");
                        request.setAttribute("answerNo", "В следующий раз...");
                    }
                    getServletContext().getRequestDispatcher("/game.jsp").forward(request, response);

                }
                else if(nextQ == 0) {
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    logger.info("game restart");
                }
                else{
                    request.setAttribute("question", "Пока!");
                    getServletContext().getRequestDispatcher("/gameover.jsp").forward(request, response);
                    logger.info("game over");
                }

        }
        catch (ServletException e) {
            logger.error("error message" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        logger.info("destroy servlet");
    }
}

