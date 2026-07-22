# LangGraph AI 에이전트 개발_1일차



- https://docs.google.com/spreadsheets/d/1sXKP8Q-BASQtxVi0FqXiwDEpMnt0QxFHJIY4WzN6LM4/edit?gid=0#gid=0
- https://bit.ly/li_ai_agent

- https://forms.gle/juDAEZGjvXYsvEiM8 사전 설문

- https://drive.google.com/drive/folders/1HhglsixyaErjxyQKRI5SaTtwczn_WssJ?usp=sharing

- 한기영 강사 - hanky74@datainsight.biz



### LLM의 중요한 특징

- 질문 -> 수학적 연산 -> 확률 분포 -> 답변 생성 -> 최종 답변
- 확률분포에서 답변을 뽑기 때문에 질문에 따른 어떤 확률분포를 만드냐에 따라 답변이 달라질 수 있음
- AI 에이전트로서 사용하려고 하기 때문에(잘못된 결과 도출 방지) 가능한 내가 원하는 확률분포로 생성하도록 유도하는것이 중요함
- 해당 확률분포를 유도하기 위해 프롬프트 엔지니어링(구체적인 질문 -> 구체적인 답변)이 등장함
- LLM의 단어 생성 로직 - 내부적으로 각 단어 후보마다 로짓(logits)을 계산한 뒤, 소프트맥스 함수를 통해 온도값 계산(확률?)
- 온도값(temperature) - LLM의 확률분포 도출 설정값
  - 확률 분포 자체를 날카롭게(0) 혹은 넓게(1+) 조절
  - 낮은 값(0) - 항상 확률 높은 단어를 고름 -> 일관되고 예측 가능한 결과
  - 높은 값(1) - 낮은 확률 단어도 고를 가능성 있음 -> 창의적이지만 불안정한 결과



- LLM 파일 구글드라이브 저장 후 colab으로 저장(안되어있으면 colab 설치)
- gpt-4.1-mini - 가성비 갑 추천(싸면서 성능 나쁘지 않음)
- 