# 🌈이상청

### 이화인을 위한 기상 정보 및 옷차림 정보 제공 서비스

<br>



## 🌞프로젝트 설명

### 🌦 ABOUT E-WEATHER


> 💡 기존 교내 커뮤니티 날씨 게시판의 시각 정보 부족과, 기상정보를 종합적으로 파악할 수 없다는 문제점에서 시작하여 고안한, 이화인을 위한 기상정보 서비스입니다. 오픈 API를 활용해 매일의 기상정보를 제공하며, 옷에 대한 투표를 통해 교내 커뮤니티  날씨게시판의 기능을 시각적으로 확장합니다. 이화그린 색의 야잠, 뽀글이후드 등을 입은 이화 곰돌이를 통해 기온과 강수상태를 파악할 수 있습니다.

<br>


<table>
    <tr width="100%">
        <td width="50%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/intro1.png"></td>
        <td width="50%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/intro2.png"></td>
    </tr>
</table>
<table>
    <tr width="100%">
        <td width="25%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/func1.png"></td>
        <td width="25%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/func2.png"></td>
        <td width="25%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/func3.png"></td>
        <td width="25%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/func4.png"></td>
    </tr>
</table>


## 🌟팀원 소개


<table border="1" cellspacing="0" cellpadding="0" width="90%">
    <tr width="100%">
        <td width="20%" align="center"><a href= "https://github.com/thfla1105">이소림</a></td>
        <td width="20%" align="center"><a href= "https://github.com/Sohyun-Dev">박소현</a></td>
        <td width="20%" align="center"><a href= "https://github.com/sunnyineverywhere">이선의</a></td>
        <td width="20%" align="center"><a href= "https://github.com/eunseo22mv">김은서</a></td>
        <td width="20%" align="center"><a href= "https://github.com/Gom3rye">김명지</a></td>
    </tr>
    <tr width="100%">
        <td width="20%" align="center"><img src = "https://github.com/thfla1105.png"></td>
        <td width="20%" align="center"><img src = "https://github.com/Sohyun-Dev.png"/></td>
        <td width="20%" align="center"><img src = "https://github.com/sunnyineverywhere.png"/></td>
        <td width="20%" align="center"><img src = "https://github.com/eunseo22mv.png"/></td>
        <td width="20%" align="center"><img src = "https://github.com/Gom3rye.png"/></td>
    </tr>
    <tr width="100%">
        <td width="20%" align="center">
서버 인프라 관리 및 CICD 환경 구축, RDS 환경 구축, S3 Bucket 환경 설정 및 파일 업로드 API 구현, OAuth JWT 로그인 시스템 구현, [캘린더, EWEATHER, ICON 등 COMPONENT]  CRUD API 로직 설계 및 개발, GLOBAL Config 및 환경 요소 설정, 데이터베이스 설계</td>
        <td width="20%" align="center">
곰돌이 logic 설계, 곰돌이 코디 조회 관련 로직 구현 및 CRUD API 개발, 기온 관련 로직 구현 및 CRUD API 개발, DB 데이터 관리, 데이터베이스 설계</td>
        <td width="20%" align="center">
데이터베이스 설계, 투표 조회 관련 로직 구현 및 투표 게시글 CRUD API 개발 및 구현, 투표 게시글 좋아요&싫어요 기능 개발, 기상청 단기예보 OPEN API 가공, S3 Bucket 생성 및 환경 설정, oauth2를 활용한 로그인 </td>
        <td width="20%" align="center">
데이터베이스 설계, 투표 조회 관련 로직 구현 및 투표 게시글 CRUD API 개발 및 구현, 투표 게시글 좋아요&싫어요 기능 개발</td>
        <td width="20%" align="center">
배포, 인프라 유지 보수</td>
   </tr>
</table>

## ⚡프로젝트 구조

<table>
    <tr width="100%">
        <td width="30%" align="left">
<h3>기술 STACK</h3>
<ul>
<li>Java 11</li>
<li>Spring Boot</li>
<li>AWS EC2, RDS, S3, CodeDeploy</li>
<li>Nginx</li>
<li>Travis CI</li>
</ul>
</td>
        <td width="70%" align="center"><img src="https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/structure.png"></td>
    </tr>
</table>


## ☂ERD



