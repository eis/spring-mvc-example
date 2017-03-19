spring-mvc-example
==================
<a href="https://travis-ci.org/eis/spring-mvc-example" title="Build Status"><img src="https://api.travis-ci.org/eis/spring-mvc-example.svg?branch=master"></a>
<a href="https://coveralls.io/github/eis/spring-mvc-example?branch=master" title="Coverage Status"><img src="http://img.shields.io/coveralls/eis/spring-mvc-example/master.svg"></a>



Hello World with Spring MVC, using Spring 4 xml configs, having Tomcat deployment
preconfigured. This is meant to be simplest-as-possible but with MVC
configuration and Maven POM ready to be deployed.

In addition to being simple, it does have
  - HTML GET interface ([/](http://localhost:8080/spring-mvc-example)) and JSON-using REST interface ([/add](http://localhost:8080/spring-mvc-example/add))
  - "runme" build and deploy script
  - integration tests
  - resteasy tests
  - robot acceptance test
  - Travis configuration
  - Swagger documentation
  - Vagrantfile with Tomcat setup
  - Tomcat setup scripts for Ubuntu environment

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

```
mvn -s settings-vm.xml tomcat7:deploy
```

See the app yourself
--------------------

[http://localhost:8080/spring-mvc-example](http://localhost:8080/spring-mvc-example)
