# student-management-system assignment

### Insomnia
- localhost:8080/student-management-system/api/v1/student

### CRUD Endpoints
- Create: 
	- /new
- Read: 
	- /getall
	- /{id}
	- /query?lastname={lastname}
- Update:
	- /update/{id}
- Delete:
	- /delete/{id}

- Subject
- localhost:8080/student-management-system/api/v1/subject/
	- query?subName=Language
- Subject GetAll
	- getall

- Teacher
- localhost:8080/student-management-system/api/v1/teachers/
- Teachers GetAll
	- getall

### JSON-Bodies

Create & Update
```
{
	"firstName": "firstname",
	"lastName": "lastname",
	"email": "asdasd@asdasd.se",
	"phoneNumber": "123123"
}
```

GetAll, GetById, GetStudentByLastName & Delete use no body
