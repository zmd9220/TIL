@C:\oraclexe\app\oracle\product\11.2.0\server\sqlplus\admin\plustrce.sql

grant plustrace to hr;

-- sql developer에서는 추가 권한 필요
grant select on v_$sql to hr;
grant select on v_$sql_plan_statistics_all to hr;
grant select on v_$parameter to hr;

GRANT SELECT ON V_$SQL                      TO HR;
GRANT SELECT ON V_$SQL_PLAN                 TO HR;
GRANT SELECT ON V_$SQL_PLAN_STATISTICS_ALL  TO HR;
GRANT SELECT ON V_$SESSION                  TO HR;
GRANT SELECT ON V_$SQL_PLAN_STATISTICS      TO HR;
GRANT SELECT ON V_$SQL_WORKAREA             TO HR;
GRANT SELECT ON V_$SQL_WORKAREA_ACTIVE      TO HR;