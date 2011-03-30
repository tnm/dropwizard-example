import sbt._
import assembly._
import maven._
import de.element34.sbteclipsify._

class DropWizardExampleProject(info: ProjectInfo) extends DefaultProject(info)
                                                          with MavenDependencies
                                                          with IdeaProject
                                                          with AssemblyBuilder
                                                          with Eclipsify {
  /**
   * Always check for new versions of snapshot dependencies.
   */
  override def snapshotUpdatePolicy = SnapshotUpdatePolicy.Always

  /**
   * Repositories
   */
  val codasRepo = "Coda's Repo" at "http://repo.codahale.com"
  val googleMaven = "Google Maven" at "http://google-maven-repository.googlecode.com/svn/repository/"
  val sunRepo = "Sun Repo" at "http://download.java.net/maven/2/"
  val yammerRepo = "Yammer's Internal Repo" at "http://repo.yammer.com/maven/"

  /**
   * Service Dependencies
   */
  val dropWizard = "com.yammer" %% "dropwizard" % "0.0.3-SNAPSHOT"

  /**
   * Test Dependencies
   */
  val simplespec = "com.codahale" %% "simplespec" % "0.2.0" % "test"
  val mockito = "org.mockito" % "mockito-all" % "1.8.4" % "test"

  override def fork = forkRun(List(
    "-server", // make sure we're using the 64-bit server VM
    "-d64",
    "-XX:+UseParNewGC", // use parallel GC for the new generation
    "-XX:+UseConcMarkSweepGC", // use concurrent mark-and-sweep for the old generation
    "-XX:+CMSParallelRemarkEnabled", // use multiple threads for the remark phase
    "-XX:+AggressiveOpts", // use the latest and greatest in JVM tech
    "-XX:+UseFastAccessorMethods", // be sure to inline simple accessor methods
    "-XX:+UseBiasedLocking", // speed up uncontended locks
    "-Xss128k", // reduce the thread stack size, freeing up space for the heap
    "-Xmx500M", // same with the max heap size
    //      "-XX:+PrintGCDetails",                 // log GC details to stdout
    //      "-XX:+PrintGCTimeStamps",
    "-XX:+HeapDumpOnOutOfMemoryError" // dump the heap if we run out of memory
  ))

  lazy val server = runTask(
    getMainClass(true), runClasspath, List("server", "example.conf")
  ) dependsOn(compile) describedAs("Runs Example Service with example.conf")
}
