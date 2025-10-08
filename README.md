# Apartment Tracker

This application utilizes Selenium to scrape information on user-specified apartment listings from Apartments.com, 
stores the data, and looks for changes in price. A Thymeleaf Web UI is provided to help with managing data operations, as well as sorting through data.


## Setup and Installation

### Prerequisites

*   Java 21
*   Maven
*   PostgreSQL
*   Docker (optional)

### Database Setup

1.  If you plan to run in docker, a database will automatically be set up.
2.  If developing and running locally, create an empty PostgreSQL database however you see fit (I personally used [Neon](https://neon.com/) #notsponsored). Create the file 'application-local.properties' under 'src/main/resources' and add the following properties, replacing `<your_database_url>` with your database url and `<your_username>` and `<your_password>` with, you guessed it, your PostgreSQL database credentials:

```properties
spring.datasource.url=<your_database_url>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
```

### Running the Application

1.  Clone the repository:
    ```bash
    git clone https://github.com/jordenwd/apartment-tracker.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd apartment-tracker
    ```
3.  Build the project using Maven:
    ```bash
    mvnw clean install
    ```
4.  Run the application:
    ```bash
    java -jar target/apartment-tracker-0.0.1-SNAPSHOT.jar
    ```

The application will be available at `http://localhost:8080`.

## Docker

### Running with Docker Compose

1.  Make sure you have Docker and Docker Compose installed.
2.  You can update the `application-docker.properties` credentials in the `compose.yaml` file with different credentials if you so choose.
3.  Run the following command in the root directory of the project:

```bash
docker-compose up -d
```

This will start the application, Selenium, and a PostgreSQL database in Docker containers. The application will be available at `http://localhost:8080`.

### Future Plans
* Add Zillow scraping
* Add toasts to inform user of functions or updated data
* Improve UI
* Add more sorting operations
* Add filtering

### Important Note
While I did build this project as a solution to a problem I had, I mainly built it as a test of my ability to build a functioning CRUD application and a web scraper. It is likely I won't be updating this project again until I am looking for a new apartment and need to keep track of prices (which as of writing this will be a while🤓). If you come across this project and would like to use it for yourself feel free to fork it and make any changes you need.
