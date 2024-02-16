package org.example.mycardata;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.mycardata.domain.Owner;
import org.example.mycardata.domain.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// JPA 컴포넌트 중심 테스트에서 사용하는 애너테이션
// H2 Hibernate DB, Spring Data가 자동으로 테스트 구성
// SQL 로깅 활성화
@DataJpaTest
public class OwnerRepositoryTest {

  @Autowired
  private OwnerRepository repository;

  @Test
  void saveOwner() {
    repository.save(new Owner("Lucy", "Smith"));
    assertThat(repository.findByFirstName("Lucy").isPresent()).isTrue();
  }

  @Test
  void deleteOwner() {
    repository.save(new Owner("Lina", "Morrison"));
    repository.deleteAll();
    assertThat(repository.count()).isEqualTo(0);
  }
}
