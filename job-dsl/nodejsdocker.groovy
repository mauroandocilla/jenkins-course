job('NodeJS Docker example DSL') {
    scm {
        git('git://github.com/mauroandocilla/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Mauro DSL User')
            node / gitConfigEmail('m.andocilla@me.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('cargo77/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
