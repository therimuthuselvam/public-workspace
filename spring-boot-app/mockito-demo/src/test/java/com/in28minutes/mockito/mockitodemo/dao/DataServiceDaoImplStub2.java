package com.in28minutes.mockito.mockitodemo.dao;

import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;

public class DataServiceDaoImplStub2 implements DataServiceDao {

  @Override
  public int[] retriveAllData() {
    return new int[] { 35 };
  }

}
