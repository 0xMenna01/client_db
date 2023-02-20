package model.dao;

import exception.DAOException;
import model.dao.secretary.*;
import model.domain.*;
import model.domain.report.ReportEntrances;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class SecretaryMangerDAO {

    private SecretaryMangerDAO(){}

    public static Course addCoursesDAO(String name, BigDecimal price, int min, int max, int pool) throws DAOException {
        return AddCourseDAO.getInstance().execute(name, price, min, max, pool);
    }


    public static Lesson addLessonsDAO(int numDay, Time hour, int id, int duration, Date startingDate, int numWeeks) throws DAOException {
        return AddLessonsDAO.getInstance().execute(numDay, hour, id, duration, startingDate, numWeeks);
    }

    public static Participant addParticipantDAO(String code, String name, String address,
                                                int house, String postalCode, String json, int id) throws DAOException {

        return AddParticipantDAO.getInstance().execute(code, name, address, house, postalCode, json, id);

    }

    public static Pool addPoolDAO() throws DAOException {
        return AddPoolDAO.getInstance().execute();
    }

    public static ListForTable<Course> getCoursesDAO() throws DAOException {
        return CoursesDAO.getInstance().execute();
    }

    public static Participant enrollParticipantDAO(String code, int id) throws DAOException {
        return EnrollParticipantDAO.getInstance().execute(code, id);
    }

    public static Entrance registerEntranceDAO(String code) throws DAOException {
        return EntranceDAO.getInstance().execute(code);
    }

    public static ListForTable<Course> participantCoursesDAO(String code) throws DAOException {
        return ParticipantCoursesDAO.getInstance().execute(code);
    }

    public static ListForTable<Participant> participantsDAO() throws DAOException {
        return ParticipantsDAO.getInstance().execute();
    }

    public static ReportEntrances reportEntrancesDAO(Date fromDate, int numDays) throws DAOException {
        return ReportEntrancesDAO.getInstance().execute(fromDate, numDays);
    }
}
