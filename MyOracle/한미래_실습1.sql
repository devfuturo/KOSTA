--1
SELECT employee_id, first_name, salary
FROM employees
WHERE salary BETWEEN 10000 AND 14000; 

--2
SELECT employee_id, first_name, last_name
FROM employees
WHERE Lower(first_name) LIKE '%e%';

--3
SELECT employee_id, first_name
FROM employees
WHERE First_name Like 'J%'AND First_name Like '%n%';

--4
SELECT employee_id, first_name , DECODE(manager_id, NUll, '������ �ƴ�' , '��������')
FROM employees;

--5
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0)���� 
FROM employees
WHERE department_id IN(30,90) AND salary >=10000;

--6
SELECT employee_id, department_id, first_name, salary, salary*NVL(commission_pct,0) ����
FROM employees
WHERE department_id NOT IN(30,60,90) AND salary>=10000;

--7
SELECT employee_id, TRUNC((SYSDATE-hire_Date)/365,0)
FROM employees
ORDER BY 2 DESC;