package com.d_cherkashyn.epam.dao.models.account;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Account;

public interface AccountDAO {

    Account find(Long id) throws DAOException;

    boolean delete(Long id) throws DAOException;

    boolean update(Account account) throws DAOException;

    Account create(Account account) throws DAOException;

}
