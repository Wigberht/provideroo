package com.dimbo.dao.models.account;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Account;

public interface AccountDAO {

    Account find(Long id) throws DAOException;

    boolean delete(Long id) throws DAOException;

    boolean update(Account account) throws DAOException;

    Account create(Account account) throws DAOException;

}
