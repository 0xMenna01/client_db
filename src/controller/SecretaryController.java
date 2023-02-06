package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.Course;
import model.domain.Lesson;
import model.domain.ListForTable;
import model.domain.Pool;
import utils.SecretaryOption;
import view.SecretaryView;
import view.components.SecretaryComponents;
import java.io.IOException;
import java.sql.SQLException;


public class SecretaryController implements Controller {

    private int actionStatus; // 0: Error: Back to menu, 1: Error re-do operation, 2: Operation successful
    private Course recentAddedCourse = null;

    @Override
    public void start() {

        try{
            ConnectionFactory.changeRoleToSecretary();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        while(true) {
            SecretaryOption choice;
            try {
                choice = SecretaryView.showMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case ADD_COURSE -> addCourse();
                case ADD_LESSONS -> addLessons();
                case ADD_PARTICIPANT -> System.out.println();
                case ENROLL_PARTICIPANT -> System.out.println();
                case ADD_POOL -> addPool();
                case REGISTER_ENTRANCE -> System.out.println();
                case ENTRANCES_REPORT -> System.out.println();
                case SHOW_COURSES -> coursesList();
                case SHOW_PARTICIPANT_COURSES -> System.out.println();
                case SHOW_PARTICIPANTS -> System.out.println();
                case QUIT -> {
                    try {
                        SecretaryComponents.showExitBanner();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                }
                default -> throw new RuntimeException("Invalid choice");
            }

        }
    }

    private void addCourse(){
        Course newCourse;
        while(true){
            try {
                newCourse = SecretaryView.provideCourse();
                recentAddedCourse = new AddCourseDAO().execute(
                        newCourse.getName(), newCourse.getPrice(), newCourse.getMinParticipants(),
                        newCourse.getMaxParticipants(), newCourse.getPool());

                SecretaryComponents.showMessage("Corso " + recentAddedCourse.toString() + " AGGIUNTO CON SUCCESSO!\n");
                SecretaryView.next();
                actionStatus = 2;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                actionStatus = SecretaryComponents.showErrorMessage(e.getMessage());
            }
            if(actionStatus != 1) break;
        }

    }


    private void addLessons(){

        ListForTable<Course> courses;
        Lesson inputLesson , newLesson;
        while(true){

            try{
                courses = new CoursesDAO().execute();
                inputLesson = SecretaryView.provideLesson(courses.toStringMapsList(), courses.getColumnNames());
                newLesson = new AddLessonsDAO().execute(inputLesson.getDayNumber(), inputLesson.getHour(),
                        inputLesson.getCourse().getId(), inputLesson.getDuration(), inputLesson.getFromDate(),
                        inputLesson.getNumOfWeeks());

                SecretaryComponents.showMessage("Lezione del corso " + newLesson.toString() + " AGGIUNTA CON SUCCESSO!\n");
                SecretaryView.next();
                actionStatus = 2;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                actionStatus = SecretaryComponents.showErrorMessage(e.getMessage());
            }
            if(actionStatus != 1) break;

        }

    }

    private void addPool(){
        Pool newPool;
        try {
            newPool = new AddPoolDAO().execute();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        SecretaryComponents.showMessage(newPool.toString() + " AGGIUNTA CON SUCCESSO!\n");
        try {
            SecretaryView.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void coursesList(){
        ListForTable<Course> courses;
        try {
            courses = new CoursesDAO().execute();
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        SecretaryView.printTable(courses.toStringMapsList(), courses.getColumnNames());

        try {
            SecretaryView.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
