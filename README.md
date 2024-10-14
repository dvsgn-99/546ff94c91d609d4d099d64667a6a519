## 프로젝트 구조
```
{project-root}
├── api
│   ├── build.gradle.kts
│   └── src
├── buildSrc
│   ├── build.gradle.kts
│   └── src
├── common
│   ├── build.gradle.kts
│   └── src
├── domain
│   ├── build.gradle.kts
│   └── src
├── infra
│   ├── build.gradle.kts
│   └── src
├── gradle
├── Dockerfile
├── docker-compose.yml
├── build.gradle.kts
└── settings.gradle.kts
```

## 프로젝트 실행 방법
> *참고사항*
> - docker 기반 실행
> - spring boot application + mongo db 구조로 되어 있음

- 프로젝트 ROOT에서 docker compose 수행
```shell
$ docker-compose up --build -d 
```

## 프로젝트 설명
### place 검색
- 주어진 요구사항에 맞게 정렬 및 갯수를 제한하여 나타냄
- SearchAggregator를 구현하여 다른 검색 클라이언트가 늘어나더라도 구조적으로 확장을 용이하게 만듦
- 유사도 측정 인터페이스 및 정렬 인터페이스를 사용하여 결과값 도출
- 코틀린 코루틴과 웹플럭스 환경에서 로직 수행
- WebFilter에서 이벤트를 발행하여 비동기 키워드 카운트 증가 로직 수행
- 몽고 디비의 $inc 메서드를 사용하여 분산환경에서의 업데이트 원자적 수행
![시퀀스다이어그램](/statics/kb-drawio.png)
### query count
- 몽고 DB에 저장된 카운트를 단순 조회

## 실행 방법
### curl
*장소 검색*
```shell
$ curl --location 'localhost:8080/v1/places?q={queryParam}'
```
*검색 키워드 목록*
```shell
$ curl --location 'localhost:8080/v1/places/counts'
```