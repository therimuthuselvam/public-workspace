package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.mockito.mockitodemo.business.SomeBusinessImpl;
import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplMockEasierTest {

  @Mock
  DataServiceDao dataServiceDaoEasierMock;

  @InjectMocks
  SomeBusinessImpl someBusinessImpl;

  @Test
  void findGreatestFromAllData_basicScenario() {
    when(dataServiceDaoEasierMock.retriveAllData()).thenReturn(new int[] { 25, 15, 5 });
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(25, greatestNumber);
  }

  @Test
  void findGreatestFromAllData_withOneValue() {
    when(dataServiceDaoEasierMock.retriveAllData()).thenReturn(new int[] { 35 });
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(35, greatestNumber);
  }

  @Test
  void findGreatestFromAllData_emptyArray() {
    when(dataServiceDaoEasierMock.retriveAllData()).thenReturn(new int[] { });
    int greatestNumber = someBusinessImpl.findGreatestFromAllData();
    System.out.println(greatestNumber);
    assertEquals(Integer.MIN_VALUE, greatestNumber);
  }
}
