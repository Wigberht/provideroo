package com.dimbo.dao.models.tariff;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Tariff;

import java.util.List;

public interface TariffDAO {
    
    Tariff find(Long id) throws DAOException;
    
    List<Tariff> findByService(Long serviceId) throws DAOException;
    
    List<Tariff> search(String word1, String word2, String word3) throws DAOException;
    
    boolean delete(Long id) throws DAOException;
    
    boolean update(Tariff tariff) throws DAOException;
    
    Tariff create(Tariff tariff) throws DAOException;
}
