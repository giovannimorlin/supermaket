version := "0.1.0-SNAPSHOT"

scalaVersion :="2.13.8"

val DoobieVersion = "1.0.0-RC2"

val NewTypeVersion = "0.4.4"

//akka-actor, akka-stream,  .. need the same version
val akkaVersion      = "2.6.8"

//akka-http, is an extrenal like alpakka, persistance ..
// isn't constraint to have the same vesion as core module
val akkaHttpVersion  = "10.2.10"

val scalaTestVersion = "3.2.13"

val circeVersion = "0.14.1"

val http4sVersion = "0.23.12"


libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core" % DoobieVersion,
  "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
  "org.tpolecat" %% "doobie-hikari" % DoobieVersion,
  "io.estatico" %% "newtype" % NewTypeVersion,

  "com.github.nscala-time" %% "nscala-time" % "2.30.0",

    //actor
    "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
    // akka http
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,

    "org.scalatest" %% "scalatest" % scalaTestVersion,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,

)

//scalacOptions ++= Seq ("-Ypartial-unification" )

//versione non giusta :( (prova la versione sotto 2, cit Luca)
//addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.1")










