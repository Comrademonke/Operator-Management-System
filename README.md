# Operator Management System

The system is developed using Object-Oriented Programming (OOP) principles to ensure maintainability and scalability. Key OOP concepts such as **inheritance**, **polymorphism**, and **abstract classes** are used to structure the system and ensure flexibility and modifications.

This project has developed a centralised system for managing multiple Aotearoa New Zealand activity operators. The system is designed to oversee details of various operators, their offered activities, and the feedback received from customers and experts.

With this system, administrative staff can:

- View a list of all registered operators.
- Register new operators by providing a name and location.
- Create activities and assign them to existing operators.
- Collect and display different types of reviews for each activity.
- Perform various actions on reviews depending on their type.
- Determine the top activity in each location.

The interface will be entirely terminal-driven. This means that the administrators will interact with the system through a menu displayed in the terminal; they select commands by entering the corresponding codes, and additional inputs are prompted as necessary. Once a command is selected, the system processes the action accordingly.

The menu of commands will be displayed on the terminal as follows:
<img width="1225" height="335" alt="image" src="https://github.com/user-attachments/assets/d7289cff-1a09-4539-a851-b492e2e94eaa" />


# Command to run:
To run the system use the following command:

For Unix/Mac OS:
- './mvnw compile exec:java@run' 

For Windows:
- '.\mvnw.cmd compile exec:java@run' 
