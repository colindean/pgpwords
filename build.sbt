name := "pgpwords"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies +=
  "com.storm-enroute" %% "scalameter" % "0.7-SNAPSHOT" % "test"

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

parallelExecution in Test := false

