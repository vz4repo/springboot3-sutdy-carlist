package org.example.mycardata.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// spring data REST 이용해서 엔드포인트 경로를 다른 경로로 설정 (/api/cars -> /api/vehicles)
//@RepositoryRestResource(path = "vehicles")
// 쿼리에 @param 사용을하기 위한 애너테이션 추가
@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {
  // CrudRepository 인터페이스에 여러 CRUD 관련 메서드가 있으니 참고하자
  // 메서드가 하나만 리턴하면 T 대신 Optional<T> 타입으로 써서 NPE 방지

  // custom 쿼리 정의.
  // 쿼리는 findBy로 시작해야한다
  // Color로 Car 검색
  List<Car> findByColor(@Param("color")String color);
  // Year로 Car 검색
  List<Car> findByYear(@Param("year") int year);
// brand, model로 Car 검색
  List<Car> findByBrandAndModel(String brand, String model);
// brand, color로 Car 검색
  List<Car> findByBrandOrColor(String brand, String color);
// brand로 Car 검색하고 연도순 정렬
  List<Car> findByBrandOrderByYearAsc(String brand);
  // Brand로 Car 검색 with SQL
  @Query("select c from Car c where c.brand = ?1")
  List<Car> findByBrand(String brand);
  // Like를 이용한 SQL
  @Query("select c from Car c where c.brand like %?1")
  List<Car> findByBrandEndWith(String brand);


}
