lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := """ADN-NuevoIntentoJDBC""",
    organization := "com.ceiba",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(evolutions, jdbc),
    libraryDependencies ++= Seq(
      guice,
      javaJdbc,
      "com.h2database" % "h2" % "2.1.212",
      "io.vavr" % "vavr" % "0.10.4",
      "org.jdbi" % "jdbi" % "2.78",
      javaWs % "test",
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % "2.9.4",
      "com.fasterxml.jackson.module" % "jackson-module-parameter-names" % "2.9.3",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.9.3",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.9.3",
      "org.postgresql" % "postgresql" % "42.2.5",
      "org.projectlombok" % "lombok" % "1.18.24",
      "org.mockito" % "mockito-core" % "4.5.1" % "test",
      "org.awaitility" % "awaitility" % "4.0.1" % "test",
      "org.assertj" % "assertj-core" % "3.14.0" % "test",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "com.github.javafaker" % "javafaker" % "1.0.2"
    ),
    Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
  )




