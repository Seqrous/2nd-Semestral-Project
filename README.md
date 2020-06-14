Still in development - refining the functionalities

# 2nd-Semetral-Project
A product/order management system inspired by a Croatian company

### Overview:
On a 2nd semester we were faced with a problem of finding a company that would be willing to co-operate with our development group. The task was to perform a business analysis, examine company's structure and research with what kind of software both employees and employers would be satisfied with and implement a system. Our choice was a Croatian company called Ferometal-Prerada, producing parts for both rail and agriculture industries. After short, but fruitful investigation we have decided to create a software that could help them with storage/product and order management, with the aim of helping them save the money. In the end, after the group had fallen apart, only a small prototype has been delivered.

### Project consists of:
- code located in *src*,
- simple SQL database script in *sql scripts* that will create a local database,
- Java DataBase Connectivity files in *jdbc*,
- project report in *report*

### With the current version of this project you should be able to:
- create and edit a product,
- create/find and edit a customer,
- create/view/edit an order,
- add products to the order

### Prerequisites:
The project has been written using Eclipse IDE and has not been tested outside of it yet
```
- Eclipse IDE for Java
- Some environment for managing SQL (SQL Server Management Studio in my case)
```
### Run the project:
##### 1. Setting up the project in the Eclipse IDE
- 1.1. Import project from **File >>> Open Projects from File System...**
- 1.2. Right click the project file and select **Build Path >>> Configure Build Path...**
![alt text](https://github.com/Seqrous/2nd-Semestral-Project/blob/master/1.png)
- 1.3. Navigate to 'Libraries' tab and click **Add External Jars...**
![alt text](https://github.com/Seqrous/2nd-Semestral-Project/blob/master/2.png)
- 1.4. Locate the project folder and select both of the .jar files found in *jdbc* folder
- 1.5. Now you should see them on the list in 'Libraries' tab and you can click **Apply and Close**

##### 2. Creating the database
**Disclaimer! - In my case, I was using SQL Server Management Studio**
- 2.1 In the environment you are using for SQL, import the query found in *sql scripts*
- 2.2 Run it and the databaset should be set up.

##### 3. Now you should be able to run the project, just execute GUI.java file
