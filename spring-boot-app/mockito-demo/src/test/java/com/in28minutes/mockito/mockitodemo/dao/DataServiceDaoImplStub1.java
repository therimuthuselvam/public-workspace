package com.in28minutes.mockito.mockitodemo.dao;

import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;

public class DataServiceDaoImplStub1 implements DataServiceDao {

  @Override
  public int[] retriveAllData() {
    return new int[] { 25, 15, 5 };
  }

}
