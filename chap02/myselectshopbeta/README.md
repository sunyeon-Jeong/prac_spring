# MySelectShopBeta
- - -
## 🐱 myselectshop 서비스 소개
1. 키워드로 상품검색 -> 결과를 목록으로 보여줌
2. 관심상품 등록하기
3. 관심상품 조회하기
4. 관심상품 최저가 등록하기
- - -
## 🐰 상품검색 API 동작순서 정리
![img.png](img.png)
- - -
## 🦄 API 명세서
|기능|Method|URL|Request|Response|
|---|---|---|---|---|
|메인페이지|`GET`|/api/shop|-|index.html|
|키워드로상품검색->결과목록으로보여주기|`GET`|/api/search?query=검색어|-|[<br>{<br>"title":String,<br>"link":String,<br>"image":String,<br>"lprice":int<br>},<br>...<br>]|
|관심상품 등록하기|`POST`|/api/products|{<br>"title":String,<br>"link":String,<br>"image":String,<br>"lprice":int<br>}|{<br>"id":Long,<br>"title":String,<br>"link":String,<br>"image":String,<br>"lprice":int,<br>"myprice":int<br>}|
|관심상품 조회하기|`GET`|/api/products|-|[<br>{<br>"id":Long,<br>"title":String,<br>"link":String,<br>"image":String,<br>"lprice":int,<br>"myprice":int<br>},<br>...<br>]|
|관심상품 최저가 등록하기|PUT|/apiproducts/{id}|{<br>"myprice":int<br>}|id|
- - -