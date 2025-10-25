-- Sample DB dump for jobportal (MySQL)
CREATE DATABASE IF NOT EXISTS jobportal DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE jobportal;

-- roles table
CREATE TABLE IF NOT EXISTS role (name VARCHAR(255) PRIMARY KEY);
INSERT INTO role (name) VALUES ('ROLE_EMPLOYER'), ('ROLE_APPLICANT');

-- users table (simplified for dump)
CREATE TABLE IF NOT EXISTS users (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 full_name VARCHAR(255),
 email VARCHAR(255) UNIQUE,
 password VARCHAR(255),
 company_name VARCHAR(255)
);

-- sample users (passwords are bcrypt of Password@123)
INSERT INTO users (full_name,email,password,company_name) VALUES
('Alice Employer','employer@example.com','$2a$10$8u1w...replace_with_real_hash','Acme Corp'),
('Bob Applicant','applicant@example.com','$2a$10$8u1w...replace_with_real_hash',NULL);

-- jobs table
CREATE TABLE IF NOT EXISTS jobs (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 title VARCHAR(255),
 description TEXT,
 location VARCHAR(255),
 remote_job BOOLEAN,
 job_type VARCHAR(255),
 salary_min INT,
 salary_max INT,
 posted_at DATETIME,
 employer_id BIGINT
);

-- applications table
CREATE TABLE IF NOT EXISTS application (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 job_id BIGINT,
 applicant_id BIGINT,
 resume_url VARCHAR(1024),
 status VARCHAR(50),
 applied_at DATETIME
);

-- End of sample dump
