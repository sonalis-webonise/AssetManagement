
# API Documentation (Version:v1)

## User Authentcation


### Register

Add a new user to the system.

* HTTP Verb: **POST**

* URL: `/api/v1/register`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
* Body (JSON object): 
```JSON
{
		"user": {
				"email": "random@website.com",
				"password": "super_secret"
		}
}
```
* Responses:
  * Sucess:
```JSON 
{
	"auth_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyfQ.jQ2g_Zy-GY6wfj8w3N5ScjfcBLA6LgNvMACaQc4tP-4",
	"user": {
			"id": 2,
			"email": "random@website.com"
	}
}
	```
  * Failure:
```JSON
{
		"status": {
				"code": 2001,
				"message": [
						"Email has already been taken"
				]
		}
}
```

### Login

Give access to a user via a token.

* HTTP Verb: **POST**

* URL: `/api/v1/login`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
* Body (JSON object): 
```JSON
{
		"user": {
				"email": "random@website.com",
				"password": "super_secret"
		}
}
```
* Responses:
  * Sucess: 
```JSON 
{
	"auth_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyfQ.jQ2g_Zy-GY6wfj8w3N5ScjfcBLA6LgNvMACaQc4tP-4",
	"user": {
			"id": 2,
			"email": "random@website.com"
	}
}
```
  * Failure: (Invalid credentials)
```JSON
{
		"status": {
				"code": 404,
				"message": "Not Found: User: Couldn't find User"
		}
}
```


## Assets
Allows you to access and manipulate assets.

### Index Assets
* HTTP Verb: **GET**
* URL: `/api/v1/assets`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Responses:
  * Sucess: 
```JSON
{
		"status": {
				"code": 200,
				"message": "Successful"
		},
		"assets": [
				{
						"id": 2,
						"tag": "WL0102",
						"model": "MacBook",
						"purchase_date": null,
						"serialID": "JG469158VJHV74HJJ38",
						"status": "working",
						"owner_type": "webonise",
						"note": null,
						"brand_id": 1,
						"category_id": 1,
						"created_at": "2016-10-21T05:18:14.448Z",
						"updated_at": "2016-10-21T05:18:14.448Z",
						"parent_id": null,
						"allocation_status": "available",
						"specifications": {
								"hdd": 500,
								"ram": [
										{
												"capacity": 8,
												"quantity": 1
										},
										{
												"capacity": 4,
												"quantity": 2
										}
								],
								"processor": "I3 2330m 2.20GHz"
						}
				},
				{
						"id": 1,
						"tag": "WL002",
						"model": null,
						"purchase_date": null,
						"serialID": "JG46958VJHV74HJJ38",
						"status": "working",
						"owner_type": null,
						"note": null,
						"brand_id": 1,
						"category_id": 1,
						"created_at": "2016-10-20T13:48:44.932Z",
						"updated_at": "2016-10-21T06:05:02.182Z",
						"parent_id": null,
						"allocation_status": "available",
						"specifications": "{}"
				}
		]
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```

### Find Asset 
* HTTP Verb: **GET**
* URL: `/api/v1/assets/:id` (`:id` is a number representing `id` field of `asset`)
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Responses:
  * Sucess: 
```JSON 
{
	"status": {
			"code": 200,
			"message": "Successful"
	},
	"asset": {
			"id": 2,
			"tag": "WL0102",
			"model": "MacBook",
			"purchase_date": null,
			"serialID": "JG469158VJHV74HJJ38",
			"status": "working",
			"owner_type": "webonise",
			"note": null,
			"brand_id": 1,
			"category_id": 1,
			"created_at": "2016-10-21T05:18:14.448Z",
			"updated_at": "2016-10-21T05:18:14.448Z",
			"parent_id": null,
			"allocation_status": "available",
			"specifications": {
					"hdd": 500,
					"ram": [
							{
									"capacity": 8,
									"quantity": 1
							},
							{
									"capacity": 4,
									"quantity": 2
							}
					],
					"processor": "I3 2330m 2.20GHz"
			}
	}
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```

