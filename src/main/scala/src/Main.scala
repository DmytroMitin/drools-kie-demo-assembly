package src

import org.kie.api.KieServices
import org.kie.api.runtime.{KieContainer, KieSession}

object Main extends App {
  val kieServices: KieServices = KieServices.Factory.get
  val kieContainer: KieContainer = kieServices.getKieClasspathContainer
  val kieSession: KieSession = kieContainer.newKieSession("DroolDummyKS")
  println(kieServices)
  println(kieContainer)
  println(kieSession)
}