package com.in28minutes.mockito.mockitodemo.business;

import com.in28minutes.mockito.mockitodemo.dao.DataServiceDao;

public class SomeBusinessImpl {

  private DataServiceDao dataServiceDao;

  // public SomeBusinessImpl() {
  // }

  SomeBusinessImpl(DataServiceDao dataServiceDao) {
    this.dataServiceDao = dataServiceDao;
  }

  public int findGreatestFromAllData() {
    int[] data = dataServiceDao.retriveAllData();
    int greatestValue = Integer.MIN_VALUE;
    for (int value : data) {
      if (value > greatestValue) {
        greatestValue = value;
      }
    }
    return greatestValue;
  }

  public int findGreatestFromAllDataTryAndCatch() {
    try {
      int[] data = dataServiceDao.retriveAllData();
      int greatestValue = Integer.MIN_VALUE;
      for (int value : data) {
        if (value > greatestValue) {
          greatestValue = value;
        }
      }
      return greatestValue;
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }
}
