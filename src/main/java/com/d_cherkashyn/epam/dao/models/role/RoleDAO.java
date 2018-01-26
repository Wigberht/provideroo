package com.d_cherkashyn.epam.dao.models.role;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Role;

public interface RoleDAO {
    
    public Role find(String name) throws DAOException;
    
}
