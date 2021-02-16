# java-lotto
로또 미션 진행을 위한 저장소

## 요구 사항 정의
- 구입금액을 입력받는다.
  
- 복권의 개수를 구한다.
- 복권의 개수만큼 로또 번호를 생성한다.
   
- 지난 주 당첨 번호를 입력받는다.
- 보너스볼을 입력받는다.
  
- 당첨 통계를 구한다.

### LottoManager
- 구입금액에 따른 복권 개수를 구한다.
- 복권의 개수만큼 로또 번호를 생성한다.

### Lotto
- 6자리 로또 번호를 갖는다.

### LottoGenerator
- 로또 번호를 생성한다.

### WinningNumber
- 지난 주 당첨 번호.
- 보너스 볼.

### WinningCalculator
- 통계를 구한다.

---
### Commit Convention
- 커밋 메시지 언어 : 한글
- feat : 기능 추가.
- refactor : 구조 개선.
- fix : 에러가 나는 부분 해결.
- docs : document 파일 관련.
- test : 테스트 코드만 바꿀 때 사용.
- style : 들여쓰기 수정, 변수명 및 메소드명 수정.
- chore : 그 외.