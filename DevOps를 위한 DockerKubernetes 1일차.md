# DevOps를 위한 Docker/Kubernetes 1일차

- 230619



## 오전



https://drive.google.com/drive/folders/1lZ4VwxG71NkQx3fO0MThdUYSbx5p_V9Z



### 사전 평가

3.**다음 중 Docker에 대한 설명으로 가장 적절한 것은?**

가상화된 컨테이너를 사용하여 애플리케이션을 개발, 배포 및 실행하기 위한 오픈소스 플랫폼이다.

서버의 하드웨어 리소스를 최적화하기 위한 오픈소스 플랫폼이다.

데이터베이스를 관리하기 위한 오픈소스 플랫폼이다.

운영체제 가상화를 위한 오픈소스 플랫폼이다.

**올바르지 않음**

0/10 포인트

4.**다음 중 Docker 이미지에 대한 설명으로 옳은 것은?**

Docker 컨테이너의 실행 인스턴스이다.

Docker 컨테이너의 사본이다.

Docker 컨테이너를 생성하기 위한 애플리케이션 파일, 라이브러리, 기본 데이터 등의 초기 파일의 묶음이다.

Docker 컨테이너를 저장하기 위한 저장소이다.

**정답**

10/10 포인트

5.**다음 중 Docker Hub에 대한 설명으로 옳은 것은?**

Docker 컨테이너를 실행하기 위한 가상 머신 환경이다.

Docker 컨테이너 이미지를 저장, 공유하기 위해 네트워크 상에 존재하는 Docker의 기본저장소(Registry)이다.

Docker 컨테이너의 로그를 확인하기 위한 모니터링 도구이다.

Docker 컨테이너를 관리하기 위한 대시보드이다.

**정답**

10/10 포인트

6.**다음 중 Docker 컨테이너를 생성하면서 실행하기 위한 명령어로 가장 적절한 것은?**

docker build

docker start

docker create

docker run

**정답**

10/10 포인트

7.**다음 중 Docker에서 Docker 컨테이너 이미지를 빌드하기 위한 파일의 이름으로 옳은 것은?**

buildfile

image.json

dockerfile

imagefile

**정답**

10/10 포인트

8.**다음 중 Kubernetes에 대한 설명으로 옳은 것은?**

Kubernetes는 단일 노드에서만 실행되는 애플리케이션을 관리하는 도구이다.

Kubernetes는 컨테이너 오케스트레이션 플랫폼으로 여러 대의 노드에서 컨테이너화된 애플리케이션을 실행하고 관리하는 기능을 제공한다.

Kubernetes는 오직 Docker 컨테이너만을 관리할 수 있다.

Kubernetes는 클라우드 환경에서만 사용할 수 있다.

**정답**

10/10 포인트

9.**다음 중 Kubernetes의 애플리케이션 기본 실행 단위로 옳은 것은?**

container

pod

node

volume

**정답**

10/10 포인트

10.**다음 중 Kubernetes 클러스터의 노드에서 파드를 스케줄링하기 위해 사용되는 구성 요소는?**

kube-scheduler

kube-proxy

kubelet

kube-controller-manager

**정답**

10/10 포인트

11.**다음 중 Kubernetes에서 파드 간 통신을 위해 사용되는 오브젝트는?**

link

service

deployment

replicaset

**정답**

10/10 포인트

12.**다음 중 Kubernetes의 워크로드 리소스에 해당하지 않는 것은?**

replicaset

deployment

daemonset

service



### 개요

- 유닉스, 리눅스가 최초로 나왔을 당시, 리소스가 많이 부족함 -> 물리적 서버별로 나눠서 관리했음
- 각각 전용 물리서버(웹, 관리자 등등등)를 두고 관리하게 되면 비용적 측면에서도 문제
- 90년대 후반 부터 가상화 개념 도입으로 하나의 물리서버에 논리적으로 구분하기 시작



실습 가상 머신

CentOS 7

https://drive.google.com/drive/folders/1lZ4VwxG71NkQx3fO0MThdUYSbx5p_V9Z?usp=sharing 

 

 

OS : 사용자가 컴퓨터 하드웨어 리소스를 쉽게 사용할 수 있도록 도움을 주는 시스템 소프트웨어

 

가상화(Virtualization)

 시스템의 리소스를 논리적으로 나눠서 사용


 가상화 종류

 서버 가상화

  물리적인 컴퓨터를 가상머신으로 만들어 사용하는 기술

  \* Hypervisor : 가상머신의 리소스 관리 및 서로 독립적인 환경을 제공하기 위한 가상머신 간의 리소스 접근 방지 등을 제공하는 소프트웨어



- 가상머신 환경 내 자원 분배 관리 - Hypervisor (오라클 버츄어박스, vmware, kvm-리눅스)
- 타입1, 타입2 방식
- ![image-20230619103111339](C:\Users\LDCC\Desktop\md\DevOps를 위한 DockerKubernetes 1일차.assets\image-20230619103111339.png)



### 네트워크 가상화

- 네트워크를 논리적으로 만들어 사용하는 기술

-  SDN(Software Define Network)

- 네트워크를 소프트웨어 형태로 정의

- NFV(Network Function Virtualization)

- 네트워크 장비를 가상화하여 가상 네트워크에 배치하는 기술

   

### 스토리지 가상화

-  저장 공간(Storage)를 가상화 하는 기술 (RAID, LVM)
-  SDS(Software Defined Storage)
-  Ceph, Glusterfs, …

 

### 컨테이너 가상화

- 컨테이너 단위로 애플리케이션을 배포하고 관리하도록 하는 가상화 기술



- 4일간 배울 기술은 컨테이너 가상화 관련된 기술



#### 리눅스 컨테이너(LXC)

 리눅스 운영체제에서 제공하는 컨테이너 도구

리눅스 컨테이너를 구성하는 핵심 기술

 CGroups, namespace

CGroups

- Control Group의 약어로 프로세스를 그룹화하여 관리하며 시스템의 리소스를 제한할 수 있는 기술

Namespace

 다수의 오브젝트를 격리할 수 있는 기술

 오브젝트 별 독립적인 환경을 제공함.

- PID Namespace : Namespace 에서 독립적인 PID 사용
- Network Namespace : Namespace 에서 독립적인 네트워크 기능 사용
- Mount Namespace : Namespace 에서 독립적인 Mount Point 사용
- UID Namespace : Namespace 에서 독립적인 UID 사용
- UTS Namespace : Namespace 에서 독립적인 Hostname 사용
- IPC Namespace : Namespace 에서 독립적인 IPC 사용

