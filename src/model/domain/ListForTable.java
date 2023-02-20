package model.domain;

import exception.AttributeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    This class model serves to manage a List of given objects (DB entities) and provides
    useful methods that converts data used to print a formatted table of such list.

*/

public class ListForTable<P extends BaseEntityForList> implements GenericListForTable<P> {

    private final List<P> list = new ArrayList<>();
    private final List<String> columnNames = new ArrayList<>();

    @Override
    public void addToList(P object) {
        this.list.add(object);
    }

    @Override
    public void addColumnName(String columnName) {
        this.columnNames.add(columnName);
    }

    @Override
    public List<P> getList() {
        return list;
    }

    @Override
    public List<String> getColumnNames() {
        return columnNames;
    }

    @Override
    public List<HashMap<String, String>> toStringMapsList() throws AttributeException {
        List<HashMap<String, String>> listMap = new ArrayList<>();
        String attributeValue;

        for(P obj: list){
            HashMap<String, String> map = new HashMap<>();
            for(String columnName: columnNames){
                attributeValue = obj.getValueByAttributeName(columnName);
                if(attributeValue == null){
                    throw new AttributeException("An attribute is missing");
                }
                map.put(columnName ,obj.getValueByAttributeName(columnName));
            }
            listMap.add(map);
        }

        return listMap;
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
