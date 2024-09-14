Financial Management Tool

Overview
  >The Financial Management Tool is a web-based application that helps users manage their financial data efficiently. Users can create accounts using their email and manage details of their assets, liabilities,      income, and expenses. The application allows adding, editing, and deleting financial entries, providing a simple yet powerful way to track personal finances.


Features

  >User Account Creation: Users can sign up with their email addresses.

  >Asset Management: Add, edit, or delete asset details.
  
  >Liability Management: Keep track of liabilities and update them as needed.
  
  >Income & Expenses Tracking: Log income and expenses for better financial management.
  
  >Responsive UI: User-friendly interface developed using HTML and CSS.
  
  >Secure Backend: Built with Java Spring Boot for secure and efficient backend operations.


Tech Stack

>Frontend: HTML, CSS

>Backend: Java (Spring Boot)

>Database: MySQL

>Design Principles:

    SOLID Principles (e.g., Single Responsibility Principle)
  
    GRASP Principles for maintainable and scalable code


Setup Instructions

  Clone the repository:

    git clone https://github.com/yourusername/financial-management-tool.git

  Navigate to the project directory:

    cd financial-management-tool

  Configure the database connection in the application.properties file:

    spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword

  Build and run the application:

    mvn spring-boot:run
