# JavaToyProject

- [프로젝트 회고록](https://iheese.github.io/project/2022/05/14/javatoyproject/)
- Java를 이용한 회원관리 프로그램입니다.
- DB는 H2을 사용했고 JDBC를 이용했습니다.
- 아이디(MEMBER_ID), 이름(NAME), 전화번호(PHONE_NUMBER)를 받아 회원가입을 진행할 수 있습니다.
- 콘솔을 통해 DB 조회, 등록, 수정(전화번호 수정), 삭제가 가능하게 했습니다. 
- 유효성 확인 기능을 추가하였습니다.
> - 아이디 검증
> - 이름 검증
> - 전화번호 검증

<br>

- 프로그램 시작

```
#############################
### 회원 관리 프로그램 START ##
#############################
목록을 원하시면 1번을 입력하세요.
등록을 원하시면 2번을 입력하세요.
수정을 원하시면 3번을 입력하세요.
삭제를 원하시면 4번을 입력하세요.
종료를 원하시면 0번을 입력하세요.
```

<hr>

- 회원 등록

```
2
아이디를 입력하세요 (형식 M-00001): M-00001
이름을 입력하세요 : 이철수
전화번호를 입력하세요 : 010-1234-1234
---> 회원가입에 성공하셨습니다.
```

<hr>

- 회원 목록

```
1
현재 등록된 회원 목록입니다.
---> Member [memberId=M-00001, name=이철수, phoneNumber=010-1234-1234]
```

<hr>

- 회원 전화번호 수정

```
3
수정할 아이디를 입력하세요 (형식 M-00001): M-00001
수정할 전화번호를 입력하세요 : 010-0000-1111
---> 회원수정에 성공하셨습니다.
```

<hr>

- 회원 삭제 및 확인

```
4
삭제할 아이디를 입력하세요 : M-00001
---> M-00001 회원 삭제에 성공하셨습니다.
목록을 원하시면 1번을 입력하세요.
등록을 원하시면 2번을 입력하세요.
수정을 원하시면 3번을 입력하세요.
삭제를 원하시면 4번을 입력하세요.
종료를 원하시면 0번을 입력하세요.
1
등록된 회원이 없습니다.
```

<hr>

- 유효성 검사
- 이름, 아이디, 전화번호 모두 같은 로직으로 움직인다.

```
2
//같은 아이디가 이미 존재할 때
아이디를 입력하세요 (형식 M-00001): M-00001
M-00001가 이미 존재합니다.

//아이디 입력 방식이 틀렸을 때
아이디를 입력하세요 (형식 M-00001): m-00001
아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.

//이름을 적지 않았을 때(아이디, 전화번호 모두 같은 로직으로 구성)
아이디를 입력하세요 (형식 M-00001): M-00001
이름을 입력하세요 :  
이름은 필수 입력항목입니다.

//전화번호 입력 방식이 틀렸을 때
아이디를 입력하세요 (형식 M-00001): M-00001
이름을 입력하세요 : 김미애
전화번호를 입력하세요 : 01012341234
전화번호는 두 개의 '-'를 포함하여 총 13개의 문자로 구성해야 합니다.
```

```
3
수정할 아이디를 입력하세요 (형식 M-00001): M-00002
수정할 M-00002 회원 정보가 존재하지 않습니다.
```

- 수정, 삭제에도 모두 유효성 검사가 적용되어 있다.

<hr>

- 프로그램 종료

```
0
#############################
### GOOD-BYE 프로그램 종료 ###
#############################
```
