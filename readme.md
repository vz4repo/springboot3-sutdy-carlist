# BE Study : springboot3 + gradle
> car list 

## #01 - application 개요

## #02 - 배포 중 trouble shooting

### Before : 원래 생각한 내용
1. EC2 t2 인스턴스 생성
2. 설치
    - sudo yum -y install git
    - sudo yum -y install [jdk 맞는 버전으로]
3. gradlew로 build
4. java -jar [artifacts]

### After : 실제로 막히는 부분

1. `please set 'javax.persistence.jdbc.url', 'hibernate.connection.url', or 'hibernate.dialect'` 에러 발생
2. `$ ./gradlew build` 에서 테스트를 통과 못하는 경우 
3. 포트가 이미 점유되어 실행 실패하는 경우

### DO : 미봉책
1. `application.properties` 에 코드 추가</br>
`spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQLDialect`
2. 테스트 건너뛰고 빌드하기
```shell 
$ ./gradlew build -x test
```
3. 포트 찾아내서 kill 하기
```shell
$ lsof -i:8080 </br>
$ kill -9 $(ps -elf | grep [pid]8080)
```
### Todo : 앞으로 할 일
- spring security 이용한 JWT 로그인
- oracle cloud DB 접합하기
- cloudFront와 함께 CORS 해결하기

