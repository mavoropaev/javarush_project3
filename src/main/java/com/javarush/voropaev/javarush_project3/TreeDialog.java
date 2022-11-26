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
                .text("You have been returned home. You won!")
                .isWin(true)
                .build();
        treeQuestions.put(4, winQuestion);

        Question lieQuestion1 = Question.builder()
                .number(5)
                .text("Your lies have been exposed. You lose!")
                .isLoose(true)
                .build();
        treeQuestions.put(5, lieQuestion1);

        Question lieQuestion2 = Question.builder()
                .number(6)
                .text("You didn't negotiate. You lose!")
                .isLoose(true)
                .build();
        treeQuestions.put(6, lieQuestion2);

        Question lieQuestion3 = Question.builder()
                .number(7)
                .text("You rejected the call. You lose!")
                .isLoose(true)
                .build();
        treeQuestions.put(7, lieQuestion3);

        Question question1 = Question.builder()
                .text("You have climbed the bridge. Who are you?")
                .number(3)
                .answers(List.of(
                        Answer.builder()
                                .text("Tell the truth about yourself")
                                .nextQuestion(4)
                                .build(),
                        Answer.builder()
                                .text("Lie about yourself")
                                .nextQuestion(5)
                                .build()
                ))
                .build();
        treeQuestions.put(3, question1);

        Question question2 = Question.builder()
                .text("You accepted the challenge. Are you going up to the captain's bridge?")
                .number(2)
                .answers(List.of(
                        Answer.builder()
                                .text("Climb to the bridge")
                                .nextQuestion(3)
                                .build(),
                        Answer.builder()
                                .text("Refuse to climb the bridge")
                                .nextQuestion(6)
                                .build()
                ))
                .build();
        treeQuestions.put(2, question2);

        Question question3 = Question.builder()
                .text("You've lost your memory. Accept the UFO challenge?")
                .number(1)
                .answers(List.of(
                        Answer.builder()
                                .text("Take the challenge")
                                .nextQuestion(2)
                                .build(),
                        Answer.builder()
                                .text("Reject a call")
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
