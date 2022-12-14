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
    public void treeInitJSON(int idQuest){
        JSONParser parser = new JSONParser();
        String nameJson = "quest.json";
        switch (idQuest){
            case 1:
                nameJson = "quest.json";
                break;
            case 2:
                nameJson = "quest2.json";
                break;
        }
        File file = new File(getClass().getClassLoader().getResource(nameJson).getFile());
        logger.info("get name json file ");

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
