package model.dao;

import exception.DAOException;


public interface GenericProcedureDAO<P> {

    P execute(Object... params) throws DAOException;
}
