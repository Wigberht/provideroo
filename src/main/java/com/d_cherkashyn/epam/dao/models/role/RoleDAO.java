package com.d_cherkashyn.epam.dao.models.role;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Role;


/**
 * Interface with methods required to manage Role in DB as DAO
 */
public interface RoleDAO {
    
    /**
     * Get role by name
     *
     * @param name
     * @return
     * @throws DAOException
     */
    public Role find(String name) throws DAOException;
    
}