#### Docker

 Docker사에서 2013년 개발한 컨테이너 도구

 기존의 Linux Container 기술보다 사용성이 개선됨.

 개발환경, 테스트 환경, 서비스 환경을 모두 동일하게 구성하여 사용할 수 있음.

#### Docker 특징

- **서버를 코드 형태로 정의**할 수 있음
- **이식성이 높음**
- 다양한 벤더 환경에서의 **상호 운용성**

Docker 관련 용어

![img](C:\Users\LDCC\Desktop\md\DevOps를 위한 DockerKubernetes 1일차.assets\JOxvDME5X1Gsk6jwX3qrSYa1SdTQpCbzIA7TS-a3XFTSXw8Y17gFOFaQktU8MXka0w5uZQOan1iYEYbBkMuas-zTPFmddCZrLwruWUvEqJmh1b-Da1BuHAs2lXd7qNUjn0TgC_R6g8u_Lu3Fbz1npFM.png)

이미지(Image)

 애플리케이션이 포함된 컨테이너를 생성하기 위한 실행환경을 포함하는 데이터 세트

저장소(Registry)

 컨테이너를 생성하기 위한 이미지를 저장하는 네트워크 상의 저장 공간(Docker Hub)

컨테이너(Container)

 컨테이너 이미지가 실행된 형태

—--

Docker 환경 구축

VM 계정 정보

일반사용자

 Username : user  Password : user

Root 사용자

 Username : root  Password : root

일반 사용자 계정 -> root 전환

$ sudo -i 

Docker CE 설치

```sh
# yum install epel-release -y

# cd /etc/yum.repos.d/

# wget[ https://download.docker.com/linux/centos/docker-ce.repo](https://download.docker.com/linux/centos/docker-ce.repo)

# yum install docker-ce docker-ce-cli containerd.io -y

# systemctl start docker.service

# systemctl enable docker.service

# usermod -aG docker user #도커권한 부여
```





도커 정보 확인

$ docker info

도커 버전 확인

$ docker version

도커 이미지 목록 확인

$ docker image ls

$ docker images

REPOSITORY  TAG    IMAGE ID  CREATED  SIZE

Registry : 이미지 저장소

 Repository : 이미지 저장소에 각 이미지별로 이미지가 저장되는 공간

 

도커 공식 레지스트리(Docker Hub)

https://hub.docker.com/

도커 이미지 검색(Docker hub)

OFFICIAL - 도커에서 직접 올리고 관리하는 이미지들

$ docker search **KEYWORD**

NAME               DESCRIPTION                   STARS   OFFICIAL  AUTOMATED

centos              The official build of CentOS.          7093   [OK]               bitnami/centos-base-buildpack   Centos base compilation image   0                [OK]

hello-world 도커 컨테이너 실행

$ docker container run hello-world

도커 아키텍처

docker client - 명령어

docker host - 도커를 실행, 관리하는 곳

docker registry - 도커 이미지들이 모인 저장소 (docker pull 을 통해 받아올 수 있음)

![img](C:\Users\LDCC\Desktop\md\DevOps를 위한 DockerKubernetes 1일차.assets\L3C0DOwSR6-_bluvr_2aJAE5NzL74psBsLtCBiubtqDiybeTG_DlbP2qCyz23epDfTsN-BU2rfe0k1Puh7uOPqyMQuXiB9xmDOeleQaKcmFLkFjga4qnWoQGSxaBwhPN60VIopKdxbYbeJ8iaqiNBKw.png)

https://docs.docker.com/get-started/overview/



## 오후



#### vi editor

- command mod - 명령어
- insert mod - 글씨 입력, 편집 (i)
- extended(last nine?) mod - 검색, 찾기 등
- command mod 에서  [i]키 후 입력 모드 진입 후 esc로 나오기
- command mod 에서 [:] 키 입력 후 사용 종류시 자동 나옴 or esc

- 저장 후 종료 command mod에서 :wq 입력
- 저장 x 종료 :q!
- ![image-20230619141517576](C:\Users\LDCC\Desktop\md\DevOps를 위한 DockerKubernetes 1일차.assets\image-20230619141517576.png)



