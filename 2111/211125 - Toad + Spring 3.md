# 211125 - Toad + Spring 3



## 오늘의 피드백

- 2가지 이상의 테이블을 JOIN 하여 쓸 경우, 가능하면 SELECT나 WHERE의 COLUMN에 ALIAS(AS)를 사용하여 명시하는 것이 좋다.

  - 먼저 가독성 측면에서 해당 테이블의 구조를 자세히 모르더라도 어떤 테이블의 column인지 알게 되어 접근 및 수정이 용이해진다.

    - ```sql
      -- e = emp 관련 column, s = salgrade 관련 column 임을 명확히 알 수 있다.
      select e.empno, e.ename, e.sal, s.grade
      from emp e, salgrade s
      where e.sal between s.losal and s.hisal 

  - 또한 중복된 이름의 COLUMN이 존재하는데 ALIAS를 지정하지 않았을 경우 해당 어떤 COLUMN을 가르키는지 SQL문이 알 수 없어 에러가 발생하므로 반드시 명시해야한다.

    - ```sql
      -- select의 deptno가 어떤 테이블의 deptno 인지 알 수 없어 에러
      select empno, ename, deptno 
      from emp e, dept d
      where e.deptno = d.deptno
      -- 어떤 테이블의 deptno인지 명시함으로써 에러가 발생하지 않음
      select e.empno, e.ename, e.deptno 
      from emp e, dept d
      where e.deptno = d.deptno

- JOIN과 서브 쿼리를 WHERE 절에서 동시에 사용할 때, JOIN 먼저? vs 서브 쿼리문 비교 먼저?

  - 둘의 순서를 바꾼다고 성능상으론 큰 차이가 없지만, 일반적으로는 JOIN을 먼저 작성하고 서브쿼리 문을 작성하는 것을 원칙으로 삼는다. (현재 FD 시스템의 코드 규약인지 SQL 문법상의 원칙인지 까지는 아직 모르겠다.)

- 현재 FD 지원 시스템 기준으로는 Tab보단 스페이스바로 간격을 맞추는 것을 규칙으로 하고 있다.

  - Tab의 경우 사용자의 세팅에 따라 2칸, 4칸, 8칸 등 자유롭기 때문에 통일하기가 어렵다.
  - 그래서 스페이스바를 통해 간격을 통일하고 있음

- 프로그램을 개발할 때, 코드 규약을 잘 맞추는 것이 중요하다.

  - 현재 FD 시스템에 있어서 LG CNS 분들이 맡으셨던 개발 부분은 주석도 잘 정리되어 있고, 처음에 정해진 코드 작성법에 따라 작성하였기 때문에 코드의 가독성이 좋았다.
  - 반면 천차장님 말씀으로는 지도 관련 서비스를 개발했던 다른 회사에서는 개발자마다 코드 작성법이 다르고 주석을 적지 않은 부분도 있어 확인하기 까다롭다고 하셨다. 
  - 프로그램은 개발을 하고 끝나는 것이 아니라 이후 유지보수까지 생각해야 하기 때문에 항상 처음에 정해진 규칙대로 코드를 작성해야 코드 전체적으로 통일성을 줄 수 있어 읽기 수월하다.

- 5-8번 문제 "emp 테이블에서 부서별 최대 급여(sal)를 받는 사원의 empno, ename, deptno, sal을 출력하시오." 에서 '부서별' 때문에 group by로 풀려고 했는데, 도저히 정답이 생각나지 않았다.

  - 그래서 다른 방법을 찾아보니 나눠주셨던 엑셀 파일의 서브쿼리 2 시트에서 해결법을 찾을 수 있었다.

  - ```sql 
    -- 사원 중에 각 부서의 평균 급여보다 많은 급여를 받는 사원은?
    SELECT empno, sal, deptno
    FROM emp e
    WHERE sal > (SELECT AVG(sal) FROM emp WHERE deptno = e.deptno);

  - 위와 같은 코드에서 sql문의 진행순서는
    - emp에서 차례로 1개의 row를 읽고 서브쿼리에 전달
    - e.deptno에 현재 읽고 있는 row의 deptno를 값으로 하여 서브쿼리의 where문을 비교
    - where 문이 True로 반환되면 서브쿼리 값이 전달되면서 메인쿼리의 where문과 비교
    - 메인쿼리의 where문이 True가 되면 해당 하는 row는 select에 추가



