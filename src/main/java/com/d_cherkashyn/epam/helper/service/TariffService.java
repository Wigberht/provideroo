package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.FactoryGenerator;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAO;
import com.d_cherkashyn.epam.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

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
    
    public boolean updateTariff(Tariff tariff) {
        return FactoryGenerator.getFactory()
                               .makeTariffDAO(connection)
                               .update(tariff);
    }
    
    public boolean deleteTariff(long id) {
        return FactoryGenerator.getFactory()
                               .makeTariffDAO(connection)
                               .delete(id);
    }
    
    public List<Tariff> search(String searchQ) {
        
        /*wow, what a genius way to deal with a multiple search words, congratz*/
        String[] strings = searchQ.split(" ");
        String str1 = strings.length > 0 ? strings[0] : null;
        String str2 = strings.length > 1 ? strings[1] : null;
        String str3 = strings.length > 2 ? strings[2] : null;
        LOGGER.info("Str1: " + str1);
        LOGGER.info("Str2: " + str2);
        LOGGER.info("Str3: " + str3);
        
        return FactoryGenerator.getFactory()
                               .makeTariffDAO(connection)
                               .search(str1, str2, str3);
    }
}
