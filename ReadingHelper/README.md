## 1. 설치항목<br/>
		vue-cli 설치<br/>
		nodejs 설치<br/>
		jdk1.8 설치<br/>
		sts4 설치<br/>
		<br/>
		<br/>
## 2. clone후 해야 할 일<br/>
		@backend기동<br/>
		스프링 프로젝트 run (port:8080)<br/><br/>
		@frontend기동<br/>
		$ cd frontend<br/>
		$ npm install<br/>
		$ npm run serve<br/>
		<br/>
		<br/>
## 3. 배포방법<br/>
		$ cd frontend<br/>
		$ npm run build<br/>
		src/main/resources/static 하위에 css,js,index.html 파일이 생성된 것을 확인<br/>
		maven build<br/>
		war파일 확인<br/>
		<br/>
		<br/>
## 4. 커밋 시 주의사항<br/>
		src/main/resources/static 하위의 파일은 제외하고 commit해야 한다.
