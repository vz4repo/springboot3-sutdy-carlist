package org.example.mycardata;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.mycardata.web.CarController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyCarDataApplicationTests {

  @Autowired
  private CarController controller;

  @Test
  @DisplayName("첫 테스트 케이스")
  void contextLoads() {
    // CarController 인스턴스가 정상 생성/주입 되었는지 확인
    assertThat(controller).isNotNull();
  }


}
