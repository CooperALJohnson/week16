/**
 * 
 */
package com.promineotech.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class DefaultJeepSalesService implements JeepSalesService {

  @Autowired
  private JeepSalesDao JeepSalesDao;
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("The fetchJeeps method was called with model={} and trim={}", model, trim);
        List<Jeep> jeeps = JeepSalesDao.fetchJeeps(model, trim);
        Collections.sort(jeeps);
        return jeeps;
  }

}
