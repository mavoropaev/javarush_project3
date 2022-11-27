package com.javarush.voropaev.javarush_project3;

import lombok.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TreeDialog {
    HashMap<Integer, Question> treeQuestions = new HashMap<>();

    public Question getQuestion(int number){
        return treeQuestions.get(number);
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
                //.text("You've lost your memory. Accept the UFO challenge?")
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

    }
    public void startGame(){
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
