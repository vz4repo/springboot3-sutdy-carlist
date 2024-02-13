package org.example.mycardata.web;

import org.example.mycardata.domain.Car;
import org.example.mycardata.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// 이 클래스가 RESTful 컨트롤러로 지정한다는 의미
@RestController
public class CarController {

  // DB에서 Car를 받을 수 있게 CarRepo에서 Controller에 주입.
  // Controller에 있는 findAll() 사용할 수 있다
  // @RestController가 데이터를 JSON 포맷으로 직렬화된다
  @Autowired
  private CarRepository carRepository;

  // mapping되는 엔드포인트로 이동하면 메서드가 실행된다
  @RequestMapping(value = "/cars", method = RequestMethod.GET)
  public Iterable<Car> getCars(){
    // 모든 자동차 객체를 리턴
    // Jackson lib 통해 JSON 객체로 마샬링
    return carRepository.findAll();
  }
}
