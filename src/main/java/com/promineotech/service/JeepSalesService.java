package com.promineotech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;


public interface JeepSalesService {
@Autowired
  List<Jeep> fetchJeeps(JeepModel model, String trim);
}
