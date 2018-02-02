package com.d_cherkashyn.epam.helper.service;

import com.d_cherkashyn.epam.dao.DAOException;
import com.d_cherkashyn.epam.dao.factory.DAOFactory;
import com.d_cherkashyn.epam.dao.models.tariff.TariffDAO;
import com.d_cherkashyn.epam.model.Tariff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TariffService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TariffService.class);
    
    public static Tariff createTariff(Tariff tariff) {
        Tariff resultingTariff = null;
        TariffDAO tariffDAO = DAOFactory.getFactory()
                                        .makeTariffDAO();
        
        try {
            resultingTariff = tariffDAO.create(tariff);
            LOGGER.info("Tariff created");
        } catch (DAOException ex) {
            LOGGER.error("Tariff not created");
        }
        
        return resultingTariff;
    }
    
    public static Tariff getTariff(long id) {
        return DAOFactory.getFactory()
                         .makeTariffDAO()
                         .find(id);
    }
    
    public static boolean updateTariff(Tariff tariff) {
        return DAOFactory.getFactory()
                         .makeTariffDAO()
                         .update(tariff);
    }
    
    public static boolean deleteTariff(long id) {
        return DAOFactory.getFactory()
                         .makeTariffDAO()
                         .delete(id);
    }
    
    public static List<Tariff> search(String searchQ) {
        
        /*wow, what a genius way to deal with a multiple search words, congratz*/
        String[] strings = searchQ.split(" ");
        String str1 = strings.length > 0 ? strings[0] : null;
        String str2 = strings.length > 1 ? strings[1] : null;
        String str3 = strings.length > 2 ? strings[2] : null;
        
        return DAOFactory.getFactory()
                         .makeTariffDAO()
                         .search(str1, str2, str3);
    }
}
