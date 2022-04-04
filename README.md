spring-mvc-example
==================
<a href="https://github.com/eis/spring-mvc-example/actions/workflows/github-actions-build.yml" title="Build Status"><img src="https://github.com/eis/spring-mvc-example/actions/workflows/github-actions-build.yml/badge.svg"></a>
<a href="https://codecov.io/gh/eis/spring-mvc-example" title="Coverage Status"><img src="https://codecov.io/gh/eis/spring-mvc-example/branch/master/graph/badge.svg?token=6PUI9CYUZR"></a>

Hello World with Spring MVC, using Spring 4 xml configs, having Tomcat deployment
preconfigured. This is meant to be simplest-as-possible but with MVC
configuration and Maven POM ready to be deployed.

In addition to being simple, it does have
  - HTML GET interface ([/](http://localhost:8080/spring-mvc-example)) and JSON-using REST interface ([/add](http://localhost:8080/spring-mvc-example/add))
  - "runme" build and deploy script
  - integration tests
  - resteasy tests
  - robot acceptance test
  - GitHub Actions build
  - CodeCov integration
  - Swagger documentation
  - Vagrantfile with Tomcat setup
  - Tomcat setup scripts for Ubuntu environment

Infra setup (Docker)
--------------------

```
docker build . -t spring-mvc-example
docker run -p 8080:8080 spring-mvc-example
```

Infra setup (VM)
----------------

```
vagrant up
```

Infra setup (Ubuntu host)
-------------------------

```
./ubuntu-deps.sh
./add-tomcat-admin.sh
systemctl restart tomcat8
```

Build & run the app
-------------------

First time (full cycle):
```
mvn -s settings-vm.xml tomcat7:deploy
```
Deploy again, only deploy:
```
mvn -s settings-vm.xml tomcat7:redeploy-only
```

See the app yourself
--------------------

[http://localhost:8080/spring-mvc-example](http://localhost:8080/spring-mvc-example)
