variable "aws_region" {
  description = "AWS Region"
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "ECS Cluster Name"
  default     = "employee-cluster1"
}

variable "ecr_repo_name" {
  description = "ECR Repository Name"
  default     = "employee-department-eks"
}

variable "cloudwatch_log_group_name" {
  description = "CloudWatch Log Group name"
  default     = "/ecs/employee-department-eks"
}
