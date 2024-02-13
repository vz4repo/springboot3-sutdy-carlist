package org.example.mycardata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
// owner(1):car(M) 관계에서 오류 발생(해결1,해결2 필요)
// Car직렬화 -> 연결된 Owner 직렬화 -> Owner가 소유한 Car 직렬화
// 해결1. Hibernate가 생성한 필드 무시
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter@Setter
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ownerid;
  private String firstName, lastName;

  // (1:owner - many:cars)
  // 1:M 애너테이션에는 2가지 특성이 있다.
  // cascafe 특성(삭제/수정에 연속효과. ALL:모든작업적용), mappedBy 특성(해당 클래스의 필드가 이 관계의 기본키임을 지정)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  // 해결2. 직렬화 프로세스 중에서 cars 필드 무시하는 애너테이션
  @JsonIgnore
  private List<Car> cars;
  public Owner() {  }

  public Owner(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}

