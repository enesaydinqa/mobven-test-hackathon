pipelineJob('Hackathon') {
    definition {
        cpsScm {
            lightweight(true)
            scm {
                gitSCM {
                    branches {
                        branchSpec {
                            name('*/master')
                        }
                    }
                    userRemoteConfigs {
                        userRemoteConfig {
                            url('https://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@github.com/enesaydinqa/mobven-test-hackathon.git')
                            credentialsId('bitbucket-ssh-login')
                            name('origin')
                            refspec('')
                        }
                    }
                    doGenerateSubmoduleConfigurations(false)
                    browser {
                    }
                    gitTool('')
                }
            }
            scriptPath('jenkins-file/AirbnbPipeline.jenkinsfile')
        }
    }
}