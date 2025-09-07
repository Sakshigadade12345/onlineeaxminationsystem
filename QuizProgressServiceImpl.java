package com.example.examproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizProgressServiceImpl implements QuizProgressService {

    @Autowired
    private QuizProgressRepository repository;

    @Override
    public void saveProgress(QuizProgress progress) {
        QuizProgressEntity entity = repository.findByUserIdAndQuizName(progress.getUserId(), progress.getQuizName());

        if (entity == null) {
            entity = new QuizProgressEntity();
            entity.setUserId(progress.getUserId());
            entity.setQuizName(progress.getQuizName());
        }

        entity.setAnswersJson(progress.getAnswersJson());
        repository.save(entity);
    }

    @Override
    public QuizProgress loadProgress(int userId, String quizName) {
        QuizProgressEntity entity = repository.findByUserIdAndQuizName(userId, quizName);

        if (entity == null) return null;

        QuizProgress progress = new QuizProgress();
        progress.setUserId(entity.getUserId());
        progress.setQuizName(entity.getQuizName());
        progress.setAnswersJson(entity.getAnswersJson());

        return progress;
    }
}
