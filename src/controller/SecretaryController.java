package controller;

import exception.DAOException;
import model.dao.AddCourseDAO;
import model.dao.ConnectionFactory;
import model.domain.Course;
import utils.SecretaryOption;
import view.SecretaryView;
import view.components.SecretaryComponents;
import java.io.IOException;
import java.sql.SQLException;


public class SecretaryController implements Controller {

    Course recentAddedCourse = null;

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
                case ADD_LESSONS -> System.out.println();
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

        int action; // 0: Error: back to menu, 1: Error re-add course, 2: course added successfully
        while(true){

            try {
                Course newCourse = SecretaryView.provideCourse();
                recentAddedCourse = new AddCourseDAO().execute(
                        newCourse.getName(), newCourse.getPrice(), newCourse.getMinParticipants(),
                        newCourse.getMaxParticipants(), newCourse.getPool());

                SecretaryView.showMessage("Corso " + recentAddedCourse.toString() + " AGGIUNTO CON SUCCESSO!");
                SecretaryView.next();
                action = 2;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                action = SecretaryView.showErrorMessage(e.getMessage());
            }
            if(action != 1) break;
        }

    }


    private void addLessons(){

        while(true){


        }

    }


}
