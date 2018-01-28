package com.d_cherkashyn.epam.dao.models.tariff;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.model.Tariff;

import java.util.List;

public interface TariffDAO {
    
    Tariff find(Long id) throws DAOException;
    
    List<Tariff> findByService(long serviceId) throws DAOException;
    
    List<Tariff> findByServiceSorted(long serviceId,
                                     String field, String order) throws DAOException;
    
    List<Tariff> search(String word1, String word2, String word3) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean update(Tariff tariff) throws DAOException;
    
    Tariff create(Tariff tariff) throws DAOException;
}
