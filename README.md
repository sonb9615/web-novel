# web-novel

## Project Spec
    - Java11
    - Spring boot 2.7
    - JPA
    - MySQL
    - Redis
    - Docker
    
## docs
    - ERD Diagram
    - 테이블 DDL
    - figma 자료
    
## settings
    - redis 서버 실행을 위한 docker-compose file
    

## API 설명

### 1. 캐쉬 충전 API

#### HTTP Mthod
- POST

#### Request Url
- /charge/cache

#### Request Body
| 속성 | 타입  | 필수여부 | 설명 |
| --- | --- | --- | ---|
| userNo | String | Y | 회원 고유 번호
| money | int | Y | 충전 결제 금액
 
#### Request
```json
{
	"userNo": "ef21dc73ec124a93b1d8896c08374de7",
	"money" : 1000
}
```

#### Response
```json
{
    "result": "SUCESS"
}
```

### 2. 이용권 충전 API

#### HTTP Mthod
- POST

#### Request Url
- /charge/tickets

#### Request Body
| 속성 | 타입  | 필수여부 | 설명 |
| --- | --- | --- | ---|
| userNo | String | Y | 회원 고유 번호
| novelId | String | Y | 이용권 구입할 소설 고유 ID
| chargeTicketsCnt | int | Y | 구입할 이용권 수량
 
#### Request
```json
{
	"userNo": "ef21dc73ec124a93b1d8896c08374de7",
	"novelId" : "45ed40d9224a4d70bc1fff6f8306881c",
    "chargeTicketsCnt" : 3
}
```

#### Response
```json
{
    "result": "SUCESS"
}
```

