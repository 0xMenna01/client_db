package utils;

import model.domain.BaseEntityForList;
import model.domain.ListForTable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ListFactoryDAO<P extends BaseEntityForList> {


    public ListForTable<P> getListByAttributes(ResultSet rs) throws SQLException {

        ListForTable<P> list = new ListForTable<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            list.addColumnName(metaData.getColumnName(i));
        }

        return list;
    }
}