```
[user@localhost ~]$ sudo -i

We trust you have received the usual lecture from the local System
Administrator. It usually boils down to these three things:

    #1) Respect the privacy of others.
    #2) Think before you type.
    #3) With great power comes great responsibility.

[sudo] password for user: 
Sorry, try again.
[sudo] password for user: 
Sorry, try again.
[sudo] password for user: 
sudo: 2 incorrect password attempts
[user@localhost ~]$ su -
Password: 
Last login: Sun Sep 12 22:38:49 KST 2021 on :0
[root@localhost ~]# yum install epel-release -y
Loaded plugins: fastestmirror, langpacks
Existing lock /var/run/yum.pid: another copy is running as pid 3233.
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 05:52 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 05:54 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 05:56 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 05:58 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:00 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:02 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:04 ago
    State  : Sleeping, pid: 3233
^[cAnother app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:06 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:08 ago
    State  : Sleeping, pid: 3233
^[cAnother app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 133 M RSS (550 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:10 ago
    State  : Sleeping, pid: 3233

^C

Exiting on user cancel.
[root@localhost ~]# pkill PackageKit
[root@localhost ~]# yum install epel-release
Loaded plugins: fastestmirror, langpacks
Existing lock /var/run/yum.pid: another copy is running as pid 3233.
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 123 M RSS (541 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:23 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 123 M RSS (541 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:25 ago
    State  : Sleeping, pid: 3233
Another app is currently holding the yum lock; waiting for it to exit...
  The other application is: PackageKit
    Memory : 123 M RSS (541 MB VSZ)
    Started: Mon Jun 19 13:20:42 2023 - 06:27 ago
    State  : Sleeping, pid: 3233
^C

Exiting on user cancel.
[root@localhost ~]# ps -ef | grep -i packagekit
root      2113     1  0 13:19 ?        00:00:00 /usr/libexec/packagekitd
root      3233  2113  3 13:20 ?        00:00:15 /usr/bin/python /usr/share/PackageKit/helpers/yum/yumBackend.py get-updates none
root      3967  3831  0 13:27 pts/0    00:00:00 grep --color=auto -i packagekit
[root@localhost ~]# kill 3233
[root@localhost ~]# ps -ef | grep -i packagekit
root      2113     1  0 13:19 ?        00:00:00 /usr/libexec/packagekitd
root      3233  2113  3 13:20 ?        00:00:15 /usr/bin/python /usr/share/PackageKit/helpers/yum/yumBackend.py get-updates none
root      4035  3831  0 13:28 pts/0    00:00:00 grep --color=auto -i packagekit
[root@localhost ~]# kill -9 3233
-bash: kill: (3233) - No such process
[root@localhost ~]# ps -ef | grep -i packagekit
root      2113     1  0 13:19 ?        00:00:00 /usr/libexec/packagekitd
root      4048  3831  0 13:29 pts/0    00:00:00 grep --color=auto -i packagekit
[root@localhost ~]# yum install epel-release
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: mirror.kakao.com
 * extras: mirror.kakao.com
 * updates: mirror.kakao.com
Resolving Dependencies
--> Running transaction check
---> Package epel-release.noarch 0:7-11 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

================================================================================
 Package                Arch             Version         Repository        Size
================================================================================
Installing:
 epel-release           noarch           7-11            extras            15 k

Transaction Summary
================================================================================
Install  1 Package

Total download size: 15 k
Installed size: 24 k
Is this ok [y/d/N]: y
Downloading packages:
epel-release-7-11.noarch.rpm                               |  15 kB   00:00     
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Installing : epel-release-7-11.noarch                                     1/1 
  Verifying  : epel-release-7-11.noarch                                     1/1 

Installed:
  epel-release.noarch 0:7-11                                                    

Complete!
[root@localhost ~]# cd /etc/yum.repos.d/
[root@localhost yum.repos.d]# wget https://download.docker.com/linux/centos/docker-ce.repo
--2023-06-19 13:30:19--  https://download.docker.com/linux/centos/docker-ce.repo
Resolving download.docker.com (download.docker.com)... 99.86.207.76, 99.86.207.93, 99.86.207.27, ...
Connecting to download.docker.com (download.docker.com)|99.86.207.76|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 1919 (1.9K) [binary/octet-stream]
Saving to: ‘docker-ce.repo’

100%[======================================>] 1,919       --.-K/s   in 0s      

2023-06-19 13:30:19 (239 MB/s) - ‘docker-ce.repo’ saved [1919/1919]

[root@localhost yum.repos.d]# yum install docker-ce docker-ce-cli containerd.io -y
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile


 One of the configured repositories failed (Unknown),
 and yum doesn't have enough cached data to continue. At this point the only
 safe thing yum can do is fail. There are a few ways to work "fix" this:

     1. Contact the upstream for the repository and get them to fix the problem.

     2. Reconfigure the baseurl/etc. for the repository, to point to a working
        upstream. This is most often useful if you are using a newer
        distribution release than is supported by the repository (and the
        packages for the previous distribution release still work).

     3. Run the command with the repository temporarily disabled
            yum --disablerepo=<repoid> ...

     4. Disable the repository permanently, so yum won't use it by default. Yum
        will then just ignore the repository until you permanently enable it
        again or use --enablerepo for temporary usage:

            yum-config-manager --disable <repoid>
        or
            subscription-manager repos --disable=<repoid>

     5. Configure the failing repository to be skipped, if it is unavailable.
        Note that yum will try to contact the repo. when it runs most commands,
        so will have to try and fail each time (and thus. yum will be be much
        slower). If it is a very temporary problem though, this is often a nice
        compromise:

            yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true

Cannot retrieve metalink for repository: epel/x86_64. Please verify its path and try again
[root@localhost yum.repos.d]# ls
CentOS-Base.repo       CentOS-Media.repo          docker-ce.repo
CentOS-CR.repo         CentOS-Sources.repo        epel.repo
CentOS-Debuginfo.repo  CentOS-Vault.repo          epel-testing.repo
CentOS-fasttrack.repo  CentOS-x86_64-kernel.repo
[root@localhost yum.repos.d]# yum install docker-ce docker-ce-cli containerd.io -y
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile


 One of the configured repositories failed (Unknown),
 and yum doesn't have enough cached data to continue. At this point the only
 safe thing yum can do is fail. There are a few ways to work "fix" this:

     1. Contact the upstream for the repository and get them to fix the problem.

     2. Reconfigure the baseurl/etc. for the repository, to point to a working
        upstream. This is most often useful if you are using a newer
        distribution release than is supported by the repository (and the
        packages for the previous distribution release still work).

     3. Run the command with the repository temporarily disabled
            yum --disablerepo=<repoid> ...

     4. Disable the repository permanently, so yum won't use it by default. Yum
        will then just ignore the repository until you permanently enable it
        again or use --enablerepo for temporary usage:

            yum-config-manager --disable <repoid>
        or
            subscription-manager repos --disable=<repoid>

     5. Configure the failing repository to be skipped, if it is unavailable.
        Note that yum will try to contact the repo. when it runs most commands,
        so will have to try and fail each time (and thus. yum will be be much
        slower). If it is a very temporary problem though, this is often a nice
        compromise:

            yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true

Cannot retrieve metalink for repository: epel/x86_64. Please verify its path and try again
[root@localhost yum.repos.d]# ls
CentOS-Base.repo       CentOS-Media.repo          docker-ce.repo
CentOS-CR.repo         CentOS-Sources.repo        epel.repo
CentOS-Debuginfo.repo  CentOS-Vault.repo          epel-testing.repo
CentOS-fasttrack.repo  CentOS-x86_64-kernel.repo
[root@localhost yum.repos.d]# sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
Loaded plugins: fastestmirror, langpacks
adding repo from: https://download.docker.com/linux/centos/docker-ce.repo
grabbing file https://download.docker.com/linux/centos/docker-ce.repo to /etc/yum.repos.d/docker-ce.repo
repo saved to /etc/yum.repos.d/docker-ce.repo
[root@localhost yum.repos.d]# yum install docker-ce docker-ce-cli containerd.io -y
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile


 One of the configured repositories failed (Unknown),
 and yum doesn't have enough cached data to continue. At this point the only
 safe thing yum can do is fail. There are a few ways to work "fix" this:

     1. Contact the upstream for the repository and get them to fix the problem.

     2. Reconfigure the baseurl/etc. for the repository, to point to a working
        upstream. This is most often useful if you are using a newer
        distribution release than is supported by the repository (and the
        packages for the previous distribution release still work).

     3. Run the command with the repository temporarily disabled
            yum --disablerepo=<repoid> ...

     4. Disable the repository permanently, so yum won't use it by default. Yum
        will then just ignore the repository until you permanently enable it
        again or use --enablerepo for temporary usage:

            yum-config-manager --disable <repoid>
        or
            subscription-manager repos --disable=<repoid>

     5. Configure the failing repository to be skipped, if it is unavailable.
        Note that yum will try to contact the repo. when it runs most commands,
        so will have to try and fail each time (and thus. yum will be be much
        slower). If it is a very temporary problem though, this is often a nice
        compromise:

            yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true

Cannot retrieve metalink for repository: epel/x86_64. Please verify its path and try again
[root@localhost yum.repos.d]# sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile


 One of the configured repositories failed (Unknown),
 and yum doesn't have enough cached data to continue. At this point the only
 safe thing yum can do is fail. There are a few ways to work "fix" this:

     1. Contact the upstream for the repository and get them to fix the problem.

     2. Reconfigure the baseurl/etc. for the repository, to point to a working
        upstream. This is most often useful if you are using a newer
        distribution release than is supported by the repository (and the
        packages for the previous distribution release still work).

     3. Run the command with the repository temporarily disabled
            yum --disablerepo=<repoid> ...

     4. Disable the repository permanently, so yum won't use it by default. Yum
        will then just ignore the repository until you permanently enable it
        again or use --enablerepo for temporary usage:

            yum-config-manager --disable <repoid>
        or
            subscription-manager repos --disable=<repoid>

     5. Configure the failing repository to be skipped, if it is unavailable.
        Note that yum will try to contact the repo. when it runs most commands,
        so will have to try and fail each time (and thus. yum will be be much
        slower). If it is a very temporary problem though, this is often a nice
        compromise:

            yum-config-manager --save --setopt=<repoid>.skip_if_unavailable=true

Cannot retrieve metalink for repository: epel/x86_64. Please verify its path and try again
[root@localhost yum.repos.d]# ls
CentOS-Base.repo       CentOS-Media.repo          docker-ce.repo
CentOS-CR.repo         CentOS-Sources.repo        epel.repo
CentOS-Debuginfo.repo  CentOS-Vault.repo          epel-testing.repo
CentOS-fasttrack.repo  CentOS-x86_64-kernel.repo
[root@localhost yum.repos.d]# vim epel.repo
[root@localhost yum.repos.d]# yum install epel-release
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: mirror.kakao.com
 * extras: mirror.kakao.com
 * updates: mirror.kakao.com
base                                                            | 3.6 kB  00:00:00     
docker-ce-stable                                                | 3.5 kB  00:00:00     
epel                                                            | 4.7 kB  00:00:00     
extras                                                          | 2.9 kB  00:00:00     
updates                                                         | 2.9 kB  00:00:00     
(1/5): docker-ce-stable/7/x86_64/updateinfo                     |   55 B  00:00:00     
(2/5): docker-ce-stable/7/x86_64/primary_db                     | 111 kB  00:00:01     
(3/5): epel/x86_64/group_gz                                     |  99 kB  00:00:02     
(4/5): epel/x86_64/updateinfo                                   | 1.0 MB  00:00:03     
epel/x86_64/primary_db         FAILED                                          
http://download.fedoraproject.org/pub/epel/7/x86_64/repodata/a9d0780c81ddfd0a0d7ef2565fa74bec53a37084ecae9ba2d22ccd2ad61eb635-primary.sqlite.bz2: [Errno 14] HTTP Error 404 - Not Found
Trying other mirror.
To address this issue please refer to the below wiki article 

https://wiki.centos.org/yum-errors

If above article doesn't help to resolve this issue please use https://bugs.centos.org/.

epel/x86_64/primary_db                                          | 7.0 MB  00:00:09     
Resolving Dependencies
--> Running transaction check
---> Package epel-release.noarch 0:7-11 will be updated
---> Package epel-release.noarch 0:7-14 will be an update
--> Finished Dependency Resolution

Dependencies Resolved

=======================================================================================
 Package                  Arch               Version            Repository        Size
=======================================================================================
Updating:
 epel-release             noarch             7-14               epel              15 k

Transaction Summary
=======================================================================================
Upgrade  1 Package

Total download size: 15 k
Is this ok [y/d/N]: y
Downloading packages:
epel/x86_64/prestodelta        FAILED                                          
http://download.fedoraproject.org/pub/epel/7/x86_64/repodata/3a073e0bfcce8dcbfdc9cfdab546400bc3b25e720d936bfec8742df4b91e2696-prestodelta.xml.gz: [Errno 14] HTTP Error 404 - Not Found
Trying other mirror.
Failed to download prestodelta for repository epel: [Errno 256] No more mirrors to try.
warning: /var/cache/yum/x86_64/7/epel/packages/epel-release-7-14.noarch.rpm: Header V4 RSA/SHA256 Signature, key ID 352c64e5: NOKEY
Public key for epel-release-7-14.noarch.rpm is not installed
epel-release-7-14.noarch.rpm                                    |  15 kB  00:00:01     
Retrieving key from file:///etc/pki/rpm-gpg/RPM-GPG-KEY-EPEL-7
Importing GPG key 0x352C64E5:
 Userid     : "Fedora EPEL (7) <epel@fedoraproject.org>"
 Fingerprint: 91e9 7d7c 4a5e 96f1 7f3e 888f 6a2f aea2 352c 64e5
 Package    : epel-release-7-11.noarch (@extras)
 From       : /etc/pki/rpm-gpg/RPM-GPG-KEY-EPEL-7
Is this ok [y/N]: y                   
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Updating   : epel-release-7-14.noarch                                            1/2 
warning: /etc/yum.repos.d/epel.repo created as /etc/yum.repos.d/epel.repo.rpmnew
  Cleanup    : epel-release-7-11.noarch                                            2/2 
  Verifying  : epel-release-7-14.noarch                                            1/2 
  Verifying  : epel-release-7-11.noarch                                            2/2 

Updated:
  epel-release.noarch 0:7-14                                                           

Complete!
[root@localhost yum.repos.d]# yum install docker-ce docker-ce-cli containerd.io -y
Loaded plugins: fastestmirror, langpacks
Loading mirror speeds from cached hostfile
 * base: mirror.kakao.com
 * extras: mirror.kakao.com
 * updates: mirror.kakao.com
Resolving Dependencies
--> Running transaction check
---> Package containerd.io.x86_64 0:1.6.21-3.1.el7 will be installed
--> Processing Dependency: container-selinux >= 2:2.74 for package: containerd.io-1.6.21-3.1.el7.x86_64
---> Package docker-ce.x86_64 3:24.0.2-1.el7 will be installed
--> Processing Dependency: docker-ce-rootless-extras for package: 3:docker-ce-24.0.2-1.el7.x86_64
---> Package docker-ce-cli.x86_64 1:24.0.2-1.el7 will be installed
--> Processing Dependency: docker-buildx-plugin for package: 1:docker-ce-cli-24.0.2-1.el7.x86_64
--> Processing Dependency: docker-compose-plugin for package: 1:docker-ce-cli-24.0.2-1.el7.x86_64
--> Running transaction check
---> Package container-selinux.noarch 2:2.119.2-1.911c772.el7_8 will be installed
---> Package docker-buildx-plugin.x86_64 0:0.10.5-1.el7 will be installed
---> Package docker-ce-rootless-extras.x86_64 0:24.0.2-1.el7 will be installed
--> Processing Dependency: fuse-overlayfs >= 0.7 for package: docker-ce-rootless-extras-24.0.2-1.el7.x86_64
--> Processing Dependency: slirp4netns >= 0.4 for package: docker-ce-rootless-extras-24.0.2-1.el7.x86_64
---> Package docker-compose-plugin.x86_64 0:2.18.1-1.el7 will be installed
--> Running transaction check
---> Package fuse-overlayfs.x86_64 0:0.7.2-6.el7_8 will be installed
--> Processing Dependency: libfuse3.so.3(FUSE_3.2)(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
--> Processing Dependency: libfuse3.so.3(FUSE_3.0)(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
--> Processing Dependency: libfuse3.so.3()(64bit) for package: fuse-overlayfs-0.7.2-6.el7_8.x86_64
---> Package slirp4netns.x86_64 0:0.4.3-4.el7_8 will be installed
--> Running transaction check
---> Package fuse3-libs.x86_64 0:3.6.1-4.el7 will be installed
--> Finished Dependency Resolution

Dependencies Resolved

=======================================================================================
 Package                    Arch    Version                    Repository         Size
=======================================================================================
Installing:
 containerd.io              x86_64  1.6.21-3.1.el7             docker-ce-stable   34 M
 docker-ce                  x86_64  3:24.0.2-1.el7             docker-ce-stable   24 M
 docker-ce-cli              x86_64  1:24.0.2-1.el7             docker-ce-stable   13 M
Installing for dependencies:
 container-selinux          noarch  2:2.119.2-1.911c772.el7_8  extras             40 k
 docker-buildx-plugin       x86_64  0.10.5-1.el7               docker-ce-stable   12 M
 docker-ce-rootless-extras  x86_64  24.0.2-1.el7               docker-ce-stable  9.1 M
 docker-compose-plugin      x86_64  2.18.1-1.el7               docker-ce-stable   12 M
 fuse-overlayfs             x86_64  0.7.2-6.el7_8              extras             54 k
 fuse3-libs                 x86_64  3.6.1-4.el7                extras             82 k
 slirp4netns                x86_64  0.4.3-4.el7_8              extras             81 k

Transaction Summary
=======================================================================================
Install  3 Packages (+7 Dependent packages)

Total download size: 105 M
Installed size: 372 M
Downloading packages:
(1/10): container-selinux-2.119.2-1.911c772.el7_8.noarch.rpm    |  40 kB  00:00:00     
warning: /var/cache/yum/x86_64/7/docker-ce-stable/packages/docker-buildx-plugin-0.10.5-1.el7.x86_64.rpm: Header V4 RSA/SHA512 Signature, key ID 621e9f35: NOKEY
Public key for docker-buildx-plugin-0.10.5-1.el7.x86_64.rpm is not installed
(2/10): docker-buildx-plugin-0.10.5-1.el7.x86_64.rpm            |  12 MB  00:00:21     
(3/10): docker-ce-24.0.2-1.el7.x86_64.rpm                       |  24 MB  00:00:19     
(4/10): docker-ce-cli-24.0.2-1.el7.x86_64.rpm                   |  13 MB  00:00:06     
(5/10): docker-ce-rootless-extras-24.0.2-1.el7.x86_64.rpm       | 9.1 MB  00:00:00     
(6/10): fuse-overlayfs-0.7.2-6.el7_8.x86_64.rpm                 |  54 kB  00:00:00     
(7/10): slirp4netns-0.4.3-4.el7_8.x86_64.rpm                    |  81 kB  00:00:00     
(8/10): fuse3-libs-3.6.1-4.el7.x86_64.rpm                       |  82 kB  00:00:00     
(9/10): containerd.io-1.6.21-3.1.el7.x86_64.rpm                 |  34 MB  00:00:56     
(10/10): docker-compose-plugin-2.18.1-1.el7.x86_64.rpm          |  12 MB  00:00:14     
---------------------------------------------------------------------------------------
Total                                                     1.7 MB/s | 105 MB  01:02     
Retrieving key from https://download.docker.com/linux/centos/gpg
Importing GPG key 0x621E9F35:
 Userid     : "Docker Release (CE rpm) <docker@docker.com>"
 Fingerprint: 060a 61c5 1b55 8a7f 742b 77aa c52f eb6b 621e 9f35
 From       : https://download.docker.com/linux/centos/gpg
Running transaction check
Running transaction test
Transaction test succeeded
Running transaction
  Installing : 2:container-selinux-2.119.2-1.911c772.el7_8.noarch                 1/10 
  Installing : containerd.io-1.6.21-3.1.el7.x86_64                                2/10 
  Installing : docker-buildx-plugin-0.10.5-1.el7.x86_64                           3/10 
  Installing : slirp4netns-0.4.3-4.el7_8.x86_64                                   4/10 
  Installing : fuse3-libs-3.6.1-4.el7.x86_64                                      5/10 
  Installing : fuse-overlayfs-0.7.2-6.el7_8.x86_64                                6/10 
  Installing : docker-compose-plugin-2.18.1-1.el7.x86_64                          7/10 
  Installing : 1:docker-ce-cli-24.0.2-1.el7.x86_64                                8/10 
  Installing : docker-ce-rootless-extras-24.0.2-1.el7.x86_64                      9/10 
  Installing : 3:docker-ce-24.0.2-1.el7.x86_64                                   10/10 
  Verifying  : 3:docker-ce-24.0.2-1.el7.x86_64                                    1/10 
  Verifying  : docker-compose-plugin-2.18.1-1.el7.x86_64                          2/10 
  Verifying  : fuse3-libs-3.6.1-4.el7.x86_64                                      3/10 
  Verifying  : fuse-overlayfs-0.7.2-6.el7_8.x86_64                                4/10 
  Verifying  : containerd.io-1.6.21-3.1.el7.x86_64                                5/10 
  Verifying  : slirp4netns-0.4.3-4.el7_8.x86_64                                   6/10 
  Verifying  : 2:container-selinux-2.119.2-1.911c772.el7_8.noarch                 7/10 
  Verifying  : 1:docker-ce-cli-24.0.2-1.el7.x86_64                                8/10 
  Verifying  : docker-ce-rootless-extras-24.0.2-1.el7.x86_64                      9/10 
  Verifying  : docker-buildx-plugin-0.10.5-1.el7.x86_64                          10/10 

Installed:
  containerd.io.x86_64 0:1.6.21-3.1.el7         docker-ce.x86_64 3:24.0.2-1.el7        
  docker-ce-cli.x86_64 1:24.0.2-1.el7          

Dependency Installed:
  container-selinux.noarch 2:2.119.2-1.911c772.el7_8                                   
  docker-buildx-plugin.x86_64 0:0.10.5-1.el7                                           
  docker-ce-rootless-extras.x86_64 0:24.0.2-1.el7                                      
  docker-compose-plugin.x86_64 0:2.18.1-1.el7                                          
  fuse-overlayfs.x86_64 0:0.7.2-6.el7_8                                                
  fuse3-libs.x86_64 0:3.6.1-4.el7                                                      
  slirp4netns.x86_64 0:0.4.3-4.el7_8                                                   

Complete!
[root@localhost yum.repos.d]# systemctl start docker.service
[root@localhost yum.repos.d]# systemctl enable docker.service
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
[root@localhost yum.repos.d]# usermod -aG docker user
[root@localhost yum.repos.d]# id user
uid=1000(user) gid=1000(user) groups=1000(user),10(wheel),981(docker)
[root@localhost yum.repos.d]# 

```



