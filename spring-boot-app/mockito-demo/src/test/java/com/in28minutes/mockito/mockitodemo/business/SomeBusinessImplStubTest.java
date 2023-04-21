package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.in28minutes.mockito.mockitodemo.business.SomeBusinessImpl;
import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;
import com.in28minutes.mockito.mockitodemo.dao.DataServiceDaoImplStub1;
import com.in28minutes.mockito.mockitodemo.dao.DataServiceDaoImplStub2;

public class SomeBusinessImplStubTest {

  @Test
  void findGreatestFromAllData_basicScenario() {
    DataServiceDao dataServiceDaoImplStub = new DataServiceDaoImplStub1();
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceDaoImplStub);
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(25, greatestNumber);
  }

  @Test
  void findGreatestFromAllData_withOneValue() {
    DataServiceDao dataServiceDaoImplStub = new DataServiceDaoImplStub2();
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceDaoImplStub);
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(35, greatestNumber);
  }
}
