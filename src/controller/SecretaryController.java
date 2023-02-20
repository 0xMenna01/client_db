package controller;

import exception.AttributeException;
import exception.DAOException;
import model.dao.ConnectionFactory;
import model.dao.secretary.*;
import model.domain.*;
import model.domain.report.ReportEntrances;
import utils.SecretaryOption;
import view.SecretaryView;
import view.components.SecretaryComponents;
import java.io.IOException;
import java.sql.SQLException;


public class SecretaryController implements Controller {


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
                case REGISTER_ENTRANCE -> executeEntrance();
                case ENTRANCES_REPORT -> generateReportEntrances();
                case SHOW_COURSES -> coursesList();
                case SHOW_PARTICIPANT_COURSES -> participantsCoursesList();
                case SHOW_PARTICIPANTS -> participantsList();
                case QUIT -> {
                    try {
                        SecretaryComponents.showExitBanner();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                }
            }

        }
    }

    private void addCourse(){
        Course newCourse, course;
        while(true){
            try {
                newCourse = SecretaryView.provideCourse();
                course = AddCourseDAO.getInstance().execute(
                        newCourse.getName(), newCourse.getPrice(), newCourse.getMinParticipants(),
                        newCourse.getMaxParticipants(), newCourse.getPool());

                SecretaryComponents.showMessage(course.toString() + " AGGIUNTO CON SUCCESSO!\n");
                SecretaryView.next();
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                int choice = SecretaryComponents.showErrorMessage(e.getMessage());
                if(choice == 0) break;
            }
        }

    }


    private void addLessons() {

        ListForTable<Course> courses;
        Lesson inputLesson , newLesson;
        while(true){

            try{
                courses = CoursesDAO.getInstance().execute();
                if(!courses.isEmpty()){
                    inputLesson = SecretaryView.provideLesson(courses.toStringMapsList(), courses.getColumnNames());
                    newLesson = AddLessonsDAO.getInstance().execute(inputLesson.getDayNumber(), inputLesson.getHour(),
                            inputLesson.getCourse().getId(), inputLesson.getDuration(), inputLesson.getFromDate(),
                            inputLesson.getNumOfWeeks());

                    SecretaryComponents.showMessage("Lezione del " + newLesson.toString() + " AGGIUNTA CON SUCCESSO!\n");

                }else{
                    SecretaryComponents.showMessage("Non è consentito aggiungere lezioni poichè non esistono corsi\n\n");
                }
                SecretaryView.next();
                break;
            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                int choice = SecretaryComponents.showErrorMessage(e.getMessage());
                if(choice == 0) break;
            }

        }

    }

    private void addParticipant(){
        Participant participant, addedParticipant;
        ListForTable<Course> courses;
        while(true){
            try {
                courses = CoursesDAO.getInstance().execute();
                if(!courses.isEmpty()){
                    participant = SecretaryView.provideNewParticipant(courses.toStringMapsList(), courses.getColumnNames());

                    addedParticipant = AddParticipantDAO.getInstance().execute(participant.getCode(), participant.getName(),
                            participant.getAddress(), participant.getHouseNumber(), participant.getPostalCode(),
                            participant.getContacts().toString(), participant.getCourseSubscription().getId());

                    SecretaryComponents.showMessage(addedParticipant.toString() + " AGGIUNTO CON SUCCESSO!\n");

                }else{
                    SecretaryComponents.showMessage("Non è consentito aggiungere partecipanti poichè non esistono corsi\n\n");
                }

                SecretaryView.next();
                break;
            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                int choice = SecretaryComponents.showErrorMessage(e.getMessage());
                if(choice == 0) break;
            }
        }

    }

    private void addPool(){
        Pool newPool;
        try {
            newPool = AddPoolDAO.getInstance().execute();
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

    private void executeEntrance(){
        Entrance participantEntrance, enteredParticipant;
        ListForTable<Participant> participants;

        try {
            participants = ParticipantsDAO.getInstance().execute();
            if(!participants.isEmpty()){
                participantEntrance = SecretaryView.provideEntrance(participants.toStringMapsList(), participants.getColumnNames());
                enteredParticipant = EntranceDAO.getInstance().execute(participantEntrance.getParticipant().getCode());
                SecretaryComponents.showMessage(enteredParticipant.toString() + " AVVENUTO CON SUCCESSO!\n");

            }else{
                SecretaryComponents.showMessage("Non è consentito effettuare alcun ingresso poichè non esistono partecipanti\n\n");
            }


        } catch (IOException | AttributeException e) {
            throw new RuntimeException(e);
        } catch (DAOException e) {
            SecretaryComponents.showMessage(e.getMessage().concat("\n"));
        }

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
                participants = ParticipantsDAO.getInstance().execute();
                if(!participants.isEmpty()){
                    courses = CoursesDAO.getInstance().execute();
                    participant = SecretaryView.provideSubscription(participants.toStringMapsList(), participants.getColumnNames(),
                            courses.toStringMapsList(), courses.getColumnNames());

                    subscribedParticipant = EnrollParticipantDAO.getInstance().execute(participant.getCode(),
                            participant.getCourseSubscription().getId());

                    SecretaryComponents.showMessage(subscribedParticipant.toString() + " ISCRITTO CON SUCCESSO!\n");
                }else{
                    SecretaryComponents.showMessage("Non esiste alcun partecipante\n\n");
                }

                SecretaryView.next();
                break;
            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                int choice = SecretaryComponents.showErrorMessage(e.getMessage());
                if(choice == 0) break;
            }

        }
    }


    private void generateReportEntrances(){
        ReportEntrances inputReport, finalReport;

        while(true){

            try {

                inputReport = SecretaryView.provideReport();

                finalReport = ReportEntrancesDAO.getInstance().execute(inputReport.getFromDate(), inputReport.getNumOfDays());
                SecretaryComponents.showMessage("\n\n");
                SecretaryComponents.showMessage(finalReport.toString().concat("\n\n"));

                SecretaryComponents.showMessage("DETTAGLI REPORT ENTRATE GIORNALIERE\n\n");
                SecretaryView.printTable(finalReport.getDetails().toStringMapsList(),
                        finalReport.getDetails().getColumnNames());

                SecretaryView.next();
                break;

            } catch (IOException | AttributeException e) {
                throw new RuntimeException(e);
            } catch (DAOException e) {
                int choice = SecretaryComponents.showErrorMessage(e.getMessage());
                if(choice == 0) break;
            }
        }
    }



    private void coursesList() {
        ListForTable<Course> courses;
        try {
            courses = CoursesDAO.getInstance().execute();
            if(!courses.isEmpty()){
                SecretaryView.printTable(courses.toStringMapsList(), courses.getColumnNames());
            }else{
                SecretaryComponents.showMessage("Non esistono corsi\n\n");
            }

            SecretaryView.next();
        } catch(DAOException | IOException | AttributeException e) {
            throw new RuntimeException(e);
        }

    }

    private void participantsCoursesList() {

        Participant participant;
        ListForTable<Participant> allParticipants;
        ListForTable<Course> participantCourses;

        try {
            allParticipants = ParticipantsDAO.getInstance().execute();
            if(!allParticipants.isEmpty()){
                participant = SecretaryView.provideParticipant(allParticipants.toStringMapsList(), allParticipants.getColumnNames());
                participantCourses = ParticipantCoursesDAO.getInstance().execute(participant.getCode());

                SecretaryView.printTable(participantCourses.toStringMapsList(), participantCourses.getColumnNames());
            }else {
                SecretaryComponents.showMessage("Non esistono partecipanti\n\n");
            }


            SecretaryView.next();
        } catch(DAOException | AttributeException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void participantsList() {
        ListForTable<Participant> participants;
        try {
            participants = ParticipantsDAO.getInstance().execute();
            if(!participants.isEmpty()){
                SecretaryView.printTable(participants.toStringMapsList(), participants.getColumnNames());
            }else{
                SecretaryComponents.showMessage("Non esistono partecipanti\n\n");
            }

            SecretaryView.next();
        } catch(DAOException | AttributeException | IOException e) {
            throw new RuntimeException(e);
        }

    }


}
