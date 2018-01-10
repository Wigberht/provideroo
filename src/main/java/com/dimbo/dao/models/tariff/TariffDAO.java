package com.dimbo.dao.models.tariff;

import com.dimbo.dao.DAOException;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Tariff;
import java.util.List;

public interface TariffDAO {

    public Tariff find(Long id) throws DAOException;

    public List<Tariff> findByService(Long serviceId) throws DAOException;

    public boolean delete(Long id) throws DAOException;

    public Tariff update(Tariff tariff) throws DAOException;

    public Tariff create(Tariff tariff) throws DAOException;

}