- ![image-20230619142948694](C:\Users\LDCC\Desktop\md\DevOps를 위한 DockerKubernetes 1일차.assets\image-20230619142948694.png)

- centos - 도커 공식판
- repoID/centos - 각 도커계정(유저)가 만든 centos



- docker image는 1개 이상의 레이어로 구성? - 추후 설명
- 





### 

```
$ docker container ls -a

CONTAINER ID  IMAGE        COMMAND  CREATED     STATUS           PORTS   NAMES

897b10433f8e  hello-world:latest  "/hello"  4 minutes ago  Exited (0) 4 minutes ago       vigilant_lalande
```





도커 이미지 다운로드

```
$ docker image pull **IMG_REPO:TAG**

$ docker pull **IMG_REPO:TAG**
```





새로운 컨테이너 실행

```
$ docker container run [OPTION] IMAGE [COMMAND] [ARG…]

$ docker run [OPTION] IMAGE [COMMAND] [ARG…]

$ docker container run -it --name centos7 centos:7
# centos 이미지의 7이라는 태그 이미지를 설치 및 실행 - centos7 라는 이름으로 이미지 만들기, it 옵션 적용 
```



| 옵션 | 설명                                                         |
| ---- | ------------------------------------------------------------ |
| -i   | 표준 입력을 컨테이너에 연결(키보드 입력을 컨테이너로 전달) 컨테이너 내부에 입력이 필요한 경우 키보드를 연결하기 위한 옵션 |
| -t   | 제어 터미널을 사용(Psuedo-terminal) - 컨테이너 내부에서 터미널 사용가능 (시작 프로세스가 쉘인 이미지의 경우 이 옵션을 사용하지 않으면 실행 후 바로 종료, 예시 이미지-centos,ubuntu 등) |
| -d   | 백그라운드(detach mode)로 컨테이너 실행 - 제어 터미널 없이 실행 (계속 켜져 있어야 하는 서비스-db서비스 같은것, 실행되고 있는 내역이 필요없는 경우, fawrground? ) |



