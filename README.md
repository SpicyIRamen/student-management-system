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

GetAll, GetById & Delete use no body
