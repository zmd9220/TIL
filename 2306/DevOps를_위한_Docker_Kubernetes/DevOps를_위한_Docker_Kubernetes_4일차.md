# DevOps를_위한_Docker_Kubernetes_4일차



## 오전



### Controller 이어서



##### 시작전

```bash
vagrant@kube-control1:~/work$ vim ~/.vimrc

# ~/.vimrc
syntax on
autocmd FileType yaml setlocal ts=2 sts=2 sw=2 expandtab (autoindent 제거)
```





```bash
vagrant@kube-control1:~/work$ kubectl get all -n kube-system
NAME                                           READY   STATUS    RESTARTS        AGE
pod/calico-kube-controllers-64cc74d646-jfnhc   1/1     Running   2 (2m52s ago)   19h
pod/calico-node-hz2wh                          1/1     Running   1 (2m25s ago)   19h
pod/calico-node-s8wf9                          1/1     Running   1 (2m40s ago)   19h
pod/calico-node-xbvwf                          1/1     Running   1 (2m30s ago)   19h
pod/calico-node-xqsjk                          1/1     Running   2 (2m52s ago)   19h
pod/coredns-64897985d-cnlf7                    1/1     Running   2 (2m52s ago)   19h
pod/coredns-64897985d-f8znk                    1/1     Running   2 (2m52s ago)   19h
pod/etcd-kube-control1                         1/1     Running   2 (2m52s ago)   19h
pod/kube-apiserver-kube-control1               1/1     Running   2 (2m52s ago)   19h
pod/kube-controller-manager-kube-control1      1/1     Running   2 (2m52s ago)   19h
pod/kube-proxy-m9q2x                           1/1     Running   2 (2m52s ago)   19h
pod/kube-proxy-mh8vq                           1/1     Running   2 (2m25s ago)   19h
pod/kube-proxy-vmdbg                           1/1     Running   2 (2m30s ago)   19h
pod/kube-proxy-xfg2j                           1/1     Running   2 (2m40s ago)   19h
pod/kube-scheduler-kube-control1               1/1     Running   2 (2m52s ago)   19h

NAME               TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)                  AGE
service/kube-dns   ClusterIP   10.96.0.10   <none>        53/UDP,53/TCP,9153/TCP   19h

NAME                         DESIRED   CURRENT   READY   UP-TO-DATE   AVAILABLE   NODE SELECTOR            AGE
daemonset.apps/calico-node   4         4         4       4            4           kubernetes.io/os=linux   19h
daemonset.apps/kube-proxy    4         4         4       4            4           kubernetes.io/os=linux   19h

NAME                                      READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/calico-kube-controllers   1/1     1            1           19h
deployment.apps/coredns                   2/2     2            2           19h

NAME                                                 DESIRED   CURRENT   READY   AGE
replicaset.apps/calico-kube-controllers-64cc74d646   1         1         1       19h
replicaset.apps/coredns-64897985d                    2         2         2       19h
```



#### Deployment : Replicaset, 파드의 배포를 관리하는 컨트롤러

> 기본 내역은 Replicaset + 배포 관련 기능을 추가한 컨트롤러

##### Deployment Strategy

- RollingUpdate : 버전 업데이트 시 일부의 파드를 부분적으로 업데이트를 진행하여 새로운 버전으로 전환하는 배포 전략으로 다운타임 없이 업데이트가 가능하며 다만 업데이트 중에는 기존버전과 신규 버전이 공존하는 시간이 존재한다. (파드를 하나 하나씩 업데이트 진행, 기존 버전과 신규 버전이 공존하는 과도기 존재, 대신 서비스 중단이 없음)
- Recreate : 버전 업데이트 시 기존 버전을 모두 폐기한 후 새로운 버전의 파드를 실행하여 업데이트 하는 방식으로 다운타임이 존재함. (모두 종료 후 업데이트 후 모두 실행 방식으로 모두 종료 ~ 업데이트 후 실행 사이에 서비스 불가)



#### [Deployment Rollback 관련 실습]

