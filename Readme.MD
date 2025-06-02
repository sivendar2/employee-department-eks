
  1. Build Spring Boot App & Docker Image
mvn clean package
docker build -t employee-department-eks -f Docker/Dockerfile .

2. Push Image to ECR
Create ECR repository:
aws ecr create-repository --repository-name employee-department-eks
Authenticate ecr -
aws ecr get-login-password | docker login --username AWS --password-stdin 779846797240.dkr.ecr.us-east-1.amazonaws.com

docker tag employee-department-eks:latest 779846797240.dkr.ecr.us-east-1.amazonaws.com/employee-department-eks

Tag and Push:
docker tag employee-department-eks:latest 779846797240.dkr.ecr.us-east-1.amazonaws.com/employee-department-eks
docker push 779846797240.dkr.ecr.us-east-1.amazonaws.com/employee-department-eks

3. Create ECS Cluster
aws ecs create-cluster --cluster-name employee-cluster1

 4. Create IAM Role for ECS Task Execution
Create a role -
aws iam create-role  --role-name ecsTaskExecutionRole --assume-role-policy-document file://D:\VATest\ECS\employee-department\ecs-trust-policy.json
aws iam attach-role-policy --role-name ecsTaskExecutionRole --policy-arn arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy

5. Create Task Definition
aws ecs register-task-definition --cli-input-json file://D:\VATest\ECS\employee-department\task-def1.json

6. Create ECS Service with Fargate + Load Balancer (optional)
aws ecs create-service --cluster employee-cluster1 --service-name employee-service --task-definition employee-task1 --launch-type FARGATE --desired-count 1 --network-configuration "awsvpcConfiguration={subnets=[subnet-0c06c9ba80675ca5b],securityGroups=[sg-03992897fd20860bd],assignPublicIp=ENABLED}"


Detail Explanantion of the project -

The EmployeeController is a Spring Boot REST controller that exposes multiple GET endpoints to retrieve employee information based on department and sorting criteria. It delegates the core business logic to the EmployeeInfoBusinessService.

1. Get employees in a department, sorted by name
URL: /by-department/{departmentName}

Description: Returns a list of employees in the specified department, sorted alphabetically by employee name.

Delegates to: getEmployeesByDepartmentSortedByName(departmentName)

2. Get department name by employee number
URL: /{employeeNumber}/department

Description: Returns the department name for the given employee number.

Delegates to: getDepartmentNameByEmployeeNumber(employeeNumber)

3. Get employees by department, sorted by employee number
URL: /department/{departmentName}/sorted-by-id

Description: Returns employees in the specified department, sorted by employee number.

Delegates to: getEmployeesByDepartmentSortedByEmpNo(departmentName)

4. Get employees in a department whose names start with 'A'
URL: /department/{departmentName}/starting-with-a

Description: Returns employees in the specified department whose names start with the letter 'A'.

Delegates to: getEmployeesStartingWithA(departmentName)

5. Get all employees sorted by department and employee number
URL: /sorted-by-department-and-id

Description: Returns all employees sorted first by department name and then by employee number.

Delegates to: getAllEmployeesSortedByDeptAndEmpNo()

✅ Use Case Summary
Provides flexible query options to retrieve employee data based on:

Department name

Sorting by employee name or number

Filtering names starting with a specific character (A)



Deployment steps 
================
Package your Spring Boot app into a .jar.

Push it to ECR or upload it manually.

Use the AWS Console to:

Create a Fargate task definition

Setup an ECS service

Connect to ALB, VPC, subnets, etc.
