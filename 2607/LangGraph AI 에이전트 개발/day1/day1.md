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



#### 메시지 종류

- systemmessage - ai에게 주는 지침
- humanmessage - 사용자 질문 또는 요청
- LLM에선 위의 2개를 구분해서 받아들이지는 않음.(통째로 프롬프트 그대로 받아들임) 다만 사용자가 관리 용이하기 위해 구분(시스템-핵심 기본 요소, 휴먼 - 실제 질의)
- aimessage - ai 응답
- toolmessage



#### 프롬프트 엔지니어링

- GPT에게 정확하고 유용한 응답을 얻기 위해 질문(프롬프트)을 설계하는 기술



#### 생성형 AI의 답변 확률 분포에 영향을 줄 수 있는 방법

- 프롬프트 엔지니어링
- 기본 구조
  - 역할 부여 - “넌 제조 분야 데이터 분석 전문가야"
  - 내용 구성(맥락(context) 및 목적(goal) 포함 - 지침) - “2024년 영업 데이터를 바탕으로… 간결한 요약 보고서를 작성해줘”
  - 답변 형식 지정 - “항목별 bullet -point 로 정리해줘”

- 마크다운 문법 권장

```markdown
# 역할
너는데이터분석전문가다.
# 요청사항
-2026년 수요데이터를기반으로주요인사이트를도출해
-의사결정을위한간결한요약보고서작성해
# 출력형식
-핵심인사이트를항목별bullet point 로 정리해
-각 항목은1~2문장으로간결하게작성해
```

- system message - 역할 부여, 지침, 출력형식 지정

- human message - 요청사항(질문)

- 예시

- ```python
  sys_msg = '''
  # 역할
  너는 입력하는 도시와 여행 기간을 입력 받아 여행 계획을 수립하는 여행 플래너야
  # 지침
  - 도시에서 유명한 관광지를 먼저 찾아
  - 여행기간을 감안하여 일정을 수립해
  # 출력 형식
  - bullet point 형식
  - 일정별 오전, 오후로 나눠서 간결하게 설명
  '''
  human_msg = '''
  # 여행 도시 : 부산
  # 여행 기간 : 2박3일
  '''
  result = chat.invoke([SystemMessage(sys_msg), HumanMessage(human_msg)])
  print(result.content)
  ```



- 온도값에 따라 결과 도출이 다름(FAQ 같은 봇이면 0이 좋을듯)



- LLM 호출 후 출력의 type
- LLM의 답변은 기본적으로 문자열(string)
- 리스트 - [값, 값, 값]
- 딕셔너리 - {키:값, 키:값, 키:값}
- ast.literal_eval() - 문자열 형태의 Python dict/list를 실제 객체로 변환
- few-shot learning



## AI agent 기본 구조

- LLM은 사용자의 입력을 받아 응답을 생성 
- 필요시, 
- 외부에서정보를검색하거나(Retrieval)
- • 툴을사용하거나(Tool)
-  • 메모리를읽고쓰며(Memory)
-  • 결과를종합해서응답을생성



#### Agent 주요 구성 요소

- Node, Edge, State, Conditional Edge
- Node - 일처리 단위
- Edge - 각 노드 간 연결(단방향)
- State - 데이터 흐름 구조(Edge의 연결 구조 전체)
- Conditional Edge - 각 노드에서 분기에 따라 다른 노드로 나뉘어져 가는 Edge



#### Agent 구축 절차

- State 정의 및 LLM 준비
- 노드 준비 - def
- 그래프 구성 - ai agent, work flow(흐름, 비즈니스 프로세스)



#### State 정의 및 LLM 준비

![image-20260722113625584](C:\Users\GD\Desktop\새 폴더\TIL\2607\LangGraph AI 에이전트 개발\day1\day1.assets\image-20260722113625584.png)



#### 노드 준비

##### 노드의 기본 구조(가능한 외우는걸 추천)

1. State에서 필요한 정보만 뽑아서 저장하기
2. prompt 구성
3. LLM 호출
4. State 업데이트

![image-20260722113644535](C:\Users\GD\Desktop\새 폴더\TIL\2607\LangGraph AI 에이전트 개발\day1\day1.assets\image-20260722113644535.png)

![image-20260722113655475](C:\Users\GD\Desktop\새 폴더\TIL\2607\LangGraph AI 에이전트 개발\day1\day1.assets\image-20260722113655475.png)



#### 그래프 구성

- 그래프는 Start와 End로 구성 (그 사이에 로직 구성)

1. 그래프 초기화(StateGraph)
2. Node 추가(add_node)
3. 노드 연결(add_edge)
4. 컴파일

![image-20260722113703234](C:\Users\GD\Desktop\새 폴더\TIL\2607\LangGraph AI 에이전트 개발\day1\day1.assets\image-20260722113703234.png)



- 메모리, 벡터db, 툴
- 과거의 자동화 - 사람이 해야할 작업 전까지만 자동화 -> 사람이 작업 후 후작업을 자동화
- 현재의 자동화 - 사람이 작업해야할 부분 마저도 AI 에이전트로 대체
- AI Hr(AI에이전트 관리)



- MessagesState의 경우 messages의 경우 기본값이 append라서 문의 응답이 반복될수록 계속 쌓임(업데이트가 아님)(response는 최신값만 업데이트)



#### State

- Agent에서의 State
- State는 공유 메모리가 아니라 합의된 데이터 계약 (각 서랍장 안에 넣을 데이터는 미리 결정되어 있음)
- 각 노드의 입력과 출력 : State
  - 입력 : State가 그대로 입력됨
  - 출력 : State에 필요한 키만 업데이트
- State 키 업데이트 방식
  - 덮어쓰기(Overwrite) : 기존 값을 덮어 씀
  - 쌓기(Reducer) : 리스트 형식, 값을 추가해감 (messages)



## AI Agent 기본 패턴

- 순차흐름 - 단방향
- 라우팅 - 특정 입력에 따라 경로 선택 (분기 등)
- 반복루프 - 조건에 따라 반복 수행 (특정 조건일때만 반복), 무한반복 방지가 중요(횟수 제한)
- 항상 출력에서 다른 말 하지 말도록 유도하는게 중요..
- ㅣ



## AI Agent 도구와 메모리

- Tool - Agent가 외부 기능을 사용하기 위한 기능(계산기, 웹 검색, DB조회, 날씨 확인 등)
- mcp - 도구모음
- 외부 도구 - 구글, 아카이브(arxiv), 노션, git 등등