![eweather](https://eweather-bucket.s3.ap-northeast-2.amazonaws.com/share/readme/erd2.png)


## ☃API 문서


<b> <h3>[링크](http://ewhaweather.com/v2/api-docs) </h3></b>

## 🌜폴더 구조



```jsx
├── README.md
├── build.gradle
├── gradle
├── gradlew
├── gradlew.bat
├── media
│   └── uploads
├── settings
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── efub
    │   │       └── team4
    │   │           └── backend_eweather
    │   │               ├── BackendEWeatherApplication.java
    │   │               ├── domain
    │   │               │   ├── auth
    │   │               │   │   ├── controller
    │   │               │   │   │   └── AuthController.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── UserRefreshToken.java
    │   │               │   │   └── repository
    │   │               │   │       └── UserRefreshTokenRepository.java
    │   │               │   ├── bear
    │   │               │   │   ├── controller
    │   │               │   │   │   └── BearController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── BearDto.java
    │   │               │   │   │   ├── BearImageResponseDto.java
    │   │               │   │   │   └── BearMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Bear.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── BearAlreadyExistsException.java
    │   │               │   │   │   └── BearNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── BearRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── BearService.java
    │   │               │   ├── calendar
    │   │               │   │   ├── controller
    │   │               │   │   │   └── CalendarController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── CalendarDto.java
    │   │               │   │   │   └── CalendarMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Calendar.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── CalendarAlreadyExistsException.java
    │   │               │   │   │   └── CalendarNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── CalendarRepository.java
    │   │               │   │   ├── service
    │   │               │   │   │   └── CalendarService.java
    │   │               │   │   └── specification
    │   │               │   │       ├── CalendarSearchCriteria.java
    │   │               │   │       └── CalendarSpecification.java
    │   │               │   ├── dayNight
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── DayNightDto.java
    │   │               │   │   │   └── DayNightMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── DayNight.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── DayNightAlreadyExistsException.java
    │   │               │   │   │   └── DayNightNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── DayNightRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── DayNightService.java
    │   │               │   ├── eweather
    │   │               │   │   ├── controller
    │   │               │   │   │   └── EweatherController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── EweatherDto.java
    │   │               │   │   │   └── EweatherMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Eweather.java
    │   │               │   │   └── service
    │   │               │   │       └── EweatherService.java
    │   │               │   ├── hello
    │   │               │   │   └── controller
    │   │               │   │       └── HelloController.java
    │   │               │   ├── icon
    │   │               │   │   ├── controller
    │   │               │   │   │   └── IconController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── IconDto.java
    │   │               │   │   │   └── IconMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Icon.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── IconAlreadyExistsException.java
    │   │               │   │   │   └── IconNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── IconRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── IconService.java
    │   │               │   ├── item
    │   │               │   │   ├── controller
    │   │               │   │   └── entity
    │   │               │   │       └── Item.java
    │   │               │   ├── media
    │   │               │   │   ├── controller
    │   │               │   │   │   └── FileUploadController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── UploadedFileDto.java
    │   │               │   │   │   └── UploadedFileMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── UploadedFile.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── S3BucketNotAvailableException.java
    │   │               │   │   │   ├── UploadedFileInvalidException.java
    │   │               │   │   │   └── UploadedFileNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── UploadedFileRepository.java
    │   │               │   │   └── service
    │   │               │   │       ├── FileHandler.java
    │   │               │   │       ├── S3Service.java
    │   │               │   │       └── UploadedFileService.java
    │   │               │   ├── profile
    │   │               │   │   ├── controller
    │   │               │   │   │   └── UserProfileController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── ProfileDto.java
    │   │               │   │   │   └── ProfileMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Profile.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── ProfileAlreadyExistsException.java
    │   │               │   │   │   └── ProfileNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── ProfileRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── ProfileService.java
    │   │               │   ├── pty
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── PtyDto.java
    │   │               │   │   │   └── PtyMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Pty.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── PtyAlreadyExistsException.java
    │   │               │   │   │   └── PtyNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── PtyRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── PtyService.java
    │   │               │   ├── season
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── SeasonDto.java
    │   │               │   │   │   └── SeasonMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Season.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── SeasonAlreadyExistsException.java
    │   │               │   │   │   └── SeasonNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── SeasonRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── SeasonService.java
    │   │               │   ├── sky
    │   │               │   │   ├── controller
    │   │               │   │   │   └── SkyController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── SkyDto.java
    │   │               │   │   │   └── SkyMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Sky.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── SkyAlreadyExistsException.java
    │   │               │   │   │   └── SkyNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── SkyRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── SkyService.java
    │   │               │   ├── temperature
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── TemperatureDto.java
    │   │               │   │   │   └── TemperatureMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── Temperature.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── TemperatureAlreadyExistsException.java
    │   │               │   │   │   └── TemperatureNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── TemperatureRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── TemperatureService.java
    │   │               │   ├── user
    │   │               │   │   ├── controller
    │   │               │   │   │   └── UserController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── UserResponseDto.java
    │   │               │   │   ├── entity
    │   │               │   │   │   └── User.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── UserRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── UserService.java
    │   │               │   ├── vote
    │   │               │   │   ├── controller
    │   │               │   │   │   └── VotePostsController.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── VoteRequestDto.java
    │   │               │   │   │   ├── VoteResponseDto.java
    │   │               │   │   │   ├── VoteUpdateRequestDto.java
    │   │               │   │   │   ├── VotesDto.java
    │   │               │   │   │   └── VotesMapper.java
    │   │               │   │   ├── entity
    │   │               │   │   │   ├── VotePosts.java
    │   │               │   │   │   └── Votes.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── VoteAlreadyExistsException.java
    │   │               │   │   │   └── VoteNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   ├── VotePostsRespsitory.java
    │   │               │   │   │   └── VotesRepository.java
    │   │               │   │   ├── service
    │   │               │   │   │   ├── VotePostsService.java
    │   │               │   │   │   └── VoteService.java
    │   │               │   │   └── specification
    │   │               │   │       ├── VoteSearchCriteria.java
    │   │               │   │       └── VotesSpecification.java
    │   │               │   └── weather
    │   │               │       ├── controller
    │   │               │       │   └── OpenWeatherApiController.java
    │   │               │       ├── dto
    │   │               │       │   ├── BearResponseDto.java
    │   │               │       │   ├── CalendarWeatherResponseDto.java
    │   │               │       │   ├── CurrentWeatherResponseDto.java
    │   │               │       │   ├── ForcastResponseDto.java
    │   │               │       │   ├── OpenWeatherResponseDto.java
    │   │               │       │   └── WeatherResponseDto.java
    │   │               │       └── service
    │   │               │           └── OpenWeatherAPI.java
    │   │               └── global
    │   │                   ├── config
    │   │                   │   ├── CORSconfig.java
    │   │                   │   ├── S3Config.java
    │   │                   │   ├── SecurityConfig.java
    │   │                   │   ├── SetupDataLoader.java
    │   │                   │   ├── SpringFoxConfig.java
    │   │                   │   ├── WebConfig.java
    │   │                   │   ├── auth
    │   │                   │   │   ├── CurrentUser.java
    │   │                   │   ├── properties
    │   │                   │   │   ├── AppProperties.java
    │   │                   │   │   └── CorsProperties.java
    │   │                   │   └── security
    │   │                   │       └── JwtConfig.java
    │   │                   ├── dto
    │   │                   │   ├── ApiResponse.java
    │   │                   │   ├── ApiResponseHeader.java
    │   │                   │   └── DeletedEntityIdResponseDto.java
    │   │                   ├── entity
    │   │                   │   └── BaseTimeEntity.java
    │   │                   ├── exception
    │   │                   │   ├── ErrorCode.java
    │   │                   │   └── ErrorResponse.java
    │   │                   ├── outh
    │   │                   │   ├── entity
    │   │                   │   │   ├── ProviderType.java
    │   │                   │   │   ├── RoleType.java
    │   │                   │   │   └── UserPrincipal.java
    │   │                   │   ├── exception
    │   │                   │   │   ├── GlobalExceptionHandler.java
    │   │                   │   │   ├── InvalidUserRoleException.java
    │   │                   │   │   ├── OAuthProviderMissMatchException.java
    │   │                   │   │   ├── RestAuthenticationEntryPoint.java
    │   │                   │   │   └── TokenValidFailedException.java
    │   │                   │   ├── filter
    │   │                   │   │   └── TokenAuthenticationFilter.java
    │   │                   │   ├── handler
    │   │                   │   │   ├── OAuth2AuthenticationFailureHandler.java
    │   │                   │   │   ├── OAuth2AuthenticationSuccessHandler.java
    │   │                   │   │   └── TokenAccessDeniedHandler.java
    │   │                   │   ├── info
    │   │                   │   │   ├── OAuth2UserInfo.java
    │   │                   │   │   ├── OAuth2UserInfoFactory.java
    │   │                   │   │   └── impl
    │   │                   │   │       └── GoogleOAuth2UserInfo.java
    │   │                   │   ├── repository
    │   │                   │   │   └── OAuth2AuthorizationRequestBasedOnCookieRepository.java
    │   │                   │   ├── service
    │   │                   │   │   ├── CustomOAuth2UserService.java
    │   │                   │   │   └── CustomUserDetailsService.java
    │   │                   │   └── token
    │   │                   │       ├── AuthToken.java
    │   │                   │       └── AuthTokenProvider.java
    │   │                   └── util
    │   │                       ├── CookieUtil.java
    │   │                       ├── FileUtil.java
    │   │                       ├── HeaderUtil.java
    │   │                       └── TimeUtil.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── efub
                └── team4
                    └── backend_eweather
                        └── BackendEWeatherApplicationTests.java
```