실행중인 컨테이너 목록 확인 - 컨테이너 내부에서는 도커 명령어 실행 X(컨테이너 내 도커 설치 X), 호스트에서 해야함

```
$ docker container ls
$ docker ps
```



### 컨테이너 내 터미널과 호스트 터미널 비교

```
$ ps -ef # 루트에서는 pid 1번이 운영체제 최고 관리자, 컨테이너에서는 1번이 최초 실행자(bash 등) - 네임스페이스로 나뉨을 확인 가능
$ ls -l / # 루트 내 파일 확인시 서로의 루트 폴더 내용이 다름 -> 분리를 확인 가능

```



종료된 컨테이너 포함 모든 컨테이너 목록 확인

```
$ docker container ls -a # -a = all

$ docker ps -a

CONTAINER ID  IMAGE        COMMAND  CREATED     STATUS           PORTS   NAMES

# name 지정하지 않으면 랜덤 이름이 주어지므로 관리하기 편하게 이름 지정하는게 좋음
```



#### 위의 내역 정리

```
[user@localhost ~]$ docker run -it --name centos7 centos:7
Unable to find image 'centos:7' locally
7: Pulling from library/centos
2d473b07cdd5: Pull complete 
Digest: sha256:be65f488b7764ad3638f236b7b515b3678369a5124c47b8d32916d6487418ea4
Status: Downloaded newer image for centos:7
[root@517b1113e775 /]# 
[root@517b1113e775 /]# ps -ef 
UID        PID  PPID  C STIME TTY          TIME CMD
root         1     0  0 06:58 pts/0    00:00:00 /bin/bash
root        15     1  0 07:12 pts/0    00:00:00 ps -ef
[root@517b1113e775 /]# ls -l
total 12
-rw-r--r--.   1 root root 12114 Nov 13  2020 anaconda-post.log
lrwxrwxrwx.   1 root root     7 Nov 13  2020 bin -> usr/bin
drwxr-xr-x.   5 root root   360 Jun 19 06:58 dev
drwxr-xr-x.   1 root root    66 Jun 19 06:58 etc
drwxr-xr-x.   2 root root     6 Apr 11  2018 home
lrwxrwxrwx.   1 root root     7 Nov 13  2020 lib -> usr/lib
lrwxrwxrwx.   1 root root     9 Nov 13  2020 lib64 -> usr/lib64
drwxr-xr-x.   2 root root     6 Apr 11  2018 media
drwxr-xr-x.   2 root root     6 Apr 11  2018 mnt
drwxr-xr-x.   2 root root     6 Apr 11  2018 opt
dr-xr-xr-x. 238 root root     0 Jun 19 06:58 proc
dr-xr-x---.   2 root root   114 Nov 13  2020 root
drwxr-xr-x.  11 root root   148 Nov 13  2020 run
lrwxrwxrwx.   1 root root     8 Nov 13  2020 sbin -> usr/sbin
drwxr-xr-x.   2 root root     6 Apr 11  2018 srv
dr-xr-xr-x.  13 root root     0 Jun 19 02:27 sys
drwxrwxrwt.   7 root root   132 Nov 13  2020 tmp
drwxr-xr-x.  13 root root   155 Nov 13  2020 usr
drwxr-xr-x.  18 root root   238 Nov 13  2020 var
[root@517b1113e775 /]# touch centos-container.txt
[root@517b1113e775 /]# ls -l
total 12
-rw-r--r--.   1 root root 12114 Nov 13  2020 anaconda-post.log
lrwxrwxrwx.   1 root root     7 Nov 13  2020 bin -> usr/bin
-rw-r--r--.   1 root root     0 Jun 19 07:15 centos-container.txt
drwxr-xr-x.   5 root root   360 Jun 19 06:58 dev
drwxr-xr-x.   1 root root    66 Jun 19 06:58 etc
drwxr-xr-x.   2 root root     6 Apr 11  2018 home
lrwxrwxrwx.   1 root root     7 Nov 13  2020 lib -> usr/lib
lrwxrwxrwx.   1 root root     9 Nov 13  2020 lib64 -> usr/lib64
drwxr-xr-x.   2 root root     6 Apr 11  2018 media
drwxr-xr-x.   2 root root     6 Apr 11  2018 mnt
drwxr-xr-x.   2 root root     6 Apr 11  2018 opt
dr-xr-xr-x. 238 root root     0 Jun 19 06:58 proc
dr-xr-x---.   2 root root   114 Nov 13  2020 root
drwxr-xr-x.  11 root root   148 Nov 13  2020 run
lrwxrwxrwx.   1 root root     8 Nov 13  2020 sbin -> usr/sbin
drwxr-xr-x.   2 root root     6 Apr 11  2018 srv
dr-xr-xr-x.  13 root root     0 Jun 19 02:27 sys
drwxrwxrwt.   7 root root   132 Nov 13  2020 tmp
drwxr-xr-x.  13 root root   155 Nov 13  2020 usr
drwxr-xr-x.  18 root root   238 Nov 13  2020 var
[root@517b1113e775 /]# exit
exit
[user@localhost ~]$ 
[user@localhost ~]$ docker ps 
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS                      PORTS     NAMES
517b1113e775   centos:7   "/bin/bash"   28 minutes ago   Exited (0) 39 seconds ago             centos7
[user@localhost ~]$ 
[user@localhost ~]$ 

```





