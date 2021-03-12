## 1. 설치항목<br/>
		@frontend Tool
		vue-cli 설치
		nodejs 설치
		vscode 설치
		
		@backend Tool
		jdk1.8 설치
		sts4 설치
		
		@db Tool
		DBeaver
		
		
## 2. clone후 해야 할 일<br/>
		@backend기동
		sts4 UTF-8 및 기타 설정
		스프링 프로젝트 run (port:8080)
		@frontend기동 (VSCode로 frontend폴더까지 들어간 후 Open)
		$ npm install
		$ npm run serve (port:8081)
		
## 3. 개발시 주의사항<br/>
		http://localhost:8081/#/
		(admin/admin) 로그인시 Webpack빌드된 8080으로 넘어감(Spring Security설정으로 인해 방법이 없는듯..)
		http://localhost:8081/#/main 으로 검색 후 개발 할 것	
	
		
## 4. 배포방법<br/>
		$ cd frontend
		$ npm run build
		src/main/resources/static 하위에 css,js,index.html 파일이 생성된 것을 확인
		maven build
		war파일 확인
	
		
## 5. 커밋 시 주의사항<br/>
		src/main/resources/static 하위의 파일은 제외하고 commit해야 한다.
		로컬 브랜치 생성 -> 원격 브랜치 push -> switch master pull request -> 원격 master push
		ex) dykim -> 원격dykim push -> switch master pull request -> 원격master push
		작업은 개인 브랜치 생성 후 작업하도록 한다.
		개인환경설정에 따라 생성된 불필요한 커밋목록에서 제외시킬 파일은 루트경로에 있는 .gitignore파일에 추가한다.(해당파일은 커밋하면 안됨)
		
## 6. 커밋 시 이슈<br/>
		간혹 frontend경로 하위에 있는 파일을 VSCode에서 수정하였지만 커밋목록에 나타나지 않는 이슈가 발생한다.
		@해결방법
		1.폴더에서 frontend/src를 복사하여 프로젝트에 붙여넣는다.
		2.sts4에서 Git을 사용하는 대신 SourceTree를 사용한다.