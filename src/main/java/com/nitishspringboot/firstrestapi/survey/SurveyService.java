package com.nitishspringboot.firstrestapi.survey;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();
    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);
        surveys.add(survey);
    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurveyById(String surveyId) {
//        Predicate<? super Survey> predicate = survey -> survey.getId()==surveyId;
        Optional<Survey> optionalSurvey = surveys.stream().filter(survey -> survey.getId().equalsIgnoreCase( surveyId)).findFirst();
        if(optionalSurvey.isEmpty()){
            return null;
        }
        return optionalSurvey.get();
    }
    public List<Question> retrieveAllSurveyQuestions(String surveyId){
        Survey survey = retrieveSurveyById(surveyId);
        if(survey==null){
            return null;
        }
        return survey.getQuestions();
    }
    public Question retrieveAQuestionById(String surveyId, String questionId) {
        List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        Optional<Question> optionalQuestion = questions.stream().filter(question -> question.getId().equalsIgnoreCase(questionId)).findFirst();
        if(optionalQuestion.isEmpty()){
            return null;
        }
        return optionalQuestion.get();
    }

    public void addNewSurveyQuestion(String surveyId, Question question) {
        List<Question> questions = retrieveAllSurveyQuestions(surveyId);
        questions.add(question);
    }
}
