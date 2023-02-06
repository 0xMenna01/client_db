package controller;

import exception.DAOException;
import model.dao.AddCourseDAO;
import model.dao.AddLessonsDAO;
import model.dao.ConnectionFactory;
import model.dao.CoursesDAO;
import model.domain.Course;
import model.domain.CoursesList;
import model.domain.Lesson;
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
                case ADD_POOL -> System.out.println();
                case REGISTER_ENTRANCE -> System.out.println();
                case ENTRANCES_REPORT -> System.out.println();
                case SHOW_COURSES -> System.out.println();
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

        CoursesList courses;
        Lesson lesson , newLesson;
        while(true){

            try{
                courses = new CoursesDAO().execute();
                lesson = SecretaryView.provideLesson(courses.getCourses(), courses.getCoursesInfo());
                newLesson = new AddLessonsDAO().execute(lesson.getDayNumber(), lesson.getHour(),
                        lesson.getCourse().getId(), lesson.getDuration(), lesson.getFromDate(),
                        lesson.getNumOfWeeks());
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

    private void coursesList(){
        CoursesList courses;
        try {
            courses = new CoursesDAO().execute();
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        SecretaryView.printCoursesTable(courses.getCourses(), courses.getCoursesInfo());

        try {
            SecretaryView.next();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
