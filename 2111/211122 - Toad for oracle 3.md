# 211122 - Toad for oracle 3 + ERD 살펴보기





## 오늘의 피드백

- group by나 select 등에서 to_char나 case 문으로 분기하는 경우 중복된 키워드를 계속 넣어야 하는 경우가 존재하는데, 중복을 방지하는 방법은 존재하는지?

  - ```sql
    select case when to_char(hiredate, 'mm') between '03' and '05' then '1'
            when to_char(hiredate, 'mm') between '06' and '08' then '2'
            when to_char(hiredate, 'mm') between '09' and '11' then '3'
            else '4'
            end "분기",
            trunc(avg(sal))
    from emp
    where to_char(hiredate, 'yyyy') = '1981'
    -- 중복 되는 부분
    group by case when to_char(hiredate, 'mm') between '03' and '05' then '1'
            when to_char(hiredate, 'mm') between '06' and '08' then '2'
            when to_char(hiredate, 'mm') between '09' and '11' then '3'
            else '4'
            end
    order by "분기"
    ```

  - 존재하지 않는다..  다만 위의 sql 문을 listagg 라는 함수를 이용하여 그룹화 하는 방법도 존재한다. 이와 관련된 자세한 내용은 검색 해보기

## SQL 반올림, 올림, 내림, 버림 정리해보기

- 반올림 - ROUND( number , decimal_places )
  - 특정 자릿수를 기준으로 가장 가까운 값으로 올리거나 내려서 리턴
  - number - 반올림 할 숫자
  - decimal_places - 반올림된 소수 자릿수. 이 값은 정수 여야하고 이 매개 변수를 생략하면 ROUND 함수는 숫자를 소수점 이하 0 자리로 반올림한다.
- 올림 - CEIL(number) - ceiling function의 약자
  - 올림은 소수 자릿수를 결정할 수 없으며 항상 0 자리로 올림값을 반환 한다.(소수 1자리 이하 값이 있으면 무조건 +1 정수부터는 값 변동 X), 무조건 정수로 만든다고 생각하면 됨
- 내림 - FLOOR(number) - floor function의 약자
  - 올림과 마찬가지로 항상 0자리 아래 값을 내려서 계산하는 함수 (무조건 정수로 만든다고 생각)
- 버림 - TRUNC(number, decimal_places)
  - 특정 자릿수를 기준으로 자른 숫자를 반환(0으로 만든다고 보면 됨)
  - number - 절사 할 숫자
  - decimal_places - 절사 할 대상의 소수점 이하 자릿수. 이 값은 정수 여야만 하고 이 매개 변수가 생략되면 TRUNC 함수는 숫자를 소수점 이하 0 자리로 자른다.
- 예제

```sql
-- 반올림
SELECT ROUND(1282.1212) FROM DUAL; -- 12
SELECT ROUND(1282.8282) FROM DUAL; -- 12

-- 정수 2번째 자리 반올림
SELECT ROUND(1282.8282, -2) FROM DUAL; -- 1300
 
-- 소수점 2번째 자리 반올림
SELECT ROUND(1282.8282, 2) FROM DUAL; -- 1282.83

-- 올림
SELECT CEIL(12.12) FROM DUAL; -- 13
SELECT CEIL(12.82) FROM DUAL; -- 13
 
-- 내림
SELECT FLOOR(12.12) FROM DUAL; -- 12
SELECT FLOOR(12.82) FROM DUAL; -- 12

-- 버림
SELECT TRUNC(12.12) FROM DUAL; -- 12
SELECT TRUNC(12.82) FROM DUAL; -- 12
 
-- 정수 2번째 자리 버림
SELECT TRUNC(1282.8282, -2) FROM DUAL; -- 1200
 
-- 소수점 2번째 자리 버림
SELECT TRUNC(1282.8282, 2) FROM DUAL; -- 1282.82
```

- 참고 URL 
  - https://deftkang.tistory.com/131
  - https://junghn.tistory.com/entry/Oracle-오라클-올림-반올림-내림-버림-함수TRUNCROUNDCEILFLOOR-사용법과-예제

