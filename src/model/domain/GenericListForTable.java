package model.domain;

import exception.AttributeException;

import java.util.HashMap;
import java.util.List;

public interface GenericListForTable<P> {

    void addToList(P object);

    void addColumnName(String columnName);

    List<P> getList();

    List<String> getColumnNames(); // Returns all attributes names of a given object (DB entity)

    List<HashMap<String, String>> toStringMapsList() throws AttributeException;
    /*
    Converts the list of a given object (DB entity) into a list of mappings.
    The mapping serves to map a key value (attribute of an entity) to its given value.
    The implementation will be useful to show in a formatted table all information regarding the entity.
    */
}
