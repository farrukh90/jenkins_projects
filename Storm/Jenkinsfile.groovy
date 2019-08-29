node{
    stage("Update jenkins"){
        properties([parameters([string(defaultValue: '63.33.56.132', description: 'Please provide IP', name: 'ENVIR', trim: true)])])
        sh "echo Parameter added"
    }
    stage("Install git"){
        sh "ssh  ec2-user@${ENVIR} sudo yum install git python-pip -y"
    }
    stage("Remove repo"){
        sh "ssh  ec2-user@${ENVIR} sudo  rm -rf /home/ec2-user/stormpath-flask-sample"
    }
    stage("Copy script"){
        sh "scp script.sh  ec2-user@${ENVIR}:/home/ec2-user"
        sh "ssh  ec2-user@${ENVIR} bash script.sh"
    }
    stage("Pull Repo"){
        sh "ssh  ec2-user@${ENVIR} git clone https://github.com/farrukh90/stormpath-flask-sample.git 2> /dev/null"
    }
    stage("Install Requirements"){
        sh "echo Hello"
    }
    stage("Pip Install"){
        sh "ssh  ec2-user@${ENVIR} sudo pip install -r /home/ec2-user/stormpath-flask-sample/requirements.txt"
    }
    stage("Run App"){
        sh "ssh  ec2-user@${ENVIR}  python /home/ec2-user/stormpath-flask-sample/app.py"
    }
}