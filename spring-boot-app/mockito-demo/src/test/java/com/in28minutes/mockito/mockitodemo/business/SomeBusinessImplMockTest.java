package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.in28minutes.mockito.mockitodemo.business.SomeBusinessImpl;
import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;

public class SomeBusinessImplMockTest {

  @Test
  void findGreatestFromAllData_basicScenario() {
    DataServiceDao dataServiceDaoMock = mock(DataServiceDao.class);
    when(dataServiceDaoMock.retriveAllData()).thenReturn(new int[] { 25, 15, 5 });
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceDaoMock);
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(25, greatestNumber);
  }

  @Test
  void findGreatestFromAllData_withOneValue() {
    DataServiceDao dataServiceDaoMock = mock(DataServiceDao.class);
    when(dataServiceDaoMock.retriveAllData()).thenReturn(new int[] { 35 });
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceDaoMock);
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(35, greatestNumber);
  }

  @Test(expected = "IllegalArgumentException.class")
  void findGreatestFromAllDataTryAndCatch_TestException() {
    DataServiceDao dataServiceDaoMock = mock(DataServiceDao.class);
    when(dataServiceDaoMock.retriveAllData()).thenThrow(new IllegalArgumentException());
    SomeBusinessImpl someBusinessImpl = new SomeBusinessImpl(dataServiceDaoMock);
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(0, greatestNumber);
  }

}
