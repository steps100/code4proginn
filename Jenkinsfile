def buildResults = [:]

pipeline {
    agent any

    triggers {
        //03:00 daily
        pollSCM('0 3 * * *')
    }

    tools {
	jdk "jdk8"
	maven "M3"
    }

    environment {
	IMAGE = 'xuezheyoushi/edu-open-api'
	CREDENTIAL = 'yuanzhou-docker-registry-id'
    }

    stages {
	
        stage('Maven Build') {
            steps {
		echo 'Maven Building...'
		sh 'mvn -version'
		sh 'mvn clean compile package install -Dmaven.test.skip=true'
            }
        }

	stage('Docker Build Image') {
	    steps {
                echo 'Docker Image Building...'
		script {
			app = docker.build("registry-internal.cn-qingdao.aliyuncs.com/${env.IMAGE}")
		}
	    }
	}

        stage('Docker Push Image') {
            steps {
                echo 'Docker Image Pushing...'
		script {
			docker.withRegistry('http://registry-internal.cn-qingdao.aliyuncs.com', "${env.CREDENTIAL}") {
				//app.push("${env.BUILD_NUMBER}")
            			app.push("${env.BRANCH_NAME}")
			}
		}
            }
        }

        stage('Maven Deploy') {
            steps {
                echo 'Deploying....'
		sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
    }

post {
    // Always runs. And it runs before any of the other post conditions.
    always {
      // Let's wipe out the workspace before we finish!
  echo ''
    }
    
    success {
      // mail(from: "jenkins@xuezheyoushi.cn",
           // to: "devops@xuezheyoushi.com",
           // subject: "SUCCESS - ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER}",
           // body: "Build Number: # ${env.BUILD_NUMBER}\nBuild Results: ${env.BUILD_URL}")
      sh """
      curl -X POST \
        'https://oapi.dingtalk.com/robot/send?access_token=302e0b29d239598996e810b576b3bfe27bd4c2789b3a8b3ca04a899ae27a8e0b' \
        -H 'cache-control: no-cache' \
        -H 'content-type: application/json;charset=utf-8' \
        -d '{
              "msgtype": "markdown",
              "markdown": {
                      "title":"Jenkins任务成功",
                      "text": "#### Jenkins任务成功 @18514669006\n ##### 任务：${env.JOB_NAME} - BUILD #${env.BUILD_NUMBER}\n\n [![screenshot](https://ws3.sinaimg.cn/large/006tNc79ly1fk1kgwwokzj31kw11onpd.jpg)](${env.BUILD_URL})\n ###### [查看任务详情](${env.BUILD_URL}) \n"
                      },
              "at": {
                      "atMobiles": [
                              "18514669006"
                      ],
                      "isAtAll": false
              }
      }'
      """
    }

    failure {
      mail(from: "jenkins@xuezheyoushi.cn", 
           to: "devops@xuezheyoushi.com", 
           subject: "[!!!] FAILURE - ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER}",
           body: "Build Number: # ${env.BUILD_NUMBER}\nBuild Results: ${env.BUILD_URL}")
      sh """
      curl -X POST \
        'https://oapi.dingtalk.com/robot/send?access_token=302e0b29d239598996e810b576b3bfe27bd4c2789b3a8b3ca04a899ae27a8e0b' \
        -H 'cache-control: no-cache' \
        -H 'content-type: application/json;charset=utf-8' \
        -d '{
              "msgtype": "markdown",
              "markdown": {
                      "title":"Jenkins任务失败",
                      "text": "#### Jenkins任务失败 @18514669006\n ##### 任务：${env.JOB_NAME} - BUILD #${env.BUILD_NUMBER}\n\n [![screenshot](https://ws3.sinaimg.cn/large/006tNc79ly1fk1kgwwokzj31kw11onpd.jpg)](${env.BUILD_URL})\n ###### [查看详情](${env.BUILD_URL}) \n"
                      },
              "at": {
                      "atMobiles": [
                              "18514669006"
                      ],
                      "isAtAll": false
              }
      }'
      """
    }
  }
  
  // The options directive is for configuration that applies to the whole job.
  options {
    // For example, we'd like to make sure we only keep 10 builds at a time, so
    // we don't fill up our storage!
    buildDiscarder(logRotator(numToKeepStr:'10'))
    
    // And we'd really like to be sure that this build doesn't hang forever, so
    // let's time it out after an hour.
    timeout(time: 60, unit: 'MINUTES')
  }
}
