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
## 🐣 Postman 실행 결과
**1. 키워드로 상품검색 -> 결과를 목록으로 보여줌**
   [
   {
   "title": "Apple <b>맥북</b> 에어 2020년형 M1 256G 스페이스 그레이 (MGN63KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=26101847522",
   "image": "https://shopping-phinf.pstatic.net/main_2610184/26101847522.20220705135838.jpg",
   "lprice": 1181490
   },
   {
   "title": "Apple <b>맥북</b> 에어 2022년 M2 8코어 CPU 및 8코어 GPU 256G 미드나이트 (MLY33KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=33092527619",
   "image": "https://shopping-phinf.pstatic.net/main_3309252/33092527619.20220705135254.jpg",
   "lprice": 1468000
   },
   {
   "title": "Apple <b>맥북</b> 프로 14형 2021년 M1 Pro 8코어 CPU 및 14코어 GPU 512G 실버 (MKGR3KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=29413009625",
   "image": "https://shopping-phinf.pstatic.net/main_2941300/29413009625.20220705151907.jpg",
   "lprice": 2476800
   },
   {
   "title": "Apple <b>맥북</b> 에어 2020년형 M1 256G 실버 (MGN93KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=25039820524",
   "image": "https://shopping-phinf.pstatic.net/main_2503982/25039820524.20220705135902.jpg",
   "lprice": 1181500
   },
   {
   "title": "Apple <b>맥북</b> 프로 13 2022년 M2 8코어 CPU 및 10코어 GPU 256G 스페이스 그레이 (MNEH3KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=33091369619",
   "image": "https://shopping-phinf.pstatic.net/main_3309136/33091369619.20220705140153.jpg",
   "lprice": 1582390
   },
   {
   "title": "Apple <b>맥북</b> 프로 14형 2021년 M1 Pro 8코어 CPU 및 14코어 GPU 512G 스페이스 그레이 (MKGP3KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=29412775622",
   "image": "https://shopping-phinf.pstatic.net/main_2941277/29412775622.20220705151945.jpg",
   "lprice": 2440440
   },
   {
   "title": "Apple <b>맥북</b> 에어 2022년 M2 8코어 CPU 및 8코어 GPU 256G 스페이스 그레이 (MLXW3KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=33094298618",
   "image": "https://shopping-phinf.pstatic.net/main_3309429/33094298618.20220705135406.jpg",
   "lprice": 1462800
   },
   {
   "title": "Apple <b>맥북</b> 프로 16형 2021년 M1 Pro 10코어 CPU 및 16코어 GPU 512G 스페이스 그레이 (MK183KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=29413184619",
   "image": "https://shopping-phinf.pstatic.net/main_2941318/29413184619.20220705152113.jpg",
   "lprice": 2797000
   },
   {
   "title": "애플 <b>맥북</b> 에어 렌트 2020년형 영상편집 M1 256G 실버 60개월의무 추천",
   "link": "https://search.shopping.naver.com/gate.nhn?id=35585396163",
   "image": "https://shopping-phinf.pstatic.net/main_3558539/35585396163.jpg",
   "lprice": 3120000
   },
   {
   "title": "Apple <b>맥북</b> 프로 13 2022년 M2 8코어 CPU 및 10코어 GPU 256G 실버 (MNEP3KH/A)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=33091995619",
   "image": "https://shopping-phinf.pstatic.net/main_3309199/33091995619.20220705140226.jpg",
   "lprice": 1540360
   },
   {
   "title": "추천 애플 <b>맥북</b> 대여 에어 2020년형 초경량 M1 256G 골드 60개월의무",
   "link": "https://search.shopping.naver.com/gate.nhn?id=35856835129",
   "image": "https://shopping-phinf.pstatic.net/main_3585683/35856835129.jpg",
   "lprice": 3120000
   },
   {
   "title": "Apple <b>맥북</b> 에어 13형 2020년 M1 CTO 256GB (16GB RAM)",
   "link": "https://search.shopping.naver.com/gate.nhn?id=26380658523",
   "image": "https://shopping-phinf.pstatic.net/main_2638065/26380658523.20220705161232.jpg",
   "lprice": 1517530
   },
   {
   "title": "애플 대여 <b>맥북</b> 에어 2020년형 M1 256G 고사양 스페이스 그레이 60개월의무 할인",
   "link": "https://search.shopping.naver.com/gate.nhn?id=35881416112",
   "image": "https://shopping-phinf.pstatic.net/main_3588141/35881416112.jpg",
   "lprice": 3120000
   },
   {
   "title": "고사양 애플 <b>맥북</b> 장기 에어 2022년 기획전 M2 8코어CPU 8코어 GPU 256G 미드나이트 60개월의무",
   "link": "https://search.shopping.naver.com/gate.nhn?id=35606277021",
   "image": "https://shopping-phinf.pstatic.net/main_3560627/35606277021.jpg",
   "lprice": 3780000
   },
   {
   "title": "애플 영상편집 <b>맥북</b> 에어 2020년형 M1 256G 게이밍 골드 60개월의무 사무실",
   "link": "https://search.shopping.naver.com/gate.nhn?id=35629179534",
   "image": "https://shopping-phinf.pstatic.net/main_3562917/35629179534.jpg",
   "lprice": 3120000
   }
   ]