Docker 컨테이너 내부에 연결

```
# 백그라운드로 실행 or 컨테이너에서 벗어났을 경우 진입을 위해 사용되지만 진입 도중 꺼질 수 있는 등, 문제 소지가 있어 exec 커맨드로 새로운 창을 띄워 진입
$ docker container attach CONTAINER

[root@33d336f1c3e8 /]# 

- 컨테이너 내부로 진입하여 Shell로 작업하고자 하는 경우 docker container exec 커맨드로 새로운 Shell 을 실행하여 작업하는 것을 권장함.
  (docker container attach 커맨드는 상황에 따라 작업이 진행되지 않을 수 있음.)
```



Docker 컨테이너에 추가 프로세스 실행

```
$ docker container exec -i -t CONTAINER /bin/bash # 옵션을 지정해서 실행 쉘을 새롭게 띄움

$ docker container exec -i -t CONTAINER /bin/sh

$ docker container exec CONTAINER PROCESS

$ docker exec CONTAINER PROCESS
```



```
# 컨테이너를 Background로 실행 후 컨테이너 내에 추가 터미널 실행하여 컨테이너 내부로 진입

[user@localhost ~]$ docker run -dt --name centos7-2d centos:7
4efb675c48eb65f72121aa831a70fdd52497fd0cec1140ae24a6fb1ef8864ee9
[user@localhost ~]$ 
[user@localhost ~]$ docker ps
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS          PORTS     NAMES
4efb675c48eb   centos:7   "/bin/bash"   42 seconds ago   Up 42 seconds             centos7-2d
[user@localhost ~]$ 
[user@localhost ~]$ docker exec -it centos7-2d /bin/bash
[root@4efb675c48eb /]# ps -ef 
UID        PID  PPID  C STIME TTY          TIME CMD
root         1     0  0 07:30 pts/0    00:00:00 /bin/bash
root        15     0  0 07:33 pts/1    00:00:00 /bin/bash
root        29    15  0 07:35 pts/1    00:00:00 ps -ef
[root@4efb675c48eb /]# exit
exit
[user@localhost ~]$ docker ps
CONTAINER ID   IMAGE      COMMAND       CREATED         STATUS         PORTS     NAMES
4efb675c48eb   centos:7   "/bin/bash"   7 minutes ago   Up 7 minutes             centos7-2d
[user@localhost ~]$ 


```



