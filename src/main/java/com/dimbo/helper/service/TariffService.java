package com.dimbo.helper.service;

import com.dimbo.dao.DAOException;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.dao.models.tariff.TariffDAO;
import com.dimbo.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class TariffService extends ServiceHelper {
    Logger LOGGER = LoggerFactory.getLogger(TariffService.class);
    
    public TariffService() {
        super();
    }
    
    public TariffService(Connection connection) {
        super(connection);
    }
    
    public Tariff createTariff(Tariff tariff) {
        Tariff resultingTariff = null;
        TariffDAO tariffDAO = FactoryGenerator.getFactory()
                                              .makeTariffDAO(connection);
        
        try {
            resultingTariff = tariffDAO.create(tariff);
            LOGGER.info("Tariff created");
        } catch (DAOException ex) {
            LOGGER.error("Tariff not created");
        }
        
        return resultingTariff;
    }
    
    public Tariff getTariff(long id) {
        return FactoryGenerator.getFactory()
                               .makeTariffDAO(connection)
                               .find(id);
    }
}
