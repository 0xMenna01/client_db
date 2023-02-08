package controller;

import exception.AttributeException;
import exception.DAOException;
import model.dao.*;
import model.domain.*;
import utils.SecretaryOption;
import view.SecretaryView;
import view.components.SecretaryComponents;
import java.io.IOException;
import java.sql.SQLException;


public class SecretaryController implements Controller {

    private int actionStatus; // 0: Error: Back to menu, 1: Error re-do operation, 2: Operation successful
    private Course recentAddedCourse = null;
    private Participant recentAddedParticipant = null;


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
                case ADD_PARTICIPANT -> addParticipant();
                case ENROLL_PARTICIPANT -> enrollParticipant();
                case ADD_POOL -> addPool();
                case REGISTER_ENTRANCE -> System.out.println();
                case ENTRANCES_REPORT -> System.out.println();
                case SHOW_COURSES -> coursesList();
                case SHOW_PARTICIPANT_COURSES -> System.out.println();
                case SHOW_PARTICIPANTS -> participantsList();
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

                SecretaryComponents.showMessage(recentAddedCourse.toString() + " AGGIUNTO CON SUCCESSO!\n");
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


    private void addLessons() {

        ListForTable<Course> courses;
        Lesson inputLesson , newLesson;
        while(true){

            try{
                courses = new CoursesDAO().execute();
                inputLesson = SecretaryView.provideLesson(courses.toStringMapsList(), courses.getColumnNames());
                newLesson = new AddLessonsDAO().execute(inputLesson.getDayNumber(), inputLesson.getHour(),
                        inputLesson.getCourse().getId(), inputLesson.getDuration(), inputLesson.getFromDate(),
                        inputLesson.getNumOfWeeks());

                SecretaryComponents.showMessage("Lezione del " + newLesson.toString() + " AGGIUNTA CON SUCCESSO!\n");
                SecretaryView.next();
                actionStatus = 2;

            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                actionStatus = SecretaryComponents.showErrorMessage(e.getMessage());
            }
            if(actionStatus != 1) break;

        }

    }

    private void addParticipant(){
        Participant participant;
        ListForTable<Course> courses;
        while(true){
            try {
                courses = new CoursesDAO().execute();
                participant = SecretaryView.provideNewParticipant(courses.toStringMapsList(), courses.getColumnNames());

                recentAddedParticipant = new AddParticipantDAO().execute(participant.getCode(), participant.getName(),
                        participant.getAddress(), participant.getHouseNumber(), participant.getPostalCode(),
                        participant.getContacts().toString(), participant.getCourseSubscription().getId());

                SecretaryComponents.showMessage(recentAddedParticipant.toString() + " AGGIUNTO CON SUCCESSO!\n");
                SecretaryView.next();
                actionStatus = 2;
            } catch (IOException | AttributeException e) {
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

    private void enrollParticipant(){
        ListForTable<Participant> participants;
        ListForTable<Course> courses;
        Participant subscribedParticipant, participant;
        while(true){

            try{
                participants = new ParticipantsDAO().execute();
                courses = new CoursesDAO().execute();

                participant = SecretaryView.provideSubscription(participants.toStringMapsList(), participants.getColumnNames(),
                        courses.toStringMapsList(), courses.getColumnNames());

                subscribedParticipant = new EnrollParticipantDAO().execute(participant.getCode(),
                        participant.getCourseSubscription().getId());

                SecretaryComponents.showMessage(subscribedParticipant.toString() + " ISCRITTO CON SUCCESSO!\n");

                SecretaryView.next();
                actionStatus = 2;

            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                actionStatus = SecretaryComponents.showErrorMessage(e.getMessage());
            }
            if(actionStatus != 1) break;

        }
    }

    private void coursesList() {
        ListForTable<Course> courses;
        try {
            courses = new CoursesDAO().execute();
            SecretaryView.printTable(courses.toStringMapsList(), courses.getColumnNames());
            SecretaryView.next();
        } catch(DAOException | IOException | AttributeException e) {
            throw new RuntimeException(e);
        }

    }

    private void participantsList() {
        ListForTable<Participant> participants;
        try {
            participants = new ParticipantsDAO().execute();
            SecretaryView.printTable(participants.toStringMapsList(), participants.getColumnNames());
            SecretaryView.next();
        } catch(DAOException | AttributeException | IOException e) {
            throw new RuntimeException(e);
        }

    }



}
