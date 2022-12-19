ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "untitled",
    libraryDependencies ++= Seq(
      "org.drools" % "drools-core" % "8.31.1.Final",
      "org.drools" % "drools-compiler" % "8.31.1.Final",
      "org.drools" % "drools-decisiontables" % "8.31.1.Final",
      "org.drools" % "drools-mvel" % "8.31.1.Final",
      "org.drools" % "drools-model-compiler" % "8.31.1.Final",
      "org.drools" % "drools-xml-support" % "8.31.1.Final",
      "org.kie" % "kie-api" % "8.31.1.Final"
    ),
    resolvers in Global ++= Seq(
      "Sbt plugins" at "https://dl.bintray.com/sbt/sbt-plugin-releases",
    ),
    Compile / packageBin / mainClass := Some("src.Main"),
    Compile / run / mainClass := Some("src.Main")
  )
  .settings(
//    assembly / assemblyJarName := "myJar.jar",
    assembly / assemblyMergeStrategy := {
      case x if x.endsWith("module-info.class") => MergeStrategy.discard
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    },
    publish / skip := true,
  )

lazy val forPublishing = project
  .settings(
    name := "drools-kie-demo-assembly",
    Compile / packageBin := (root / assembly).value,
    organization := "com.github.dmytromitin",
    organizationName := "Dmytro Mitin",
    organizationHomepage := Some(url("https://github.com/DmytroMitin")),
    version := "0.1",
    scmInfo := Some(ScmInfo(
      url("https://github.com/DmytroMitin/drools-kie-demo-assembly"),
      "https://github.com/DmytroMitin/drools-kie-demo-assembly.git"
    )),
    developers := List(Developer(
      id = "DmytroMitin",
      name = "Dmytro Mitin",
      email = "dmitin3@gmail.com",
      url = url("https://github.com/DmytroMitin")
    )),
    description := "drools kie demo assembly",
    licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("https://github.com/DmytroMitin/drools-kie-demo-assembly")),
    // Remove all additional repository other than Maven Central from POM
    pomIncludeRepository := { _ => false },
    publishMavenStyle := true,
    credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential"),
    publishTo := sonatypePublishToBundle.value,
    sonatypeCredentialHost := "oss.sonatype.org",
    sonatypeRepository := "https://oss.sonatype.org/service/local",
  )