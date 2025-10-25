# Job Portal (Spring Boot)
Contains a minimal, realistic job portal project with:
- Roles: ROLE_EMPLOYER, ROLE_APPLICANT
- User registration & login (Spring Security + BCrypt)
- Employers can post jobs
- Applicants can view & apply
- Thymeleaf templates and Bootstrap UI

## Test credentials (created by DataLoader)
- Employer: employer@example.com / Password@123
- Applicant: applicant@example.com / Password@123

## Notes before running
1. Update `src/main/resources/application.properties` with your MySQL credentials.
2. Build with Maven (STS): `mvn clean package`
3. Run: `mvn spring-boot:run` (or import the project into STS and run JobPortalApplication)
4. The app uses `spring.jpa.hibernate.ddl-auto=update` so tables will be created automatically.

## Included
- Full source for controllers, models, repositories, basic security config.
- Templates: index, login, register, post-job, job-details.
- Sample SQL dump `db-sample.sql`.

This is a starter full-featured scaffold. You can extend: add pagination, upload resumes, resume download, email notifications, admin pages, and better validation.
