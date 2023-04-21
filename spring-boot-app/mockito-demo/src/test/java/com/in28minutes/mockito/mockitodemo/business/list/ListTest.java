package com.in28minutes.mockito.mockitodemo.business.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListTest {
  @Test
  void simpleTest() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(3);
    assertEquals(3, listMock.size());
  }

  @Test
  void multipleReturns() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(3).thenReturn(2).thenReturn(1);
    assertEquals(3, listMock.size()); // 1st return
    assertEquals(2, listMock.size()); // 2nd return
    assertEquals(1, listMock.size()); // 3rd return
    assertEquals(1, listMock.size()); // though we return 3 values, if we try to access 4th value, the 3rd value will
                                      // only be returned
  }

  @Test
  void specificParameters() {
    List listMock = mock(List.class);
    when(listMock.get(0)).thenReturn("SomeString");
    assertEquals("SomeString", listMock.get(0));
    assertEquals(null, listMock.get(1));
  }

  @Test
  void genericParameters() {
    List listMock = mock(List.class);
    when(listMock.get(Mockito.anyInt())).thenReturn("SomeString");
    assertEquals("SomeString", listMock.get(0));
    assertEquals("SomeString", listMock.get(1));
  }

}
