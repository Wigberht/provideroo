package com.dimbo.dao.models.role;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Role;

public interface RoleDAO {
    
    public Role find(String name) throws DAOException;
    
}
