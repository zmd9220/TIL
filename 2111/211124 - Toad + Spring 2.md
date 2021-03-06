# 211124 - Toad + Spring 2



## 오늘의 피드백

- 4-13번 문제를 풀던 도중 `여기서, 그래프는 sal이 500을 초과할 때마다 *를 추가한다.` 를 보고 어떻게 해야할 지 막막하여 구글링한 결과, lpad라는 키워드를 찾을 수 있었다.
- 다른 방법도 있을 것 같은데, 지금으로썬 생각나는 방법이 없다.

```sql
-- lpad를 사용하여 푼 4-13번 문제
-- 13. sal이 2000 이상이고, deptno가 20 또는 30인 사원들에 대하여 empno, ename, sal, dname, grade, 그래프를 출력하시오. 여기서, 그래프는 sal이 500을 초과할 때마다 *를 추가한다.
select empno, ename, sal, dname, grade, lpad(' ', floor(sal/500)+1, '*') "그래프"
from emp, dept, salgrade
where sal >= 2000 and emp.deptno in (20, 30) 
and emp.deptno = dept.deptno and 
emp.sal between salgrade.losal and salgrade.hisal;
```

![image-20211124151341582]()

### LPAD, RPAD에 대한 정리

- 숫자형 문자인 경우 문자길이를 똑같이 맞춰야하는 경우가 있다. 
- LPAD는 왼쪽, RPAD는 오른쪽부터 총길이 만큼 지정한 문자를 채운다.
- ex) 1, 10, 123 -> 00001, 00010, 00123
- "총 문자길이" - Length("값") = 채울 문자 수

#### LPAD 함수

- LPAD 함수는 지정한 길이 만큼 왼쪽부터 특정문자로 채워준다.
- 함수 : LPAD("기본값", "총 문자길이", "채울문자")

#### RPAD 함수

- RPAD 함수는 지정한 길이 만큼 오른쪽부터 특정문자로 채워준다.
- 함수 : RPAD("기본값", "총 문자길이", "채울문자")

#### 예제

```sql
-- 자릿수 맞추기
SELECT 
LPAD(12345, 10, 0), -- 왼쪽부터 총 10자리까지 0으로 채우기 0000012345
RPAD(12345, 10, 0) -- 오른쪽부터 총 10자리까지 0으로 채우기 1234500000
FROM DUAL;
-- 문자열 삽입
SELECT 
LPAD('1234-1234',13, '010-'), -- 왼쪽에 '010-' 문자 삽입 010-1234-1234
RPAD('010-1234-1234', 23, '(전화번호)') -- 오른쪽끝에 '(전화번호)' 문자 삽입 010-1234-1234 (전화번호)
FROM DUAL;
```