```bash
$ kubectl delete deployment my-nginx-deployment
deployment.apps "my-nginx-deployment" deleted
 
$ kubectl create -f deployment-nginx.yaml --record
deployment.apps/my-nginx-deployment created

$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS    RESTARTS   AGE
pod/my-nginx-deployment-7484748b57-8k5wj   1/1     Running   0          13s
pod/my-nginx-deployment-7484748b57-9d6br   1/1     Running   0          13s
pod/my-nginx-deployment-7484748b57-tjnkf   1/1     Running   0          13s

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-7484748b57   3         3         3       13s

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           13s

$ kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record
deployment.apps/my-nginx-deployment image updated

$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS              RESTARTS   AGE
pod/my-nginx-deployment-6cc6fd4885-db7dn   0/1     ContainerCreating   0          1s
pod/my-nginx-deployment-6cc6fd4885-h4svr   1/1     Running             0          6s
pod/my-nginx-deployment-7484748b57-8k5wj   1/1     Running             0          2m
pod/my-nginx-deployment-7484748b57-9d6br   1/1     Running             0          2m
pod/my-nginx-deployment-7484748b57-tjnkf   0/1     Terminating         0          2m

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-6cc6fd4885   2         2         1       6s
replicaset.apps/my-nginx-deployment-7484748b57   2         2         2       2m

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     2            3           2m
$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS    RESTARTS   AGE
pod/my-nginx-deployment-6cc6fd4885-db7dn   1/1     Running   0          24s
pod/my-nginx-deployment-6cc6fd4885-h4svr   1/1     Running   0          29s
pod/my-nginx-deployment-6cc6fd4885-mwrvt   1/1     Running   0          19s

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-6cc6fd4885   3         3         3       29s
replicaset.apps/my-nginx-deployment-7484748b57   0         0         0       2m23s

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           2m23s

$ kubectl describe deployment my-nginx-deployment
Name:                   my-nginx-deployment
Namespace:              default
CreationTimestamp:      Fri, 28 Jan 2022 01:54:14 +0000
Labels:                 <none>
Annotations:            deployment.kubernetes.io/revision: 2
                        kubernetes.io/change-cause: kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record=true
Selector:               app=my-nginx
Replicas:               3 desired | 3 updated | 3 total | 3 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge
Pod Template:
  Labels:  app=my-nginx
  Containers:
   nginx:
    Image:        nginx:1.21
    Port:         80/TCP
    Host Port:    0/TCP
    Environment:  <none>
    Mounts:       <none>
  Volumes:        <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  <none>
NewReplicaSet:   my-nginx-deployment-6cc6fd4885 (3/3 replicas created)
Events:
  Type    Reason             Age    From                   Message
  ----    ------             ----   ----                   -------
  Normal  ScalingReplicaSet  3m33s  deployment-controller  Scaled up replica set my-nginx-deployment-7484748b57 to 3
  Normal  ScalingReplicaSet  99s    deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 1
  Normal  ScalingReplicaSet  94s    deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 2
  Normal  ScalingReplicaSet  94s    deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 2
  Normal  ScalingReplicaSet  89s    deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 1
  Normal  ScalingReplicaSet  89s    deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 3
  Normal  ScalingReplicaSet  84s    deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 0
$ 
$ kubectl rollout history deployment my-nginx-deployment
deployment.apps/my-nginx-deployment 
REVISION  CHANGE-CAUSE
1         kubectl create --filename=deployment-nginx.yaml --record=true
2         kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record=true

$ 
$ kubectl rollout undo deployment my-nginx-deployment --to-revision=1
deployment.apps/my-nginx-deployment rolled back
$ 
$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS        RESTARTS   AGE
pod/my-nginx-deployment-6cc6fd4885-mwrvt   0/1     Terminating   0          4m28s
pod/my-nginx-deployment-7484748b57-f4n6l   1/1     Running       0          14s
pod/my-nginx-deployment-7484748b57-s8njz   1/1     Running       0          17s
pod/my-nginx-deployment-7484748b57-sz9gh   1/1     Running       0          12s

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-6cc6fd4885   0         0         0       4m38s
replicaset.apps/my-nginx-deployment-7484748b57   3         3         3       6m32s

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           6m32s
$ 
$ kubectl get replicasets --show-labels
NAME                             DESIRED   CURRENT   READY   AGE     LABELS
my-nginx-deployment-6cc6fd4885   0         0         0       5m40s   app=my-nginx,pod-template-hash=6cc6fd4885
my-nginx-deployment-7484748b57   3         3         3       7m34s   app=my-nginx,pod-template-hash=7484748b57

$ kubectl describe deployment my-nginx-deployment
Name:                   my-nginx-deployment
Namespace:              default
CreationTimestamp:      Fri, 28 Jan 2022 01:54:14 +0000
Labels:                 <none>
Annotations:            deployment.kubernetes.io/revision: 3
                        kubernetes.io/change-cause: kubectl create --filename=deployment-nginx.yaml --record=true
Selector:               app=my-nginx
Replicas:               3 desired | 3 updated | 3 total | 3 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge
Pod Template:
  Labels:  app=my-nginx
  Containers:
   nginx:
    Image:        nginx:1.10
    Port:         80/TCP
    Host Port:    0/TCP
    Environment:  <none>
    Mounts:       <none>
  Volumes:        <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  <none>
NewReplicaSet:   my-nginx-deployment-7484748b57 (3/3 replicas created)
Events:
  Type    Reason             Age                   From                   Message
  ----    ------             ----                  ----                   -------
  Normal  ScalingReplicaSet  6m35s                 deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 1
  Normal  ScalingReplicaSet  6m30s                 deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 2
  Normal  ScalingReplicaSet  6m30s                 deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 2
  Normal  ScalingReplicaSet  6m25s                 deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 1
  Normal  ScalingReplicaSet  6m25s                 deployment-controller  Scaled up replica set my-nginx-deployment-6cc6fd4885 to 3
  Normal  ScalingReplicaSet  6m20s                 deployment-controller  Scaled down replica set my-nginx-deployment-7484748b57 to 0
  Normal  ScalingReplicaSet  2m14s                 deployment-controller  Scaled up replica set my-nginx-deployment-7484748b57 to 1
  Normal  ScalingReplicaSet  2m11s                 deployment-controller  Scaled down replica set my-nginx-deployment-6cc6fd4885 to 2
  Normal  ScalingReplicaSet  2m9s (x2 over 8m29s)  deployment-controller  Scaled up replica set my-nginx-deployment-7484748b57 to 3
  Normal  ScalingReplicaSet  2m7s (x3 over 2m11s)  deployment-controller  (combined from similar events): Scaled down replica set my-nginx-deployment-6cc6fd4885 to 0
```



