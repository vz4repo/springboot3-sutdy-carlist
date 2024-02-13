package org.example.mycardata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Car {

  @Id   // PK 설정
  @GeneratedValue(strategy = GenerationType.AUTO)   // 자동으로 값 생성
  private long id;

  // 차종, 모델, 색상, 등록번호
  private String brand, model, color, registerNumber;

  // `` 으로 감싸지 않으면 SQL:expected "identifier"; 오류 발생
  @Column(name = "`YEAR`")
  private int year;
  private int price;

  // 다대일 관계(many:car - 1:owner)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner")
  private Owner owner;

  public Car() {  }
  public Car(String brand, String model, String color, String registerNumber, int year, int price, Owner owner) {
    this.brand = brand;
    this.model = model;
    this.color = color;
    this.registerNumber = registerNumber;
    this.year = year;
    this.price = price;
    this.owner = owner;
  }
}
