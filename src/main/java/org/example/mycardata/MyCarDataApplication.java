package org.example.mycardata;

import java.util.Arrays;
import org.example.mycardata.domain.Car;
import org.example.mycardata.domain.CarRepository;
import org.example.mycardata.domain.Owner;
import org.example.mycardata.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyCarDataApplication implements CommandLineRunner {

  // springboot starter package 에 포함된 slf4j.Logger
  // 로깅 수준 7가지를 맞추려면 resources/application.properties 에서 lever 변경
  private static final Logger logger = LoggerFactory.getLogger(MyCarDataApplication.class);

  // 새로운 Car 객체를 DB에 저장할 수 있도록 CarRepo를 main 클래스에 주입
  // 의존성 주입을 위해 Autowired 애너테이션 사용. 의존성을 객체로 전달할 수 있게 한다
  @Autowired
  private CarRepository repository;

  // Owner 정보를 담기 위해 객체 주입
  @Autowired
  private OwnerRepository ownerRepository;
  public static void main(String[] args) {
    SpringApplication.run(MyCarDataApplication.class, args);
  }

  // App이 완전히 실행전에 추가 코드를 실행해서 demo 데이터를 준비할 수 있다
  @Override
  public void run(String... args) throws Exception {
    // 각각 owner 객체 생성 후 saveAll(...)로 추가
    Owner owner1 = new Owner("NAME01", "name001");
    Owner owner2 = new Owner("NAME02", "name002");

    // Autowired로 주입 후, 해당 객체의 여러 CRUD 메서드 이용
/*
기존: 각각 save()
    repository.save(new Car("Genesis", "G90", "Black", "GNS-90", 2023, 90000, owner1));
    repository.save(new Car("Genesis", "G80", "Blue", "GNS-80", 2023, 8000, owner1));
    repository.save(new Car("Genesis", "G70", "white", "GNS-70", 2023, 60000, owner2));
    repository.save(new Car("Ford", "Mustang", "Red", "ADF-1211", 2021, 59000, owner2));
개선: 각각 생성 후 saveAll(...)
*/
    Car car1 = new Car("Genesis", "G90", "Black", "GNS-90", 2023, 90000, owner1);
    Car car2 = new Car("Genesis", "G80", "Blue", "GNS-80", 2023, 8000, owner1);
    Car car3 = new Car("Genesis", "G70", "white", "GNS-70", 2023, 60000, owner2);
    Car car4 = new Car("Ford", "Mustang", "Red", "ADF-1211", 2021, 59000, owner2);
    // 모든 Car 객체 목록 콘솔출력
    for (Car car : repository.findAll()) {
      logger.info(car.getBrand() + "|" + car.getModel());
    }

    // saveAll(...)로 저장
    ownerRepository.saveAll(Arrays.asList(owner1, owner2));
    repository.saveAll(Arrays.asList(car1, car2, car3, car4));
  }
}