##### 실습

- [start-docker-kubernetes/chapter6/deployment-nginx.yaml at master · alicek106/start-docker-kubernetes · GitHub](https://github.com/alicek106/start-docker-kubernetes/blob/master/chapter6/deployment-nginx.yaml)

```yaml
# deployment-nginx.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-nginx-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-nginx
  template: # pod 정의 부분
    metadata:
      name: my-nginx-pod
      labels:
        app: my-nginx # matchLabels 과 동일해야 생성이 됨
    spec:
      containers:
      - name: nginx
        image: nginx:1.10
        ports:
        - containerPort: 80
```



```bash
# deployment 생성
vagrant@kube-control1:~/work$ kubectl create -f deployment-nginx.yaml --record
Flag --record has been deprecated, --record will be removed in the future
deployment.apps/my-nginx-deployment created
vagrant@kube-control1:~/work$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS    RESTARTS      AGE
pod/my-nginx-deployment-6b4b7f7cdc-gt6vn   1/1     Running   0             8m41s
pod/my-nginx-deployment-6b4b7f7cdc-lkwxn   1/1     Running   0             8m41s
pod/my-nginx-deployment-6b4b7f7cdc-tqtbv   1/1     Running   0             8m41s
pod/replicaset-nginx-4qh5v                 1/1     Running   1 (45m ago)   16h
pod/replicaset-nginx-v9cmz                 1/1     Running   1 (45m ago)   16h
pod/replicaset-nginx-w8xps                 1/1     Running   1 (45m ago)   16h

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-6b4b7f7cdc   3         3         3       8m41s
replicaset.apps/replicaset-nginx                 3         3         3       16h

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           8m41s

# 이미지 업데이트 -> pod 교체됨
vagrant@kube-control1:~/work$ kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record
Flag --record has been deprecated, --record will be removed in the future
deployment.apps/my-nginx-deployment image updated
vagrant@kube-control1:~/work$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS              RESTARTS      AGE
pod/my-nginx-deployment-5cdb4bc675-88xn2   1/1     Running             0             62s
pod/my-nginx-deployment-5cdb4bc675-p2j2z   1/1     Running             0             108s
pod/my-nginx-deployment-5cdb4bc675-x66bf   0/1     ContainerCreating   0             17s
pod/my-nginx-deployment-6b4b7f7cdc-lkwxn   1/1     Running             0             13m
pod/replicaset-nginx-4qh5v                 1/1     Running             1 (50m ago)   16h
pod/replicaset-nginx-v9cmz                 1/1     Running             1 (50m ago)   16h
pod/replicaset-nginx-w8xps                 1/1     Running             1 (49m ago)   16h

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-5cdb4bc675   3         3         2       108s
replicaset.apps/my-nginx-deployment-6b4b7f7cdc   1         1         1       13m
replicaset.apps/replicaset-nginx                 3         3         3       16h

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           13m

vagrant@kube-control1:~/work$ kubectl describe deployment my-nginx-deployment
Name:                   my-nginx-deployment
Namespace:              default
CreationTimestamp:      Thu, 22 Jun 2023 00:43:04 +0000
Labels:                 <none>
Annotations:            deployment.kubernetes.io/revision: 2
                        kubernetes.io/change-cause: kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record=true
Selector:               app=my-nginx
Replicas:               3 desired | 3 updated | 3 total | 3 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge # 업데이트 당시에 비활성화 할 pod 비율, 중간에 추가로 만들어질 pod 비율 (pod 3->4(구3 신1)->3(구2 신1) 이런식)
Pod Template:
  Labels:  app=my-nginx
  Containers:
   nginx:
    Image:        nginx:1.21
    Port:         80/TCP
    Host Port:    0/TCP
    Environment:  <none>
    Mounts:       <none>
  Volumes:        <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  <none>
NewReplicaSet:   my-nginx-deployment-5cdb4bc675 (3/3 replicas created)
Events:
  Type    Reason             Age   From                   Message
  ----    ------             ----  ----                   -------
  Normal  ScalingReplicaSet  39m   deployment-controller  Scaled up replica set my-nginx-deployment-6b4b7f7cdc to 3
  Normal  ScalingReplicaSet  27m   deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 1
  Normal  ScalingReplicaSet  27m   deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 2
  Normal  ScalingReplicaSet  27m   deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 2
  Normal  ScalingReplicaSet  26m   deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 1
  Normal  ScalingReplicaSet  26m   deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 3
  Normal  ScalingReplicaSet  25m   deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 0
  
# rollout history (record) 내역 확인
vagrant@kube-control1:~/work$ kubectl rollout history deployment my-nginx-deployment
deployment.apps/my-nginx-deployment
REVISION  CHANGE-CAUSE
1         kubectl create --filename=deployment-nginx.yaml --record=true
2         kubectl set image deployment my-nginx-deployment nginx=nginx:1.21 --record=true

# rollout undo --to-revision (이전 버전 원복)
vagrant@kube-control1:~/work$ kubectl rollout undo deployment my-nginx-deployment --to-revision=1
deployment.apps/my-nginx-deployment rolled back
vagrant@kube-control1:~/work$ kubectl get pods,rs,deployment
NAME                                       READY   STATUS    RESTARTS      AGE
pod/my-nginx-deployment-6b4b7f7cdc-5xg5d   1/1     Running   0             31s
pod/my-nginx-deployment-6b4b7f7cdc-dzhvx   1/1     Running   0             32s
pod/my-nginx-deployment-6b4b7f7cdc-z99ss   1/1     Running   0             28s
pod/replicaset-nginx-4qh5v                 1/1     Running   1 (83m ago)   16h
pod/replicaset-nginx-v9cmz                 1/1     Running   1 (83m ago)   17h
pod/replicaset-nginx-w8xps                 1/1     Running   1 (83m ago)   17h

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-5cdb4bc675   0         0         0       35m
replicaset.apps/my-nginx-deployment-6b4b7f7cdc   3         3         3       47m
replicaset.apps/replicaset-nginx                 3         3         3       17h

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           47m

# label 확인
vagrant@kube-control1:~/work$ kubectl get replicasets --show-labels
NAME                             DESIRED   CURRENT   READY   AGE   LABELS
my-nginx-deployment-5cdb4bc675   0         0         0       37m   app=my-nginx,pod-template-hash=5cdb4bc675 # hash kube에서 pod 레이블 구분하기 위해 자동생성
my-nginx-deployment-6b4b7f7cdc   3         3         3       48m   app=my-nginx,pod-template-hash=6b4b7f7cdc
replicaset-nginx                 3         3         3       17h   <none>

# deployment 원복으로 인해 로그 증가 확인 (describe)
vagrant@kube-control1:~/work$ kubectl describe deployments my-nginx-deployment
Name:                   my-nginx-deployment
Namespace:              default
CreationTimestamp:      Thu, 22 Jun 2023 00:43:04 +0000
Labels:                 <none>
Annotations:            deployment.kubernetes.io/revision: 3
                        kubernetes.io/change-cause: kubectl create --filename=deployment-nginx.yaml --record=true
Selector:               app=my-nginx
Replicas:               3 desired | 3 updated | 3 total | 3 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge
Pod Template:
  Labels:  app=my-nginx
  Containers:
   nginx:
    Image:        nginx:1.10
    Port:         80/TCP
    Host Port:    0/TCP
    Environment:  <none>
    Mounts:       <none>
  Volumes:        <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  <none>
NewReplicaSet:   my-nginx-deployment-6b4b7f7cdc (3/3 replicas created)
Events:
  Type    Reason             Age                  From                   Message
  ----    ------             ----                 ----                   -------
  Normal  ScalingReplicaSet  38m                  deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 1
  Normal  ScalingReplicaSet  38m                  deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 2
  Normal  ScalingReplicaSet  38m                  deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 2
  Normal  ScalingReplicaSet  37m                  deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 1
  Normal  ScalingReplicaSet  37m                  deployment-controller  Scaled up replica set my-nginx-deployment-5cdb4bc675 to 3
  Normal  ScalingReplicaSet  36m                  deployment-controller  Scaled down replica set my-nginx-deployment-6b4b7f7cdc to 0
  Normal  ScalingReplicaSet  3m55s                deployment-controller  Scaled up replica set my-nginx-deployment-6b4b7f7cdc to 1
  Normal  ScalingReplicaSet  3m54s                deployment-controller  Scaled down replica set my-nginx-deployment-5cdb4bc675 to 2
  Normal  ScalingReplicaSet  3m54s                deployment-controller  Scaled up replica set my-nginx-deployment-6b4b7f7cdc to 2
  Normal  ScalingReplicaSet  3m52s                deployment-controller  Scaled down replica set my-nginx-deployment-5cdb4bc675 to 1
  Normal  ScalingReplicaSet  3m51s (x2 over 50m)  deployment-controller  Scaled up replica set my-nginx-deployment-6b4b7f7cdc to 3
  Normal  ScalingReplicaSet  3m50s                deployment-controller  Scaled down replica set my-nginx-deployment-5cdb4bc675 to 0
```



#### Daemonset : 기본적으로 워커 노드에 반드시 한개씩 파드가 실행되도록 하는 컨트롤러

- control-plane 에는 특정 어플리케이션만 작동 taint toleration

#### [Daemonset 실습]

##### daemonset-example.yaml

```yaml
apiVersion: apps/v1 # 구현 api 버전
kind: DaemonSet
metadata: # 해당 daemonset 정의
  name: daemonset-example
spec: # 상세 정의, pod갯수 지정안함
  selector: # daemonset이 관리할 pod 들의 레이블 선택자 (해당 조건을 만족하는 pod 관리)
    matchLabels:
      name: my-daemonset-example
  template:
    metadata:
      labels:
        name: my-daemonset-example
    spec:
      containers:
      - name: daemonset-example
        image: busybox # 리눅스 필수 도구로만 만들어진 이미지 (최소한의 이미지), 제어 터미널이 없으면 실행 후 종료 (제어 쉘 함께 생성해야함)
        args: ["tail", "-f", "/dev/null"] # tail -f /dev/null 명령어 추가
        # tail 명령어 변경 내역 추적(로그) tail 명령어 지정시 해당 프로세스를 계속 잡고있어야함 -> busybox 종료가 되지 않도록 임의의 프로세스 유지 하기 위해 명령어 사용
```



```bash
$ vim daemonset-example.yaml
$ 
$ kubectl create -f daemonset-example.yaml 
daemonset.apps/daemonset-example created
$ 
$ kubectl get all
NAME                                       READY   STATUS    RESTARTS   AGE
pod/daemonset-example-6j22x                1/1     Running   0          17s
pod/daemonset-example-82ldv                1/1     Running   0          17s
pod/daemonset-example-r27c6                1/1     Running   0          17s
pod/my-nginx-deployment-7484748b57-f4n6l   1/1     Running   0          33m
pod/my-nginx-deployment-7484748b57-n9rf9   1/1     Running   0          15m
pod/my-nginx-deployment-7484748b57-s8njz   1/1     Running   0          33m
pod/my-nginx-deployment-7484748b57-sz9gh   1/1     Running   0          33m

NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   22h

NAME                               DESIRED   CURRENT   READY   UP-TO-DATE   AVAILABLE   NODE SELECTOR   AGE
daemonset.apps/daemonset-example   3         3         3       3            3           <none>          17s

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   4/4     4            4           40m

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-6cc6fd4885   0         0         0       38m
replicaset.apps/my-nginx-deployment-7484748b57   4         4         4       40m
$ 
$ kubectl exec -it daemonset-example-82ldv /bin/sh
kubectl exec [POD] [COMMAND] is DEPRECATED and will be removed in a future version. Use kubectl exec [POD] -- [COMMAND] instead.
/ # ls /
bin   dev   etc   home  proc  root  sys   tmp   usr   var
/ # exit
$ 
$ kubectl get pods
NAME                                   READY   STATUS    RESTARTS   AGE
daemonset-example-6j22x                1/1     Running   0          4m23s
daemonset-example-82ldv                1/1     Running   0          4m23s
daemonset-example-r27c6                1/1     Running   0          4m23s
my-nginx-deployment-7484748b57-f4n6l   1/1     Running   0          37m
my-nginx-deployment-7484748b57-n9rf9   1/1     Running   0          19m
my-nginx-deployment-7484748b57-s8njz   1/1     Running   0          37m
my-nginx-deployment-7484748b57-sz9gh   1/1     Running   0          37m
$ kubectl get pods -o wide
NAME                                   READY   STATUS    RESTARTS   AGE     IP                NODE         NOMINATED NODE   READINESS GATES
daemonset-example-6j22x                1/1     Running   0          4m31s   192.168.119.142   kube-node3   <none>           <none>
daemonset-example-82ldv                1/1     Running   0          4m31s   192.168.233.211   kube-node2   <none>           <none>
daemonset-example-r27c6                1/1     Running   0          4m31s   192.168.9.77      kube-node1   <none>           <none>
my-nginx-deployment-7484748b57-f4n6l   1/1     Running   0          38m     192.168.119.141   kube-node3   <none>           <none>
my-nginx-deployment-7484748b57-n9rf9   1/1     Running   0          19m     192.168.233.210   kube-node2   <none>           <none>
my-nginx-deployment-7484748b57-s8njz   1/1     Running   0          38m     192.168.9.76      kube-node1   <none>           <none>
my-nginx-deployment-7484748b57-sz9gh   1/1     Running   0          38m     192.168.233.209   kube-node2   <none>           <none>
```



##### 실습

```bash
# daemonset 생성
vagrant@kube-control1:~/work$ vim daemonset-example.yaml
vagrant@kube-control1:~/work$ kubectl create -f daemonset-example.yaml
daemonset.apps/daemonset-example created
vagrant@kube-control1:~/work$ kubectl get all
NAME                                       READY   STATUS    RESTARTS       AGE
pod/daemonset-example-7v2mv                1/1     Running   0              103s
pod/daemonset-example-drvx5                1/1     Running   0              103s
pod/daemonset-example-wktxn                1/1     Running   0              103s
pod/my-nginx-deployment-6b4b7f7cdc-5xg5d   1/1     Running   0              17m
pod/my-nginx-deployment-6b4b7f7cdc-dzhvx   1/1     Running   0              17m
pod/my-nginx-deployment-6b4b7f7cdc-z99ss   1/1     Running   0              17m
pod/replicaset-nginx-4qh5v                 1/1     Running   1 (100m ago)   17h
pod/replicaset-nginx-v9cmz                 1/1     Running   1 (101m ago)   17h
pod/replicaset-nginx-w8xps                 1/1     Running   1 (100m ago)   17h

NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   21h

NAME                               DESIRED   CURRENT   READY   UP-TO-DATE   AVAILABLE   NODE SELECTOR   AGE
daemonset.apps/daemonset-example   3         3         3       3            3           <none>          103s # 현재 pod 만큼 생성

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/my-nginx-deployment   3/3     3            3           64m

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/my-nginx-deployment-5cdb4bc675   0         0         0       52m
replicaset.apps/my-nginx-deployment-6b4b7f7cdc   3         3         3       64m
replicaset.apps/replicaset-nginx                 3         3         3       17h
# daemonset 내부 접속
vagrant@kube-control1:~/work$ kubectl exec -it daemonset-example-7v2mv /bin/sh
kubectl exec [POD] [COMMAND] is DEPRECATED and will be removed in a future version. Use kubectl exec [POD] -- [COMMAND] instead.
/ #
/ # ls
bin    dev    etc    home   lib    lib64  proc   root   sys    tmp    usr    var
/ # ps -ef
PID   USER     TIME  COMMAND
    1 root      0:00 tail -f /dev/null
    6 root      0:00 /bin/sh
   12 root      0:00 ps -ef
/ # exit
vagrant@kube-control1:~/work$ kubectl get pods -o wide
NAME                                   READY   STATUS    RESTARTS       AGE     IP                NODE         NOMINATED NODE   READINESS GATES
daemonset-example-7v2mv                1/1     Running   0              4m35s   192.168.233.201   kube-node2   <none>           <none>
daemonset-example-drvx5                1/1     Running   0              4m35s   192.168.119.138   kube-node3   <none>           <none>
daemonset-example-wktxn                1/1     Running   0              4m35s   192.168.9.71      kube-node1   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-5xg5d   1/1     Running   0              20m     192.168.119.137   kube-node3   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-dzhvx   1/1     Running   0              20m     192.168.233.200   kube-node2   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-z99ss   1/1     Running   0              20m     192.168.9.70      kube-node1   <none>           <none>
replicaset-nginx-4qh5v                 1/1     Running   1 (103m ago)   17h     192.168.233.197   kube-node2   <none>           <none>
replicaset-nginx-v9cmz                 1/1     Running   1 (103m ago)   17h     192.168.9.67      kube-node1   <none>           <none>
replicaset-nginx-w8xps                 1/1     Running   1 (103m ago)   17h     192.168.119.133   kube-node3   <none>           <none>
```



### Kubernetes 네트워크

- 교재 (p 316~ 쿠버네티스 서비스)

- Docker Host가 여러개 있는 환경 (각 pod)
- 컨테이너 오케스트레이션 (각각 pod를 하나의 네트워크로 묶을 수 있음)





#### Ch06. 6.5-서비스(참조: p.318 ~ ) - 일반적인 서비스가 아닌 쿠버네티스 네트워크 서비스

- 서비스 오브젝트 - 일반적인 서비스와 구분하기 위해 명칭

#### 서비스 오브젝트(네트워크) 종류

- Cluster IP : 쿠버네티스 **클러스터 내부에서만 파드들에 접근**할 때 사용하는 서비스 오브젝트. 외부로 파드를 노출하지 않기 때문에 쿠버네티스 클러스터 내부에서만 사용되는 파드에 적합한 서비스 오브젝트

- Headless : 클러스터 내부에서 접근 가능한 서비스 오브젝트로 특정 파드에 대한 IP 주소를 제공하는 서비스 오브젝트(Cluster IP: None). **특정 파드에 직접 접근**해야하는 경우에 사용함 (특정 파드를 직접 명시하여 접근)

- NodePort : 파드에 접근할 수 있는 포트를 클러스터의 **모든 노드에 동일하게 개방**하여 외부에서 파드에 접근할 수 있는 서비스 오브젝트. 접근할 수 있는 포트는 기본적으로 랜덤(기본적으로 30000 ~ 35000 사이)으로 지정되나 특정 포트로 접근하도록 설정할 수도 있음. (Cluster IP 기능도 포함)

- LoadBalancer : 클라우드 플랫폼에서 제공하는 로드 밸런서를 동적으로 프로비저닝해 파드에 연결하는 서비스 오브젝트. NodePort 타입과 마찬가지로 외부에서 파드에 접근할 수 있는 서비스 타입이나 일반적으로 AWS. GCP 등의 클라우드 플랫폼 환경에서만 사용 가능함. (온프레미스에서는 일반적으로 로드밸런스가 어려움, 로드밸런서 사용가능환경에서 진행)

##### Port 관련 용어

- port : Kubernetes Cluster 내에서 접근 가능한 포트
- containerPort : 컨테이너가 개방하는 포트
- targetPort : 파드가 개방하는 포트
- nodeport: 외부에서 직접적으로 접근 가능한 포트 (클러스터 외부에서 접근)
- servicePort: ingress 에서 연결해주는 포트로 연결해주는 포트가 endpoint 의 포트(targetPort)가 됨.



#### [ClusterIP 실습]

##### 예제

```bash
$ vim deployment-hostname.yaml 
$ vim hostname-svc-clusterip.yaml 

$ kubectl create -f deployment-hostname.yaml 
deployment.apps/hostname-deployment created
$ kubectl create -f hostname-svc-clusterip.yaml 
service/hostname-svc-clusterip created

$ kubectl get deployment,pods,services
NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/hostname-deployment   3/3     3            3           62s

NAME                                       READY   STATUS    RESTARTS   AGE
pod/hostname-deployment-7dfd748479-9hv85   1/1     Running   0          62s
pod/hostname-deployment-7dfd748479-kxznm   1/1     Running   0          62s
pod/hostname-deployment-7dfd748479-s7hv8   1/1     Running   0          62s

NAME                             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
service/hostname-svc-clusterip   ClusterIP   10.103.64.125   <none>        8080/TCP   22s
service/kubernetes               ClusterIP   10.96.0.1       <none>        443/TCP    24h
$ kubectl get deployment,rs,pods,services
NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/hostname-deployment   3/3     3            3           69s

NAME                                             DESIRED   CURRENT   READY   AGE
replicaset.apps/hostname-deployment-7dfd748479   3         3         3       69s

NAME                                       READY   STATUS    RESTARTS   AGE
pod/hostname-deployment-7dfd748479-9hv85   1/1     Running   0          69s
pod/hostname-deployment-7dfd748479-kxznm   1/1     Running   0          69s
pod/hostname-deployment-7dfd748479-s7hv8   1/1     Running   0          69s

NAME                             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
service/hostname-svc-clusterip   ClusterIP   10.103.64.125   <none>        8080/TCP   29s
service/kubernetes               ClusterIP   10.96.0.1       <none>        443/TCP    24h
$ kubectl describe service hostname-svc-clusterip
Name:              hostname-svc-clusterip
Namespace:         default
Labels:            <none>
Annotations:       <none>
Selector:          app=webserver
Type:              ClusterIP
IP:                10.103.64.125
Port:              web-port  8080/TCP
TargetPort:        80/TCP
Endpoints:         192.168.119.144:80,192.168.233.212:80,192.168.9.78:80
Session Affinity:  None
Events:            <none>
$ 
$ kubectl describe pod hostname-deployment-7dfd748479-9hv85
Name:         hostname-deployment-7dfd748479-9hv85
IP:           192.168.9.78

$ kubectl describe pod hostname-deployment-7dfd748479-kxznm
IP:           192.168.119.144
$ kubectl describe pod hostname-deployment-7dfd748479-s7hv8
Name:         hostname-deployment-7dfd748479-s7hv8
IP:           192.168.233.212


$ kubectl run -it --rm debug --image=alicek106/ubuntu:curl --restart=Never -- bash
If you don't see a command prompt, try pressing enter.
root@debug:/# curl http://10.103.64.125:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
	<blockquote>
	<p>Hello,  hostname-deployment-7dfd748479-9hv85</p>	</blockquote>
</div>
root@debug:/# exit

$ curl http://10.103.64.125:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
	<blockquote>
	<p>Hello,  hostname-deployment-7dfd748479-kxznm</p>	</blockquote>
</div>
```



##### 실습

###### deployment-hostname.yaml

```yaml
# deployment-hostname.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hostname-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: webserver
  template:
    metadata:
      name: my-webserver
      labels:
        app: webserver
    spec:
      containers:
      - name: my-webserver
        image: alicek106/rr-test:echo-hostname # pod 이름 host 이름 출력하는 간단한 이미지
        ports:
        - containerPort: 80
```



```bash
vagrant@kube-control1:~/work$ vim deployment-hostname.yaml
vagrant@kube-control1:~/work$ kubectl create -f deployment-hostname.yaml
deployment.apps/hostname-deployment created
```



###### hostname-svc-clusterip.yaml

```yaml
# hostname-svc-clusterip.yaml
apiVersion: v1
kind: Service
metadata:
  name: hostname-svc-clusterip
spec:
  ports:
    - name: web-port
      port: 8080 # 서비스 오브젝트 요청 받을때 사용 클러스터 내부에서 접근 포트
      targetPort: 80 # 컨테이너(pod)가 열고 있는 port (컨테이너 포트와 일치해야함)
  selector:
    app: webserver # 레이블 셀렉터 (셀렉터를 적지 않으면 endpoint 오브젝트 직접 정의 후 생성 필요)
  type: ClusterIP
```



```bash
vagrant@kube-control1:~/work$ vim hostname-svc-clusterip.yaml
vagrant@kube-control1:~/work$ kubectl create -f hostname-svc-clusterip.yaml
service/hostname-svc-clusterip created
vagrant@kube-control1:~/work$ kubectl get services
NAME                     TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)    AGE
hostname-svc-clusterip   ClusterIP   10.105.169.243   <none>        8080/TCP   4s
kubernetes               ClusterIP   10.96.0.1        <none>        443/TCP    22h

# 상세내역
vagrant@kube-control1:~/work$ kubectl describe services hostname-svc-clusterip
Name:              hostname-svc-clusterip
Namespace:         default
Labels:            <none>
Annotations:       <none>
Selector:          app=webserver
Type:              ClusterIP
IP Family Policy:  SingleStack
IP Families:       IPv4
IP:                10.105.169.243
IPs:               10.105.169.243
Port:              web-port  8080/TCP
TargetPort:        80/TCP
Endpoints:         192.168.119.140:80,192.168.233.202:80 # 주소가 안나오면 문제 상황, 해당 포인트를 통해서 타겟에 요청이 들어감, 각 pod 별 가지고 있는 주소+타겟포트
Session Affinity:  None
Events:            <none>

# ip주소 맞는지 확인 (엔드포인트와 비교)
vagrant@kube-control1:~/work$ kubectl get pods -o wide
NAME                                   READY   STATUS             RESTARTS       AGE   IP                NODE         NOMINATED NODE   READINESS GATES
daemonset-example-7v2mv                1/1     Running            0              54m   192.168.233.201   kube-node2   <none>           <none>
daemonset-example-drvx5                1/1     Running            0              54m   192.168.119.138   kube-node3   <none>           <none>
daemonset-example-wktxn                1/1     Running            0              54m   192.168.9.71      kube-node1   <none>           <none>
hostname-deployment-7c58c4bd6f-7rkvd   1/1     Running            0              11m   192.168.119.140   kube-node3   <none>           <none>
hostname-deployment-7c58c4bd6f-dzmm2   1/1     Running            0              11m   192.168.233.202   kube-node2   <none>           <none>
hostname-deployment-7c58c4bd6f-tq688   0/1     ImagePullBackOff   0              11m   192.168.9.74      kube-node1   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-5xg5d   1/1     Running            0              69m   192.168.119.137   kube-node3   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-dzhvx   1/1     Running            0              70m   192.168.233.200   kube-node2   <none>           <none>
my-nginx-deployment-6b4b7f7cdc-z99ss   1/1     Running            0              69m   192.168.9.70      kube-node1   <none>           <none>
replicaset-nginx-4qh5v                 1/1     Running            1 (153m ago)   17h   192.168.233.197   kube-node2   <none>           <none>
replicaset-nginx-lw9sw                 1/1     Running            0              46m   192.168.119.139   kube-node3   <none>           <none>
replicaset-nginx-v9cmz                 1/1     Running            1 (153m ago)   18h   192.168.9.67      kube-node1   <none>           <none>
replicaset-nginx-w8xps                 1/1     Running            1 (153m ago)   18h   192.168.119.133   kube-node3   <none>           <none>

# curl로 연결 확인 (3개 중 랜덤으로 연결)
vagrant@kube-control1:~/work$ curl http://10.105.169.243:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
        <blockquote>
        <p>Hello,  hostname-deployment-7c58c4bd6f-dzmm2</p>     </blockquote>
</div>
vagrant@kube-control1:~/work$ curl http://10.105.169.243:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
        <blockquote>
        <p>Hello,  hostname-deployment-7c58c4bd6f-dzmm2</p>     </blockquote>
</div>
vagrant@kube-control1:~/work$ curl http://10.105.169.243:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
        <blockquote>
        <p>Hello,  hostname-deployment-7c58c4bd6f-7rkvd</p>     </blockquote>
</div>
vagrant@kube-control1:~/work$ curl http://10.105.169.243:8080
<!DOCTYPE html>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<div class="form-layout">
        <blockquote>
        <p>Hello,  hostname-deployment-7c58c4bd6f-dzmm2</p>     </blockquote>
</div>
```



- 외부에서의 접근? - nodeport, loadbalancer 
- 특정세션과의 지속적인 연결? 세션어피니티?



## 오후