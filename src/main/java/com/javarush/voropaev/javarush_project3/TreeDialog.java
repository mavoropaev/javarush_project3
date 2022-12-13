package com.javarush.voropaev.javarush_project3;

import lombok.Builder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeDialog {
    private static final Logger logger = LoggerFactory.getLogger(TreeDialog.class);
    HashMap<Integer, Question> treeQuestions = new HashMap<>();

    public Question getQuestion(int number) {
        if (treeQuestions.containsKey(number)) {
            return treeQuestions.get(number);
        }
        return null;
    }
    public void treeInitJSON(){
        JSONParser parser = new JSONParser();
        File file = new File(
                getClass().getClassLoader().getResource("quest.json").getFile()
        );

        //try(FileReader reader = new FileReader("C:\\Users\\mvoro\\IdeaProjects\\javarush_project3\\src\\main\\resources\\quest.json", StandardCharsets.UTF_8)) {
        try(FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
        JSONObject questJSONObject = (JSONObject)parser.parse(reader);

            JSONArray questionsJsonArray = (JSONArray)questJSONObject.get("quest");

            for (Object itemQ : questionsJsonArray){
                JSONObject questionJsonObject = (JSONObject) itemQ;

                long numberQ = (Long) questionJsonObject.get("number");
                String textQ = (String)questionJsonObject.get("text");

                JSONArray answersJsonArray = (JSONArray) questionJsonObject.get("answers");
                if (answersJsonArray != null) {
                    List<Answer> answerListQ = new ArrayList<>();

                    for (Object itemA : answersJsonArray) {
                        JSONObject answerJsonObject = (JSONObject) itemA;

                        String textA = (String) answerJsonObject.get("text");
                        long nextQuestionA = (Long) answerJsonObject.get("nextQuestion");
                        answerListQ.add(Answer.builder()
                                .text(textA)
                                .nextQuestion((int) nextQuestionA)
                                .build());
                    }

                    boolean isWinQ = (boolean) questionJsonObject.get("isWin");
                    boolean isLooseQ = (boolean) questionJsonObject.get("isLoose");

                    treeQuestions.put((int) numberQ, Question.builder()
                            .number((int) numberQ)
                            .text(textQ)
                            .answers(answerListQ)
                            .isWin(isWinQ)
                            .isLoose(isLooseQ)
                            .build());
                }
                else{
                    boolean isWinQ = (boolean) questionJsonObject.get("isWin");
                    boolean isLooseQ = (boolean) questionJsonObject.get("isLoose");

                    treeQuestions.put((int) numberQ, Question.builder()
                            .number((int) numberQ)
                            .text(textQ)
                            .isWin(isWinQ)
                            .isLoose(isLooseQ)
                            .build());
                }

            }
            logger.info("json parser - init tree questions");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void treeInit() {
        Question winQuestion = Question.builder()
                .number(4)
                .text("Тебя вернули домай. Ты победил!")
                .isWin(true)
                .build();
        treeQuestions.put(4, winQuestion);

        Question lieQuestion1 = Question.builder()
                .number(5)
                .text("Твою ложь разоблачили. Ты проиграл!")
                .isLoose(true)
                .build();
        treeQuestions.put(5, lieQuestion1);

        Question lieQuestion2 = Question.builder()
                .number(6)
                .text("Ты не пошёл на переговоры. Ты проиграл!")
                .isLoose(true)
                .build();
        treeQuestions.put(6, lieQuestion2);

        Question lieQuestion3 = Question.builder()
                .number(7)
                .text("Ты отклонил вызов. Ты проиграл!")
                .isLoose(true)
                .build();
        treeQuestions.put(7, lieQuestion3);

        Question question1 = Question.builder()
                .text("Ты поднялся на мостик. Ты кто?")
                .number(3)
                .answers(List.of(
                        Answer.builder()
                            .text("Рассказать правду о себе")
                            .nextQuestion(4)
                            .build(),
                        Answer.builder()
                            .text("Солгать о себе")
                            .nextQuestion(5)
                            .build()
                ))
                .build();
        treeQuestions.put(3, question1);

        Question question2 = Question.builder()
                .text("Ты принял вызов. Поднимаешься на мостик к капитану?")
                .number(2)
                .answers(List.of(
                        Answer.builder()
                                .text("Подняться на мостик")
                                .nextQuestion(3)
                                .build(),
                        Answer.builder()
                                .text("Отказаться подниматься на мостик")
                                .nextQuestion(6)
                                .build()
                ))
                .build();
        treeQuestions.put(2, question2);

        Question question3 = Question.builder()
                .text("Ты потерял память. Принять вызов НЛО?")
                .number(1)
                .answers(List.of(
                        Answer.builder()
                                .text("Принять вызов")
                                .nextQuestion(2)
                                .build(),
                        Answer.builder()
                                .text("Отклонить вызов")
                                .nextQuestion(7)
                                .build()
                ))
                .build();
        treeQuestions.put(1, question3);

        logger.info("tree question init");

    }
    @Builder
    static
    class Question{
        int number;
        String text;
        List<Answer> answers;
        boolean isWin;
        boolean isLoose;
    }

    @Builder
    static
    class Answer{
        String text;
        int nextQuestion;
    }
}
