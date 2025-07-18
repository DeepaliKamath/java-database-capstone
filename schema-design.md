This Spring Boot application uses both MVC and REST controllers. Thymeleaf templates are used for the Admin and Doctor dashboards, while REST APIs serve all other modules. The application interacts with two databases—MySQL (for patient, doctor, appointment, and admin data) and MongoDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MySQL uses JPA entities while MongoDB uses document models.
## MySQL Database Design
       Tables: patients, doctors, appointments, admin
        ### Table: Patients
          - patient_id: INT, Primary Key, Auto Increment
          - patient_name: String, Not Null
          - age : INT, Not Null
          - email : String, Not Null
          - password : String, Not Null, Size(min = 6)
          - phone : INT, Not Null
          - address : String, Not Null
      
        ### Table: Doctors
          - doctor_id: INT,Primary Key, Auto Increment
          - doctor_name: String, Not Null
          - speciality : String, Not Null
          - email : String, Not Null
          - password : String, Not Null, Size(min = 6)
          - phone : INT, Not Null
          - availableTimes : List<String>
      
        ### Table: appointments
          - id: INT, Primary Key, Auto Increment
          - doctor_id: INT, Foreign Key → doctors(id)
          - patient_id: INT, Foreign Key → patients(id)
          - appointment_time: DATETIME, Not Null
          - status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)

        ### Table: admin
          - id: INT, Primary Key, Auto Increment
          - username: String, Not Null
          - password : String, Not Null, Size(min = 6)
- 
## MongoDB Collection Design
        prescriptions, feedback, logs, or messages
        ### Collection: prescriptions
            ```json
            {
              "_id": "ObjectId('64abc123456')",
              "patientName": "John Smith",
              "appointmentId": 51,
              "medication": "Paracetamol",
              "dosage": "500mg",
              "doctorNotes": "Take 1 tablet every 6 hours.",
              "refillCount": 2,
              "pharmacy": {
                "name": "Walgreens SF",
                "location": "Market Street"
              }
            }

            ### Collection: feedback
            ```json
            {
              "_id": "ObjectId('64abc123456')",
              "patientName": "John Smith",
              "appointmentId": 51,
              "feedback": "Doctor could have given a lose dosage tablet"              
            }

            ### Collection: logs
            ```json
            {
              "_id": "ObjectId('64abc123456')",
              "patientName": "John Smith",
              "appointmentId": 51,
              "medication": "Paracetamol",
              "dosage": "500mg",
              "doctorNotes": "Take 1 tablet every 6 hours.",
              "refillCount": 2,
              "pharmacy": {
                "name": "Walgreens SF",
                "location": "Market Street"
              }
            }

            
            