### Create Asset 
* HTTP Verb: **POST**
* URL: `/api/v1/assets`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Body (JSON object): 
```JSON
{
		"asset": {
				"category_id": 1,
				"model": "MacBook",
				"tag": "WL0144",
				"brand_id": 1,
				"serialID": "JG4691158VJHV74HJJ38",
				"owner_type": "Webonise",
				"status": "Working",
				"note": "General multiline note",
				"specifications": {
						"processor": "I3 2330m 2.20GHz",
						"ram": [
								{
										"capacity": 8,
										"quantity": 1
								},
								{
										"capacity": 4,
										"quantity": 2
								}
						],
						"hdd": 500,
						"macWiFi": "AC:62:19:4D:3A:12",
						"macLan": "FC:21:29:D4:A3:35",
						"OSType": 1,
						"OSName": "Mac OS",
						"OSVersion": "Sierra"
				}
		}
}
```
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		},
		"id": 3
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```
   * Failure: (Duplicate data)
```JSON
{
	"status": {
			"code": 2001,
			"message": [
					"Serialid has already been taken",
					"Tag has already been taken"
			]
	}
}
```

### Edit Asset
* HTTP Verb: **PATCH**
* URL: `/api/v1/assets/:id` (`:id` is a number representing `id` field of `asset`)
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Body (JSON object): 
```JSON
{
		"asset": {
				"note": "New multiline note edited"
		}
}
```
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		}
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```


## Allocations
Allows you to access and manipulate allocations.

### Index Allocations
* HTTP Verb: **GET**
* URL: `/api/v1/allocations`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		},
		"allocations": [
				{
						"id": 2,
						"return_date": null,
						"assignment_type": "permanent",
						"asset_id": 1,
						"employee_id": 1,
						"created_at": "2016-10-20T13:49:59.970Z",
						"updated_at": "2016-10-20T13:49:59.970Z",
						"issue_date": "2016-10-20T11:57:24.000Z"
				},
				{
						"id": 3,
						"return_date": null,
						"assignment_type": "permanent",
						"asset_id": 2,
						"employee_id": 1,
						"created_at": "2016-10-20T13:50:23.586Z",
						"updated_at": "2016-10-20T13:50:23.586Z",
						"issue_date": "2016-10-20T11:57:24.000Z"
				}
		]
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```

### Find Allocation 
* HTTP Verb: **GET**
* URL: `/api/v1/allocations/:id` (`:id` is a number representing `id` field of `allocation`)
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		},
		"allocation": {
				"id": 2,
				"return_date": null,
				"assignment_type": "permanent",
				"asset_id": 1,
				"employee_id": 1,
				"created_at": "2016-10-20T13:49:59.970Z",
				"updated_at": "2016-10-20T13:49:59.970Z",
				"issue_date": "2016-10-20T11:57:24.000Z"
		}
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```

### Create Allocation 
* HTTP Verb: **POST**
* URL: `/api/v1/allocations`
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Body (JSON object): 
```JSON
{
			"assignment_details": {
				"asset_id":"1",
				"employee_id":"1",
				"assignment_type": "temporary",
				"issue_date": "2016-10-20 17:27:24 +0530",
				"return_date": "216-10-20 17:27:24 +0530"
			}
}
```
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		},
		"id": 3
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```
   * Failure: (Asset already allocated)
```JSON
{
		"status": {
				"code": 2001,
				"message": [
						"Asset can't be reallocated"
				]
		}
}
```

### Edit Allocation (To de-allocate an asset)
* HTTP Verb: **PATCH**
* URL: `/api/v1/allocations/:id` (`:id` is a number representing `id` field of `allocation`)
* Headers (key/value pairs)
  * `Accept: application/json`
  * `Content-Type: application/json`
  * `Authorization: Bearer <authentication_token>` (Insert actual token)
* Body (JSON object): 
```JSON
{
		"assignment_details": {
				"return_date": "2016-9-20 17:27:24 +0530"
		}
}
```
* Responses:
  * Sucess: 
```JSON 
{
		"status": {
				"code": 200,
				"message": "Successful"
		}
}
```
   * Failure: (Token invalid)
```JSON
{
		"status": {
				"code": 2002,
				"message": "Not Authenticated"
		}
}
```

    

