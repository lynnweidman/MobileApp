package com.example.c196lynnweidman.DATABASE;

import android.app.Application;

import com.example.c196lynnweidman.DOA.CourseDAO;
import com.example.c196lynnweidman.DOA.ObjectiveDAO;
import com.example.c196lynnweidman.DOA.PerformanceDAO;
import com.example.c196lynnweidman.DOA.TermDAO;
import com.example.c196lynnweidman.ENTITY.CoursesEntity;
import com.example.c196lynnweidman.ENTITY.ObjectiveAssessment;
import com.example.c196lynnweidman.ENTITY.PerformanceAssessment;
import com.example.c196lynnweidman.ENTITY.TermsEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private ObjectiveDAO mObjectiveDAO;
    private PerformanceDAO mPerformanceDAO;
    private List<TermsEntity> mAllTerms;
    private List<CoursesEntity> mAllCourses;
    private List<ObjectiveAssessment> mAllObjectiveAssessments;
    private List<PerformanceAssessment> mAllPerformanceAssessments;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    //constructor
    public Repository(Application application) {
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mObjectiveDAO = db.objectiveDAO();
        mPerformanceDAO= db.performanceDAO();

    }


    public List<TermsEntity> getAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(TermsEntity term) {

        databaseExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermsEntity term) {
        databaseExecutor.execute(() -> {
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(TermsEntity term) {
        databaseExecutor.execute(() -> {
            mTermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<CoursesEntity> getAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(CoursesEntity course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CoursesEntity course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(CoursesEntity course) {
        databaseExecutor.execute(() -> {
            mCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        public List<ObjectiveAssessment> getAllObjectiveAssessments() {
            databaseExecutor.execute(() -> {
                mAllObjectiveAssessments = mObjectiveDAO.getAllObjectiveAssessments();
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mAllObjectiveAssessments;
        }

        public void insert (ObjectiveAssessment objectiveAssessment){
            databaseExecutor.execute(() -> {
                mObjectiveDAO.insert(objectiveAssessment);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void delete (ObjectiveAssessment objectiveAssessment){
            databaseExecutor.execute(() -> {
                mObjectiveDAO.delete(objectiveAssessment);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void update (ObjectiveAssessment objectiveAssessment) {
            databaseExecutor.execute(() -> {
                mObjectiveDAO.update(objectiveAssessment);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


            public List<PerformanceAssessment> getAllPerformanceAssessments() {
                databaseExecutor.execute(() -> {
                    mAllPerformanceAssessments = mPerformanceDAO.getAllPerformanceAssessments();
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mAllPerformanceAssessments;
            }

            public void insert (PerformanceAssessment performanceAssessment){
                databaseExecutor.execute(() -> {
                    mPerformanceDAO.insert(performanceAssessment);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void delete (PerformanceAssessment performanceAssessment){
                databaseExecutor.execute(() -> {
                    mPerformanceDAO.delete(performanceAssessment);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void update (PerformanceAssessment performanceAssessment){
                databaseExecutor.execute(() -> {
                    mPerformanceDAO.update(performanceAssessment);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


