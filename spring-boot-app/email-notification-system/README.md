# Email Notification System (ENS)

The Email Notification System (ENS) is a Spring Batch project that automates sending email notifications to users. It retrieves user records from the database based on the `notification_enabled` flag using a reader. The records are then processed to create a simple mail message in the processor, and the writer sends the email to users one by one. The job is automatically triggered every minute based on the scheduled cron job defined in the application.

This project includes the following features:
- **Spring Profiles** for environment-specific configurations.
- **Accurate logging** for tracking execution flow and errors.
- **Reader Listener** and **Job Listener** to monitor batch processing.
- **Spring Data JPA** for database interactions.
- **Spring Scheduler** with a cron job for periodic execution.