### bash종류

https://sasca37.tistory.com/286

https://docs.docker.com/engine/reference/commandline/exec/







Docker 컨테이너 종료

```
$ docker container stop CONTAINER

$ docker stop CONTAINER

$ exit # 컨테이너 내부에서 종료
```



```
실행 중인 컨테이너 종료 실습

[user@localhost ~]$ docker run -dt --name centos7-3d centos:7
dece290e06585783003613d46802ce8d4e6dee4a7362c162fa462eb839c04029
[user@localhost ~]$ 
[user@localhost ~]$ docker ps
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS          PORTS     NAMES
dece290e0658   centos:7   "/bin/bash"   10 seconds ago   Up 9 seconds              centos7-3d
4efb675c48eb   centos:7   "/bin/bash"   12 minutes ago   Up 12 minutes             centos7-2d
[user@localhost ~]$ 
[user@localhost ~]$ docker stop centos7-3d
centos7-3d
[user@localhost ~]$ 
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS                            PORTS     NAMES
dece290e0658   centos:7   "/bin/bash"   2 minutes ago    Exited (137) About a minute ago             centos7-3d
4efb675c48eb   centos:7   "/bin/bash"   14 minutes ago   Up 14 minutes                               centos7-2d
517b1113e775   centos:7   "/bin/bash"   46 minutes ago   Exited (0) 19 minutes ago                   centos7
[user@localhost ~]$ 

```



```
docker run # 생성+실행
docker create # 생성만
docker start # 실행만
```



Docker 컨테이너 시작 (종료되었던 컨테이너)

```
$ docker container start CONTAINER
```



```
컨테이너 시작

[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE      COMMAND       CREATED             STATUS                        PORTS     NAMES
dece290e0658   centos:7   "/bin/bash"   40 minutes ago      Exited (137) 39 minutes ago             centos7-3d
[user@localhost ~]$ 
[user@localhost ~]$ docker start centos7-3d
centos7-3d
[user@localhost ~]$ 
[user@localhost ~]$ docker ps 
CONTAINER ID   IMAGE      COMMAND       CREATED          STATUS          PORTS     NAMES
dece290e0658   centos:7   "/bin/bash"   41 minutes ago   Up 24 seconds             centos7-3d
4efb675c48eb   centos:7   "/bin/bash"   54 minutes ago   Up 54 minutes             centos7-2d
```





