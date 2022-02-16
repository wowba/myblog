설치한 Dependencies
- Spring web
- Lombok
- H2 Database
- Spring JPA
- MySQL Driver
- JSON in JAVA

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

만들어야 할 API 목록

1. 전체 게시글 목록 조회
    - [x] 제목, 작성자명, 작성 날짜를 조회하기 
    - [x] 작성 날짜 기준으로 내림차순 정렬하기  
2. 게시글 작성
    - [x] 제목, 작성자명, 작성 내용을 입력하기
3. 게시글 조회
    - [x] 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
    - [x] 조회하는 게시글에 작성된 모든 댓글을 목록 형식으로 볼 수 있도록 하기
4. 게시글 수정
    - [x] 제목, 작성자명, 작성 내용 중 원하는 내용을 수정하기
5. 게시글 삭제
    - [x] 원하는 게시물을 삭제하기
6. 댓글 목록 조회
    - [x] 작성 날짜 기준으로 내림차순 정렬하기
7. 댓글 작성
    - [x] 댓글 내용을 비워둔 채 댓글 작성 API를 호출하면 "댓글 내용을 입력해주세요" 라는 메세지를 return하기
    - [x] 댓글 내용을 입력하고 댓글 작성 API를 호출한 경우 작성한 댓글을 추가하기
8. 댓글 수정
    - [x] 댓글 내용을 비워둔 채 댓글 수정 API를 호출하면 "댓글 내용을 입력해주세요" 라는 메세지를 return하기
    - [x] 댓글 내용을 입력하고 댓글 수정 API를 호출한 경우 작성한 댓글을 수정하기
9. 댓글 삭제
    - [x] 원하는 댓글을 삭제하기

AWS 배포 

1. RDB 연결
   - [x] MySQL을 이용하기
2. EC2 배포
   - [x] Ubuntu EC2 를 구매한 뒤, 8080 포트와 80번 포트를 연결하여 포트 번호 없이도 
     서비스에 접속 가능하게 하기

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

프로젝트 설계

API / Method, URL, return type

1. 전체 게시글 조회 - GET /api/postings List\<PostingResponseDto> `내림차순 정렬.`
2. 게시글 작성 - POST /api/postings PostingResponseDto
3. 게시글 조회 - GET api/postings/{id} PostingResponseDto
4. 게시글 수정 - PUT /api/postings/{id} id
5. 게시글 삭제 - DELETE /api/postings/{id} id
6. 댓글 작성 - POST /api/postings/{id}/comments CommentResponseDto
7. 댓글 수정 - PUT /api/comments/{id} id
8. 댓글 삭제 - DELETE /api/comments/{id} id

3계층 설계 / controller, service, repository

Controller

- PostingRestController : 게시글 관련 컨트롤러
- CommentRestController : 댓글 관련 컨트롤러

Service

- PostingService : 게시글 수정하기
- CommentService : 댓글 수정하기

Repository

- PostingRepository : 게시글 Repo
- CommentRepository : 댓글 Repo

Model

- Posting : 게시글 Entity
- Comment : 댓글 Entity
- Timestamped : 생성시간, 수정시간 확인하기.

Dto

- PostingRequestDto 게시글 Get 용
- PostingResponseDto 게시글 Post 용
- CommentRequestDto 댓글 Get 용
- CommentResponseDto 댓글 Post 용 
