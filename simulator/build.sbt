name := "DirtCleanerSimulator"

version := "0.0.1"

scalaVersion := "2.9.0"

// Testing stuff
libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1"

// Logging
libraryDependencies += "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.6"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "0.9.26"