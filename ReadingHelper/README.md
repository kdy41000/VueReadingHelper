1. 설치항목

vue-cli 설치

nodejs 설치

jdk1.8 설치

sts4 설치


2. clone후 해야 할 일

@backend기동

스프링 프로젝트 run (port:8080)



@frontend기동

$ cd frontend

$ npm install

$ npm run serve



3. 배포방법

$ cd frontend

$ npm run build

src/main/resources/static 하위에 css,js,index.html 파일이 생성된 것을 확인


maven build

war파일 확인



4. 커밋 시 주의사항

src/main/resources/static 하위의 파일은 제외하고 commit해야 한다.
