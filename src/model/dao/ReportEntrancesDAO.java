package model.dao;

import exception.DAOException;
import model.domain.report.IntervalType;
import model.domain.ListForTable;
import model.domain.report.DailyReportDetail;
import model.domain.report.ReportEntrances;
import utils.ListFactoryDAO;

import java.sql.*;

public class ReportEntrancesDAO implements GenericProcedureDAO<ReportEntrances>{

    @Override
    public ReportEntrances execute(Object... params) throws DAOException {
        Date fromDate = (Date) params[0];
        int numOfDays = (int) params[1];

        ReportEntrances report;
        int entrances, expectedBySubscription;
        ListForTable<DailyReportDetail> details = null;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call genera_report_ingressi(?,?,?,?)}");
            cs.setDate(1, fromDate);
            cs.setInt(2, numOfDays);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.INTEGER);
            boolean status = cs.execute();

            entrances = cs.getInt(3);
            expectedBySubscription = cs.getInt(4);

            if (status) {
                ResultSet rs = cs.getResultSet();
                details = new ListFactoryDAO<DailyReportDetail>().getListByAttributes(rs);

                while(rs.next()){
                    DailyReportDetail detail = new DailyReportDetail(rs.getDate(1), rs.getInt(2),
                            rs.getInt(3));


                    details.addToList(detail);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error: " + e.getMessage());
        }

        report = new ReportEntrances(fromDate, IntervalType.DAILY, entrances, expectedBySubscription, details);
        report.setNumOfDays(numOfDays);

        return  report;
    }
}

