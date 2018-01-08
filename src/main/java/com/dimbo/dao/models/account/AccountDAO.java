package com.dimbo.dao.models.account;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Account;

public interface AccountDAO {

    public Account find(Long id) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public Account update(Account account) throws DAOException;

    public Account create(Account account) throws DAOException;

}