Docker 컨테이너 삭제 - 삭제 전 켜져있는 컨테이너가 있다면 삭제 X -> 종료 후 삭제 진행

``` 
$ docker container rm CONTAINER
$ docker rm CONTAINER
```



```
컨테이너 종료 및 삭제
  - 컨테이너 삭제시 종료된 컨테이너만 삭제 가능

[user@localhost ~]$ 
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE                COMMAND       CREATED             STATUS                         PORTS     NAMES
9978455a1dd7   hello-world:latest   "/hello"      3 minutes ago       Exited (0) 3 minutes ago                 strange_faraday
dece290e0658   centos:7             "/bin/bash"   47 minutes ago      Up 6 minutes                             centos7-3d
4efb675c48eb   centos:7             "/bin/bash"   About an hour ago   Up About an hour                         centos7-2d
517b1113e775   centos:7             "/bin/bash"   2 hours ago         Exited (0) About an hour ago             centos7
[user@localhost ~]$ 
[user@localhost ~]$ docker stop centos7-3d
centos7-3d
[user@localhost ~]$ 
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE                COMMAND       CREATED             STATUS                         PORTS     NAMES
9978455a1dd7   hello-world:latest   "/hello"      4 minutes ago       Exited (0) 4 minutes ago                 strange_faraday
dece290e0658   centos:7             "/bin/bash"   48 minutes ago      Exited (137) 13 seconds ago              centos7-3d
4efb675c48eb   centos:7             "/bin/bash"   About an hour ago   Up About an hour                         centos7-2d
517b1113e775   centos:7             "/bin/bash"   2 hours ago         Exited (0) About an hour ago             centos7
[user@localhost ~]$ 
[user@localhost ~]$ docker rm centos7-3d
centos7-3d
[user@localhost ~]$ 
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE                COMMAND       CREATED             STATUS                         PORTS     NAMES
9978455a1dd7   hello-world:latest   "/hello"      5 minutes ago       Exited (0) 5 minutes ago                 strange_faraday
4efb675c48eb   centos:7             "/bin/bash"   About an hour ago   Up About an hour                         centos7-2d
517b1113e775   centos:7             "/bin/bash"   2 hours ago         Exited (0) About an hour ago             centos7
[user@localhost ~]$ 

```





Docker 컨테이너 로그 확인

````
$ docker container logs CONTAINER
$ docker logs CONTAINER
````



```
컨테이너 실행 로그 확인

[user@localhost ~]$ docker run -dt -p 80:80 --name httpd httpd:latest
3cdeb79840929cbfd6c5bf4d14dd2dd936316abdb65a7806658ccd8a4348c8c6
[user@localhost ~]$ 
[user@localhost ~]$ docker ps -a
CONTAINER ID   IMAGE                COMMAND              CREATED             STATUS                         PORTS                               NAMES
3cdeb7984092   httpd:latest         "httpd-foreground"   15 seconds ago      Up 14 seconds                  0.0.0.0:80->80/tcp, :::80->80/tcp   httpd
9978455a1dd7   hello-world:latest   "/hello"             11 minutes ago      Exited (0) 11 minutes ago                                          strange_faraday
4efb675c48eb   centos:7             "/bin/bash"          About an hour ago   Up About an hour                                                   centos7-2d
517b1113e775   centos:7             "/bin/bash"          2 hours ago         Exited (0) About an hour ago                                       centos7
[user@localhost ~]$ 
[user@localhost ~]$ curl http://localhost/
<html><body><h1>It works!</h1></body></html>
[user@localhost ~]$ curl http://localhost/
<html><body><h1>It works!</h1></body></html>
[user@localhost ~]$ curl http://localhost/
<html><body><h1>It works!</h1></body></html>
[user@localhost ~]$ 
[user@localhost ~]$ docker logs httpd
AH00558: httpd: Could not reliably determine the server's fully qualified domain name, using 172.17.0.3. Set the 'ServerName' directive globally to suppress this message
AH00558: httpd: Could not reliably determine the server's fully qualified domain name, using 172.17.0.3. Set the 'ServerName' directive globally to suppress this message
[Mon Jun 19 08:38:11.324327 2023] [mpm_event:notice] [pid 1:tid 139790264289152] AH00489: Apache/2.4.57 (Unix) configured -- resuming normal operations
[Mon Jun 19 08:38:11.324485 2023] [core:notice] [pid 1:tid 139790264289152] AH00094: Command line: 'httpd -D FOREGROUND'
172.17.0.1 - - [19/Jun/2023:08:39:51 +0000] "GET / HTTP/1.1" 200 45
172.17.0.1 - - [19/Jun/2023:08:40:02 +0000] "GET / HTTP/1.1" 200 45
172.17.0.1 - - [19/Jun/2023:08:40:03 +0000] "GET / HTTP/1.1" 200 45
[user@localhost ~]$ 
[user@localhost ~]$ docker stop httpd
httpd
[user@localhost ~]$ docker rm httpd
httpd
[user@localhost ~]$ 
```





Docker Container에 파일 복사 (Docker Host -> Docker Container)

```
$ docker container cp SOURCE CONTAINER:/PATH # cp = copy

$ docker cp SOURCE CONTAINER:/PATH
```



```
Docker Host -> Docker Container 파일 복사


[user@localhost ~]$ docker ps 
CONTAINER ID   IMAGE      COMMAND       CREATED             STATUS             PORTS     NAMES
4efb675c48eb   centos:7   "/bin/bash"   About an hour ago   Up About an hour             centos7-2d
[user@localhost ~]$ 
[user@localhost ~]$ echo "Docker Host write file" > docker-host1.txt
[user@localhost ~]$ 
[user@localhost ~]$ docker cp docker-host1.txt centos7-2d:/root/
[user@localhost ~]$ docker exec centos7-2d ls -l /root
total 8
-rw-------. 1 root root 3416 Nov 13  2020 anaconda-ks.cfg
-rw-rw-r--. 1 1000 1000   23 Jun 19 08:46 docker-host1.txt
[user@localhost ~]$ docker exec centos7-2d cat /root/docker-host1.txt
Docker Host write file
[user@localhost ~]$ 
```



Docker Container에서 로컬 시스템으로 파일 복사 (Docker Container -> Docker Host)

```
$ docker container cp CONTAINER:/PATH/FILE DESTINATION

$ docker cp CONTAINER:/PATH/FILE DESTINATION
```



```
```

