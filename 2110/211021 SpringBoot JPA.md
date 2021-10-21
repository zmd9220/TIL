# SpringBoot JPA



## 찾아보기

- Hibernate(Entity, Repository)
- 안드로이드 Room

------

- JPA 가 무엇인지?
- JPA db 모델 설계
- crud

## ORM Object Relational Mapping-

- 객체
- 객체 관계 맵핑
- 장고도 모델 기준으로 정리

## 2가지

### @Entity

### JpaRepository

### 끝.

------

## 게시판 댓글

- 게시판PK:UID누가: 작성자언제: 작성일자어디서:ip무엇을 : 제목, 내용어떻게: 모바일, 웹왜: (생략)
- 댓글PK:UID**FK: 게시판 UID**누가: 작성자언제 : 작성일자어디서: ip무엇을 : 내용어떻게: 모바일, 웹왜: (생략)’

YAML

1 2 3 4 5 6 7 8 9

```
spring.datasource.url="" spring.jpa.generate.ddl=true spring.jpa.hibernate.ddl-auto=update spring.jpa.show.sql=true spring.jpa.properties.hibernate.format_sql=true logging.level.org.hibernate.type.descriptor.sql.BasicBinde=true 
```

## 꿀팁

키는 데이터 용량을 줄이면서 더 많은 값을 넣을 수 있는 방법 (unsigned_int)

- 다중성@OneToOne@OneToMany@ManyToOne@ManyToMany일대다 != 다대일게시물을 조회하고 댓글 가져오기댓글 가져오고 어떤 게시물인지 확인
- 방향성@JoinColumnObjectA -> ObjectBObjectB -> ObjectAObjectA <-> ObjectB양방향은 신중하게 사용해야 한다.무한 참조의 경험을 해볼 수 있다.설계가 ㅠㅠJPA 를 사용 ㅠㅠ
- 연관관계의 주인@OneToMany(mappedBy = “boardUid”)양방향일 경우 어떤 테이블 기준으로 데이터를 삭제하면 그것에 관련된 데이터들을 다 삭제할 것인가?FK 키 관리 주인다 쪽이 주인@ManyToOne은 항상 주인인다.

## Repository

- READ - find**
- Delete - delete
- Create save
- Update 값 변경후 save

## @Builder

Java

1 2 3 4 5 6 7 8 9

```
@Builder public Board(String user, LocalDateTime createDate, String ip, String title, String contents, int how){    this.user = user;    this.createDate = createDate;    this.ip = ip;    this.title = title;    this.contents = contents;    this.how = how; }
```

## SpringBoot JPA Docs

Java

1 2 3

```
public interface BoardRepository extends JpaRepository<Board, Integer>{    public List<Board> findTop1000ByOrderByUidDesc(); }
```