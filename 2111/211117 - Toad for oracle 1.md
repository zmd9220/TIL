# 211117



### Toad for Oracle 관련 단축키

- Ctrl + B = 한줄 주석
- Crtl + Shift + B = 한줄 주석 해제

- 테이블 이름을 드래그 한 뒤, F4 = 해당 테이블 정보 보기
- Ctrl + Enter = 해당 커서 위치와 관련된 sql문 실행



### 문제를 풀면서 했던 질문

- 질문 1. 지금은 테이블을 알려줘서 비교적 쉽게 찾는데, 나중에는 테이블을 직접 찾아가야 하는데 어떻게 찾아가는지? 
  - 많이 보면서 현재 상황에 어떤 테이블이 필요한지 잘 알아 두기 + Eclipse나 FD 지원 시스템 페이지에 공통코드관리 페이지를 참고하여 어떤 파일에 어떤 SQL과 테이블이 연결되었는지 확인하고, 찾아가보는 작업 연습해보기
- 질문 2. 전체 테이블 마다 어떤 유형으로 들어가있고 어떤 데이터가 있는지 확인하기 위해 F4번이나, schema browser를 이용하는데, F4번은 테이블 명을 알아야만 접근이 가능한 한계가 있고, schema browser는 모든 테이블 조회가 가능하지만 브라우저를 여러개 띄우는 방법을 알 수 없어서 계속 바꿔가며 보다보니 헷갈리는 문제가 존재한다. 이 문제에 대한 해결 방법이나 팁은? 
  - browser는 별도로 나눠서 확인하는 기능이 없고 테이블 f4를 통해 확인하는 방법 밖에 없다. 다만 browser에서는 프로시저를 확인 및 변경이 가능하다(더블클릭). 변경의 경우 더블클릭 후 변경하고 싶은 부분 수정 후 ctrl + F9로 컴파일 실행
  - Eclipse가 되었든, DB가 되었든 가장 중요한 것은 해당 내역을 수정하기 전에 백업을 받아 두기(SVN, 소프트매니저를 맹신해선 안된다.)



### 작성한 DB문 관련 피드백

- Decode기능의 유용성
  - 기존 - `COUNT(CASE WHEN TO_CHAR(USE_LO.USE_DT, 'YYYY-MM') = '2021-05' THEN 1 END)`
  - 변경 후 - `sum(decode(substr(to_char(use_dt),4,2),'05',1,0))`
- BETWEEN문 사용해보기
  - 기존 - `FR_STA_YMD <= '20180523' AND FR_END_YMD >= '20180523'`
  - 변경 후 - `'20180523' BETWEEN FR_STA_YMD AND FR_END_YMD`
- 여러 번 사용하는 문장이나 데이터의 경우 서브 쿼리로 테이블 만드는 것도 좋을 것

```sql
select MNU_ID, MNU_NM, sum(decode(substr(to_char(use_dt),4,2),'05',1,0))
from TB_CO_MNU_MT MNU_MT , TB_CO_SYS_MNU_USE_LO USE_LO
WHERE MNU_MT.MNU_SEQ = USE_LO.MNU_SEQ 
group by MNU_ID, MNU_NM
```

- 팁으로 View -> Toad Options -> Data Grids -> Visual -> Null Columns : yellow 설정 시 NULL 부분이 노란색으로 보여서 가독성이 상승한다.
- Editor -> Behavior -> Language -> PL/SQL -> Edit -> Highlighting 탭에서 Custom Font 클릭을 통해 원하는 폰트와 크기, 색상을 정할 수 있다.





이클립스 관련 단축키
ctrl + shift + r = 파일 찾기

